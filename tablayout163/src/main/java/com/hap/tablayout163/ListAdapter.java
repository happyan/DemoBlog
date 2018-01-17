package com.hap.tablayout163;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.hap.tablayout163.event.EventBusActivity;

/**
 * Created by showhome002 on 2017/11/29.
 */

public class ListAdapter  extends RecyclerView.Adapter<ListAdapter.ViewHolder> {


    private Context mContext;

    public ListAdapter(Context context){
        this.mContext = context;
    }
    @Override
    public ListAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view =
                LayoutInflater.from(parent.getContext()).inflate(R.layout.item_rv, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListAdapter.ViewHolder holder, int position) {
        final View view = holder.mView;
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(mContext,"坚持就会有改变，继续加油吧！",Toast.LENGTH_SHORT).show();
                mContext.startActivity(new Intent(mContext , EventBusActivity.class));
            }
        });
    }

    @Override
    public int getItemCount() {
        return 20;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public final View mView;

        public ViewHolder(View view) {
            super(view);
            mView = view;
        }
    }
}
