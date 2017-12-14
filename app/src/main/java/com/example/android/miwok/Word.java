package com.example.android.miwok;

/**
 * Created by Arvind on 26/09/17.
 */

public class Word {
    private static final int HAS_IMAGE = -1;
    private String mEnglish;
    private String mMiwok;
    private int mImageResourceId = HAS_IMAGE;
    private int mAudioResourceId;

    public Word(String English, String Miwok, int audioResoureId) {
        mEnglish = English;
        mMiwok = Miwok;
        mAudioResourceId = audioResoureId;

    }

    public Word(String English, String Miwok, int imageResource, int audioResourceId) {
        mEnglish = English;
        mMiwok = Miwok;
        mImageResourceId = imageResource;
        mAudioResourceId = audioResourceId;
    }

    public String getmEnglish() {
        return mEnglish;
    }

    public String getmMiwok() {
        return mMiwok;
    }

    public int getmImageResourceId() {
        return mImageResourceId;
    }

    public int getmAudioResourceId() {
        return mAudioResourceId;
    }

    public boolean hasImage() {
        /*if(mImageResourceId==HAS_IMAGE){
            return false;
        }
        return true;*/
        return mImageResourceId != HAS_IMAGE;
    }
}
