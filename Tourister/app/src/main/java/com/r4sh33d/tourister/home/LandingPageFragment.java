package com.r4sh33d.tourister.home;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.r4sh33d.tourister.R;
import com.r4sh33d.tourister.base.BaseFragment;
import com.r4sh33d.tourister.recommedationrequestform.RecommendationRequestFormFragment;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class LandingPageFragment extends BaseFragment {

    public LandingPageFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_landing_page, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        hideToolbar();
    }

    @OnClick(R.id.continue_button)
    public void onClickContinueButton(){
       replaceFragment(new RecommendationRequestFormFragment(), true);
    }
}
