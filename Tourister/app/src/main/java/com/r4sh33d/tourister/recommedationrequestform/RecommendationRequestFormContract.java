package com.r4sh33d.tourister.recommedationrequestform;

import com.r4sh33d.tourister.base.BaseContract;
import com.r4sh33d.tourister.network.RecommendationListModel;
import com.r4sh33d.tourister.network.RecommendationRequestData;

public interface RecommendationRequestFormContract {

    interface Presenter {
        void findRecommendations(RecommendationRequestData recommendationRequestData);
    }

    interface View extends BaseContract.view {
        void onRecommendationsFetched(RecommendationListModel recommendationListModel);
    }
}
