package com.hap.tablayout163;

import android.graphics.Color;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("Code");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mTabLayout = (TabLayout) findViewById(R.id.tabs);

        assignViews();

    }


    private void assignViews() {
        List<String> titles = new ArrayList<>();
        titles.add("头条");
        titles.add("娱乐");
        titles.add("热点");
        titles.add("体育");
        titles.add("网易号");
        titles.add("视频");
        titles.add("NBA");
        titles.add("励志");
        titles.add("图文");
        titles.add("财经");
        titles.add("科技");
        titles.add("东莞");
        titles.add("直播");

        for (int i = 0; i < titles.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setText(titles.get(i)));
        }
        List<Fragment> fragments = new ArrayList<>();
        for (int i = 0; i < titles.size(); i++) {
            fragments.add(new ListFragment());
        }
        FragmentAdapter adapter = new FragmentAdapter(getSupportFragmentManager(), fragments, titles);
        //给ViewPager设置适配器scrollable
        mViewPager.setAdapter(adapter);
//        adapter.notifyDataSetChanged();
//        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        //将TabLayout和ViewPager关联起来。
        mTabLayout.setupWithViewPager(mViewPager);
    }


}
