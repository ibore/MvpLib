package me.ibore.library.mvp;

import android.app.Activity;
import android.content.Context;
import android.support.annotation.StringRes;

/**
 * description:
 * author: Ibore Xie
 * date: 2017-04-03 09:57
 * website: ibore.me
 */

public interface XView {
    Activity getActivity();
    Context getContext();

    void showToast(@StringRes int id);
    void showToast(String content);

}
