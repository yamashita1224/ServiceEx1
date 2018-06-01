package jp.ac.titech.itpro.sdl.serviceex1;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private final static String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d(TAG, "onCreate in " + Thread.currentThread());
        setContentView(R.layout.activity_main);

    }

    public void pressTest1(View v) {
        testService1();
    }

    public void pressTest2(View v) {
        testService2();
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

}
