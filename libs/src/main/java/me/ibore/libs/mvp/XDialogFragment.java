package me.ibore.libs.mvp;

import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.ColorRes;
import android.support.annotation.DrawableRes;
import android.support.annotation.Nullable;
import android.support.annotation.StringRes;
import android.support.v4.app.DialogFragment;
import android.support.v4.content.ContextCompat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * description:
 * author: Ibore Xie
 * date: 2017-04-03 09:57
 * website: ibore.me
 */

public abstract class XDialogFragment<P extends XPresenter> extends DialogFragment implements XView, IView<P> {

    protected final String TAG = getClass().getSimpleName();

    private Unbinder unBinder;

    private P presenter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView = getLayoutView(inflater, getLayoutId());
        unBinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onBindView(view, savedInstanceState);
        presenter = ClassUtil.getClass(this, 0);
        presenter.onViewAttached(this);
        onBindData();
    }

    @Override
    public void onDestroyView() {
        presenter.onViewDetached();
        unBinder.unbind();
        super.onDestroyView();
    }

    @Override
    public P getPresenter() {
        return presenter;
    }

    @Override
    public View getLayoutView(LayoutInflater inflater, int layoutId) {
        return inflater.inflate(layoutId, null);
    }

    @Override
    public void openActivity(Class clazz) {
        openActivity(clazz, null);
    }

    @Override
    public void openActivity(Class clazz, Bundle bundle) {
        Intent intent = new Intent(getActivity(), clazz);
        if (bundle != null) intent.putExtras(bundle);
        startActivity(intent);
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
    public void showToast(@StringRes int id) {
        this.showToast(getString(id));
    }

    @Override
    public void showToast(String content) {
        Toast.makeText(getContext(), content, Toast.LENGTH_SHORT).show();
    }
}
