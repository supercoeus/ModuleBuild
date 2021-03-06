package com.qianmi.modulebuildapk;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.qianmi.baselibrary.AppConfig;
import com.qianmi.baselibrary.BaseActivity;
import com.qianmi.baselibrary.utils.ToastUtil;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;


public class MainActivity extends BaseActivity {


    @Bind(R.id.btnOrderManager)
    Button btnOrderManager;
    @Bind(R.id.btnPointManager)
    Button btnPointManager;
    @Bind(R.id.tip)
    TextView tip;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btnOrderManager, R.id.btnPointManager})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btnOrderManager:
//                startActivityForResult(new Intent(MainActivity.this, OrderActivity.class), 1);
                Intent intent = getStartActivityIntent(AppConfig.OrderManagerModuleActivity);
                try {
                    startActivityForResult(intent, 1);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                    ToastUtil.showToast(getApplicationContext(), "该功能尚未开放");
                }
                break;
            case R.id.btnPointManager:
                Intent intent2 = getStartActivityIntent(AppConfig.PointManagerModuleActivity);
                try {
                    startActivityForResult(intent2, 1);
                } catch (ActivityNotFoundException e) {
                    e.printStackTrace();
                    ToastUtil.showToast(getApplicationContext(), "该功能尚未开放");
                }
                break;
        }
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (data.hasExtra(AppConfig.ORDER_INTENT_VALUE)) {
                tip.setText(data.getStringExtra(AppConfig.ORDER_INTENT_VALUE));
            }
        }
    }
}
