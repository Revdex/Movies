package com.digitu.movies.modules.home;

import android.os.Bundle;

import com.digitu.movies.R;
import com.digitu.movies.base.BaseActivity;
import com.digitu.movies.data.source.local.entity.Movie;

import androidx.fragment.app.FragmentTransaction;

public class HomeActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        // Begin the transaction
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
// Replace the contents of the container with the new fragment
        ft.replace(R.id.container, HomeFragment.newInstance(Movie.POPULAR));
// or ft.add(R.id.your_placeholder, new FooFragment());
// Complete the changes added above
        ft.commit();
    }
}
