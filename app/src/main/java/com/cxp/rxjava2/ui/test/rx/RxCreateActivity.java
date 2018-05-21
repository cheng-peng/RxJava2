package com.cxp.rxjava2.ui.test.rx;

import android.util.Log;
import android.widget.TextView;

import com.cxp.rxjava2.R;
import com.cxp.rxjava2.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * 文 件 名: RxCreateActivity
 * 创 建 人: CXP
 * 创建日期: 2018-04-27 15:39
 * 描    述: 最常见的操作符，用于生产一个发射对象
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class RxCreateActivity extends BaseActivity {

    private static final String TAG = "RxCreateActivity";

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
        Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(ObservableEmitter<Integer> e) throws Exception {
                mRxTv.append("Observable emit 1" + "\n");
                Log.e(TAG, "Observable emit 1" + "\n");
                e.onNext(1);
                mRxTv.append("Observable emit 2" + "\n");
                Log.e(TAG, "Observable emit 2" + "\n");
                e.onNext(2);
                mRxTv.append("Observable emit 3" + "\n");
                Log.e(TAG, "Observable emit 3" + "\n");
                e.onNext(3);
                e.onComplete();
                mRxTv.append("Observable emit 4" + "\n");
                Log.e(TAG, "Observable emit 4" + "\n" );
                e.onNext(4);
            }
        }).subscribe(new Observer<Integer>() {
            private int i;
            private Disposable mDisposable;

            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mRxTv.append("onSubscribe : " + d.isDisposed() + "\n");
                Log.e(TAG, "onSubscribe : " + d.isDisposed() + "\n" );
                mDisposable = d;
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                mRxTv.append("onNext : value : " + integer + "\n");
                Log.e(TAG, "onNext : value : " + integer + "\n" );
                i++;
                if (i == 2) {
                    // 在RxJava 2.x 中，新增的Disposable可以做到切断的操作，让Observer观察者不再接收上游事件
                    mDisposable.dispose();
                    mRxTv.append("onNext : isDisposable : " + mDisposable.isDisposed() + "\n");
                    Log.e(TAG, "onNext : isDisposable : " + mDisposable.isDisposed() + "\n");
                }
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mRxTv.append("onError : value : " + e.getMessage() + "\n");
                Log.e(TAG, "onError : value : " + e.getMessage() + "\n" );
            }

            @Override
            public void onComplete() {
                mRxTv.append("onComplete" + "\n");
                Log.e(TAG, "onComplete" + "\n" );
            }
        });
    }
}
