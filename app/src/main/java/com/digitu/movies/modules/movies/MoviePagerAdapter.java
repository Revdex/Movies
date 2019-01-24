package com.digitu.movies.modules.movies;

import com.digitu.movies.base.BaseFragment;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class MoviePagerAdapter extends FragmentPagerAdapter {

    private final List<BaseFragment> fragments;

    public MoviePagerAdapter(@NonNull FragmentManager fm, List<BaseFragment> fragments) {
        super(fm);
        this.fragments = fragments;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            default:
            case 0:
                return "Popular";
            case 1:
                return "Top Rated";
            case 2:
                return "Upcoming";
            case 3:
                return "Now Playing";
        }

    }

}
