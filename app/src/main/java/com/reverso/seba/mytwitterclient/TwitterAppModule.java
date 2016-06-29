package com.reverso.seba.mytwitterclient;

import android.content.Context;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by seba on 29/06/16.
 */
@Module
public class TwitterAppModule {
    Context context;

    public TwitterAppModule(Context context) {
        this.context = context;
    }

    @Provides
    @Singleton
    Context provideContext() {
        return this.context;
    }
}
