package com.reverso.seba.mytwitterclient.images;

import com.reverso.seba.mytwitterclient.entities.Images;

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
    void setContent(List<Images> items);
}
