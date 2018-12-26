package com.example.myungger.pocket.activity;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myungger.pocket.R;
import com.example.myungger.pocket.entity.FragmentAdapter;
import com.example.myungger.pocket.entity.LineChartManager;
import com.example.myungger.pocket.entity.Record;
import com.example.myungger.pocket.fragment.OutcomeFragment;
import com.example.myungger.pocket.manager.RecordManager;
import com.example.myungger.pocket.util.ToastUtil;
import com.github.mikephil.charting.charts.LineChart;

import org.litepal.LitePal;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private FragmentAdapter fragmentAdapter;
    private DatePickerDialog datePickerDialog;
    private Calendar calendar;

    private TextView arrow_detail;
    private TextView outcome_detail;
    private TextView income_detail;
    private FloatingActionButton floatingActionButton;
    private TextView textView_test;
    private TextView textView_test2;

    private Button button_delete_detail;
    private EditText editText_delete_detail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        ActivityShare.instance = this;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initFragment();
        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        LitePal.getDatabase();
        calendar = Calendar.getInstance();

        arrow_detail = findViewById(R.id.arrow_detail);
        outcome_detail = findViewById(R.id.income_detail);
        income_detail = findViewById(R.id.outcome_detail);
        button_delete_detail = findViewById(R.id.button_delete_detail);
        editText_delete_detail = findViewById(R.id.editText_delete_detail);

        button_delete_detail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText_delete_detail.length() == 0)
                    ToastUtil.showToast(MainActivity.this, "请输入帐单编号!");
                else {
                   // 删除的函数内容
                    Intent intent = new Intent(MainActivity.this, DeleteActivity.class);
                    startActivity(intent);
                    int i = Integer.parseInt(editText_delete_detail.getText().toString());
                    if(RecordManager.getAllID().contains(i)){
                        RecordManager.deleteRecord(i);
                        ToastUtil.showToast(MainActivity.this, "删除成功！");
                        MainActivity.this.finish();
                    }else
                        ToastUtil.showToast(MainActivity.this, "不存在这项纪录!");
                }
            }
        });

        arrow_detail.setOnClickListener(this);
        //Log.d("测试","到这了");
        textView_test = findViewById(R.id.textView_test);
        textView_test2 = findViewById(R.id.textView_test2);
        List<Record> temp_list = new ArrayList<>();
        float total;
        temp_list = RecordManager.getRecordsByTimeIn(RecordManager.getDayBegin(),new Date());
        textView_test.setText(RecordManager.getStringByList(temp_list));
        total = RecordManager.getMoenySum(temp_list);
        income_detail.setText(String.valueOf(total));
        temp_list = RecordManager.getRecordsByTimeOut(RecordManager.getDayBegin(),new Date());
        textView_test2.setText(RecordManager.getStringByList(temp_list));
        total = RecordManager.getMoenySum(temp_list);
        outcome_detail.setText(String.valueOf(total));


        floatingActionButton = findViewById(R.id.fabutton_record);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                startActivity(intent);

            }
        });

        LineChart lineChart_week = findViewById(R.id.lineChart_week);
        LineChart lineChart_month = findViewById(R.id.lineChart_month);
        LineChart lineChart_year = findViewById(R.id.lineChart_year);

        LineChartManager lineChartManager_week = new LineChartManager(lineChart_week);
        LineChartManager lineChartManager_month = new LineChartManager(lineChart_month);
        LineChartManager lineChartManager_year = new LineChartManager(lineChart_year);

        TextView textView_week_alloutcomenum = findViewById(R.id.textView_week_alloutcomenum);
        TextView textView_week_allincomenum = findViewById(R.id.textView_week_allincomenum);
        TextView textView_week_avgoutcomenum = findViewById(R.id.textView_week_avgoutcomenum);
        TextView textView_week_avgincomenum = findViewById(R.id.textView_week_avgincomenum);
        double temp, week_alloutcomenum = 0.00, week_allincomenum = 0.00, week_avgoutcomenum = 0.00, week_avgincomenum = 0.00;

        //week设置x轴的数据
        ArrayList<Float> xValues_week = new ArrayList<>();
        for (int i = 1; i <= 7; i++) {
            xValues_week.add((float) i);
        }

        //week设置y轴的数据()
        List<List<Float>> yValues_week = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            List<Float> yValue = new ArrayList<>();
            for (int j = 1; j <= 7; j++) {
                if (i == 0) {
                    temp = Math.random() * 80;
                    yValue.add((float) temp);
                    week_alloutcomenum += temp;
                } else if (i == 1) {
                    temp = Math.random() * 80;
                    yValue.add((float) temp);
                    week_allincomenum += temp;
                }
            }
            yValues_week.add(yValue);
        }
        week_avgoutcomenum = week_alloutcomenum / 8;
        week_avgincomenum = week_allincomenum / 8;


        TextView textView_month_alloutcomenum = findViewById(R.id.textView_month_alloutcomenum);
        TextView textView_month_allincomenum = findViewById(R.id.textView_month_allincomenum);
        TextView textView_month_avgoutcomenum = findViewById(R.id.textView_month_avgoutcomenum);
        TextView textView_month_avgincomenum = findViewById(R.id.textView_month_avgincomenum);
        double month_alloutcomenum = 0.00, month_allincomenum = 0.00, month_avgoutcomenum = 0.00, month_avgincomenum = 0.00;

        //month设置x轴的数据
        ArrayList<Float> xValues_month = new ArrayList<>();
        for (int i = 1; i <= 31; i++) {
            xValues_month.add((float) i);
        }

        //month设置y轴的数据()
        List<List<Float>> yValues_month = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            List<Float> yValue = new ArrayList<>();
            for (int j = 1; j <= 31; j++) {
                if (i == 0) {
                    temp = Math.random() * 80;
                    yValue.add((float) temp);
                    month_alloutcomenum += temp;
                } else if (i == 1) {
                    temp = Math.random() * 80;
                    yValue.add((float) temp);
                    month_allincomenum += temp;
                }
            }
            yValues_month.add(yValue);
        }
        month_avgoutcomenum = month_alloutcomenum / 8;
        month_avgincomenum = month_allincomenum / 8;


        TextView textView_year_alloutcomenum = findViewById(R.id.textView_year_alloutcomenum);
        TextView textView_year_allincomenum = findViewById(R.id.textView_year_allincomenum);
        TextView textView_year_avgoutcomenum = findViewById(R.id.textView_year_avgoutcomenum);
        TextView textView_year_avgincomenum = findViewById(R.id.textView_year_avgincomenum);
        double year_alloutcomenum = 0.00, year_allincomenum = 0.00, year_avgoutcomenum = 0.00, year_avgincomenum = 0.00;

        //year设置x轴的数据
        ArrayList<Float> xValues_year = new ArrayList<>();
        for (int i = 1; i <= 12; i++) {
            xValues_year.add((float) i);
        }

        //year设置y轴的数据()
        List<List<Float>> yValues_year = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            List<Float> yValue = new ArrayList<>();
            for (int j = 1; j <= 12; j++) {
                if (i == 0) {
                    temp = Math.random() * 2479;
                    yValue.add((float) temp);
                    year_alloutcomenum += temp;
                } else if (i == 1) {
                    temp = Math.random() * 2479;
                    yValue.add((float) temp);
                    year_allincomenum += temp;
                }
            }
            yValues_year.add(yValue);
        }
        year_avgoutcomenum = year_alloutcomenum / 8;
        year_avgincomenum = year_allincomenum / 8;

        //颜色集合
        List<Integer> colours = new ArrayList<>();
        colours.add(Color.BLUE);
        colours.add(Color.RED);

        //线的名字集合
        List<String> names = new ArrayList<>();
        names.add("支出");
        names.add("收入");

        //创建多条折线的图表
        lineChartManager_week.showLineChart(xValues_week, yValues_week, names, colours);
        lineChartManager_week.setYAxis(100, 0, 11);
        lineChartManager_week.setDescription("本周数据");

        lineChartManager_month.showLineChart(xValues_month, yValues_month, names, colours);
        lineChartManager_month.setYAxis(100, 0, 11);
        lineChartManager_month.setDescription("本月数据");

        lineChartManager_year.showLineChart(xValues_year, yValues_year, names, colours);
        lineChartManager_year.setYAxis(5000, 0, 11);
        lineChartManager_year.setDescription("今年数据");

        textView_week_alloutcomenum.setText(String.format("%.2f",week_alloutcomenum));
        textView_week_allincomenum.setText(String.format("%.2f",week_allincomenum));
        textView_week_avgoutcomenum.setText(String.format("%.2f",week_avgoutcomenum));
        textView_week_avgincomenum.setText(String.format("%.2f",week_avgincomenum));

        textView_month_alloutcomenum.setText(String.format("%.2f",month_alloutcomenum));
        textView_month_allincomenum.setText(String.format("%.2f",month_allincomenum));
        textView_month_avgoutcomenum.setText(String.format("%.2f",month_avgoutcomenum));
        textView_month_avgincomenum.setText(String.format("%.2f",month_avgincomenum));

        textView_year_alloutcomenum.setText(String.format("%.2f",year_alloutcomenum));
        textView_year_allincomenum.setText(String.format("%.2f",year_allincomenum));
        textView_year_avgoutcomenum.setText(String.format("%.2f",year_avgoutcomenum));
        textView_year_avgincomenum.setText(String.format("%.2f",year_avgincomenum));
    }


    private void showDialog() {
        datePickerDialog = new DatePickerDialog(
                this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                String time = String.valueOf(year) + " " + String.valueOf(month + 1) + " " + Integer.toString(dayOfMonth);
                Log.d("调试", time);
            }
        },
                calendar.get(calendar.YEAR),
                calendar.get(calendar.MONTH),
                calendar.get(calendar.DAY_OF_MONTH));

        datePickerDialog.show();
        datePickerDialog.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);

//        DatePicker dp = findDatePicker((ViewGroup) datePickerDialog.getWindow().getDecorView());
//        if (dp != null) {
//            ((ViewGroup)((ViewGroup)dp.getChildAt(0)).getChildAt(0))
//                    .getChildAt(2).setVisibility(View.GONE);
//        }
    }

    private DatePicker findDatePicker(ViewGroup group) {
        if (group != null) {
            for (int i = 0, j = group.getChildCount(); i < j; i++) {
                View child = group.getChildAt(i);
                if (child instanceof DatePicker) {
                    return (DatePicker) child;
                } else if (child instanceof ViewGroup) {
                    DatePicker result = findDatePicker((ViewGroup) child);
                    if (result != null)
                        return result;
                }
            }
        }
        return null;
    }

    private void initFragment() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(getSupportFragmentManager().findFragmentById(R.id.fragment_detail));
        fragmentList.add(getSupportFragmentManager().findFragmentById(R.id.fragment_graph));
        fragmentList.add(getSupportFragmentManager().findFragmentById(R.id.fragment_find));
        fragmentList.add(getSupportFragmentManager().findFragmentById(R.id.fragment_me));
        fragmentAdapter = new FragmentAdapter(fragmentList, this);
        fragmentAdapter.showFragment(0);
    }


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_detail:
                    fragmentAdapter.showFragment(0);
                    return true;
                case R.id.navigation_graph:
                    fragmentAdapter.showFragment(1);
                    return true;
                case R.id.navigation_record:
                    Intent intent = new Intent(MainActivity.this, RecordActivity.class);
                    startActivity(intent);
                    return false;
                case R.id.navigation_find:
                    fragmentAdapter.showFragment(2);
                    return true;
                case R.id.navigation_me:
                    fragmentAdapter.showFragment(3);
                    return true;
            }
            return false;
        }
    };

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.arrow_detail:
                showDialog();
                break;
            case R.id.month_detail:
                showDialog();
                break;
        }
    }
}
