package com.digitu.movies.modules.detail;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;

import com.digitu.movies.R;
import com.digitu.movies.base.BaseActivity;

//TODO DetailActivity
public class DetailActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);
    }

    public void update(View view) {
    }
}
