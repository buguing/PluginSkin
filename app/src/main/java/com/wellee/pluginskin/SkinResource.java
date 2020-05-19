package com.wellee.pluginskin;

import android.content.Context;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.Log;

import java.lang.reflect.Method;
import java.util.Objects;

public class SkinResource {

    // 资源通过这个对象获取
    private Resources mSkinResource;
    private String mPackageName;
    private static final String TAG = "SkinResource";

    public SkinResource(Context context, String skinPath) {
        try {
            // 读取本地的一个 .skin里面的资源
            Resources superRes = context.getResources();
            // 创建AssetManager
            AssetManager asset = AssetManager.class.newInstance();

            // 添加本地下载好的资源皮肤   Native层c和c++怎么搞的
            Method method = AssetManager.class.getDeclaredMethod("addAssetPath", String.class);
            // method.setAccessible(true); 如果是私有的
            // 反射执行方法    写死了，肯定是传进来的值
            method.invoke(asset, skinPath);

            mSkinResource = new Resources(asset, superRes.getDisplayMetrics(),
                    superRes.getConfiguration());


            // 获取skinPath包名
            mPackageName = Objects.requireNonNull(context.getPackageManager().getPackageArchiveInfo(
                    skinPath, PackageManager.GET_ACTIVITIES)).packageName;
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 通过名字获取Drawable
     * @param resName resName
     * @return Drawable
     */
    public Drawable getDrawableByName(String resName){
        try {
            int resId = mSkinResource.getIdentifier(resName, "drawable", mPackageName);
            Log.e(TAG,"resId -> "+resId+" mPackageName -> "+mPackageName +" resName -> "+resName);
            return mSkinResource.getDrawable(resId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 通过名字获取颜色
     * @param resName
     * @return
     */
    public ColorStateList getColorByName(String resName){
        try {
            int resId = mSkinResource.getIdentifier(resName, "color", mPackageName);
            return mSkinResource.getColorStateList(resId);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
