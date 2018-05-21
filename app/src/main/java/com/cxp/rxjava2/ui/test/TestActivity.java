package com.cxp.rxjava2.ui.test;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.cxp.rxjava2.R;
import com.cxp.rxjava2.base.BaseActivity;
import com.cxp.rxjava2.ui.test.rx.RxAsyncSubjectActivity;
import com.cxp.rxjava2.ui.test.rx.RxBehaviorSubjectActivity;
import com.cxp.rxjava2.ui.test.rx.RxBufferActivity;
import com.cxp.rxjava2.ui.test.rx.RxCompletableActivity;
import com.cxp.rxjava2.ui.test.rx.RxConcatActivity;
import com.cxp.rxjava2.ui.test.rx.RxConcatMapActivity;
import com.cxp.rxjava2.ui.test.rx.RxCreateActivity;
import com.cxp.rxjava2.ui.test.rx.RxDebounceActivity;
import com.cxp.rxjava2.ui.test.rx.RxDeferActivity;
import com.cxp.rxjava2.ui.test.rx.RxDistinctActivity;
import com.cxp.rxjava2.ui.test.rx.RxDoOnNextActivity;
import com.cxp.rxjava2.ui.test.rx.RxFilterActivity;
import com.cxp.rxjava2.ui.test.rx.RxFlatMapActivity;
import com.cxp.rxjava2.ui.test.rx.RxFlowableActivity;
import com.cxp.rxjava2.ui.test.rx.RxIntervalActivity;
import com.cxp.rxjava2.ui.test.rx.RxJustActivity;
import com.cxp.rxjava2.ui.test.rx.RxLastActivity;
import com.cxp.rxjava2.ui.test.rx.RxMapActivity;
import com.cxp.rxjava2.ui.test.rx.RxMergeActivity;
import com.cxp.rxjava2.ui.test.rx.RxPublishSubjectActivity;
import com.cxp.rxjava2.ui.test.rx.RxReduceActivity;
import com.cxp.rxjava2.ui.test.rx.RxScanActivity;
import com.cxp.rxjava2.ui.test.rx.RxSingleActivity;
import com.cxp.rxjava2.ui.test.rx.RxSkipActivity;
import com.cxp.rxjava2.ui.test.rx.RxTakeActivity;
import com.cxp.rxjava2.ui.test.rx.RxTimerActivity;
import com.cxp.rxjava2.ui.test.rx.RxWindowActivity;
import com.cxp.rxjava2.ui.test.rx.RxZipActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import butterknife.BindView;

/**
 * 文 件 名: TestActivity
 * 创 建 人: CXP
 * 创建日期: 2018-04-27 12:44
 * 描    述: RxJava基础操作
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class TestActivity extends BaseActivity {


    @BindView(R.id.test_recyclerview)
    RecyclerView mTestRecyclerview;

    private List<Map<String,String>> mDatas;
    private TestAdapter mTestAdapter;


    @Override
    protected int getContentViewLayoutId() {
        return R.layout.activity_test;
    }

    @Override
    protected void initView() {

        //初始化数据
        initData();

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        mTestRecyclerview.setLayoutManager(linearLayoutManager);
        mTestAdapter=new TestAdapter(this,mDatas,mOnItemClick);
        mTestRecyclerview.setAdapter(mTestAdapter);

    }

    //初始化数据
    private void initData() {
        mDatas=new ArrayList<>();
        Map<String,String> map=new HashMap<>();
        map.put("title","create");
        map.put("content","可用于获取一个被观察的对象");
        mDatas.add(map);
        Map<String,String> map1=new HashMap<>();
        map1.put("title","zip");
        map1.put("content","合并事件专用," +
                "分别从两个上游事件中各取出一个组合," +
                "一个事件只能被使用一次，顺序严格按照事件发送的顺序," +
                "最终下游事件收到的是和上游事件最少的数目相同（必须两两配对，多余的舍弃,类其余数据库的多表查询合并数据)");
        mDatas.add(map1);
        Map<String,String> map2=new HashMap<>();
        map2.put("title","map");
        map2.put("content","基本是RxJava 最简单的操作符了作用是对上游发送的每一个事件应用一个函数，" +
                "使得每一个事件都按照指定的函数去变化");
        mDatas.add(map2);
        Map<String,String> map3=new HashMap<>();
        map3.put("title","flatMap");
        map3.put("content","FlatMap将一个发送事件的上游Observable变换成多个发送事件的Observables， 然后将它们发射的时间合并后放进一个单独的Observable里");
        mDatas.add(map3);
        Map<String,String> map4=new HashMap<>();
        map4.put("title","concatMap");
        map4.put("content","concatMap作用和flatMap几乎一模一样，唯一的区别是它能保证事件的顺序");
        mDatas.add(map4);
        Map<String,String> map5=new HashMap<>();
        map5.put("title","doOnNext");
        map5.put("content","让订阅者在接收到数据前干点事情的操作符");
        mDatas.add(map5);
        Map<String,String> map6=new HashMap<>();
        map6.put("title","filter");
        map6.put("content","过滤操作符，取正确的值");
        mDatas.add(map6);
        Map<String,String> map7=new HashMap<>();
        map7.put("title","skip");
        map7.put("content","接受一个long型参数，代表跳过多少个数目的事件再开始接收");
        mDatas.add(map7);
        Map<String,String> map8=new HashMap<>();
        map8.put("title","take");
        map8.put("content","用于指定订阅者最多收到多少数据");
        mDatas.add(map8);
        Map<String,String> map9=new HashMap<>();
        map9.put("title","timer");
        map9.put("content","在Rxjava中timer 操作符既可以延迟执行一段逻辑，也可以间隔执行一段逻辑\n" +
                "【注意】但在RxJava 2.x已经过时了，现在用interval操作符来间隔执行，详见RxIntervalActivity\n" +
                "timer和interval都默认执行在一个新线程上。");
        mDatas.add(map9);
        Map<String,String> map10=new HashMap<>();
        map10.put("title","interval");
        map10.put("content","间隔执行操作，默认在新线程");
        mDatas.add(map10);
        Map<String,String> map11=new HashMap<>();
        map11.put("title","just");
        map11.put("content","just操作符，和RxJava 1.x 没有什么区别，就是接受一个可变参数，依次发送");
        mDatas.add(map11);
        Map<String,String> map12=new HashMap<>();
        map12.put("title","single");
        map12.put("content","顾名思义，Single只会接收一个参数" +
                "而SingleObserver只会调用onError或者onSuccess");
        mDatas.add(map12);
        Map<String,String> map13=new HashMap<>();
        map13.put("title","concat");
        map13.put("content","连接操作符，可接受Observable的可变参数，或者Observable的集合");
        mDatas.add(map13);
        Map<String,String> map14=new HashMap<>();
        map14.put("title","distinct");
        map14.put("content","去重操作符，其实就是简单的去重");
        mDatas.add(map14);
        Map<String,String> map15=new HashMap<>();
        map15.put("title","buffer");
        map15.put("content","buffer(count, skip)` 从定义就差不多能看出作用了，将 observable 中的数据按 skip（步长）分成最长不超过 count 的 buffer，然后生成一个 observable");
        mDatas.add(map15);
        Map<String,String> map16=new HashMap<>();
        map16.put("title","debounce");
        map16.put("content","过滤掉发射速率过快的数据项");
        mDatas.add(map16);
        Map<String,String> map17=new HashMap<>();
        map17.put("title","defer");
        map17.put("content","就是在每次订阅的时候就会创建一个新的 Observable");
        mDatas.add(map17);
        Map<String,String> map18=new HashMap<>();
        map18.put("title","last");
        map18.put("content","取出最后一个值，参数是没有值的时候的默认值");
        mDatas.add(map18);
        Map<String,String> map19=new HashMap<>();
        map19.put("title","merge");
        map19.put("content","将多个Observable合起来，接受可变参数，也支持使用迭代器集合");
        mDatas.add(map19);
        Map<String,String> map20=new HashMap<>();
        map20.put("title","reduce");
        map20.put("content","就是一次用一个方法处理一个值，可以有一个seed作为初始值");
        mDatas.add(map20);
        Map<String,String> map21=new HashMap<>();
        map21.put("title","scan");
        map21.put("content","和上面的reduce差不多，区别在于reduce()只输出结果，而scan()会将过程中每一个结果输出");
        mDatas.add(map21);
        Map<String,String> map22=new HashMap<>();
        map22.put("title","window");
        map22.put("content","按照时间划分窗口，将数据发送给不同的Observable");
        mDatas.add(map22);
        Map<String,String> map23=new HashMap<>();
        map23.put("title","PublishSubject");
        map23.put("content","onNext() 会通知每个观察者，仅此而已");
        mDatas.add(map23);
        Map<String,String> map24=new HashMap<>();
        map24.put("title","AsyncSubject");
        map24.put("content","在调用 onComplete() 之前，除了 subscribe() 其它的操作都会被缓存，在调用 onComplete() 之后只有最后一个 onNext() 会生效");
        mDatas.add(map24);
        Map<String,String> map25=new HashMap<>();
        map25.put("title","BehaviorSubject");
        map25.put("content","BehaviorSubject 的最后一次 onNext() 操作会被缓存，然后在 subscribe() 后立刻推给新注册的 Observer");
        mDatas.add(map25);
        Map<String,String> map26=new HashMap<>();
        map26.put("title","Completable");
        map26.put("content","只关心结果，也就是说 Completable 是没有 onNext 的，要么成功要么出错，不关心过程，在 subscribe 后的某个时间点返回结果");
        mDatas.add(map26);
        Map<String,String> map27=new HashMap<>();
        map27.put("title","Flowable");
        map27.put("content","专用于解决背压问题");
        mDatas.add(map27);
    }

    /**
     * 单击事件
     */
    private OnItemClick mOnItemClick=new OnItemClick() {
        @Override
        public void addOnItemClickLis(View view, int position) {
            switch (position) {
                case 0:
                    //create
                    startActivity(RxCreateActivity.class);
                    break;
                case 1:
                    //zip
                    startActivity(RxZipActivity.class);
                    break;
                case 2:
                    //map
                    startActivity(RxMapActivity.class);
                    break;
                case 3:
                    //flatMap
                    startActivity(RxFlatMapActivity.class);
                    break;
                case 4:
                    //concatMap
                    startActivity(RxConcatMapActivity.class);
                    break;
                case 5:
                    //doOnNext
                    startActivity(RxDoOnNextActivity.class);
                    break;
                case 6:
                    //filter
                    startActivity(RxFilterActivity.class);
                    break;
                case 7:
                    //skip
                    startActivity(RxSkipActivity.class);
                    break;
                case 8:
                    //take
                    startActivity(RxTakeActivity.class);
                    break;
                case 9:
                    //timer
                    startActivity(RxTimerActivity.class);
                    break;
                case 10:
                    //interval
                    startActivity(RxIntervalActivity.class);
                    break;
                case 11:
                    //just
                    startActivity(RxJustActivity.class);
                    break;
                case 12:
                    //single
                    startActivity(RxSingleActivity.class);
                    break;
                case 13:
                    //concat
                    startActivity(RxConcatActivity.class);
                    break;
                case 14:
                    //distinct
                    startActivity(RxDistinctActivity.class);
                    break;
                case 15:
                    //buffer
                    startActivity(RxBufferActivity.class);
                    break;
                case 16:
                    //debounce
                    startActivity(RxDebounceActivity.class);
                    break;
                case 17:
                    //defer
                    startActivity(RxDeferActivity.class);
                    break;
                case 18:
                    //last
                    startActivity(RxLastActivity.class);
                    break;
                case 19:
                    //merge
                    startActivity(RxMergeActivity.class);
                    break;
                case 20:
                    //reduce
                    startActivity(RxReduceActivity.class);
                    break;
                case 21:
                    //scan
                    startActivity(RxScanActivity.class);
                    break;
                case 22:
                    //window
                    startActivity(RxWindowActivity.class);
                    break;
                case 23:
                    //PublishSubject
                    startActivity(RxPublishSubjectActivity.class);
                    break;
                case 24:
                    //AsyncSubject
                    startActivity(RxAsyncSubjectActivity.class);
                    break;
                case 25:
                    //BehaviorSubject
                    startActivity(RxBehaviorSubjectActivity.class);
                    break;
                case 26:
                    //Completable
                    startActivity(RxCompletableActivity.class);
                    break;
                case 27:
                    //Flowable
                    startActivity(RxFlowableActivity.class);
                    break;
            }
        }
    };

}
