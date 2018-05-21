package com.cxp.rxjava2.ui.test.rx;

import android.util.Log;
import android.widget.TextView;

import com.cxp.rxjava2.R;
import com.cxp.rxjava2.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.subjects.PublishSubject;

/**
 * 文 件 名: RxPublishSubjectActivity
 * 创 建 人: CXP
 * 创建日期: 2018-04-28 14:07
 * PublishSubject
 *
 * onNext() 会通知每个观察者，仅此而已
 */
public class RxPublishSubjectActivity extends BaseActivity {

    private static final String TAG = "RxPublishSubjectActivit";

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
        mRxTv.append("PublishSubject\n");
        Log.e(TAG, "PublishSubject\n");

        PublishSubject<Integer> publishSubject = PublishSubject.create();

        publishSubject.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mRxTv.append("First onSubscribe :"+d.isDisposed()+"\n");
                Log.e(TAG, "First onSubscribe :"+d.isDisposed()+"\n");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                mRxTv.append("First onNext value :"+integer + "\n");
                Log.e(TAG, "First onNext value :"+integer + "\n");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mRxTv.append("First onError:"+e.getMessage()+"\n");
                Log.e(TAG, "First onError:"+e.getMessage()+"\n" );
            }

            @Override
            public void onComplete() {
                mRxTv.append("First onComplete!\n");
                Log.e(TAG, "First onComplete!\n");
            }
        });

        publishSubject.onNext(1);
        publishSubject.onNext(2);
        publishSubject.onNext(3);

        publishSubject.subscribe(new Observer<Integer>() {
            @Override
            public void onSubscribe(@NonNull Disposable d) {
                mRxTv.append("Second onSubscribe :"+d.isDisposed()+"\n");
                Log.e(TAG, "Second onSubscribe :"+d.isDisposed()+"\n");
            }

            @Override
            public void onNext(@NonNull Integer integer) {
                mRxTv.append("Second onNext value :"+integer + "\n");
                Log.e(TAG, "Second onNext value :"+integer + "\n");
            }

            @Override
            public void onError(@NonNull Throwable e) {
                mRxTv.append("Second onError:"+e.getMessage()+"\n");
                Log.e(TAG, "Second onError:"+e.getMessage()+"\n" );
            }

            @Override
            public void onComplete() {
                mRxTv.append("Second onComplete!\n");
                Log.e(TAG, "Second onComplete!\n");
            }
        });

        publishSubject.onNext(4);
        publishSubject.onNext(5);
        publishSubject.onComplete();
    }
}
