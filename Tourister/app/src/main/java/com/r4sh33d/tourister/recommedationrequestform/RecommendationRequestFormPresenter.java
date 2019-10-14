package com.r4sh33d.tourister.recommedationrequestform;

import android.content.Context;

import com.r4sh33d.tourister.network.RecommendationListModel;
import com.r4sh33d.tourister.network.RecommendationRequestData;
import com.r4sh33d.tourister.network.TouristAPIService;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import timber.log.Timber;


public class RecommendationRequestFormPresenter implements RecommendationRequestFormContract.Presenter {

    private RecommendationRequestFormContract.View view;
    private TouristAPIService touristAPIService;


    RecommendationRequestFormPresenter(RecommendationRequestFormContract.View view,
                                       TouristAPIService touristAPIService) {
        this.view = view;
        this.touristAPIService = touristAPIService;
    }

    @Override
    public void findRecommendations(RecommendationRequestData recommendationRequestData) {
        //TODO: Fetch recommendations

        view.showLoading();
        touristAPIService.registerUserEmail(recommendationRequestData).enqueue(new Callback<RecommendationListModel>() {
            @Override
            public void onResponse(Call<RecommendationListModel> call, Response<RecommendationListModel> response) {
                view.dismissLoading();
                if (response.body() != null) {
                    view.onRecommendationsFetched(response.body());
                }
            }

            @Override
            public void onFailure(Call<RecommendationListModel> call, Throwable t) {
                view.dismissLoading();
                view.showError(t.getMessage());
                Timber.e(t.getMessage());
            }
        });
    }
}
