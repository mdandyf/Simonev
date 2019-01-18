package simonev.mitrais.com.simonev.contract;

import java.util.List;

import simonev.mitrais.com.simonev.model.Login;
import simonev.mitrais.com.simonev.presenter.BasePresenter;

public interface BaseContract<T, R> {

    interface View<T> {
        void addResults(List<T> repos);
        void clearResults();
        void showContentLoading();
        void hideContentLoading();
        void showListLoading();
        void hideListLoading();
        void showContentError();
        void hideContentError();
        void showListError();
        void showEmptyResultsView();
        void hideEmptyResultsView();
    }

    interface Presenter<T, R> extends BasePresenter<R> {
        void load();
        void loadMore();
        void queryChanged(String query);
        void repositoryClick(T t);
    }
}
