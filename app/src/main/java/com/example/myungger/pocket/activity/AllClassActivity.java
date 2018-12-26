package com.example.myungger.pocket.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.example.myungger.pocket.R;

public class AllClassActivity extends BaseActivity{

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.allclass);

        ImageView imageView = findViewById(R.id.imageView_allclass_back);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AllClassActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}


