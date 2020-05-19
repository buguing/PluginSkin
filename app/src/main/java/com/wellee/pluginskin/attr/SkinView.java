package com.wellee.pluginskin.attr;

import android.view.View;

import java.util.List;

public class SkinView {

    private View view;
    private List<SkinAttr> skinAttrs;

    public SkinView(View view, List<SkinAttr> skinAttrs) {
        this.view = view;
        this.skinAttrs = skinAttrs;
    }

    public void skin() {
        for (SkinAttr skinAttr : skinAttrs) {
            skinAttr.skin(view);
        }
    }
}
