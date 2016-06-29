package com.reverso.seba.mytwitterclient.hashtags;

import com.reverso.seba.mytwitterclient.hashtags.entities.Hashtag;
import com.reverso.seba.mytwitterclient.hashtags.events.HashtagEvent;
import com.reverso.seba.mytwitterclient.hashtags.ui.HashtagsView;
import com.reverso.seba.mytwitterclient.lib.EventBus;

import java.util.List;

/**
 * Created by seba on 29/06/16.
 */
public class HashtagsPresenterImp implements HashtagsPresenter {
    private EventBus eventBus;
    private HashtagsView hashtagsView;
    private HashtagsInteractor hashtagsInteractor;

    public HashtagsPresenterImp(EventBus eventBus, HashtagsView hashtagsView, HashtagsInteractor hashtagsInteractor) {
        this.eventBus = eventBus;
        this.hashtagsView = hashtagsView;
        this.hashtagsInteractor = hashtagsInteractor;
    }

    @Override
    public void onResume() {
        eventBus.register(this);
    }

    @Override
    public void onPause() {
        eventBus.unregister(this);
    }

    @Override
    public void onDestroy() {
        this.hashtagsView = null;
    }

    @Override
    public void getHashtagTweets() {
        if (this.hashtagsView != null) {
            hashtagsView.hideList();
            hashtagsView.showProgress();
        }
        this.hashtagsInteractor.getHashtagItemsList();
    }

    @Override
    public void onEventMainThread(HashtagEvent event) {
        String errorMsg = event.getError();
        if (this.hashtagsView != null) {
            hashtagsView.showList();
            hashtagsView.hideProgress();
            if (errorMsg != null) {
                this.hashtagsView.onHashtagsError(errorMsg);
            } else {
                List<Hashtag> items = event.getHashtags();
                if (items != null && !items.isEmpty()) {
                    this.hashtagsView.setHashtags(items);
                }
            }
        }
    }
}
