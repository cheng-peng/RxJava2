package com.cxp.rxjava2.ui.test.rx;

import android.util.Log;
import android.widget.TextView;

import com.cxp.rxjava2.R;
import com.cxp.rxjava2.base.BaseActivity;

import java.util.concurrent.Callable;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 文 件 名: RxDeferActivity
 * 创 建 人: CXP
 * 创建日期: 2018-04-28 13:55
 * defer
 * <p>
 * 简单的说就是每次订阅都会创建一个新的Observable
 * 并且如果该Observable没有被订阅，就不会生成新的Observable
 */
public class RxDeferActivity extends BaseActivity {

    private static final String TAG = "RxDeferActivity";

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
        Observable<Integer> observable = Observable.defer(new Callable<ObservableSource<Integer>>() {
            @Override
            public ObservableSource<Integer> call() throws Exception {
                return Observable.just(1, 2, 3);
            }
        });


        observable.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {

            }

            @Override
            public void onNext(@NonNull Integer integer) {
                mRxTv.append("defer : " + integer + "\n");
                Log.e(TAG, "defer : " + integer + "\n");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mRxTv.append("defer : onError : " + e.getMessage() + "\n");
                Log.e(TAG, "defer : onError : " + e.getMessage() + "\n");
            }

            @Override
            public void onComplete() {
                mRxTv.append("defer : onComplete\n");
                Log.e(TAG, "defer : onComplete\n");
            }
        });
    }
}
