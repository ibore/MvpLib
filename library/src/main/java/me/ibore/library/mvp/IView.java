package me.ibore.library.mvp;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.LayoutRes;
import android.view.View;

/**
 * Created by Administrator on 2017/7/26.
 */

public interface IView {
    /** 获取视图ID */
    @LayoutRes int getLayoutId();
    /** 设置视图 */
    View getLayoutView(int layoutId);
    /** 绑定视图 */
    void onBindView(Bundle savedInstanceState);
    /** 绑定数据 */
    void onBindData();
    /** 开启Activity */
    void openActivity(Class clazz);
    /** 开启Activity带参数 */
    void openActivity(Class clazz, Bundle bundle);
    /** 获取Drawable */
    Drawable getDrawableX(@DrawableRes int id);
    /** 获取Color int */
    int getColorX(@ColorRes int id);
}
