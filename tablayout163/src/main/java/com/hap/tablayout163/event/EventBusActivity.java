package com.hap.tablayout163.event;

import android.content.Context;
import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.hap.tablayout163.R;

import org.greenrobot.eventbus.EventBus;

public class EventBusActivity extends AppCompatActivity {

    Context context;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_bus);
        context=this;
        EventBus.getDefault().postSticky(new MessageEvent("Hello everyone!"));

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(context,EventBus2Activity.class));
                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {

//                        EventBus.getDefault().post(new MessageEvent("Hello EventBus!"));
                    }
                }, 2000);
            }
        });
    }
}
