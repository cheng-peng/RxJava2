package com.cxp.rxjava2.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * 文 件 名: BaseFragment
 * 创 建 人: CXP
 * 创建日期: 2018-04-27 14:42
 * 描    述: Fragment 基类
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public abstract class BaseFragment extends Fragment {

    private Unbinder unbinder;

    /**
     * 获取布局ID
     */
    protected abstract int getContentViewLayoutId();

    /**
     * 界面初始化
     */
    protected abstract void init();

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (getContentViewLayoutId() != 0) {
            return inflater.inflate(getContentViewLayoutId(), container, false);
        } else {
            return super.onCreateView(inflater, container, savedInstanceState);
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        unbinder = ButterKnife.bind(this, view);
        init();
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
