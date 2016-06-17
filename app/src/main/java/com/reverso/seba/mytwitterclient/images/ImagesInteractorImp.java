package com.reverso.seba.mytwitterclient.images;

import javax.inject.Inject;

/**
 * Created by seba on 16/06/16.
 */
public class ImagesInteractorImp implements ImagesInteractor {
    private final ImagesRepository repository;

    @Inject
    public ImagesInteractorImp(ImagesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void getImagesList() {
        this.repository.getImages();
    }
}
