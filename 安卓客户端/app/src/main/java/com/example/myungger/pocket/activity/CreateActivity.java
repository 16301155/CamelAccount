package com.example.myungger.pocket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myungger.pocket.R;
import com.example.myungger.pocket.entity.QQLogin;
import com.example.myungger.pocket.entity.SynchronizeData;
import com.example.myungger.pocket.manager.NetTask;
import com.example.myungger.pocket.manager.RecordManager;
import com.example.myungger.pocket.manager.SynManager;
import com.example.myungger.pocket.util.ToastMessage;
import com.example.myungger.pocket.util.ToastUtil;
import com.tencent.tauth.UiError;

import org.json.JSONArray;
import org.json.JSONObject;
import org.litepal.LitePal;

public class CreateActivity extends BaseActivity implements QQLogin.QQLoginListener ,NetTask.NetListener {
    QQLogin qqLogin;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create);

        qqLogin = new QQLogin("1107957445",this,this);
        TextView textView_create_back = findViewById(R.id.textView_create_back);
        textView_create_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        ImageView imageView_qq = findViewById(R.id.imageView_qq);
        imageView_qq.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                qqLogin.launchQQLogin(CreateActivity.this);
            }
        });

        Button button_create = findViewById(R.id.button_create);
        button_create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateActivity.this, PhoneNumCreateActivity.class);
                startActivity(intent);
            }
        });

        Button button_login = findViewById(R.id.button_login);
        button_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CreateActivity.this, PhoneNumLoginActivity.class);
                startActivity(intent);
            }
        });
        Button button_zhuxiao = findViewById(R.id.button_zhuxiao);
        button_zhuxiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NetTask netTask = new NetTask(ActivityShare.url + "/logout",0,CreateActivity.this);
                netTask.execute();
            }
        });
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        qqLogin.registerResultListener(requestCode, resultCode, data);
    }

    @Override
    public void onQQLoginSuccess(JSONObject jsonObject) {
        ToastUtil.showToast(this, "登陆成功！");
        Intent intent = new Intent(CreateActivity.this, MainActivity.class);
        startActivity(intent);
        CreateActivity.this.finish();
    }

    @Override
    public void onQQLoginCancel() {
        ToastUtil.showToast(this, "取消登陆！");
    }

    @Override
    public void onQQLoginError(UiError uiError) {
        ToastUtil.showToast(this, "登陆失败！");
    }
    @Override
    public void onNetSuccess(JSONArray jsonData, int code, String message){
        RecordManager.deleteAll();
        //清空原有数据
        LitePal.deleteAll(SynchronizeData.class);
        ToastMessage.showToast(CreateActivity.this,message);
        ToastMessage.showToast(CreateActivity.this,"请放心，本账号数据已清除");
        CreateActivity.this.finish();
    }
    @Override
    public void onNetError(int errorCode, String errorMessage){
        ToastMessage.showToast(CreateActivity.this,errorMessage);
    }
}


