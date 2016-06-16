package com.reverso.seba.mytwitterclient;

import android.app.Application;
import android.support.v4.app.Fragment;

import com.reverso.seba.mytwitterclient.images.di.DaggerImagesComponent;
import com.reverso.seba.mytwitterclient.images.di.ImagesComponent;
import com.reverso.seba.mytwitterclient.images.di.ImagesModule;
import com.reverso.seba.mytwitterclient.images.ui.ImagesView;
import com.reverso.seba.mytwitterclient.images.ui.adapters.OnItemClickListener;
import com.reverso.seba.mytwitterclient.lib.di.LibsModules;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterAuthConfig;

import io.fabric.sdk.android.Fabric;

/**
 * Created by seba on 14/06/16.
 */
public class TwitterClientApp extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        initFabric();
    }

    private void initFabric() {
        TwitterAuthConfig authConfig = new TwitterAuthConfig(BuildConfig.TWITTER_KEY, BuildConfig.TWITTER_SECRET);
        Fabric.with(this, new Twitter(authConfig));
    }

    public ImagesComponent getImagesComponent(Fragment fragment, ImagesView view, OnItemClickListener clickListener){
        return DaggerImagesComponent
                .builder()
                .libsModules(new LibsModules(fragment))
                .imagesModule(new ImagesModule(view, clickListener))
                .build();
    }
}
