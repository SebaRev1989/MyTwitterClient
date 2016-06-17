package com.reverso.seba.mytwitterclient.lib.di;


import android.support.v4.app.Fragment;

import com.reverso.seba.mytwitterclient.lib.GlideImageLoader;
import com.reverso.seba.mytwitterclient.lib.GreenRobotEventBus;
import com.reverso.seba.mytwitterclient.lib.EventBus;
import com.reverso.seba.mytwitterclient.lib.ImageLoader;

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
    ImageLoader providesImageLoader(Fragment fragment) {
        return  new GlideImageLoader(fragment);
    }

    /*@Provides
    @Singleton
    RequestManager providesRequestManager(Fragment fragment) {
        return Glide.with(fragment);
    }*/

    @Provides
    @Singleton
    Fragment providesFragment() {
        return this.fragment;
    }

    @Provides
    @Singleton
    EventBus providesEventBus() {
        return new GreenRobotEventBus();
    }

    /*@Provides
    @Singleton
    org.greenrobot.eventbus.EventBus providesLibraryEventBus() {
        return org.greenrobot.eventbus.EventBus.getDefault();
    }*/
}
