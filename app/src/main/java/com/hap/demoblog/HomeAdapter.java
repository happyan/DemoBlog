package com.hap.demoblog;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

/**
 * Created by showhome002 on 2017/11/17.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.MyViewHolder>{

    private List<String> mList;//数据源列表
    private Context mContext;//

    public HomeAdapter(Context mContext,List<String>mList){
        this.mContext=mContext;
        this.mList=mList;
    }


    /**
     * 1：onCreateViewHolder是绑定我们定制的ViewHolder，同时在这里设置layout
     */
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        MyViewHolder holder = new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.item_home,parent,false));
        return holder;
    }

    /**
     * 2：这个方法是用于操作View的，比如给TextView设置值...还可以在这里定制点击事件，这个不用急后面会说到3
     */
    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        holder.tv.setText(mList.get(position));

        if (mOnItemClickListener != null) {
            holder.tv.setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View v)
                {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemClick(holder.tv,pos);
                }
            });
            holder.tv.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {
                    int pos = holder.getLayoutPosition();
                    mOnItemClickListener.onItemLongClick(holder.tv,pos);
                    return false;
                }
            });
        }
    }

    /**
     * 这里设置item的数量，一般直接用List的size就可以了
     */
    @Override
    public int getItemCount() {
        return mList.size();
    }

    /**
     * 自定义的ViewHolder需要继承RecyclerView.ViewHolder，基本操作如下
     */
    class MyViewHolder extends RecyclerView.ViewHolder
    {
        TextView tv;
        public MyViewHolder(View view)
        {
            super(view);
            tv = (TextView) view.findViewById(R.id.tv_item);
        }
    }



    public interface OnItemClickListener
    {
        void onItemClick(View view, int position);
        void onItemLongClick(View view,int position);
    }

    private OnItemClickListener mOnItemClickListener;
    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener)
    {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public void removeData(int position) {
        mList.remove(position);
        notifyItemRemoved(position);
    }


}
