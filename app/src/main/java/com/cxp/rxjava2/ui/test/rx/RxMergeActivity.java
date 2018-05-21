package com.cxp.rxjava2.ui.test.rx;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.TextView;

import com.cxp.rxjava2.R;
import com.cxp.rxjava2.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 文 件 名: RxMergeActivity
 * 创 建 人: CXP
 * 创建日期: 2018-04-28 14:06
 * merge
 * <p>
 * 将多个Observable合起来，接受可变参数，也支持使用迭代器集合
 */
@SuppressLint("CheckResult")
public class RxMergeActivity extends BaseActivity {

    private static final String TAG = "RxMergeActivity";

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
        Observable.merge(Observable.just(1, 2), Observable.just(3, 4, 5))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        mRxTv.append("merge :" + integer + "\n");
                        Log.e(TAG, "accept: merge :" + integer + "\n" );
                    }
                });
    }
}
