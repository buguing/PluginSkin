package com.wellee.pluginskin;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import java.io.File;

public class MainActivity extends BaseSkinActivity {

    private String[] permissions = {Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    private File skinDir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int result = ContextCompat.checkSelfPermission(this, Manifest.permission_group.STORAGE);
        if (result != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(this, permissions, 1000);
        }
        skinDir = getExternalFilesDir("skin");
        if (!skinDir.exists()) {
            skinDir.mkdirs();
        }
    }

    @Override
    protected int layoutResId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @Override
    protected void initData() {

    }

    public void skin(View view) {
        // 从服务器上下载
//        String skinPath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator
//                + "skin" + File.separator + "red.skin";

        File skinFile = new File(skinDir, "red.skin");
        // 换肤
        int result = SkinManager.getInstance().loadSkin(skinFile.getAbsolutePath());
    }

    public void skin1(View view) {
        // 恢复默认
        int result = SkinManager.getInstance().restoreDefault();
    }


    public void skin2(View view) {
        // 跳转
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    @Override
    public void changeSkin(SkinResource skinResource) {
        // 做一些第三方的改变
        Toast.makeText(this, "换肤了", Toast.LENGTH_SHORT).show();
    }
}
