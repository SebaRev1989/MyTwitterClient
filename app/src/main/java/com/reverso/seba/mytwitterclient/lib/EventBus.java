package com.reverso.seba.mytwitterclient.lib;

/**
 * Created by seba on 15/06/16.
 */
public interface EventBus {
    void register(Object subscriber);
    void unregister(Object subscriber);
    void post(Object event);
}
