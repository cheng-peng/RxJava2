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
 * 文 件 名: RxConcatActivity
 * 创 建 人: CXP
 * 创建日期: 2018-04-28 13:47
 * concat
 *
 * 连接操作符，可接受Observable的可变参数，或者Observable的集合
 */
@SuppressLint("CheckResult")
public class RxConcatActivity extends BaseActivity {

    private static final String TAG = "RxConcatActivity";

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
        Observable.concat(Observable.just(1,2,3), Observable.just(4,5,6))
                .subscribe(new Consumer<Integer>() {
                    @Override
                    public void accept(@NonNull Integer integer) throws Exception {
                        mRxTv.append("concat : "+ integer + "\n");
                        Log.e(TAG, "concat : "+ integer + "\n" );
                    }
                });
    }
}
