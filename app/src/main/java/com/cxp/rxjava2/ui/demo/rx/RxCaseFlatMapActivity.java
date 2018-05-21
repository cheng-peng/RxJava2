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
import io.reactivex.ObservableSource;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * 文 件 名: RxCaseFlatMapActivity
 * 创 建 人: CXP
 * 创建日期: 2018-05-21 16:11
 * flatMap 使用场景
 * <p>
 * 多个网络请求依次依赖，比如：
 * 1、注册用户前先通过接口A获取当前用户是否已注册，再通过接口B注册;
 * 2、注册后自动登录，先通过注册接口注册用户信息，注册成功后马上调用登录接口进行自动登录。
 * <p>
 * 例子所用API来自天狗网
 * <p>
 * <p>
 */
@SuppressLint("CheckResult")
public class RxCaseFlatMapActivity extends BaseActivity {

    private static final String TAG = "RxCaseFlatMapActivity";

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
                .addQueryParameter("rows", 1 + "")
                .build()
                .getObjectObservable(MobileAddress.class) // 发起获取食品列表的请求，并解析到FootList
                .subscribeOn(Schedulers.io())        // 在io线程进行网络请求
                .observeOn(AndroidSchedulers.mainThread()) // 在主线程处理获取食品列表的请求结果
                .doOnNext(new Consumer<MobileAddress>() {
                    @Override
                    public void accept(@NonNull MobileAddress foodList) throws Exception {
                        // 先根据获取食品列表的响应结果做一些操作
                        Log.e(TAG, "accept: doOnNext :" + foodList.toString());
                        mRxTv.append("accept: doOnNext :" + foodList.toString() + "\n");
                    }
                })
                .observeOn(Schedulers.io()) // 回到 io 线程去处理获取食品详情的请求
                .flatMap(new Function<MobileAddress, ObservableSource<MobileAddress>>() {
                    @Override
                    public ObservableSource<MobileAddress> apply(@NonNull MobileAddress foodList) throws Exception {
                        return Rx2AndroidNetworking.post("http://api.avatardata.cn/MobilePlace/LookUp?key=ec47b85086be4dc8b5d941f5abd37a4e&mobileNumber=13021671512")
                                .build()
                                .getObjectObservable(MobileAddress.class);
                    }
                })
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MobileAddress>() {
                    @Override
                    public void accept(@NonNull MobileAddress foodDetail) throws Exception {
                        Log.e(TAG, "accept: success ：" + foodDetail.toString());
                        mRxTv.append("accept: success ：" + foodDetail.toString() + "\n");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(@NonNull Throwable throwable) throws Exception {
                        Log.e(TAG, "accept: error :" + throwable.getMessage());
                        mRxTv.append("accept: error :" + throwable.getMessage() + "\n");
                    }
                });
    }
}
