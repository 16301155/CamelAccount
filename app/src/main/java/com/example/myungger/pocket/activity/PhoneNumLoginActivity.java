package com.example.myungger.pocket.activity;

import android.content.Intent;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.InputType;
import android.text.TextWatcher;
import android.text.method.HideReturnsTransformationMethod;
import android.text.method.PasswordTransformationMethod;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.ToggleButton;

import com.example.myungger.pocket.R;
import com.example.myungger.pocket.manager.NetTask;
import com.example.myungger.pocket.util.ToastMessage;
import com.example.myungger.pocket.util.ToastUtil;

import org.json.JSONArray;

import okhttp3.FormBody;
import okhttp3.RequestBody;

public class PhoneNumLoginActivity extends BaseActivity implements TextWatcher,NetTask.NetListener {
    private static final String TAG = "PhoneNumLoginActivity";

    private static final char SEPARATOR = '-';
    private static final int FIRST_SEPARATOR_POSITION = 3;
    private static final int SECOND_SEPARATOR_POSITION = 7;

    private ImageView imageView_login_clear;
    private EditText editText_phonenumlogin_num;
    private EditText editText_phonenumlogin_password;
    private Button button_phonenumlogin_login;
    private TextView textView_phonenumlogin_back;
    private CheckBox checkBox;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phonenumlogin);

        editText_phonenumlogin_num = findViewById(R.id.editText_phonenumlogin_num);
        editText_phonenumlogin_num.setInputType(EditorInfo.TYPE_CLASS_PHONE);
        editText_phonenumlogin_num.addTextChangedListener(this);

        editText_phonenumlogin_num.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    imageView_login_clear.setVisibility(View.VISIBLE);
                } else {
                    imageView_login_clear.setVisibility(View.GONE);
                }
            }
        });

        button_phonenumlogin_login = findViewById(R.id.button_phonenumlogin_login);
        button_phonenumlogin_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               if (editText_phonenumlogin_num.getEditableText().toString().length() < 13) {
                    ToastUtil.showToast(PhoneNumLoginActivity.this, "请输入正确的手机号格式");
                } else if (editText_phonenumlogin_password.getEditableText().toString().length() == 0){
                    ToastUtil.showToast(PhoneNumLoginActivity.this, "请输入密码");
                }else
               {
                   ActivityShare.phone_num = editText_phonenumlogin_num.getEditableText().toString();
                   ActivityShare.password = editText_phonenumlogin_password.getEditableText().toString();
                   NetTask netTask = new NetTask(ActivityShare.url + "/login",1, PhoneNumLoginActivity.this);
                   //Log.d("测试","调用网络");
                   netTask.execute(getRequestBody());
               }
            }
        });

        imageView_login_clear = findViewById(R.id.imageView_login_clear);
        imageView_login_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_phonenumlogin_num.setText(null);
            }
        });

        textView_phonenumlogin_back = findViewById(R.id.textView_phonenumlogin_back);
        textView_phonenumlogin_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneNumLoginActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });

        checkBox = findViewById(R.id.CheckBox);
        editText_phonenumlogin_password = findViewById(R.id.editText_phonenumlogin_password);
        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked){
                    //如果选中，显示密码
                    editText_phonenumlogin_password.setTransformationMethod(HideReturnsTransformationMethod.getInstance());
                }else{
                    //否则隐藏密码
                    editText_phonenumlogin_password.setTransformationMethod(PasswordTransformationMethod.getInstance());
                }
            }
        });
    }

    @Override
    public void afterTextChanged(Editable aEditable) {
        Log.d(TAG, "afterTextChanged: " + aEditable.toString());
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        Log.d(TAG, "beforeTextChanged: " + s + ", " + start + ", " + after + ", " + count);
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        Log.d(TAG, "onTextChanged: " + s + ", " + start + ", " + before + ", " + count);

        Editable phoneNumberEditable = editText_phonenumlogin_num.getEditableText();

        if (before == 1) {
            if ((start == FIRST_SEPARATOR_POSITION) || (start == (SECOND_SEPARATOR_POSITION + 1))) {
                return;
            }
        }

        switch (parsePhoneNumber(phoneNumberEditable.toString())) {
            case 1:
                int oneInvalidSeparatorIndex = getOneInvalidSeparatorIndex(phoneNumberEditable.toString());
                phoneNumberEditable.delete(oneInvalidSeparatorIndex, oneInvalidSeparatorIndex + 1);//删除该“-”
                break;

            case 2:
                phoneNumberEditable.insert(FIRST_SEPARATOR_POSITION, String.valueOf(SEPARATOR));
                break;

            case 3:
                phoneNumberEditable.insert(SECOND_SEPARATOR_POSITION + 1, String.valueOf(SEPARATOR));
                break;

            case 4:
                phoneNumberEditable.delete(phoneNumberEditable.length() - 1, phoneNumberEditable.length());
                break;

            case -1:
            case 0:
            default:
                break;
        }
    }

    private int parsePhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return -1;
        }

        if (getOneInvalidSeparatorIndex(phoneNumber) != -1) {//除index = 3和8是“-”以外，其他位置有“-”时，按1处理
            return 1;
        }

        if ((phoneNumber.length() > FIRST_SEPARATOR_POSITION)//字符数超3个，同时index=3的字符不是“-”，则按2来处理
                && (phoneNumber.charAt(FIRST_SEPARATOR_POSITION) != SEPARATOR)) {
            return 2;
        }
        if ((phoneNumber.length() > (SECOND_SEPARATOR_POSITION + 1))//字符数超8个，同时index=8的字符不是“-”，则按3来处理
                && (phoneNumber.charAt(SECOND_SEPARATOR_POSITION + 1) != SEPARATOR)) {
            return 3;
        }

        if(phoneNumber.length()>13)//超过长度，按4处理
        {
            return 4;
        }

        return 0;
    }

    /**
     * 除index = 3和8是“-”以外，其他位置有“-”时，返回该index
     * @param phoneNumber
     * @return
     */

    private int getOneInvalidSeparatorIndex(String phoneNumber) {
        if (phoneNumber == null) {
            return -1;
        }

        for (int index = 0; index < phoneNumber.length(); index++) {
            if ((index == FIRST_SEPARATOR_POSITION) || (index == (SECOND_SEPARATOR_POSITION + 1))) {
                continue;
            }

            if (phoneNumber.charAt(index) == SEPARATOR) {
                return index;
            }
        }

        return -1;
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

    public void onNetSuccess(JSONArray jsonData, int code,String message){
        ToastMessage.showToast(PhoneNumLoginActivity.this,message);
        Intent intent = new Intent(PhoneNumLoginActivity.this, MainActivity.class);
        startActivity(intent);
        PhoneNumLoginActivity.this.finish();
    }
    @Override
    public void onNetError(int errorCode, String errorMessage){
        ToastMessage.showToast(PhoneNumLoginActivity.this,errorMessage);
    }
}
