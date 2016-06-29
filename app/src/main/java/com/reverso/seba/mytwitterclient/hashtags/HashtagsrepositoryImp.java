package com.reverso.seba.mytwitterclient.hashtags;

import com.reverso.seba.mytwitterclient.api.CustomTwitterApiClient;
import com.reverso.seba.mytwitterclient.hashtags.entities.Hashtag;
import com.reverso.seba.mytwitterclient.hashtags.events.HashtagEvent;
import com.reverso.seba.mytwitterclient.lib.EventBus;
import com.twitter.sdk.android.core.Callback;
import com.twitter.sdk.android.core.Result;
import com.twitter.sdk.android.core.TwitterException;
import com.twitter.sdk.android.core.models.HashtagEntity;
import com.twitter.sdk.android.core.models.Tweet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Created by seba on 29/06/16.
 */
public class HashtagsrepositoryImp implements HashtagsRepository {
    private final EventBus eventBus;
    private final CustomTwitterApiClient client;
    private final static int TWEET_COUNT = 100;

    public HashtagsrepositoryImp(EventBus eventBus, CustomTwitterApiClient client) {
        this.eventBus = eventBus;
        this.client = client;
    }

    public void getHashtags() {
        client.getTimelineService().homeTimeline(TWEET_COUNT, true, true, true, true,
                new Callback<List<Tweet>>() {
                    @Override
                    public void success(Result<List<Tweet>> result) {
                        List<Hashtag> items = new ArrayList<Hashtag>();
                        for (Tweet tweet : result.data) {
                            if (checkIfTweetHasHashtag(tweet)) {
                                Hashtag tweetModel = new Hashtag();

                                tweetModel.setId(tweet.idStr);
                                tweetModel.setTweetText(tweet.text);
                                tweetModel.setFavoriteCount(tweet.favoriteCount);

                                List<String> hashtags = new ArrayList<String>();
                                for (HashtagEntity hashtag : tweet.entities.hashtags) {
                                    hashtags.add(hashtag.text);
                                }
                                tweetModel.setHashtags(hashtags);
                                items.add(tweetModel);
                            }
                        }
                        Collections.sort(items, new Comparator<Hashtag>() {
                            public int compare(Hashtag t1, Hashtag t2) {
                                return t2.getFavoriteCount() - t1.getFavoriteCount();
                            }
                        });
                        postEvent(items);
                    }

                    @Override
                    public void failure(TwitterException e) {
                        postEvent(e.getMessage());
                    }
                });
    }

    private boolean checkIfTweetHasHashtag(Tweet tweet) {
        return tweet.entities != null &&
               tweet.entities.hashtags != null &&
               !tweet.entities.hashtags.isEmpty();
    }

    private void postEvent(String error) {
        HashtagEvent event = new HashtagEvent();
        event.setError(error);
        eventBus.post(event);
    }

    private void postEvent(List<Hashtag> items) {
        HashtagEvent event = new HashtagEvent();
        event.setHashtags(items);
        eventBus.post(event);
    }
}
