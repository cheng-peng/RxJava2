package com.cxp.rxjava2.ui.demo.rx;

import android.annotation.SuppressLint;
import android.util.Log;
import android.widget.TextView;

import com.cxp.rxjava2.R;
import com.cxp.rxjava2.base.BaseActivity;
import com.cxp.rxjava2.model.MobileAddress;
import com.google.gson.Gson;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;

/**
 * 文 件 名: RxNetSingleActivity
 * 创 建 人: CXP
 * 创建日期: 2018-05-21 15:17
 * 描    述: 单一的网络请求
 * 采用 OkHttp3 配合 map , doOnNext , 线程切换做简单的网络请求   (subscribeOn()多次调用只会切换第一个，observeOn()可以切换多个)
 * <p>
 * 1、通过 Observable.create() 方法，调用 OkHttp 网络请求;
 * 2、通过 map 操作符结合 Gson , 将 Response 转换为 bean 类;
 * 3、通过 doOnNext() 方法，解析 bean 中的数据，并进行数据库存储等操作;
 * 4、调度线程，在子线程进行耗时操作任务，在主线程更新 UI;
 * 5、通过 subscribe(),根据请求成功或者失败来更新 UI。
 */
@SuppressLint("CheckResult")
public class RxNetSingleActivity extends BaseActivity {

    private static final String TAG = "RxNetSingleActivity";

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
        Observable.create(new ObservableOnSubscribe<Response>() {
            @Override
            public void subscribe(ObservableEmitter<Response> emitter) throws Exception {
                Request.Builder builder = new Request.Builder()
                        .url("http://api.avatardata.cn/MobilePlace/LookUp?key=ec47b85086be4dc8b5d941f5abd37a4e&mobileNumber=13021671512")
                        .get();
                Request request = builder.build();
                Call call = new OkHttpClient().newCall(request);
                Response response = call.execute();
                emitter.onNext(response);
            }
        }).map(new Function<Response, MobileAddress>() {
            @Override
            public MobileAddress apply(Response response) throws Exception {
                Log.e(TAG, "map 线程:" + Thread.currentThread().getName() + "\n");
                if (response.isSuccessful()) {
                    ResponseBody body = response.body();
                    if (body != null) {
                        Log.e(TAG, "map:转换前:" + response.body());
                        return new Gson().fromJson(body.string(), MobileAddress.class);
                    }
                }
                return null;
            }
        }).observeOn(AndroidSchedulers.mainThread())
                .doOnNext(new Consumer<MobileAddress>() {
                    @Override
                    public void accept(MobileAddress s) throws Exception {
                        Log.e(TAG, "doOnNext 线程:" + Thread.currentThread().getName() + "\n");
                        mRxTv.append("\ndoOnNext 线程:" + Thread.currentThread().getName() + "\n");
                        Log.e(TAG, "doOnNext: 保存成功：" + s.toString() + "\n");
                        mRxTv.append("doOnNext: 保存成功：" + s.toString() + "\n");
                    }
                }).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<MobileAddress>() {
                    @Override
                    public void accept(MobileAddress data) throws Exception {
                        Log.e(TAG, "subscribe 线程:" + Thread.currentThread().getName() + "\n");
                        mRxTv.append("\nsubscribe 线程:" + Thread.currentThread().getName() + "\n");
                        Log.e(TAG, "成功:" + data.toString() + "\n");
                        mRxTv.append("成功:" + data.toString() + "\n");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        Log.e(TAG, "subscribe 线程:" + Thread.currentThread().getName() + "\n");
                        mRxTv.append("\nsubscribe 线程:" + Thread.currentThread().getName() + "\n");

                        Log.e(TAG, "失败：" + throwable.getMessage() + "\n");
                        mRxTv.append("失败：" + throwable.getMessage() + "\n");
                    }
                });
    }

}
