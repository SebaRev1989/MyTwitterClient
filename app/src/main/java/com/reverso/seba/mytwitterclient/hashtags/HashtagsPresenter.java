package com.reverso.seba.mytwitterclient.hashtags;

import com.reverso.seba.mytwitterclient.hashtags.events.HashtagEvent;

/**
 * Created by seba on 29/06/16.
 */
public interface HashtagsPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getHashtagTweets();
    void onEventMainThread(HashtagEvent event);
}
