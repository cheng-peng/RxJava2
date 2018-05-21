package com.cxp.rxjava2.ui.test.rx;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.TextView;

import com.cxp.rxjava2.R;
import com.cxp.rxjava2.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.BiFunction;
import io.reactivex.functions.Consumer;

/**
 * 文 件 名: RxZipActivity
 * 创 建 人: CXP
 * 创建日期: 2018-04-28 10:37
 * <p>
 * zip
 * <p>
 * 合并事件专用
 * <p>
 * 分别从两个上游事件中各取出一个组合
 * 一个事件只能被使用一次，顺序严格按照事件发送的顺序
 * 最终下游事件收到的是和上游事件最少的数目相同（必须两两配对，多余的舍弃)
 * <p>
 */
@SuppressLint("CheckResult")
public class RxZipActivity extends BaseActivity {

    private static final String TAG = "RxZipActivity";

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
        Observable.zip(getStringObservable(), getIntegerObservable(), new BiFunction<String, Integer, String>() {
            @Override
            public String apply(@NonNull String s, @NonNull Integer integer) throws Exception {
                return s + integer;
            }
        }).subscribe(new Consumer<String>() {
            @Override
            public void accept(@NonNull String s) throws Exception {
                mRxTv.append("zip : accept : " + s + "\n");
                Log.e(TAG, "zip : accept : " + s + "\n");
            }
        });
    }

    private Observable<String> getStringObservable() {
        return Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<String> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onNext("A");
                    mRxTv.append("String emit : A \n");
                    Log.e(TAG, "String emit : A \n");
                    e.onNext("B");
                    mRxTv.append("String emit : B \n");
                    Log.e(TAG, "String emit : B \n");
                    e.onNext("C");
                    mRxTv.append("String emit : C \n");
                    Log.e(TAG, "String emit : C \n");
                }
            }
        });
    }

    private Observable<Integer> getIntegerObservable() {
        return Observable.create(new ObservableOnSubscribe<Integer>() {
            @Override
            public void subscribe(@NonNull ObservableEmitter<Integer> e) throws Exception {
                if (!e.isDisposed()) {
                    e.onNext(1);
                    mRxTv.append("Integer emit : 1 \n");
                    Log.e(TAG, "Integer emit : 1 \n");
                    e.onNext(2);
                    mRxTv.append("Integer emit : 2 \n");
                    Log.e(TAG, "Integer emit : 2 \n");
                    e.onNext(3);
                    mRxTv.append("Integer emit : 3 \n");
                    Log.e(TAG, "Integer emit : 3 \n");
                    e.onNext(4);
                    mRxTv.append("Integer emit : 4 \n");
                    Log.e(TAG, "Integer emit : 4 \n");
                    e.onNext(5);
                    mRxTv.append("Integer emit : 5 \n");
                    Log.e(TAG, "Integer emit : 5 \n");
                }
            }
        });
    }
}
