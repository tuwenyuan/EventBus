package com.twy.eventbus;

import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.twy.eventbus.listener.Subscribe;
import com.twy.eventbus.listener.ThreadMode;
import com.twy.eventbus.main.BaseActivity;
import com.twy.eventbus.main.EventBus;
import com.twy.eventbus.main.ThreadPoolManager;

public class MainActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EventBus.getDefault().register(this);
    }
}
