package simonev.mitrais.com.simonev.contract;

import java.util.List;

import simonev.mitrais.com.simonev.model.Login;
import simonev.mitrais.com.simonev.presenter.BasePresenter;

public interface LoginContract {
    interface View {
        void onViewCommand(String component, String error);
        void startNewActivity(Class className);
    }
    interface Presenter extends BasePresenter<View> {
        void login(String email, String password);
        void clearAuthTask();
        void startNewActivity();
        void commandView(String command);
    }
}
