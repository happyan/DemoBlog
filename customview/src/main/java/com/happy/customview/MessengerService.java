package com.happy.customview;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.util.Log;

/**
 * Created by showhome002 on 2017/11/30.
 */

public class MessengerService extends Service {

    public static final String TAG = "JiaLe";
    public static final int MSG_FROMCLIENT=1000;
    @SuppressLint("HandlerLeak")
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case MSG_FROMCLIENT:
                    Log.i(TAG,"收到客户端信息------- "+msg.getData().get("msg"));

                    Messenger messenger = msg.replyTo;//接收主进程传过来的Messenger对象
                    Message message = Message.obtain(null, MessengerService.MSG_FROMCLIENT);
                    Bundle bundle= new Bundle();
                    bundle.putString("rep" , "This is the server. We got the message ");
                    message.setData(bundle);
                    try {
                        messenger.send(message);//发送消息给主进程
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                    break;
            }
        }
    };

    @Override
    public IBinder onBind(Intent intent) {
        return new Messenger(mHandler).getBinder();
    }
}
