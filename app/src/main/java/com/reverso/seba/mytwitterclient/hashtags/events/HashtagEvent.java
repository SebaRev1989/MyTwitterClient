package com.reverso.seba.mytwitterclient.hashtags.events;

import com.reverso.seba.mytwitterclient.hashtags.entities.Hashtag;

import java.util.List;

/**
 * Created by seba on 29/06/16.
 */
public class HashtagEvent {
    private String error;
    private List<Hashtag> hashtags;

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public List<Hashtag> getHashtags() {
        return hashtags;
    }

    public void setHashtags(List<Hashtag> hashtags) {
        this.hashtags = hashtags;
    }
}
