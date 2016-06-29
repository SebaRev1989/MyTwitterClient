package com.reverso.seba.mytwitterclient.hashtags.ui;

import com.reverso.seba.mytwitterclient.hashtags.entities.Hashtag;

import java.util.List;

/**
 * Created by seba on 29/06/16.
 */
public interface HashtagsView {
    void showList();
    void hideList();
    void showProgress();
    void hideProgress();

    void onHashtagsError(String error);
    void setHashtags(List<Hashtag> items);
}
