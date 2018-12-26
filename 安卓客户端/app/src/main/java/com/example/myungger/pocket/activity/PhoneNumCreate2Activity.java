package com.example.myungger.pocket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myungger.pocket.R;
import com.example.myungger.pocket.manager.NetTask;
import com.example.myungger.pocket.util.ToastMessage;
import com.example.myungger.pocket.util.ToastUtil;

import org.json.JSONArray;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class PhoneNumCreate2Activity extends BaseActivity implements NetTask.NetListener {
    private EditText editText_phonenumcreate2_password;
    private EditText editText_phonenumcreate2_sure;
    private CheckBox checkBox2;
    private Button button_phonenumcreate2;
    private ImageView imageView_phonenumcreate2_back;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phonenumcreate2);

        checkBox2 = findViewById(R.id.CheckBox2);
        editText_phonenumcreate2_password = findViewById(R.id.editText_phonenumcreate2_password);
        editText_phonenumcreate2_sure = findViewById(R.id.editText_phonenumcreate2_sure);
        checkBox2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //如果选中，显示密码
                    editText_phonenumcreate2_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                    editText_phonenumcreate2_sure.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    //否则隐藏密码
                    editText_phonenumcreate2_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                    editText_phonenumcreate2_sure.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });

        button_phonenumcreate2 = findViewById(R.id.button_phonenumcreate2);
        button_phonenumcreate2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_phonenumcreate2_password.getEditableText().toString().equals(editText_phonenumcreate2_sure.getEditableText().toString())) {
                    ActivityShare.password = editText_phonenumcreate2_password.getEditableText().toString();
                    NetTask netTask = new NetTask(ActivityShare.url + "/register",1, PhoneNumCreate2Activity.this);
                    //Log.d("测试","调用网络");
                    netTask.execute(getRequestBody());
                }
                else
                    ToastMessage.showToast(PhoneNumCreate2Activity.this,"请确认输入的密码是否一致");
            }
        });

        imageView_phonenumcreate2_back = findViewById(R.id.imageView_phonenumcreate2_back);
        imageView_phonenumcreate2_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneNumCreate2Activity.this, PhoneNumCreateActivity.class);
                startActivity(intent);

            }
        });
    }

    public static RequestBody getRequestBody() {
        RequestBody requestBody;
        //Log.d("测试","生成请求体");
        Log.d("电话请求体",ActivityShare.password);
        requestBody = new FormBody.Builder()
                .add("phone_number", ActivityShare.phone_num)
                .add("password", ActivityShare.password)
                .build();
        return requestBody;
    }

    @Override
    public void onNetSuccess(JSONArray jsonData, int code, String message){
        ToastMessage.showToast(PhoneNumCreate2Activity.this,message);
        Intent intent = new Intent(PhoneNumCreate2Activity.this, CreateActivity.class);
        startActivity(intent);
        PhoneNumCreate2Activity.this.finish();
    }
    @Override
    public void onNetError(int errorCode, String errorMessage){
        ToastMessage.showToast(PhoneNumCreate2Activity.this,errorMessage);
    }
}
