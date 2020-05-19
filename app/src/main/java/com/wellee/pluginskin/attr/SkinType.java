package com.wellee.pluginskin.attr;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.wellee.pluginskin.SkinManager;
import com.wellee.pluginskin.SkinResource;

public enum SkinType {
    TEXT_COLOR("textColor") {
        @Override
        protected void init(View view, String resName) {
            SkinResource skinResource = getSkinResource();
            ColorStateList color = skinResource.getColorByName(resName);
            if (color == null) {
                return;
            }
            TextView textView = (TextView) view;
            textView.setTextColor(color);
        }
    },
    BACKGROUND("background") {
        @Override
        protected void init(View view, String resName) {
            // 背景可能是图片也可能是颜色
            SkinResource skinResource = getSkinResource();
            Drawable drawable = skinResource.getDrawableByName(resName);
            if (drawable != null) {
                ImageView imageView = (ImageView) view;
                imageView.setBackground(drawable);
                return;
            }

            // 可能是颜色
            ColorStateList color = skinResource.getColorByName(resName);
            if (color != null) {
                view.setBackgroundColor(color.getDefaultColor());
            }
        }
    },
    SRC("src") {
        @Override
        protected void init(View view, String resName) {
            // 获取资源设置
            SkinResource skinResource = getSkinResource();
            Drawable drawable = skinResource.getDrawableByName(resName);
            if (drawable != null) {
                ImageView imageView = (ImageView) view;
                imageView.setImageDrawable(drawable);
            }
        }
    };

    private String resName;

    SkinType(String resName) {
        this.resName = resName;
    }

    protected abstract void init(View view, String resName);

    public String getResName() {
        return resName;
    }

    public SkinResource getSkinResource() {
        return SkinManager.getInstance().getSkinResource();
    }
}
