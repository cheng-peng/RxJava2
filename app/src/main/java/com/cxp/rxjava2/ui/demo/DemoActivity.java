package com.cxp.rxjava2.ui.demo;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cxp.rxjava2.R;
import com.cxp.rxjava2.base.BaseActivity;
import com.cxp.rxjava2.ui.demo.rx.RxCaseConcatActivity;
import com.cxp.rxjava2.ui.demo.rx.RxCaseFlatMapActivity;
import com.cxp.rxjava2.ui.demo.rx.RxCaseZipActivity;
import com.cxp.rxjava2.ui.demo.rx.RxNetSingleActivity;
import com.cxp.rxjava2.ui.demo.rx.RxNetworkActivity;
import com.cxp.rxjava2.ui.demo.rx.RxThreadActivity;
import com.cxp.rxjava2.ui.test.OnItemClick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 文 件 名: DemoActivity
 * 创 建 人: CXP
 * 创建日期: 2018-04-27 12:44
 * 描    述: RxJava实战操作
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class DemoActivity extends BaseActivity {

    @BindView(R.id.demo_recyclerview)
    RecyclerView mDemoRecyclerview;

    private List<Map<String,String>> mDatas;
    private DemoAdapter mDemoAdapter;

    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_demo;
    }

    @Override
    protected void initView() {
        //初始化数据
        initData();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mDemoRecyclerview.setLayoutManager(linearLayoutManager);
        mDemoAdapter=new DemoAdapter(this,mDatas,mOnItemClick);
        mDemoRecyclerview.setAdapter(mDemoAdapter);

    }

    //初始化数据
    private void initData() {
        mDatas=new ArrayList<>();
        Map<String,String> map=new HashMap<>();
        map.put("title","单一的网络请求");
        map.put("content","1、通过 Observable.create() 方法，调用 OkHttp 网络请求;\n" +
                "2、通过 map 操作符结合 Gson , 将 Response 转换为 bean 类;\n" +
                "3、通过 doOnNext() 方法，解析 bean 中的数据，并进行数据库存储等操作;\n" +
                "4、调度线程，在子线程进行耗时操作任务，在主线程更新 UI;\n" +
                "5、通过 subscribe(),根据请求成功或者失败来更新 UI。");
        mDatas.add(map);
        Map<String,String> map1=new HashMap<>();
        map1.put("title","使用框架 rx2-Networking");
        map1.put("content","1、通过 Rx2AndroidNetworking 的 get() 方法获取 Observable 对象(已解析)；\n" +
                "2、调度线程，根据请求结果更新 UI。");
        mDatas.add(map1);
        Map<String,String> map2=new HashMap<>();
        map2.put("title","结合多个接口的数据再更新 UI");
        map2.put("content","zip 操作符可以把多个 Observable 的数据接口成一个数据源再发出去");
        mDatas.add(map2);
        Map<String,String> map3=new HashMap<>();
        map3.put("title","多个网络请求依次依赖");
        map3.put("content","flatMap 操作符可以让多个网络请求依次依赖,比如:\n" +
                "1、注册用户前先通过接口A获取当前用户是否已注册，再通过接口B注册;\n" +
                "2、注册后自动登录，先通过注册接口注册用户信息，注册成功后马上调用登录接口进行自动登录。");
        mDatas.add(map3);
        Map<String,String> map4=new HashMap<>();
        map4.put("title","先读取缓存数据再读取网络请求");
        map4.put("content","实用场景中经常会用到缓存数据，以通过减少频繁的网络请求达到节约流量：\n" +
                "1、concat 可以做到不交错的发射两个甚至多个 Observable 的发射物;\n" +
                "2、并且只有前一个 Observable 终止（onComplete）才会订阅下一个 Observable");
        mDatas.add(map4);
        Map<String,String> map7=new HashMap<>();
        map7.put("title","线程调度需要注意的");
        map7.put("content","RxJava 内置的线程调度器的确可以让我们的线程切换得心应手，但其中也有些需要注意的地方。\n" +
                "- 简单地说，subscribeOn() 指定的就是发射事件的线程，observerOn指定的就是订阅者接收事件的线程。\n" +
                "- 多次指定发射事件的线程只有第一次指定的有效，也就是说多次调用 subscribeOn() 只有第一次的有效，其余的会被忽略。\n" +
                "- 但多次指定订阅者接收线程是可以的，也就是说每调用一次 observerOn()，下游的线程就会切换一次。");
        mDatas.add(map7);
    }

    /**
     * 单击事件
     */
    private OnItemClick mOnItemClick=new OnItemClick() {
        @Override
        public void addOnItemClickLis(View view, int position) {
            switch (position) {
                case 0:
                    //单一的网络请求
                    startActivity(RxNetSingleActivity.class);
                    break;
                case 1:
                    //使用框架 rx2-Networking
                    startActivity(RxNetworkActivity.class);
                    break;
                case 2:
                    //结合多个接口的数据再更新
                    startActivity(RxCaseZipActivity.class);
                    break;
                case 3:
                    //多个网络请求依次依赖
                    startActivity(RxCaseFlatMapActivity.class);
                    break;
                case 4:
                    //先读取缓存数据再读取网络请求
                    startActivity(RxCaseConcatActivity.class);
                    break;
                case 5:
                    //线程调度需要注意的
                    startActivity(RxThreadActivity.class);
                    break;
            }
        }
    };

}
