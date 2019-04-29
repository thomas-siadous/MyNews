package com.siadous.thomas.mynews.most_popular_list;

import com.siadous.thomas.mynews.Model.MostPopular.MostPopular;


import java.util.List;

public class MostPopularPresenter implements MostPopularContract.Presenter, MostPopularContract.Model.OnFinishedListener {

    private MostPopularContract.View mostPopularListView;
    private MostPopularContract.Model mostPopularListModel;

    MostPopularPresenter(MostPopularContract.View mostPopularListView) {
        this.mostPopularListView = mostPopularListView;
        mostPopularListModel = new MostPopularModel();
    }

    @Override
    public void onDestroy() {
        this.mostPopularListView = null;
    }


    @Override
    public void getMoreData(int pageNo) {

        if (mostPopularListView != null) {
            mostPopularListView.showProgress();
        }
        mostPopularListModel.getMostPopularList(this, pageNo);
    }

    @Override
    public void requestDataFromServer() {
        if (mostPopularListView != null) {
            mostPopularListView.showProgress();
        }
        mostPopularListModel.getMostPopularList(this, 1);
    }

    @Override
    public void onFinished(List<MostPopular> mostPopularList) {
        mostPopularListView.setDataToRecyclerView(mostPopularList);
        if (mostPopularListView != null) {
            mostPopularListView.hideProgress();
        }
    }

    @Override
    public void onFailure(Throwable t) {

        mostPopularListView.onResponseFailure(t);
        if (mostPopularListView != null) {
            mostPopularListView.hideProgress();
        }
    }
}