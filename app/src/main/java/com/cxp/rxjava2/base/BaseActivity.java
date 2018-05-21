package com.cxp.rxjava2.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;

/**
 * 文 件 名: BaseActivity
 * 创 建 人: CXP
 * 创建日期: 2018-04-27 14:09
 * 描    述: Activity 基类
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public abstract class BaseActivity extends AppCompatActivity {


    /**
     * 界面初始化前期准备
     */
    protected void beforeInit() {

    }

    /**
     * 获取布局Id
     *
     * @return 布局id
     */
    protected abstract int getContentViewLayoutId();


    /**
     * 初始化布局及控件
     */
    protected abstract void initView();

    @Override
    public void setContentView(int layoutResID) {
        super.setContentView(layoutResID);
        ButterKnife.bind(this);
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        beforeInit();
        if (getContentViewLayoutId() != 0) {
            setContentView(getContentViewLayoutId());
            initView();
        }
    }

    //页面跳转
    protected void startActivity(Class cls) {
        Intent intent = new Intent(this,cls);
        startActivity(intent);
    }

}
