package com.example.myungger.pocket.entity;

import android.support.v4.app.Fragment;

import com.example.myungger.pocket.activity.MainActivity;

import java.util.List;

public class FragmentAdapter {

    private List<Fragment> fragmentList;
    private MainActivity mainActivity;

    public FragmentAdapter(List<Fragment> fragmentList, MainActivity mainActivity) {
        this.fragmentList = fragmentList;
        this.mainActivity = mainActivity;
    }

    public void showFragment(int index) {

        for (Fragment fragment : fragmentList) {
            mainActivity.getSupportFragmentManager().beginTransaction()
                    .hide(fragment).commit();
        }
        mainActivity.getSupportFragmentManager().beginTransaction()
                .show(fragmentList.get(index)).commit();
    }
}
