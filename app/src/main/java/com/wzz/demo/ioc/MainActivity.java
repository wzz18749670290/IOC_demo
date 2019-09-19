package com.wzz.demo.ioc;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.wzz.demo.ioc.annotation.ContentView;
import com.wzz.demo.ioc.annotation.OnClick;
import com.wzz.demo.ioc.annotation.OnLongClick;
import com.wzz.demo.ioc.annotation.ViewID;
import com.wzz.demo.ioc.inject.InjectUtils;

@ContentView(R.layout.activity_main)
public class MainActivity extends AppCompatActivity {
    @ViewID(R.id.tv)
    private Button mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        InjectUtils.inject(this);
    }

    @OnClick(R.id.tv)
    public void onClick(View view) {
        Toast.makeText(this, "view点击了", Toast.LENGTH_SHORT).show();
    }

    @OnLongClick(R.id.tv)
    public boolean onLongClick(View view) {
        Toast.makeText(this, "view长按了", Toast.LENGTH_SHORT).show();
        return false;
    }

//    @OnTouch(R.id.tv)
    public boolean onClick(View view, MotionEvent event) {
        Toast.makeText(this, "view触摸了", Toast.LENGTH_SHORT).show();
        return false;
    }
}
