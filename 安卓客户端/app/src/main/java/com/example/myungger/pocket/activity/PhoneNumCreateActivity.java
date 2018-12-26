package com.example.myungger.pocket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.myungger.pocket.R;
import com.example.myungger.pocket.util.ToastUtil;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PhoneNumCreateActivity extends BaseActivity implements TextWatcher {
    private static final String TAG = "PhoneNumCreateActivity";

    private static final char SEPARATOR = '-';
    private static final int FIRST_SEPARATOR_POSITION = 3;
    private static final int SECOND_SEPARATOR_POSITION = 7;

    private EditText editText_phonenumcreate;
    private Button button_phonenumcreate;
    private ImageView imageView_clear;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.phonenumcreate);

        ImageView imageView_phonenumcreate_back = findViewById(R.id.imageView_phonenumcreate2_back);
        imageView_phonenumcreate_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PhoneNumCreateActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });

        editText_phonenumcreate = findViewById(R.id.editText_phonenumcreate2_password);
        editText_phonenumcreate.addTextChangedListener(this);
        editText_phonenumcreate.setInputType(EditorInfo.TYPE_CLASS_PHONE);

        editText_phonenumcreate.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i(TAG, s.length() + "");
                if (s.length() > 12) {
                    button_phonenumcreate.setEnabled(true);
                } else {
                    button_phonenumcreate.setEnabled(false);
                }
            }

            @Override
            public void afterTextChanged(Editable s) {
                if (s.length() > 0) {
                    imageView_clear.setVisibility(View.VISIBLE);
                } else {
                    imageView_clear.setVisibility(View.GONE);
                }
            }
        });

        button_phonenumcreate = findViewById(R.id.button_phonenumcreate);
        button_phonenumcreate.setEnabled(false);
        button_phonenumcreate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (isPhoneNumber(editText_phonenumcreate.getEditableText().toString())) {
                    ActivityShare.phone_num = editText_phonenumcreate.getEditableText().toString();
                    Log.d("电话",ActivityShare.phone_num);
                    Intent intent = new Intent(PhoneNumCreateActivity.this, PhoneNumCreate2Activity.class);
                    startActivity(intent);

                } else {
                    ToastUtil.showToast(PhoneNumCreateActivity.this, "请输入正确的手机号格式");
                }
            }
        });

        imageView_clear = findViewById(R.id.imageView_clear);
        imageView_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                editText_phonenumcreate.setText(null);
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

        Editable phoneNumberEditable = editText_phonenumcreate.getEditableText();

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


    private boolean isPhoneNumber(String phoneNumber) {
        String regEx = "[^0-9]";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(phoneNumber);

        String string = m.replaceAll(" ").trim();
        String[] strArr = string.split(" ");

        if (strArr[0].equals("130") || strArr[0].equals("131") || strArr[0].equals("132")
                || strArr[0].equals("133") || strArr[0].equals("135") || strArr[0].equals("136")
                || strArr[0].equals("137") || strArr[0].equals("138") || strArr[0].equals("139")
                || strArr[0].equals("145") || strArr[0].equals("147") || strArr[0].equals("150")
                || strArr[0].equals("151") || strArr[0].equals("152") || strArr[0].equals("153")
                || strArr[0].equals("155") || strArr[0].equals("156") || strArr[0].equals("157")
                || strArr[0].equals("158") || strArr[0].equals("159") || strArr[0].equals("173")
                || strArr[0].equals("176") || strArr[0].equals("177") || strArr[0].equals("178")
                || strArr[0].equals("180") || strArr[0].equals("181") || strArr[0].equals("182")
                || strArr[0].equals("183") || strArr[0].equals("184") || strArr[0].equals("185")
                || strArr[0].equals("186") || strArr[0].equals("187") || strArr[0].equals("188")
                || strArr[0].equals("139"))
            return true;
        else
            return false;
    }
}
