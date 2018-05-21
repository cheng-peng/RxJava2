package com.cxp.rxjava2.ui.test.rx;

import android.util.Log;
import android.widget.TextView;

import com.cxp.rxjava2.R;
import com.cxp.rxjava2.base.BaseActivity;

import java.util.Random;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 文 件 名: RxSingleActivity
 * 创 建 人: CXP
 * 创建日期: 2018-04-28 14:10
 * single
 *
 * 顾名思义，Single只会接收一个参数
 * 而SingleObserver只会调用onError或者onSuccess
 */
public class RxSingleActivity extends BaseActivity {

    private static final String TAG = "RxSingleActivity";

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
        Single.just(new Random().nextInt())
                .subscribe(new SingleObserver<Integer>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onSuccess(@NonNull Integer integer) {
                        mRxTv.append("single : onSuccess : "+integer+"\n");
                        Log.e(TAG, "single : onSuccess : "+integer+"\n" );
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mRxTv.append("single : onError : "+e.getMessage()+"\n");
                        Log.e(TAG, "single : onError : "+e.getMessage()+"\n");
                    }
                });
    }
}
