package com.example.myungger.pocket.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputFilter;
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

public class IncomeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.record_income, null);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        final EditText editText_gongzi = getView().findViewById(R.id.editText_gongzi);
        final EditText editText_jianzhi = getView().findViewById(R.id.editText_jianzhi);
        final EditText editText_licai = getView().findViewById(R.id.editText_licai);
        final EditText editText_lijin = getView().findViewById(R.id.editText_lijin);
        final EditText editText_other = getView().findViewById(R.id.editText_other);

        InputFilter[] filters={new EditInputFilter()};

        editText_gongzi.setFilters(filters);
        editText_jianzhi.setFilters(filters);
        editText_licai.setFilters(filters);
        editText_lijin.setFilters(filters);
        editText_other.setFilters(filters);

        Button button_income_gongzi = getView().findViewById(R.id.button_income_gongzi);
        Button button_income_jianzhi = getView().findViewById(R.id.button_income_jianzhi);
        Button button_income_licai = getView().findViewById(R.id.button_income_licai);
        Button button_income_lijin = getView().findViewById(R.id.button_income_lijin);
        Button button_income_other = getView().findViewById(R.id.button_income_other);

        button_income_gongzi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityShare.instance != null) {//给一下判空
                    ActivityShare.instance.finish();//在其它的activity里面使用
                }
                if (editText_gongzi.length() == 0)
                    ToastUtil.showToast(getActivity(), "请输入金额！");
                else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Record record = new Record();
                    record.setFlag(ManageFlag.GET_IN);
                    record.setCategory("工资");
                    record.setMoney(Float.parseFloat(editText_gongzi.getText().toString()));
                    record.setRecord_date(new Date());
                    RecordManager.saveRecord(record);
                    startActivity(intent);
                }
            }
        });
        button_income_jianzhi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityShare.instance != null) {//给一下判空
                    ActivityShare.instance.finish();//在其它的activity里面使用
                }
                if (editText_jianzhi.length() == 0)
                    ToastUtil.showToast(getActivity(), "请输入金额！");
                else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Record record = new Record();
                    record.setFlag(ManageFlag.GET_IN);
                    record.setCategory("兼职");
                    record.setMoney(Float.parseFloat(editText_jianzhi.getText().toString()));
                    record.setRecord_date(new Date());
                    RecordManager.saveRecord(record);
                    startActivity(intent);
                }
            }
        });
        button_income_licai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityShare.instance != null) {//给一下判空
                    ActivityShare.instance.finish();//在其它的activity里面使用
                }
                if (editText_licai.length() == 0)
                    ToastUtil.showToast(getActivity(), "请输入金额！");
                else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Record record = new Record();
                    record.setFlag(ManageFlag.GET_IN);
                    record.setCategory("理财");
                    record.setMoney(Float.parseFloat(editText_licai.getText().toString()));
                    record.setRecord_date(new Date());
                    RecordManager.saveRecord(record);
                    startActivity(intent);
                }
            }
        });
        button_income_lijin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityShare.instance != null) {//给一下判空
                    ActivityShare.instance.finish();//在其它的activity里面使用
                }
                if (editText_lijin.length() == 0)
                    ToastUtil.showToast(getActivity(), "请输入金额！");
                else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Record record = new Record();
                    record.setFlag(ManageFlag.GET_IN);
                    record.setCategory("礼金");
                    record.setMoney(Float.parseFloat(editText_lijin.getText().toString()));
                    record.setRecord_date(new Date());
                    RecordManager.saveRecord(record);
                    startActivity(intent);
                }
            }
        });
        button_income_other.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (ActivityShare.instance != null) {//给一下判空
                    ActivityShare.instance.finish();//在其它的activity里面使用
                }
                if (editText_other.length() == 0)
                    ToastUtil.showToast(getActivity(), "请输入金额！");
                else {
                    Intent intent = new Intent(getActivity(), MainActivity.class);
                    Record record = new Record();
                    record.setFlag(ManageFlag.GET_IN);
                    record.setCategory("其它");
                    record.setMoney(Float.parseFloat(editText_other.getText().toString()));
                    record.setRecord_date(new Date());
                    RecordManager.saveRecord(record);
                    startActivity(intent);
                }
            }
        });
    }
}
