package com.reverso.seba.mytwitterclient.images.ui;

import com.reverso.seba.mytwitterclient.images.entities.Image;

import java.util.List;

/**
 * Created by seba on 16/06/16.
 */
public interface ImagesView {
    void showElements();
    void hideElements();
    void showProgress();
    void hideProgress();

    void onError(String error);
    void setContent(List<Image> items);
}
