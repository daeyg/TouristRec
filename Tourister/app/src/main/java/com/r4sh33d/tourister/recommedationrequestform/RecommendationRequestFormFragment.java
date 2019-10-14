package com.r4sh33d.tourister.recommedationrequestform;

import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.r4sh33d.tourister.Location;
import com.r4sh33d.tourister.R;
import com.r4sh33d.tourister.ResultFragment;
import com.r4sh33d.tourister.base.BaseFragment;
import com.r4sh33d.tourister.network.CostStateLovModel;
import com.r4sh33d.tourister.network.KeyTitleLovModel;
import com.r4sh33d.tourister.network.RecommendationListModel;
import com.r4sh33d.tourister.network.RecommendationRequestData;
import com.r4sh33d.tourister.network.TouristAPIService;
import com.r4sh33d.tourister.utils.FormHelper;
import com.r4sh33d.tourister.utils.RetrofitClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import retrofit2.Retrofit;
import timber.log.Timber;

public class RecommendationRequestFormFragment extends BaseFragment implements RecommendationRequestFormContract.View {

    RecommendationRequestFormContract.Presenter presenter;

    @BindView(R.id.city_spinner)
    Spinner citySpinner;
    @BindView(R.id.states_spinner)
    Spinner statesSpinner;
    @BindView(R.id.goepolitical_zone)
    Spinner zoneSpinner;
    @BindView(R.id.continue_button)
    Button continueButton;
    @BindView(R.id.budget_edit_text)
    EditText budgetEditText;
    FormHelper formHelper;


    public RecommendationRequestFormFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_recommendation_request_form, container, false);
        ButterKnife.bind(this, view);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        showToolbar();
        setShowHomeAsUpEnabled();
        setToolbarTitle("Request Form");

        Retrofit retrofit = new RetrofitClient().build();
        TouristAPIService apiService = provideRetrofitService(retrofit, TouristAPIService.class);

        presenter = new RecommendationRequestFormPresenter(this, apiService);
        formHelper = new FormHelper(getContext());
        formHelper.populateStatesAndCitySpinnerWithData(statesSpinner, citySpinner);

        statesSpinner.setSelection(31);

        Handler handler = new Handler();
        handler.postDelayed(() -> {
            // yourMethod();
            citySpinner.setSelection(10);

        }, 1000);

        budgetEditText.setText("5000");
        zoneSpinner.setSelection(3);
    }

    public static <T> T provideRetrofitService(final Retrofit retrofit, Class<T> clazz) {
        return retrofit.create(clazz);
    }

    @Override
    public void onRecommendationsFetched(RecommendationListModel recommendationListModel) {
        Timber.d("Response%s", recommendationListModel);

        ArrayList<Location> locations = new ArrayList<>();

        HashMap<String, CostStateLovModel> map = recommendationListModel.availableLocations;

        if (map != null) {
            for (String key: map.keySet()) {
                CostStateLovModel model = map.get(key);
                locations.add(new Location(model.state, model.cost, key));
            }
        }

        map = recommendationListModel.otherRecommendations;

        if (map != null) {
            for (String key: map.keySet()) {
                CostStateLovModel model = map.get(key);
                locations.add(new Location(model.state, model.cost, key));
            }
        }

        replaceFragment(ResultFragment.newInstance(locations), true);

    }

    @OnClick(R.id.continue_button)
    public void userPressedContinue() {
        hideKeyboard();

        String amount = budgetEditText.getText().toString();
        KeyTitleLovModel city = (KeyTitleLovModel) citySpinner.getSelectedItem();
        String zone = (String) zoneSpinner.getSelectedItem();

        RecommendationRequestData requestData = new RecommendationRequestData();
        requestData.budget = amount;
        requestData.zone = zone;
        requestData.presentLocation = city.name;

        presenter.findRecommendations(requestData);
    }
}
