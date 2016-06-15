package com.reverso.seba.mytwitterclient.lib.di;


import android.support.v4.app.Fragment;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestManager;
import com.reverso.seba.mytwitterclient.lib.GlideImageLoader;
import com.reverso.seba.mytwitterclient.lib.GreenRobotEventBus;
import com.reverso.seba.mytwitterclient.lib.base.EventBus;
import com.reverso.seba.mytwitterclient.lib.base.ImageLoader;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by seba on 15/06/16.
 */
@Module
public class LibsModules {
    private Fragment fragment;

    public LibsModules(Fragment fragment) {
        this.fragment = fragment;
    }

    @Provides
    @Singleton
    ImageLoader providesImageLoader(RequestManager requestManager) {
        return  new GlideImageLoader(requestManager);
    }

    @Provides
    @Singleton
    RequestManager providesRequestManager(Fragment fragment) {
        return Glide.with(fragment);
    }

    @Provides
    @Singleton
    Fragment providesFragment() {
        return this.fragment;
    }

    @Provides
    @Singleton
    EventBus providesEventBus(org.greenrobot.eventbus.EventBus eventBus) {
        return new GreenRobotEventBus(eventBus);
    }

    @Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus() {
        return org.greenrobot.eventbus.EventBus.getDefault();
    }
}
