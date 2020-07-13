package com.louis.screenadapter;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

public class Utils {
    private static Utils utils;

    //参考设备宽高
    private static final float STANDARD_WIDTH = 720;
    private static final float STANDARD_HEIGHT = 1280;

    private int mDisplayWidth;
    private int mDisplayHeight;

    private Utils(Context applicationContext){
        //获取屏幕宽高
        if (mDisplayWidth == 0 || mDisplayHeight == 0) {
            WindowManager windowManager = (WindowManager) applicationContext.getSystemService(Context.WINDOW_SERVICE);
            if (windowManager != null) {
                DisplayMetrics displayMetrics = new DisplayMetrics();
                windowManager.getDefaultDisplay().getMetrics(displayMetrics);
                if (displayMetrics.widthPixels > displayMetrics.heightPixels) {
                    //横屏
                    mDisplayWidth = displayMetrics.heightPixels;
                    mDisplayHeight = displayMetrics.widthPixels;
                } else {
                    //竖屏
                    mDisplayWidth = displayMetrics.widthPixels;
                    mDisplayHeight = displayMetrics.heightPixels - getStatusBarHeight(applicationContext);
                }
            }
        }
    }

    /**
     * 状态栏高度
     * @return
     */
    public int getStatusBarHeight(Context context){
        int resId = context.getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resId > 0) {
            return context.getResources().getDimensionPixelSize(resId);
        }
        return 0;
    }

    public static Utils getInstance(Context context) {
        if (utils == null) {
            utils = new Utils(context.getApplicationContext());
        }
        return utils;
    }

    /**
     * 获取水平方向的缩放比例
     * @return
     */
    public float getHorizontalScale(){
        return mDisplayWidth/STANDARD_WIDTH;
    }

    /**
     * 获取垂直方向的缩放比例
     * @return
     */
    public float getVerticalScale(){
        return mDisplayHeight/STANDARD_HEIGHT;
    }


}
