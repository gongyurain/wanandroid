package com.aiandroid.adapter;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.aiandroid.bean.SystemBean;
import com.example.rain.aiandroid.R;

import com.yang.flowlayoutlibrary.FlowLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by rain on 2018-7-8.
 */

public class SystemAdapter extends RecyclerView.Adapter<SystemAdapter.SystemViewHolder>{
    private List<SystemBean.DataBean> dataBeans;
    private HashMap<Integer,List<String>> strings;
    private Context context;
    private boolean ifFirst=false;
    private OnItemClickListener onItemClickListener;
    public SystemAdapter(List<SystemBean.DataBean> dataBeans,HashMap<Integer,List<String>> strings,Context context,OnItemClickListener onItemClickListener){
        this.context=context;
        this.dataBeans=dataBeans;
        this.strings=strings;
        this.onItemClickListener=onItemClickListener;
    }
    @Override
    public SystemViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_system, parent, false);
        // 实例化viewholder
        SystemAdapter.SystemViewHolder viewHolder = new SystemAdapter.SystemViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final SystemViewHolder holder, final int position) {
        holder.title.setText(dataBeans.get(position).getName());
        holder.flowLayout.setTextSize(8);
        holder.flowLayout.removeAllViews();
        holder.flowLayout.clearFocus();
        holder.flowLayout.setFlowLayout(strings.get(position), new FlowLayout.OnItemClickListener() {
                @Override
                public void onItemClick(String s) {
                    onItemClickListener.onItemClick(position);
                }

        });

        holder.flowLayout.setHorizontalSpacing(6);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.onItemClick(position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return dataBeans == null ? 0 : dataBeans.size();
    }

    public class SystemViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        FlowLayout flowLayout;
        public SystemViewHolder(View itemView) {
            super(itemView);
            title=(TextView)itemView.findViewById(R.id.tv_title);
            flowLayout=(FlowLayout) itemView.findViewById(R.id.fl_keyword);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }
}
