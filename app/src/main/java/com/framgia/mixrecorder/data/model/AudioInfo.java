package com.framgia.mixrecorder.data.model;

/**
 * Created by hoang on 1/20/2017.
 */
public class AudioInfo {
    private String mAudioName;
    private String mAudioPath;
    private int mOffset;
    private float mVolume;

    public AudioInfo(String audioName, String audioPath) {
        mAudioName = audioName;
        mAudioPath = audioPath;
    }

    public String getAudioName() {
        return mAudioName;
    }

    public void setAudioName(String audioName) {
        mAudioName = audioName;
    }

    public String getAudioPath() {
        return mAudioPath;
    }

    public void setAudioPath(String audioPath) {
        mAudioPath = audioPath;
    }

    public int getOffset() {
        return mOffset;
    }

    public void setOffset(int offset) {
        mOffset = offset;
    }

    public float getVolume() {
        return mVolume;
    }

    public void setVolume(float volume) {
        mVolume = volume;
    }
}

