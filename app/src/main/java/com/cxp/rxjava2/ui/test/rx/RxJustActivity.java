package com.cxp.rxjava2.ui.test.rx;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.TextView;

import com.cxp.rxjava2.R;
import com.cxp.rxjava2.base.BaseActivity;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

/**
 * 文 件 名: RxJustActivity
 * 创 建 人: CXP
 * 创建日期: 2018-04-28 10:45
 * 描    述: just
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@SuppressLint("CheckResult")
public class RxJustActivity extends BaseActivity {

    private static final String TAG = "RxJustActivity";

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
        Observable.just("1", "2", "3")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<String>() {
                    @Override
                    public void accept(@NonNull String s) throws Exception {
                        mRxTv.append("accept : onNext : " + s + "\n");
                        Log.e(TAG,"accept : onNext : " + s + "\n" );
                    }
                });
    }
}
