package com.reverso.seba.mytwitterclient.hashtags.di;

import android.content.Context;

import com.reverso.seba.mytwitterclient.api.CustomTwitterApiClient;
import com.reverso.seba.mytwitterclient.hashtags.HashtagsInteractor;
import com.reverso.seba.mytwitterclient.hashtags.HashtagsInteractorImp;
import com.reverso.seba.mytwitterclient.hashtags.HashtagsPresenter;
import com.reverso.seba.mytwitterclient.hashtags.HashtagsPresenterImp;
import com.reverso.seba.mytwitterclient.hashtags.HashtagsRepository;
import com.reverso.seba.mytwitterclient.hashtags.HashtagsrepositoryImp;
import com.reverso.seba.mytwitterclient.hashtags.adapters.HashtagsAdapter;
import com.reverso.seba.mytwitterclient.hashtags.entities.Hashtag;
import com.reverso.seba.mytwitterclient.hashtags.ui.HashtagsView;
import com.reverso.seba.mytwitterclient.hashtags.ui.OnItemClickListener;
import com.reverso.seba.mytwitterclient.lib.EventBus;
import com.twitter.sdk.android.Twitter;
import com.twitter.sdk.android.core.TwitterSession;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by seba on 29/06/16.
 */
@Module
public class HashtagsModules {
    private HashtagsView view;
    private OnItemClickListener clickListener;

    public HashtagsModules(HashtagsView view, OnItemClickListener clickListener) {
        this.view = view;
        this.clickListener = clickListener;
    }

    @Provides
    @Singleton
    HashtagsAdapter providesAdapter(Context context, List<Hashtag> items, OnItemClickListener clickListener) {
        return new HashtagsAdapter(context, items, clickListener);
    }

    @Provides
    @Singleton
    OnItemClickListener providesClickListener() {
        return this.clickListener;
    }

    @Provides
    @Singleton
    List<Hashtag> providesItems() {
        return new ArrayList<Hashtag>();
    }

    @Provides
    @Singleton
    HashtagsView providesHashtagsView() {
        return this.view;
    }

    @Provides
    @Singleton
    HashtagsPresenter providesHashtagsPresenter(EventBus eventBus, HashtagsView hashtagsView, HashtagsInteractor hashtagsInteractor) {
        return new HashtagsPresenterImp(eventBus, hashtagsView, hashtagsInteractor);
    }

    @Provides
    @Singleton
    HashtagsInteractor providesHashtagsInteractor(HashtagsRepository hashtagsRepository) {
        return new HashtagsInteractorImp(hashtagsRepository);
    }

    @Provides
    @Singleton
    HashtagsRepository providesHashtagsRepository(EventBus eventBus, CustomTwitterApiClient client) {
        return new HashtagsrepositoryImp(eventBus, client);
    }

    @Provides
    @Singleton
    CustomTwitterApiClient providesTwitterApiClient(TwitterSession session) {
        return new CustomTwitterApiClient(session);
    }

    @Provides
    @Singleton
    TwitterSession providesTwitterSession() {
        return Twitter.getSessionManager().getActiveSession();
    }
}
