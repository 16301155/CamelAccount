package com.example.myungger.pocket.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.myungger.pocket.R;
import com.example.myungger.pocket.activity.CreateActivity;

public class MeFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.me, null);
        return view;
    }

    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ImageView imageView_user = getActivity().findViewById(R.id.imageView_user);
        imageView_user.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateActivity.class));
            }
        });

        TextView textView_username = getActivity().findViewById(R.id.textView_username);
        textView_username.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateActivity.class));
            }
        });

        TextView textView_day_daka = getActivity().findViewById(R.id.textView_day_daka);
        textView_day_daka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateActivity.class));
            }
        });

        TextView textView_daka = getActivity().findViewById(R.id.textView_daka);
        textView_daka.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateActivity.class));
            }
        });

        TextView textView_day_use = getActivity().findViewById(R.id.textView_day_use);
        textView_day_use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateActivity.class));
            }
        });

        TextView textView_use = getActivity().findViewById(R.id.textView_use);
        textView_use.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateActivity.class));
            }
        });

        TextView textView_amount_count = getActivity().findViewById(R.id.textView_amount_count);
        textView_amount_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateActivity.class));
            }
        });

        TextView textView_count = getActivity().findViewById(R.id.textView_count);
        textView_count.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getActivity(), CreateActivity.class));
            }
        });
    }
}