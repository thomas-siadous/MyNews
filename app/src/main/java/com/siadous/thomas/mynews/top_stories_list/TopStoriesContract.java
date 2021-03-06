package com.siadous.thomas.mynews.top_stories_list;

import com.siadous.thomas.mynews.model.TopStories.TopStories;

import java.util.List;

public interface TopStoriesContract {

    // Get data from network request
    interface Model {

        interface OnFinishedListener {

            void onFinished(List<TopStories> topStoriesList);

            void onFailure(Throwable t);
        }

        void getTopStoriesList(OnFinishedListener onFinishedListener, int pageNo);
    }

    // Display data in a recycler view
    interface View {
        void showProgress();

        void hideProgress();

        void setDataToRecyclerView(List<TopStories> topStoriesList); // if success

        void onResponseFailure(Throwable throwable); // if echec

    }

    // Link Model and View together
    interface Presenter {

        void onDestroy();

        void getMoreData(int pageNo);

        void requestDataFromServer();

    }
}
