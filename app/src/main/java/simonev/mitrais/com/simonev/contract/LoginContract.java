package simonev.mitrais.com.simonev.contract;

import java.util.List;

import simonev.mitrais.com.simonev.model.Login;
import simonev.mitrais.com.simonev.presenter.BasePresenter;

public interface LoginContract {
    interface View {
        void addResults(List<Login> repos);
        void clearResults();
        void showContentLoading();
        void hideContentLoading();
        void showContentError();
        void hideContentError();
        void showEmptyResultsView();
        void hideEmptyResultsView();
    }
    interface Presenter extends BasePresenter<View> {
        void load();
        void loadMore();
        void queryChanged(String query);
        void repositoryClick(Login login);
    }
}
