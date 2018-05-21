package com.cxp.rxjava2.ui.test.rx;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.TextView;

import com.cxp.rxjava2.R;
import com.cxp.rxjava2.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Flowable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 文 件 名: RxTakeActivity
 * 创 建 人: CXP
 * 创建日期: 2018-04-28 14:12
 * take
 *
 * 用于指定订阅者最多收到多少数据
 */
@SuppressLint("CheckResult")
public class RxTakeActivity extends BaseActivity {

    private static final String TAG = "RxTakeActivity";

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
        Flowable.fromArray(1,2,3,4,5)
                .take(2)
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        mRxTv.append("take : "+integer + "\n");
                        Log.e(TAG, "accept: take : "+integer + "\n" );
                    }
                });
    }
}
