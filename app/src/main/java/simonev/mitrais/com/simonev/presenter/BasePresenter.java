package simonev.mitrais.com.simonev.presenter;

public interface BasePresenter<V> {
    void attach(V view);
    void detach();
}
