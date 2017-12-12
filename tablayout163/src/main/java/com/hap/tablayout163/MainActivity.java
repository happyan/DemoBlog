package com.hap.tablayout163;

import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private ViewPager mViewPager;
    private TabLayout mTabLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setTitle("happy code");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        final ActionBar ab = getSupportActionBar();
        ab.setHomeAsUpIndicator(R.mipmap.icon_menu);
        ab.setDisplayHomeAsUpEnabled(true);

        mViewPager =  findViewById(R.id.viewpager);
        mTabLayout = findViewById(R.id.tabs);
        mDrawerLayout =  findViewById(R.id.dl_main_drawer);
        NavigationView navigationView = findViewById(R.id.nv_main_navigation);
        if (navigationView != null) {//侧滑菜单监听
            navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
                        @Override
                        public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                            menuItem.setChecked(true);
                            mDrawerLayout.closeDrawers();
                            switch (menuItem.getItemId()){
                                case R.id.nav_home:
                                    showToast("首页");
                                    break;
                                case R.id.nav_messages:
                                    showToast("事项");
                                    break;
                            }
                            return true;
                        }
                    });
        }
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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                mDrawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void checkin(View view) {
        Snackbar.make(view, "点击成功", Snackbar.LENGTH_SHORT).show();
    }
    private void showToast(String txt){
        Toast.makeText(this,txt,Toast.LENGTH_SHORT).show();
    }
}
