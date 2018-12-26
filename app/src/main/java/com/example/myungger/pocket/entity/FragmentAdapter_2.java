package com.example.myungger.pocket.entity;

import android.support.v4.app.Fragment;

import com.example.myungger.pocket.activity.RecordActivity;

import java.util.List;

public class FragmentAdapter_2 {

    private List<Fragment> fragmentList;
    private RecordActivity recordActivity;

    public FragmentAdapter_2(List<Fragment> fragmentList, RecordActivity recordActivity) {
        this.fragmentList = fragmentList;
        this.recordActivity = recordActivity;
    }

    public void showFragment(int index) {

        for (Fragment fragment : fragmentList) {
            recordActivity.getSupportFragmentManager().beginTransaction()
                    .hide(fragment).commit();
        }
        recordActivity.getSupportFragmentManager().beginTransaction()
                .show(fragmentList.get(index)).commit();
    }
}
