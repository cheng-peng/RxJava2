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
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * 文 件 名: RxFlowableActivity
 * 创 建 人: CXP
 * 创建日期: 2018-04-28 14:01
 * Flowable
 *
 * 专用于解决背压问题
 */
@SuppressLint("CheckResult")
public class RxFlowableActivity extends BaseActivity {

    private static final String TAG = "RxFlowableActivity";

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
        Flowable.just(1,2,3,4)
                .reduce(100, new BiFunction<Integer, Integer, Integer>() {
                    @Override
                    public Integer apply(@NonNull Integer integer, @NonNull Integer integer2) throws Exception {
                        return integer+integer2;
                    }
                }).subscribe(new Consumer<Integer>() {
            @Override
            public void accept(@NonNull Integer integer) throws Exception {
                mRxTv.append("Flowable :"+integer+"\n");
                Log.e(TAG, "Flowable :"+integer+"\n" );
            }
        });
    }
}
