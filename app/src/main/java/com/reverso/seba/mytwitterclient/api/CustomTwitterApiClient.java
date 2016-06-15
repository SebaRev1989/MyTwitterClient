package com.reverso.seba.mytwitterclient.api;

import com.reverso.seba.mytwitterclient.TwitterClientApp;
import com.twitter.sdk.android.core.Session;
import com.twitter.sdk.android.core.TwitterApiClient;

/**
 * Created by seba on 15/06/16.
 */
public class CustomTwitterApiClient extends TwitterApiClient {

    public CustomTwitterApiClient(Session session) {
        super(session);
    }

    public TimelineService getTimelineService() {
        return getService(TimelineService.class);
    }
}
