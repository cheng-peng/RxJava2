package com.cxp.rxjava2.ui.demo.rx;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.TextView;

import com.cxp.rxjava2.R;
import com.cxp.rxjava2.base.BaseActivity;
import com.cxp.rxjava2.model.MobileAddress;
import com.rx2androidnetworking.Rx2AndroidNetworking;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 文 件 名: RxNetworkActivity
 * 创 建 人: CXP
 * 创建日期: 2018-05-21 16:08
 * 描    述: 仅仅是采用这个框架做 rx2 网络请求
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
@SuppressLint("CheckResult")
public class RxNetworkActivity extends BaseActivity {

    private static final String TAG = "RxNetworkActivity";

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
        Rx2AndroidNetworking.get("http://api.avatardata.cn/MobilePlace/LookUp?key=ec47b85086be4dc8b5d941f5abd37a4e&mobileNumber=13021671512")
                .build()
                .getObjectObservable(MobileAddress.class)
                .observeOn(AndroidSchedulers.mainThread()) // 为doOnNext() 指定在主线程，否则报错
                .doOnNext(new Consumer<MobileAddress>() {
                    @Override
                    public void accept(@NonNull MobileAddress data) throws Exception {
                        Log.e(TAG, "doOnNext:"+Thread.currentThread().getName()+"\n" );
                        mRxTv.append("\ndoOnNext:"+Thread.currentThread().getName()+"\n" );
                        Log.e(TAG,"doOnNext:"+data.toString()+"\n");
                        mRxTv.append("doOnNext:"+data.toString()+"\n");
                    }
                })
                .map(new Function<MobileAddress, MobileAddress.ResultBean>() {
                    @Override
                    public MobileAddress.ResultBean apply(@NonNull MobileAddress mobileAddress) throws Exception {
                        Log.e(TAG, "\n" );
                        mRxTv.append("\n");
                        Log.e(TAG, "map:"+Thread.currentThread().getName()+"\n" );
                        mRxTv.append("map:"+Thread.currentThread().getName()+"\n" );
                        return mobileAddress.getResult();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MobileAddress.ResultBean>() {
                    @Override
                    public void accept(@NonNull MobileAddress.ResultBean data) throws Exception {
                        Log.e(TAG, "subscribe 成功:"+Thread.currentThread().getName()+"\n" );
                        mRxTv.append("\nsubscribe 成功:"+Thread.currentThread().getName()+"\n" );
                        Log.e(TAG, "成功:" + data.toString() + "\n");
                        mRxTv.append("成功:" + data.toString() + "\n");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "subscribe 失败:"+Thread.currentThread().getName()+"\n" );
                        mRxTv.append("\nsubscribe 失败:"+Thread.currentThread().getName()+"\n" );
                        Log.e(TAG, "失败："+ throwable.getMessage()+"\n" );
                        mRxTv.append("失败："+ throwable.getMessage()+"\n");
                    }
                });
    }

}
