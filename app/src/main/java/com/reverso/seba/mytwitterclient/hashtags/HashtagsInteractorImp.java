package com.reverso.seba.mytwitterclient.hashtags;

/**
 * Created by seba on 29/06/16.
 */
public class HashtagsInteractorImp implements HashtagsInteractor {
    private HashtagsRepository hashtagsRepository;

    public HashtagsInteractorImp(HashtagsRepository hashtagsRepository) {
        this.hashtagsRepository = hashtagsRepository;
    }

    @Override
    public void getHashtagItemsList() {
        hashtagsRepository.getHashtags();
    }
}
