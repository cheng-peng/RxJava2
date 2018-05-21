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
import io.reactivex.subjects.BehaviorSubject;

/**
 * 文 件 名: RxBehaviorSubjectActivity
 * 创 建 人: CXP
 * 创建日期: 2018-04-28 10:54
 *
 * BehaviorSubject
 *
 * BehaviorSubject 的最后一次 onNext() 操作会被缓存，然后在 subscribe() 后立刻推给新注册的 Observer
 */
public class RxBehaviorSubjectActivity extends BaseActivity {

    private static final String TAG = "RxBehaviorSubjectActivity";

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
        mRxTv.append("BehaviorSubject\n");
        Log.e(TAG, "BehaviorSubject\n");

        BehaviorSubject<Integer> behaviorSubject = BehaviorSubject.create();

        behaviorSubject.subscribe(new Observer<Integer>() {
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

        behaviorSubject.onNext(1);
        behaviorSubject.onNext(2);
        behaviorSubject.onNext(3);

        behaviorSubject.subscribe(new Observer<Integer>() {
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

        behaviorSubject.onNext(4);
        behaviorSubject.onNext(5);
        behaviorSubject.onComplete();
    }
}
