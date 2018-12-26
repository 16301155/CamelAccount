package com.example.myungger.pocket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.myungger.pocket.R;
import com.example.myungger.pocket.entity.FragmentAdapter_2;

import java.util.ArrayList;
import java.util.List;

public class RecordActivity extends BaseActivity {
    private FragmentAdapter_2 fragmentAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.record);

        initFragment();
        BottomNavigationView navigation = findViewById(R.id.navigation2);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    private void initFragment() {
        List<Fragment> fragmentList = new ArrayList<>();
        fragmentList.add(getSupportFragmentManager().findFragmentById(R.id.fragment_outcome));
        fragmentList.add(getSupportFragmentManager().findFragmentById(R.id.fragment_income));
        fragmentList.add(getSupportFragmentManager().findFragmentById(R.id.fragment_back));
        fragmentAdapter = new FragmentAdapter_2(fragmentList, this);
        fragmentAdapter.showFragment(0);
    }

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_outcome:
                    fragmentAdapter.showFragment(0);
                    return true;
                case R.id.navigation_income:
                    fragmentAdapter.showFragment(1);
                    return true;
                case R.id.navigation_back:
                    startActivity(new Intent(RecordActivity.this, MainActivity.class));;
                    return true;
            }
            return false;
        }
    };
}