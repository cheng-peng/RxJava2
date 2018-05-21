package com.cxp.rxjava2.ui.demo;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.cxp.rxjava2.R;
import com.cxp.rxjava2.ui.test.OnItemClick;

import java.util.List;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * 文 件 名: DemoAdapter
 * 创 建 人: CXP
 * 创建日期: 2018-04-27 15:00
 * 描    述: 基础操作适配器
 * 修 改 人:
 * 修改时间：
 * 修改备注：
 */
public class DemoAdapter extends RecyclerView.Adapter<DemoAdapter.TestViewHolder> {

    private Context mContext;
    private List<Map<String, String>> mDatas;
    private OnItemClick mOnItemClick;

    public DemoAdapter(Context context, List<Map<String, String>> datas, OnItemClick mOnItemClick) {
        this.mContext = context;
        this.mDatas = datas;
        this.mOnItemClick = mOnItemClick;
    }

    @NonNull
    @Override
    public TestViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new TestViewHolder(LayoutInflater.from(mContext).inflate(R.layout.layout_item_test, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull TestViewHolder holder, final int position) {
        holder.mItemTestTitle.setText(mDatas.get(position).get("title"));
        holder.mItemTestContent.setText(mDatas.get(position).get("content"));
        holder.mItemTestRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mOnItemClick.addOnItemClickLis(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mDatas != null ? mDatas.size() : 0;
    }

    public class TestViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.item_test_root)
        LinearLayout mItemTestRoot;
        @BindView(R.id.item_test_title)
        TextView mItemTestTitle;
        @BindView(R.id.item_test_content)
        TextView mItemTestContent;

        public TestViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

}
