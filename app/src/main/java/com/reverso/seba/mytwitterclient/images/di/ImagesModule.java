package com.reverso.seba.mytwitterclient.images.di;

import com.reverso.seba.mytwitterclient.api.CustomTwitterApiClient;
import com.reverso.seba.mytwitterclient.images.entities.Image;
import com.reverso.seba.mytwitterclient.images.ui.ImagesInteractor;
import com.reverso.seba.mytwitterclient.images.ui.ImagesInteractorImp;
import com.reverso.seba.mytwitterclient.images.ui.ImagesPresenter;
import com.reverso.seba.mytwitterclient.images.ui.ImagesPresenterImp;
import com.reverso.seba.mytwitterclient.images.ui.ImagesRepository;
import com.reverso.seba.mytwitterclient.images.ui.ImagesRepositoryImp;
import com.reverso.seba.mytwitterclient.images.ui.adapters.ImagesAdapter;
import com.reverso.seba.mytwitterclient.images.ui.adapters.ImagesView;
import com.reverso.seba.mytwitterclient.images.ui.adapters.OnItemClickListener;
import com.reverso.seba.mytwitterclient.lib.base.EventBus;
import com.reverso.seba.mytwitterclient.lib.base.ImageLoader;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.Session;

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
    ImagesAdapter providesAdapter(List< Image > dataset, ImageLoader imageLoader, OnItemClickListener clickListener){
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
    ImagesPresenter providesImagesPresenter(ImagesView view, EventBus eventBus, ImagesInteractor interactor){
        return new ImagesPresenterImp(view, eventBus, interactor);
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
    ImagesRepository providesImagesReposory(EventBus eventBus, CustomTwitterApiClient client){
        return new ImagesRepositoryImp(eventBus, client);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesCustomTwitterApiClient(Session session){
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    Session providesTwitter(){
        return Twitter.getSessionManager().getActiveSession();
    }
}
