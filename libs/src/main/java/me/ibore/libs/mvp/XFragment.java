package me.ibore.libs.mvp;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * description:
 * author: Ibore Xie
 * date: 2017-04-03 09:57
 * website: ibore.me
 */

public abstract class XFragment extends Fragment implements XView {

    protected final String TAG = getClass().getSimpleName();

    private Unbinder unBinder;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View rootView =  getLayoutView(inflater, getLayoutId());
        unBinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    protected abstract int getLayoutId();

    protected View getLayoutView(LayoutInflater inflater, int layoutId) {
        return inflater.inflate(layoutId, null);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        onBindView(view, savedInstanceState);
        onBindData();
    }

    protected abstract void onBindView(View view, Bundle savedInstanceState);

    protected abstract void onBindData();


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unBinder.unbind();
    }

}
