package simonev.mitrais.com.simonev.contract;

import java.util.List;

import simonev.mitrais.com.simonev.model.Login;
import simonev.mitrais.com.simonev.presenter.BasePresenter;

public interface MainContract {
    interface View {
        void addResults(List<Login> repos);
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
    interface Presenter extends BasePresenter<LoginContract.View> {
        void load();
        void loadMore();
        void queryChanged(String query);
        void repositoryClick(Login login);
    }
}
