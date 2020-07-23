package com.example.nestedrv;

public class child_model {
    public static final int IMAGE_TYPE = 0;
    public static final int VIDEO_TYPE = 1;

    private int type;
    private String url;

    public String getUrl() {
        return url;
    }

    public int getType() {
        return type;
    }

    public child_model(int type, String url) {
        this.type = type;
        this.url = url;
    }

}
