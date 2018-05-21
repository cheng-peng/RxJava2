package com.cxp.rxjava2;

import android.view.View;

import com.cxp.rxjava2.base.BaseActivity;
import com.cxp.rxjava2.ui.demo.DemoActivity;
import com.cxp.rxjava2.ui.test.TestActivity;

import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {

    }

    @OnClick({R.id.main_bt1, R.id.main_bt2})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.main_bt1:
                //RxJava基础操作
                startActivity(TestActivity.class);
                break;
            case R.id.main_bt2:
                //RxJava实战操作
                startActivity(DemoActivity.class);
                break;
        }
    }
}
