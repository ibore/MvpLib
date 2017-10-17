package me.ibore.libs.mvp;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * description:
 * author: Ibore Xie
 * date: 2017-04-03 09:57
 * website: ibore.me
 */

public abstract class XActivity<P extends XPresenter> extends AppCompatActivity implements XView {

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

    protected abstract int getLayoutId();

    public View getLayoutView(LayoutInflater inflater, int layoutId) {
        return inflater.inflate(layoutId, null);
    }

    protected abstract void onBindView(View rootView, Bundle savedInstanceState);

    protected abstract void onBindData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onViewDetached();
        unbinder.unbind();
    }

    protected P getPresenter() {
        return presenter;
    }

    @Override
    public Activity getActivity() {
        return this;
    }

    @Override
    public Context getContext() {
        return getApplicationContext();
    }

}

