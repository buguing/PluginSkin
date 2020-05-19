package com.wellee.pluginskin;

import android.os.Bundle;

import androidx.annotation.LayoutRes;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public abstract class BaseActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(layoutResId());
        initView();
        initData();
    }

    protected abstract @LayoutRes int layoutResId();

    protected abstract void initView();

    protected abstract void initData();
}
