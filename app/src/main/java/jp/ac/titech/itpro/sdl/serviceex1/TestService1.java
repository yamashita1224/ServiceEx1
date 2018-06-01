package jp.ac.titech.itpro.sdl.serviceex1;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class TestService1 extends Service {
    private final static String TAG = "TestService1";
    public final static String EXTRA_MYARG = "MYARG";
    private final static int MESSAGE_TAG = 1234;

    private Looper looper;
    private Handler handler;

    @Override
    public void onCreate() {
        Log.d(TAG, "onCreate in " + Thread.currentThread());
        HandlerThread th = new HandlerThread("service1 thread");
        th.start();
        looper = th.getLooper();
        handler = new Handler(looper) {
            @Override
            public void handleMessage(Message message) {
                Log.d(TAG, "handleMessage in " + Thread.currentThread());
                onHandleIntent((Intent)message.obj);
                stopSelf(message.arg1);
            }
        };
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG, "onStartCommand in " + Thread.currentThread());
        Message message = handler.obtainMessage(MESSAGE_TAG, startId, 0, intent);
        handler.sendMessage(message);
        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        Log.d(TAG, "onDestroy in " + Thread.currentThread());
        looper.quit();
        super.onDestroy();
    }

    protected void onHandleIntent(Intent intent) {
        Log.d(TAG, "onHandleIntent in " + Thread.currentThread());
        Log.d(TAG, "myarg = " + intent.getStringExtra(EXTRA_MYARG));
        try {
            Thread.sleep(5000);
        }
        catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }
}
