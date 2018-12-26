package com.example.myungger.pocket.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.InputFilter;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.myungger.pocket.R;
import com.example.myungger.pocket.activity.ActivityShare;
import com.example.myungger.pocket.activity.MainActivity;
import com.example.myungger.pocket.entity.Record;
import com.example.myungger.pocket.manager.ManageFlag;
import com.example.myungger.pocket.manager.RecordManager;
import com.example.myungger.pocket.util.ToastUtil;

import java.util.Date;

public class OutcomeFragment extends Fragment {
    public static String temp;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.record_outcome, null);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final EditText editText_canyin = getView().findViewById(R.id.editText_canyin);
        final EditText editText_gouwu = getView().findViewById(R.id.editText_gouwu);
        final EditText editText_riyong = getView().findViewById(R.id.editText_riyong);
        final EditText editText_jiaotong = getView().findViewById(R.id.editText_jiaotong);
        final EditText editText_shucai = getView().findViewById(R.id.editText_shucai);
        final EditText editText_shuiguo = getView().findViewById(R.id.editText_shuiguo);
        final EditText editText_lingshi = getView().findViewById(R.id.editText_lingshi);
        final EditText editText_yundong = getView().findViewById(R.id.editText_yundong);
        final EditText editText_yule = getView().findViewById(R.id.editText_yule);
        final EditText editText_tongxun = getView().findViewById(R.id.editText_tongxun);
        final EditText editText_fushi = getView().findViewById(R.id.editText_fushi);
        final EditText editText_zhufang = getView().findViewById(R.id.editText_zhufang);
        final EditText editText_shejiao = getView().findViewById(R.id.editText_shejiao);
        final EditText editText_lvxing = getView().findViewById(R.id.editText_lvxing);
        final EditText editText_qita = getView().findViewById(R.id.editText_qita);

        InputFilter[] filters={new EditInputFilter()};

        editText_canyin.setFilters(filters);
        editText_gouwu.setFilters(filters);
        editText_riyong.setFilters(filters);
        editText_jiaotong.setFilters(filters);
        editText_shucai.setFilters(filters);
        editText_shuiguo.setFilters(filters);
        editText_lingshi.setFilters(filters);
        editText_yundong.setFilters(filters);
        editText_yule.setFilters(filters);
        editText_tongxun.setFilters(filters);
        editText_fushi.setFilters(filters);
        editText_zhufang.setFilters(filters);
        editText_shejiao.setFilters(filters);
        editText_lvxing.setFilters(filters);
        editText_qita.setFilters(filters);

        Button button_outcome_canyin = getView().findViewById(R.id.button_outcome_canyin);
        Button button_outcome_gouwu = getView().findViewById(R.id.button_outcome_gouwu);
        Button button_outcome_riyong = getView().findViewById(R.id.button_outcome_riyong);
        Button button_outcome_jiaotong = getView().findViewById(R.id.button_outcome_jiaotong);
        Button button_outcome_shucai = getView().findViewById(R.id.button_outcome_shucai);
        Button button_outcome_shuiguo = getView().findViewById(R.id.button_outcome_shuiguo);
        Button button_outcome_lingshi = getView().findViewById(R.id.button_outcome_lingshi);
        Button button_outcome_yundong = getView().findViewById(R.id.button_outcome_yundong);
        Button button_outcome_yule = getView().findViewById(R.id.button_outcome_yule);
        Button button_outcome_tongxun = getView().findViewById(R.id.button_outcome_tongxun);
        Button button_outcome_fushi = getView().findViewById(R.id.button_outcome_fushi);
        Button button_outcome_zhufang = getView().findViewById(R.id.button_outcome_zhufang);
        Button button_outcome_shejiao = getView().findViewById(R.id.button_outcome_shejiao);
        Button button_outcome_lvxing = getView().findViewById(R.id.button_outcome_lvxing);
        Button button_outcome_qita = getView().findViewById(R.id.button_outcome_qita);

        button_outcome_canyin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ActivityShare.instance != null) {//给一下判空
                    ActivityShare.instance.finish();//在其它的activity里面使用
                }
                if (editText_canyin.length() == 0)
                    ToastUtil.showToast(getActivity(), "请输入金额！");
                else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Record record = new Record();
                    record.setFlag(ManageFlag.GET_OUT);
                    record.setCategory("餐饮");
                    record.setMoney(Float.parseFloat(editText_canyin.getText().toString()));
                    record.setRecord_date(new Date());
                    RecordManager.saveRecord(record);
                    startActivity(intent);
                }
            }
        });
        button_outcome_gouwu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityShare.instance != null) {//给一下判空
                    ActivityShare.instance.finish();//在其它的activity里面使用
                }
                if (editText_gouwu.length() == 0)
                    ToastUtil.showToast(getActivity(), "请输入金额！");
                else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Record record = new Record();
                    record.setFlag(ManageFlag.GET_OUT);
                    record.setCategory("购物");
                    record.setMoney(Float.parseFloat(editText_gouwu.getText().toString()));
                    record.setRecord_date(new Date());
                    RecordManager.saveRecord(record);
                    startActivity(intent);
                }
            }
        });
        button_outcome_riyong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityShare.instance != null) {//给一下判空
                    ActivityShare.instance.finish();//在其它的activity里面使用
                }
                if (editText_riyong.length() == 0)
                    ToastUtil.showToast(getActivity(), "请输入金额！");
                else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Record record = new Record();
                    record.setFlag(ManageFlag.GET_OUT);
                    record.setCategory("日用");
                    record.setMoney(Float.parseFloat(editText_riyong.getText().toString()));
                    record.setRecord_date(new Date());
                    RecordManager.saveRecord(record);
                    startActivity(intent);
                }
            }
        });
        button_outcome_jiaotong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityShare.instance != null) {//给一下判空
                    ActivityShare.instance.finish();//在其它的activity里面使用
                }
                if (editText_jiaotong.length() == 0)
                    ToastUtil.showToast(getActivity(), "请输入金额！");
                else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Record record = new Record();
                    record.setFlag(ManageFlag.GET_OUT);
                    record.setCategory("交通");
                    record.setMoney(Float.parseFloat(editText_jiaotong.getText().toString()));
                    record.setRecord_date(new Date());
                    RecordManager.saveRecord(record);
                    startActivity(intent);
                }
            }
        });
        button_outcome_shucai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityShare.instance != null) {//给一下判空
                    ActivityShare.instance.finish();//在其它的activity里面使用
                }
                if (editText_shucai.length() == 0)
                    ToastUtil.showToast(getActivity(), "请输入金额！");
                else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Record record = new Record();
                    record.setFlag(ManageFlag.GET_OUT);
                    record.setCategory("蔬菜");
                    record.setMoney(Float.parseFloat(editText_shucai.getText().toString()));
                    record.setRecord_date(new Date());
                    RecordManager.saveRecord(record);
                    startActivity(intent);
                }
            }
        });
        button_outcome_shuiguo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityShare.instance != null) {//给一下判空
                    ActivityShare.instance.finish();//在其它的activity里面使用
                }
                if (editText_shuiguo.length() == 0)
                    ToastUtil.showToast(getActivity(), "请输入金额！");
                else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Record record = new Record();
                    record.setFlag(ManageFlag.GET_OUT);
                    record.setCategory("水果");
                    record.setMoney(Float.parseFloat(editText_shuiguo.getText().toString()));
                    record.setRecord_date(new Date());
                    RecordManager.saveRecord(record);
                    startActivity(intent);
                }
            }
        });
        button_outcome_lingshi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityShare.instance != null) {//给一下判空
                    ActivityShare.instance.finish();//在其它的activity里面使用
                }
                if (editText_lingshi.length() == 0)
                    ToastUtil.showToast(getActivity(), "请输入金额！");
                else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Record record = new Record();
                    record.setFlag(ManageFlag.GET_OUT);
                    record.setCategory("零食");
                    record.setMoney(Float.parseFloat(editText_lingshi.getText().toString()));
                    record.setRecord_date(new Date());
                    RecordManager.saveRecord(record);
                    startActivity(intent);
                }
            }
        });
        button_outcome_yundong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityShare.instance != null) {//给一下判空
                    ActivityShare.instance.finish();//在其它的activity里面使用
                }
                if (editText_yundong.length() == 0)
                    ToastUtil.showToast(getActivity(), "请输入金额！");
                else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Record record = new Record();
                    record.setFlag(ManageFlag.GET_OUT);
                    record.setCategory("运动");
                    record.setMoney(Float.parseFloat(editText_yundong.getText().toString()));
                    record.setRecord_date(new Date());
                    RecordManager.saveRecord(record);
                    startActivity(intent);
                }
            }
        });
        button_outcome_yule.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityShare.instance != null) {//给一下判空
                    ActivityShare.instance.finish();//在其它的activity里面使用
                }
                if (editText_yule.length() == 0)
                    ToastUtil.showToast(getActivity(), "请输入金额！");
                else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Record record = new Record();
                    record.setFlag(ManageFlag.GET_OUT);
                    record.setCategory("娱乐");
                    record.setMoney(Float.parseFloat(editText_yule.getText().toString()));
                    record.setRecord_date(new Date());
                    RecordManager.saveRecord(record);
                    startActivity(intent);
                }
            }
        });
        button_outcome_tongxun.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityShare.instance != null) {//给一下判空
                    ActivityShare.instance.finish();//在其它的activity里面使用
                }
                if (editText_tongxun.length() == 0)
                    ToastUtil.showToast(getActivity(), "请输入金额！");
                else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Record record = new Record();
                    record.setFlag(ManageFlag.GET_OUT);
                    record.setCategory("通讯");
                    record.setMoney(Float.parseFloat(editText_tongxun.getText().toString()));
                    record.setRecord_date(new Date());
                    RecordManager.saveRecord(record);
                    startActivity(intent);
                }
            }
        });
        button_outcome_fushi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityShare.instance != null) {//给一下判空
                    ActivityShare.instance.finish();//在其它的activity里面使用
                }
                if (editText_fushi.length() == 0)
                    ToastUtil.showToast(getActivity(), "请输入金额！");
                else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Record record = new Record();
                    record.setFlag(ManageFlag.GET_OUT);
                    record.setCategory("服饰");
                    record.setMoney(Float.parseFloat(editText_fushi.getText().toString()));
                    record.setRecord_date(new Date());
                    RecordManager.saveRecord(record);
                    startActivity(intent);
                }
            }
        });
        button_outcome_zhufang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityShare.instance != null) {//给一下判空
                    ActivityShare.instance.finish();//在其它的activity里面使用
                }
                if (editText_zhufang.length() == 0)
                    ToastUtil.showToast(getActivity(), "请输入金额！");
                else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Record record = new Record();
                    record.setFlag(ManageFlag.GET_OUT);
                    record.setCategory("住房");
                    record.setMoney(Float.parseFloat(editText_zhufang.getText().toString()));
                    record.setRecord_date(new Date());
                    RecordManager.saveRecord(record);
                    startActivity(intent);
                }
            }
        });
        button_outcome_shejiao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityShare.instance != null) {//给一下判空
                    ActivityShare.instance.finish();//在其它的activity里面使用
                }
                if (editText_shejiao.length() == 0)
                    ToastUtil.showToast(getActivity(), "请输入金额！");
                else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Record record = new Record();
                    record.setFlag(ManageFlag.GET_OUT);
                    record.setCategory("社交");
                    record.setMoney(Float.parseFloat(editText_shejiao.getText().toString()));
                    record.setRecord_date(new Date());
                    RecordManager.saveRecord(record);
                    startActivity(intent);
                }
            }
        });
        button_outcome_lvxing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityShare.instance != null) {//给一下判空
                    ActivityShare.instance.finish();//在其它的activity里面使用
                }
                if (editText_lvxing.length() == 0)
                    ToastUtil.showToast(getActivity(), "请输入金额！");
                else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Record record = new Record();
                    record.setFlag(ManageFlag.GET_OUT);
                    record.setCategory("旅行");
                    record.setMoney(Float.parseFloat(editText_lvxing.getText().toString()));
                    record.setRecord_date(new Date());
                    RecordManager.saveRecord(record);
                    startActivity(intent);
                }
            }
        });
        button_outcome_qita.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityShare.instance != null) {//给一下判空
                    ActivityShare.instance.finish();//在其它的activity里面使用
                }
                if (editText_qita.length() == 0)
                    ToastUtil.showToast(getActivity(), "请输入金额！");
                else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Record record = new Record();
                    record.setFlag(ManageFlag.GET_OUT);
                    record.setCategory("其它");
                    record.setMoney(Float.parseFloat(editText_qita.getText().toString()));
                    record.setRecord_date(new Date());
                    RecordManager.saveRecord(record);
                    startActivity(intent);
                }
            }
        });
    }
}
