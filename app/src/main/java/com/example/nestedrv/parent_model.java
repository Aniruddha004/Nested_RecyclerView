package com.example.nestedrv;

import java.util.List;

public class parent_model {
    public static final int MEDIA_TYPE = 0;
    public static final int TEXT_TYPE = 1;

    private int type;
    private String text;

    public parent_model(int type, String text, List<child_model> children) {
        this.type = type;
        this.text = text;
        this.children = children;
    }

    public int getType() {
        return type;
    }

    public String getText() {
        return text;
    }

    private List<child_model> children;


    public List<child_model> getChildren() {
        return children;
    }

}
