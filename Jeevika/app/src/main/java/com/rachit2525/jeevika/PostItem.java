package com.rachit2525.jeevika;

public class PostItem {
    private int imageResource;
    private String mText;

    public PostItem(int mimageResource,String mText){
        imageResource = mimageResource;
        this.mText = mText;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getmText() {
        return mText;
    }
}
