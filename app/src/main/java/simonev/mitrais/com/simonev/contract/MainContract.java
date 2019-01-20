package simonev.mitrais.com.simonev.contract;

import android.widget.RemoteViews;

import java.util.List;

import simonev.mitrais.com.simonev.model.Login;

public interface MainContract {
    interface ViewAction{
        // actions done before the login process is executed
    }

    interface LoginView extends RemoteViews.RemoteView {
        // actions done after the login process is executed
    }
}
