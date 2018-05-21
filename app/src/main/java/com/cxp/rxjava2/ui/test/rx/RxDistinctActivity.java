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
 * 文 件 名: RxDistinctActivity
 * 创 建 人: CXP
 * 创建日期: 2018-04-28 13:56
 * distinct
 * <p>
 * 去重操作符，其实就是简单的去重
 */
@SuppressLint("CheckResult")
public class RxDistinctActivity extends BaseActivity {

    private static final String TAG = "RxDistinctActivity";

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
        Observable.just(1, 1, 1, 2, 2, 3, 4, 5)
                .distinct()
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        mRxTv.append("distinct : " + integer + "\n");
                        Log.e(TAG, "distinct : " + integer + "\n");
                    }
                });
    }
}
