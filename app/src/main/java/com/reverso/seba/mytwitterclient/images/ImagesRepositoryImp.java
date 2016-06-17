package com.reverso.seba.mytwitterclient.images;

import com.reverso.seba.mytwitterclient.api.CustomTwitterApiClient;
import com.reverso.seba.mytwitterclient.images.entities.Image;
import com.reverso.seba.mytwitterclient.images.events.ImagesEvent;
import com.reverso.seba.mytwitterclient.lib.EventBus;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.MediaEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by seba on 16/06/16.
 */
public class ImagesRepositoryImp implements ImagesRepository {
    private final EventBus eventBus;
    private final CustomTwitterApiClient client;
    private final static int TWEET_COUNT = 100;

    public ImagesRepositoryImp(CustomTwitterApiClient client, EventBus eventBus) {
        this.client = client;
        this.eventBus = eventBus;
    }

    @Override
    public void getImages() {
        client.getTimelineService().homeTimeline(TWEET_COUNT, true, true, true, true,
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> result) {
                        List<Image> items = new ArrayList<Image>();
                        for (Tweet tweet : result.data) {
                            if (checkIfTweetHasImage(tweet)) {
                                Image tweetModel = new Image();

                                tweetModel.setId(tweet.idStr);
                                tweetModel.setFavoriteCount(tweet.favoriteCount);

                                String tweetText = tweet.text;
                                int index = tweetText.indexOf("http");
                                if (index > 0) {
                                    tweetText = tweetText.substring(0, index);
                                }
                                tweetModel.setTweetText(tweetText);

                                MediaEntity currentPhoto = tweet.entities.media.get(0);
                                String imageURL = currentPhoto.mediaUrl;
                                tweetModel.setImageURL(imageURL);

                                items.add(tweetModel);
                            }
                        }
                        Collections.sort(items, new Comparator<Image>() {
                            @Override
                            public int compare(Image t1, Image t2) {
                                return t2.getFavoriteCount() - t1.getFavoriteCount();
                            }
                        });
                    }

                    @Override
                    public void failure(TwitterException e) {
                        postEvent(e.getMessage());
                    }
                });
    }

    private boolean checkIfTweetHasImage(Tweet tweet) {
        return  tweet.entities != null &&
                tweet.entities.media != null &&
                !tweet.entities.media.isEmpty();
    }

    private void postEvent(String error){
        ImagesEvent event = new ImagesEvent();
        event.setError(error);
        eventBus.post(event);
    }

    private void postEvent(List<Image> items) {
        ImagesEvent event = new ImagesEvent();
        event.setImages(items);
        eventBus.post(event);
    }
}
