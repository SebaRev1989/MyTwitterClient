package com.reverso.seba.mytwitterclient.images.di;

import com.reverso.seba.mytwitterclient.images.ui.ImagesFragment;
import com.reverso.seba.mytwitterclient.images.ImagesPresenter;
import com.reverso.seba.mytwitterclient.lib.di.LibsModules;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by seba on 16/06/16.
 */

@Singleton @Component(modules = {LibsModules.class, ImagesModule.class})
public interface ImagesComponent {
    void inject(ImagesFragment fragment);
    ImagesPresenter getPresenter();
}
