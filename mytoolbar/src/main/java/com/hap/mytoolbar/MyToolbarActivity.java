package com.hap.mytoolbar;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.graphics.Palette;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MyToolbarActivity extends AppCompatActivity {
    private DrawerLayout mDrawerLayout;
    private LinearLayout llContent;
    private LinearLayout llTabs;
    private TextView tvClose;
    private Toolbar mToolbar;
    private ActionBarDrawerToggle mDrawerToggle;

    private void assignViews() {
        mDrawerLayout = (DrawerLayout) findViewById(R.id.id_drawerlayout);
        llContent = (LinearLayout) findViewById(R.id.ll_content);
        llTabs = (LinearLayout) findViewById(R.id.ll_tabs);
        tvClose = (TextView) findViewById(R.id.tv_close);
        mToolbar= (Toolbar) this.findViewById(R.id.toolbar);


//        mToolbar.setTitle("ToolBar Title");//设置标题
//        mToolbar.setSubtitle("This is subtitle");//设置子标题
//        mToolbar.setTitleTextColor(Color.parseColor("#ff0000"));//设置标题颜色
//        mToolbar.setLogo(R.drawable.ic_launcher);
//        mToolbar.setNavigationIcon(drawable);//设置导航按钮

        mToolbar.setTitle("Title");
        mToolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(mToolbar);
        //是否给左上角图标的左边加上一个返回的图标
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setLogo(R.mipmap.ic_launcher_round);//设置logo图片
/*
        mToolbar.setOnMenuItemClickListener(new Toolbar.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.action_settings:
                        Toast.makeText(MyToolbarActivity.this, "设置", Toast.LENGTH_SHORT).show();
                        break;
                    case R.id.action_share:
                        Toast.makeText(MyToolbarActivity.this, "分享事件", Toast.LENGTH_SHORT).show();
                        break;
                    default:
                        break;
                }
                return true;
            }


        });*/

        //设置侧或布局
        mDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.drawer_open,
                R.string.drawer_close);
        mDrawerToggle.syncState();
        mDrawerLayout.addDrawerListener(mDrawerToggle);//设置点击左边按钮打开侧边栏
        tvClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mDrawerLayout.closeDrawer(Gravity.LEFT);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_toolbar);
        assignViews();
        //使用Patette，
        setPatette();
    }


    private void setPatette() {
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.mipmap.mm);
        Palette.from(bitmap).generate(new Palette.PaletteAsyncListener() {
            @Override
            public void onGenerated(Palette palette) {
                Palette.Swatch swatch=palette.getVibrantSwatch();
                getSupportActionBar().setBackgroundDrawable(new ColorDrawable(swatch.getRgb()));

            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_settings:
//                Toast.makeText(MyToolbarActivity.this, "设置", Toast.LENGTH_SHORT).show();
               startActivity( new Intent(this,MyTabLayoutActivity.class));
                break;
            case R.id.action_share:
                Toast.makeText(MyToolbarActivity.this, "分享事件", Toast.LENGTH_SHORT).show();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }
}
