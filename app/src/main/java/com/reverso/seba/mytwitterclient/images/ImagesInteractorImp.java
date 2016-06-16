package com.reverso.seba.mytwitterclient.images;

/**
 * Created by seba on 16/06/16.
 */
public class ImagesInteractorImp implements ImagesInteractor {
    private ImagesRepository repository;

    public ImagesInteractorImp(ImagesRepository repository) {
        this.repository = repository;
    }

    @Override
    public void execute() {
        repository.getImages();
    }
}
