package com.hap.demoblog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private List<String> mList;
    private RecyclerView mRecyclerView;
    HomeAdapter mHomeAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initData();
        initView();

    }

    private void initData() {
        mList=new ArrayList<String>();
        for (int i = 1; i < 20; i++)
        {
            mList.add("Item"+ i);
        }
    }


    private void initView() {
        mRecyclerView= (RecyclerView) this.findViewById(R.id.id_recyclerview);
        //设置GridView
//        setGridView();
        //设置ListView
        setListView();
        //设置瀑布流
//        setWaterfallView();
    }

    public void setListView(){
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));//设置布局
        mRecyclerView.addItemDecoration(new DividerItemDecoration( this, DividerItemDecoration.VERTICAL_LIST));//添加分割线
        mRecyclerView.setItemAnimator(new DefaultItemAnimator());//设置Item增加、移除动画
        mHomeAdapter =new HomeAdapter(this, mList);
        setLister();
        mRecyclerView.setAdapter(mHomeAdapter);//设置adapter
    }

    private void setLister(){
        mHomeAdapter.setOnItemClickListener(new HomeAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Toast.makeText(MainActivity.this, "点击第" + (position + 1) + "条", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(View view, final int position) {
                new AlertDialog.Builder(MainActivity.this)
                        .setTitle("确认删除吗？")
                        .setNegativeButton("取消", null)
                        .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialogInterface, int i) {
                                mHomeAdapter.removeData(position);
                            }
                        })
                        .show();
            }
        });
    }
}
