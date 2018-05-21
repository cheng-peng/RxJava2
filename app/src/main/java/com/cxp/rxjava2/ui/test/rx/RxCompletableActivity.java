package com.cxp.rxjava2.ui.test.rx;

import android.util.Log;
import android.widget.TextView;

import com.cxp.rxjava2.R;
import com.cxp.rxjava2.base.BaseActivity;

import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * 文 件 名: RxCompletableActivity
 * 创 建 人: CXP
 * 创建日期: 2018-04-28 13:46
 * Completable
 * <p>
 * 只关心结果，也就是说 Completable 是没有 onNext 的，要么成功要么出错，不关心过程，在 subscribe 后的某个时间点返回结果
 */
public class RxCompletableActivity extends BaseActivity {

    private static final String TAG = "RxCompletableActivity";

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
        mRxTv.append("Completable\n");
        Log.e(TAG, "Completable\n");

        Completable.timer(1, TimeUnit.SECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {
                        mRxTv.append("onSubscribe : d :" + d.isDisposed() + "\n");
                        Log.e(TAG, "onSubscribe : d :" + d.isDisposed() + "\n");
                    }

                    @Override
                    public void onComplete() {
                        mRxTv.append("onComplete\n");
                        Log.e(TAG, "onComplete\n");
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {
                        mRxTv.append("onError :" + e.getMessage() + "\n");
                        Log.e(TAG, "onError :" + e.getMessage() + "\n");
                    }
                });
    }
}
