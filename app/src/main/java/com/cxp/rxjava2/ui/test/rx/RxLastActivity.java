package com.cxp.rxjava2.ui.test.rx;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.TextView;

import com.cxp.rxjava2.R;
import com.cxp.rxjava2.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 文 件 名: RxLastActivity
 * 创 建 人: CXP
 * 创建日期: 2018-04-28 14:04
 * 描    述:
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@SuppressLint("CheckResult")
public class RxLastActivity extends BaseActivity {

    private static final String TAG = "RxLastActivity";

    @BindView(R.id.rx_tv)
    TextView mRxTv;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_rx;
    }

    @Override
    protected void initView() {

    }


    @OnClick(R.id.rx_bt)
    public void onViewClicked() {
        Observable.just(1, 2, 3)
                .last(4)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        mRxTv.append("last : " + integer + "\n");
                        Log.e(TAG, "last : " + integer + "\n");
                    }
                });
    }
}
