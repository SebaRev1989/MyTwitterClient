package com.reverso.seba.mytwitterclient.images;

import com.reverso.seba.mytwitterclient.images.events.ImagesEvent;
import com.reverso.seba.mytwitterclient.images.ui.ImagesView;
import com.reverso.seba.mytwitterclient.lib.EventBus;
import com.reverso.seba.mytwitterclient.images.entities.Image;

import org.greenrobot.eventbus.Subscribe;

import java.util.List;

/**
 * Created by seba on 16/06/16.
 */
public class ImagesPresenterImp implements ImagesPresenter {
    private EventBus eventBus;
    private ImagesView view;
    private final ImagesInteractor interactor;

    public ImagesPresenterImp(EventBus eventBus, ImagesView view, ImagesInteractor interactor) {
        this.eventBus = eventBus;
        this.view = view;
        this.interactor = interactor;
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
        this.view = null;
    }

    @Override
    public void getImageTweets() {
        if (this.view != null) {
            view.hideImages();
            view.showProgress();
        }
        this.interactor.getImagesList();
    }

    @Override
    @Subscribe
    public void onEventMainThread(ImagesEvent event) {
        String errorMsg = event.getError();
        if (this.view != null) {
            view.showImages();
            view.hideProgress();
            if (errorMsg != null) {
                this.view.onError(errorMsg);
            } else {
                List<Image> items = event.getImages();
                this.view.setContent(items);
            }
        }
    }
}
