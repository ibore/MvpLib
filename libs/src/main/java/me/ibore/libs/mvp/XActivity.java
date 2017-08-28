package me.ibore.libs.mvp;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * description:
 * author: Ibore Xie
 * date: 2017-04-03 09:57
 * website: ibore.me
 */

public abstract class XActivity<P extends XPresenter> extends AppCompatActivity implements XView, IView<P> {

    protected final String TAG = getClass().getSimpleName();
    private P presenter;
    private Unbinder unbinder;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View rootView = getLayoutView(getLayoutInflater(), getLayoutId());
        setContentView(rootView);
        unbinder = ButterKnife.bind(this);
        onBindView(rootView, savedInstanceState);
        presenter = ClassUtil.getClass(this, 0);
        presenter.onViewAttached(this);
        onBindData();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDetached();
        unbinder.unbind();
    }

    @Override
    public View getLayoutView(LayoutInflater inflater, int layoutId) {
        return inflater.inflate(layoutId, null);
    }

    @Override
    public P getPresenter() {
        return presenter;
    }


    @Override
    public Drawable getDrawableX(@DrawableRes int id) {
        return ContextCompat.getDrawable(getContext(), id);
    }

    @Override
    public int getColorX(@ColorRes int id) {
        return ContextCompat.getColor(getContext(), id);
    }

    @Override
    public void openActivity(Class clazz) {
        openActivity(clazz, null);
    }

    @Override
    public void openActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        if (bundle != null) intent.putExtras(bundle);
        startActivity(intent);
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

    @Override
    public void showToast(@StringRes int id) {
        this.showToast(getString(id));
    }

    @Override
    public void showToast(String content) {
        Toast.makeText(getContext(), content, Toast.LENGTH_SHORT).show();
    }

}

