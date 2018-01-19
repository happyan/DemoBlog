package com.ishowtree.stickyheaderview;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        assignViews();
        initRecyclerView();
    }

    private Toolbar toolbar;
    private RecyclerView rvStickyExample;
    private FloatingActionButton fab;
    TextView tvStickyHeaderView;

    private void assignViews() {
        toolbar = findViewById(R.id.toolbar);
        rvStickyExample = findViewById(R.id.rv_sticky_example);
        fab = findViewById(R.id.fab);
        tvStickyHeaderView = findViewById(R.id.tv_sticky_header_view);
        toolbar.setTitle("Title");
        toolbar.setTitleTextColor(Color.parseColor("#ffffff"));
        setSupportActionBar(toolbar);
        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }


    private void initRecyclerView() {
        rvStickyExample.setLayoutManager(new LinearLayoutManager(this));
        rvStickyExample.setAdapter(new StickyExampleAdapter(this, DataUtil.getData()));
        rvStickyExample.addOnScrollListener(new RecyclerView.OnScrollListener() {
            /**
             回调的三个变量含义:
             recyclerView : 当前滚动的view
             dx : 水平滚动距离
             dy : 垂直滚动距离

             dx > 0 时为手指向左滚动,列表滚动显示右面的内容
             dx < 0 时为手指向右滚动,列表滚动显示左面的内容
             dy > 0 时为手指向上滚动,列表滚动显示下面的内容
             dy < 0 时为手指向下滚动,列表滚动显示上面的内容
             */
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);


                // 找到RecyclerView的item中，和RecyclerView的getTop 向下相距5个像素的那个item
                // (尝试2、3个像素位置都找不到，所以干脆用了5个像素)，
                // 我们根据这个item，来更新吸顶布局的内容，
                // 因为我们的StickyLayout展示的信息肯定是最上面的那个item的信息.
                View stickyInfoView = recyclerView.findChildViewUnder(tvStickyHeaderView.getMeasuredWidth() / 2, 5);

                if (stickyInfoView != null && stickyInfoView.getContentDescription() != null) {
                    tvStickyHeaderView.setText(String.valueOf(stickyInfoView.getContentDescription()));
                }

                // 找到固定在屏幕上方那个FakeStickyLayout下面一个像素位置的RecyclerView的item，
                // 我们根据这个item来更新假的StickyLayout要translate多少距离.
                // 并且只处理HAS_STICKY_VIEW和NONE_STICKY_VIEW这两种tag，
                // 因为第一个item的StickyLayout虽然展示，但是一定不会引起FakeStickyLayout的滚动.
                View transInfoView = recyclerView.findChildViewUnder(tvStickyHeaderView.getMeasuredWidth() / 2, tvStickyHeaderView.getMeasuredHeight() + 1);

                if (transInfoView != null && transInfoView.getTag() != null) {
                    int transViewStatus = (int) transInfoView.getTag();
                    int dealtY = transInfoView.getTop() - tvStickyHeaderView.getMeasuredHeight();

                    // 如果当前item需要展示StickyLayout，
                    // 那么根据这个item的getTop和FakeStickyLayout的高度相差的距离来滚动FakeStickyLayout.
                    // 这里有一处需要注意，如果这个item的getTop已经小于0，也就是滚动出了屏幕，
                    // 那么我们就要把假的StickyLayout恢复原位，来覆盖住这个item对应的吸顶信息.
                    if (transViewStatus == StickyExampleAdapter.HAS_STICKY_VIEW) {
                        if (transInfoView.getTop() > 0) {
                            tvStickyHeaderView.setTranslationY(dealtY);
                        } else {
                            tvStickyHeaderView.setTranslationY(0);
                        }
                    } else if (transViewStatus == StickyExampleAdapter.NONE_STICKY_VIEW) {
                        // 如果当前item不需要展示StickyLayout，那么就不会引起FakeStickyLayout的滚动.
                        tvStickyHeaderView.setTranslationY(0);
                    }
                }
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
