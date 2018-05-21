package com.cxp.rxjava2.ui.test.rx;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.TextView;

import com.cxp.rxjava2.R;
import com.cxp.rxjava2.base.BaseActivity;

import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;

/**
 * 文 件 名: RxBufferActivity
 * 创 建 人: CXP
 * 创建日期: 2018-04-28 13:44
 * buffer
 *
 * buffer(count, skip)` 从定义就差不多能看出作用了，
 * 将 observable 中的数据按 skip（步长）分成最长不超过 count 的 buffer，然后生成一个 observable
 */
@SuppressLint("CheckResult")
public class RxBufferActivity extends BaseActivity {

    private static final String TAG = "RxBufferActivity";

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
        Observable.just(1, 2, 3, 4, 5)
                .buffer(3, 2)
                .subscribe(new Consumer<List<Integer>>() {
                    @Override
                    public void accept(@NonNull List<Integer> integers) throws Exception {
                        mRxTv.append("buffer size : " + integers.size() + "\n");
                        Log.e(TAG, "buffer size : " + integers.size() + "\n");
                        mRxTv.append("buffer value : ");
                        Log.e(TAG, "buffer value : " );
                        for (Integer i : integers) {
                            mRxTv.append(i + "");
                            Log.e(TAG, i + "");
                        }
                        mRxTv.append("\n");
                        Log.e(TAG, "\n");
                    }
                });
    }


}
