package com.reverso.seba.mytwitterclient.hashtags.di;

import com.reverso.seba.mytwitterclient.TwitterAppModule;
import com.reverso.seba.mytwitterclient.hashtags.ui.HashtagsFragment;
import com.reverso.seba.mytwitterclient.lib.di.LibsModules;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by seba on 29/06/16.
 */
@Singleton @Component(modules = {HashtagsModules.class, LibsModules.class, TwitterAppModule.class})
public interface HashtagsComponent {
    void inject(HashtagsFragment fragment);
}
