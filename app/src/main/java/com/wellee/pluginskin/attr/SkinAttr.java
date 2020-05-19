package com.wellee.pluginskin.attr;

import android.view.View;

public class SkinAttr {
    private String resName;
    private SkinType skinType;

    public SkinAttr(String resName, SkinType skinType) {
        this.resName = resName;
        this.skinType = skinType;
    }

    public void skin(View view) {
        skinType.init(view, resName);
    }
}
