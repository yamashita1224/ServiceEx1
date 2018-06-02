package jp.ac.titech.itpro.sdl.serviceex1;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import static jp.ac.titech.itpro.sdl.serviceex1.TestService3.EXTRA_MYARG;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";
    private BroadcastReceiver service3Reciever;
    private IntentFilter service3Filter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate in " + Thread.currentThread());
        setContentView(R.layout.activity_main);

        service3Reciever = new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                Log.d(TAG, "onReceive: " + action);

                if (action == null) return;
                if(action.equals(TestService3.TAG)){
                    Log.d(TAG, "match broadcast" );
                }
                switch (action) {
                    case TestService3.TAG:
                        Log.d(TAG, "onHandleBroadcast in " + Thread.currentThread());
                        Log.d(TAG, "myarg = " + intent.getStringExtra(EXTRA_MYARG));
                        Toast.makeText(context, "myarg="+intent.getStringExtra(EXTRA_MYARG), Toast.LENGTH_SHORT).show();
                        /*try {
                            Thread.sleep(5000);
                        }
                        catch (InterruptedException e) {
                            e.printStackTrace();
                        }*/
                        break;
                }
            }
        };
        service3Filter = new IntentFilter();
        service3Filter.addAction(TestService3.TAG);
        registerReceiver(service3Reciever, service3Filter);
    }

    public void pressTest1(View v) {
        testService1();
    }

    public void pressTest2(View v) {
        testService2();
    }

    public void pressTest3(View v) {
        testService3();
    }

    private void testService1() {
        Log.d(TAG, "testService1 in " + Thread.currentThread());
        Intent intent = new Intent(this, TestService1.class);
        intent.putExtra(TestService1.EXTRA_MYARG, "Hello, Service1");
        startService(intent);
    }

    private void testService2() {
        Log.d(TAG, "testService2 in " + Thread.currentThread());
        Intent intent = new Intent(this, TestService2.class);
        intent.putExtra(TestService2.EXTRA_MYARG, "Hello, Service2");
        startService(intent);
    }

    private void testService3() {
        Log.d(TAG, "testService3 in " + Thread.currentThread());
        Intent intent = new Intent(this, TestService3.class);
        intent.putExtra(TestService3.EXTRA_MYARG, "Hello, Service3");
        startService(intent);


        Log.d(TAG, "testService3 tag : " + TestService3.TAG);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume");
        registerReceiver(service3Reciever, service3Filter);
    }

    @Override
    protected void onDestroy() {
        unregisterReceiver(service3Reciever);
        super.onDestroy();
    }

}
