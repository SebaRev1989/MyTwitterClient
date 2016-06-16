package com.reverso.seba.mytwitterclient.images.ui;

import com.reverso.seba.mytwitterclient.images.events.ImagesEvent;

/**
 * Created by seba on 16/06/16.
 */
public interface ImagesPresenter {
    void onResume();
    void onPause();
    void onDestroy();
    void getImageTweets();
    void onEventMainThread(ImagesEvent event);
}
