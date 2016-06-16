package com.reverso.seba.mytwitterclient.images.events;

import com.reverso.seba.mytwitterclient.images.entities.Image;

import java.util.List;

/**
 * Created by seba on 16/06/16.
 */
public class ImagesEvent {
    private String error;
    private List<Image> images;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Image> getImages() {
        return images;
    }

    public void setImages(List<Image> images) {
        this.images = images;
    }
}
