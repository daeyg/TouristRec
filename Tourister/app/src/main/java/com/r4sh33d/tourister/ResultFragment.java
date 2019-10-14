package com.r4sh33d.tourister;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultFragment extends Fragment {

    private static final String LOCATIONS_KEY = "locations";
    @BindView(R.id.recycler_view)
    RecyclerView recyclerView;

    private ResultAdapter resultAdapter;

    public ArrayList<Location> locations;

    public ResultFragment() {
        // Required empty public constructor
    }

    public static ResultFragment newInstance(ArrayList<Location> locations) {

        Bundle args = new Bundle();

        args.putParcelableArrayList(LOCATIONS_KEY, locations);

        ResultFragment fragment = new ResultFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        locations = getArguments().getParcelableArrayList(LOCATIONS_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Timber.d("Locations %s", locations);
        ButterKnife.bind(this, view);

        resultAdapter = new ResultAdapter(locations);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(resultAdapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(recyclerView.getContext(), DividerItemDecoration.VERTICAL));

    }
}
