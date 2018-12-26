package com.example.myungger.pocket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.example.myungger.pocket.R;

public class DeleteActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_loading);

        Intent intent = new Intent(DeleteActivity.this, MainActivity.class);
        startActivity(intent);
        DeleteActivity.this.finish();
    }
}


