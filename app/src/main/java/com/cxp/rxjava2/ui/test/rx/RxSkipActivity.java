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
 * 文 件 名: RxSkipActivity
 * 创 建 人: CXP
 * 创建日期: 2018-04-28 14:11
 * skip
 *
 * 接受一个long型参数，代表跳过多少个数目的事件再开始接收
 */
@SuppressLint("CheckResult")
public class RxSkipActivity extends BaseActivity {

    private static final String TAG = "RxSkipActivity";

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
        Observable.just(1,2,3,4,5)
                .skip(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        mRxTv.append("skip : "+integer + "\n");
                        Log.e(TAG, "skip : "+integer + "\n");
                    }
                });
    }
}
