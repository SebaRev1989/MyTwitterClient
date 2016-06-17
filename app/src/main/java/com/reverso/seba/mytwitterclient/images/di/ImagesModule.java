package com.reverso.seba.mytwitterclient.images.di;

import com.reverso.seba.mytwitterclient.api.CustomTwitterApiClient;
import com.reverso.seba.mytwitterclient.images.entities.Image;
import com.reverso.seba.mytwitterclient.images.ImagesInteractor;
import com.reverso.seba.mytwitterclient.images.ImagesInteractorImp;
import com.reverso.seba.mytwitterclient.images.ImagesPresenter;
import com.reverso.seba.mytwitterclient.images.ImagesPresenterImp;
import com.reverso.seba.mytwitterclient.images.ImagesRepository;
import com.reverso.seba.mytwitterclient.images.ImagesRepositoryImp;
import com.reverso.seba.mytwitterclient.images.adapters.ImagesAdapter;
import com.reverso.seba.mytwitterclient.images.ui.ImagesView;
import com.reverso.seba.mytwitterclient.images.ui.OnItemClickListener;
import com.reverso.seba.mytwitterclient.lib.EventBus;
import com.reverso.seba.mytwitterclient.lib.ImageLoader;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by seba on 16/06/16.
 */

@Module
public class ImagesModule {
    private ImagesView view;
    private OnItemClickListener clickListener;

    public ImagesModule(ImagesView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    ImagesAdapter providesAdapter(List<Image> dataset, ImageLoader imageLoader, OnItemClickListener clickListener){
        return new ImagesAdapter(dataset, imageLoader, clickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesOnItemClickListener(){
        return this.clickListener;
    }

    @Provides
    @Singleton
    List<Image> providesItemsList(){
        return new ArrayList<Image>();
    }

    @Provides
    @Singleton
    ImagesPresenter providesImagesPresenter(EventBus eventBus, ImagesView view, ImagesInteractor interactor){
        return new ImagesPresenterImp(eventBus, view, interactor);
    }

    @Provides
    @Singleton
    ImagesView providesImagesView(){
        return this.view;
    }

    @Provides
    @Singleton
    ImagesInteractor providesImagesInteractor(ImagesRepository repository){
        return new ImagesInteractorImp(repository);
    }

    @Provides
    @Singleton
    ImagesRepository providesImagesReposory(CustomTwitterApiClient client, EventBus eventBus){
        return new ImagesRepositoryImp(client, eventBus);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(TwitterSession session){
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    TwitterSession providesTwitter(){
        return Twitter.getSessionManager().getActiveSession();
    }
}
