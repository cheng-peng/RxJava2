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
import io.reactivex.subjects.AsyncSubject;

/**
 * 文 件 名: RxAsyncSubjectActivity
 * 创 建 人: CXP
 * 创建日期: 2018-04-28 10:52
 * AsyncSubject
 * <p>
 * 在 调用 onComplete() 之前，除了 subscribe() 其它的操作都会被缓存，
 * 在调用 onComplete() 之后只有最后一个 onNext() 会生效
 */
public class RxAsyncSubjectActivity extends BaseActivity {

    private static final String TAG = "RxAsyncSubjectActivity";

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
        mRxTv.append("AsyncSubject\n");
        Log.e(TAG, "AsyncSubject\n");

        AsyncSubject<Integer> asyncSubject = AsyncSubject.create();

        asyncSubject.subscribe(new Observer<Integer>() {
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

        asyncSubject.onNext(1);
        asyncSubject.onNext(2);
        asyncSubject.onNext(3);

        asyncSubject.subscribe(new Observer<Integer>() {
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

        asyncSubject.onNext(4);
        asyncSubject.onNext(5);
        asyncSubject.onComplete();
    }
}
