package com.hap.mycardview;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.widget.SeekBar;

public class CardViewActivity extends AppCompatActivity {
    private CardView mCardView;
    private SeekBar sb1;
    private SeekBar sb2;
    private SeekBar sb3;

    private void assignViews() {
        mCardView = (CardView) findViewById(R.id.tv_item);
        sb1 = (SeekBar) findViewById(R.id.sb_1);
        sb2 = (SeekBar) findViewById(R.id.sb_2);
        sb3 = (SeekBar) findViewById(R.id.sb_3);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_card_view);
        assignViews();


        //给SeekBar设置滑动监听
        sb1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mCardView.setRadius(i);//设置卡片圆角
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


        sb2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mCardView.setCardElevation(i);//设置卡片阴影大小
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
        sb3.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                mCardView.setContentPadding(i,i,i,i);//设置卡片和里面内容的间距
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });
    }
}
