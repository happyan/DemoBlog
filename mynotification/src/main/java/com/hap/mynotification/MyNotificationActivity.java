package com.hap.mynotification;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RemoteViews;
import android.widget.TextView;

public class MyNotificationActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView tvNomal;
    private TextView tvFold;
    private TextView tvHang;
    private RadioGroup rgAll;
    private RadioButton rbPublic;
    private RadioButton rbPrivate;
    private RadioButton rbSecret;

    private NotificationManager notificationManager;
    private void assignViews() {
        tvNomal = (TextView) findViewById(R.id.tv_nomal);
        tvFold = (TextView) findViewById(R.id.tv_fold);
        tvHang = (TextView) findViewById(R.id.tv_hang);
        rgAll = (RadioGroup) findViewById(R.id.rg_all);
        rbPublic = (RadioButton) findViewById(R.id.rb_public);
        rbPrivate = (RadioButton) findViewById(R.id.rb_private);
        rbSecret = (RadioButton) findViewById(R.id.rb_secret);
        tvNomal.setOnClickListener(this);
        tvFold.setOnClickListener(this);
        tvHang.setOnClickListener(this);
        notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_notification);
        assignViews();
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void selectNotofovatiomLevel(Notification.Builder builder) {
        switch (rgAll.getCheckedRadioButtonId()) {
            case R.id.rb_public:
                builder.setVisibility(Notification.VISIBILITY_PUBLIC);
                builder.setContentText("public");
                break;
            case R.id.rb_private:
                builder.setVisibility(Notification.VISIBILITY_PRIVATE);
                builder.setContentText("private");
                break;
            case R.id.rb_secret:
                builder.setVisibility(Notification.VISIBILITY_SECRET);
                builder.setContentText("secret");
                break;
            default:
                builder.setVisibility(Notification.VISIBILITY_PUBLIC);
                builder.setContentText("public");
                break;
        }
    }

    /**
     * 普通通知
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void sendNomalNotification() {
        Notification.Builder builder = new Notification.Builder(this);//为构建通知的参数和设置属性做准备
        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/happy1223247773"));//初始化意图
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.foldlef);//设置小图标，就是没拉开通知栏时看到的小图标
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));//设置大图标
        builder.setAutoCancel(true);//点击通知后自动清除
        builder.setContentTitle("内容：点击打开我的博客");//通知内容
        selectNotofovatiomLevel(builder);
        notificationManager.notify(0, builder.build());//通过builder.build()方法生成Notification对象,并发送通知,id=0

    }

    /**
     * 折叠式通知
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void sendFoldNotification() {
        Notification.Builder builder = new Notification.Builder(this);
        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/happy1223247773"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.foldlef);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setAutoCancel(true);
        builder.setContentTitle("折叠式通知");
        selectNotofovatiomLevel(builder);
        //用RemoteViews来创建自定义Notification视图
        RemoteViews remoteViews = new RemoteViews(getPackageName(), R.layout.fold_view);
        Notification notification = builder.build();
        //指定展开时的视图
        notification.bigContentView = remoteViews;
        notificationManager.notify(1, notification);
    }

    /**
     * 悬挂式通知
     */
    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    private void sendHangNotification() {
        Notification.Builder builder = new Notification.Builder(this);
        Intent mIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://blog.csdn.net/happy1223247773"));
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, mIntent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setSmallIcon(R.mipmap.foldlef);
        builder.setLargeIcon(BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher));
        builder.setAutoCancel(true);
        builder.setContentTitle("悬挂式通知");
        selectNotofovatiomLevel(builder);
        //设置点击跳转
        Intent hangIntent = new Intent();
        hangIntent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        hangIntent.setClass(this, MyNotificationActivity.class);
        //如果描述的PendingIntent已经存在，则在产生新的Intent之前会先取消掉当前的
        PendingIntent hangPendingIntent = PendingIntent.getActivity(this, 0, hangIntent, PendingIntent.FLAG_CANCEL_CURRENT);
        builder.setFullScreenIntent(hangPendingIntent, true);

        notificationManager.notify(2, builder.build());
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_nomal:
                sendNomalNotification();
                break;
            case R.id.tv_fold:
                sendFoldNotification();
                break;

            case R.id.tv_hang:
                sendHangNotification();
                break;
        }
    }
}
