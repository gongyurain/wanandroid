package com.aiandroid.adapter;

import android.app.Activity;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.aiandroid.bean.HomeArticleBean;
import com.aiandroid.bean.TreeContentBean;
import com.aiandroid.view.ContentActivity;
import com.example.rain.aiandroid.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rain on 2018-7-9.
 */

public class TreeContentAdapter extends RecyclerView.Adapter<TreeContentAdapter.ArticleViewHolder>{
    private List<TreeContentBean.DataBean.DatasBean> mData =new ArrayList<>();
    private Activity activity;
    public TreeContentAdapter(List<TreeContentBean.DataBean.DatasBean> mData, Activity activity) {
        this.activity=activity;
        this.mData=mData;
    }

    public void updateData(List<TreeContentBean.DataBean.DatasBean> mData) {
        this.mData=mData;
        Log.d("GONGYU",mData.size()+"");
        this.notifyDataSetChanged();
    }
    public void moreData(List<TreeContentBean.DataBean.DatasBean> mData){
        this.mData=mData;
        this.notifyDataSetChanged();
    }

    @Override
    public TreeContentAdapter.ArticleViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // 实例化展示的view
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_article, parent, false);
        // 实例化viewholder
        TreeContentAdapter.ArticleViewHolder viewHolder = new TreeContentAdapter.ArticleViewHolder(v);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ArticleViewHolder holder, int position) {

    }


    @Override
    public void onBindViewHolder(TreeContentAdapter.ArticleViewHolder holder, final int position, List<Object> payloads) {
        super.onBindViewHolder(holder, position, payloads);
        holder.tv_author.setText(mData.get(position).getAuthor());
        holder.tv_content.setText(mData.get(position).getTitle());
        holder.tv_time.setText(mData.get(position).getNiceDate());
        holder.tv_type.setText(mData.get(position).getSuperChapterName());
        holder.itemView.setTag(position);
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(activity, ContentActivity.class);
                intent.putExtra("url",mData.get(position).getLink());
                activity.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mData == null ? 0 : mData.size();
    }



    public static class ArticleViewHolder extends RecyclerView.ViewHolder {

        TextView tv_author;
        TextView tv_content;
        TextView tv_time;
        TextView tv_type;
        TextView tv1;
        public ArticleViewHolder(View itemView) {
            super(itemView);
            tv_author=(TextView)itemView.findViewById(R.id.tv_author);
            tv_content=(TextView)itemView.findViewById(R.id.tv_content);
            tv_time=(TextView)itemView.findViewById(R.id.tv_time);
            tv_type=(TextView)itemView.findViewById(R.id.tv_type);
            tv1=(TextView)itemView.findViewById(R.id.tv1);
        }
    }
    public interface OnItemClickListener{
        void onItemClick(int position);
    }

}
