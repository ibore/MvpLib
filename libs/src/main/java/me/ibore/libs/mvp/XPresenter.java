package me.ibore.libs.mvp;

import android.support.annotation.NonNull;

/**
 * description: 只做简单的UI处理，如显示Toast，
 * author: Ibore Xie
 * date: 2017-04-03 09:57
 * website: ibore.me
 */

public abstract class XPresenter<V extends XView> {

    protected V getView() {
        return view;
    }

    private V view;

    protected void onViewAttached(@NonNull V view) {
        if (this.view != null)
            throw new IllegalStateException("View " + this.view + " is already attached. Cannot attach " + view);
        this.view = view;
    }

    protected void onViewDetached() {
        if (this.view == null)
            throw new IllegalStateException("View is already detached");
        view = null;
    }
}
