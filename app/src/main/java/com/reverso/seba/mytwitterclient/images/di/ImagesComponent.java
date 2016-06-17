package com.reverso.seba.mytwitterclient.images.di;

import com.reverso.seba.mytwitterclient.images.ui.ImagesFragment;
import com.reverso.seba.mytwitterclient.lib.di.LibsModules;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by seba on 16/06/16.
 */

@Singleton @Component(modules = {ImagesModule.class, LibsModules.class})
public interface ImagesComponent {
    void inject(ImagesFragment fragment);
}
