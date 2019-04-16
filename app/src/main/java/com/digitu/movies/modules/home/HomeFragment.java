package com.digitu.movies.modules.home;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.digitu.movies.R;
import com.digitu.movies.base.BaseFragment;
import com.digitu.movies.databinding.FragmentHomeBinding;

import androidx.annotation.NonNull;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

public class HomeFragment extends BaseFragment {
    private HomeViewModel mViewModel;
    private FragmentHomeBinding mBinding;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mViewModel = ViewModelProviders.of(mActivity).get(HomeViewModel.class);
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false);
        mBinding.setViewModel(mViewModel);
        mBinding.setLifecycleOwner(this);
        return mBinding.getRoot();
    }

}
