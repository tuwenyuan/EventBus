package com.twy.eventbus.main;

import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.twy.eventbus.listener.Subscribe;

public class BaseActivity extends AppCompatActivity {

    @Subscribe
    public void test(String str){
        Log.i("test:::::twy",str);
    }
}
