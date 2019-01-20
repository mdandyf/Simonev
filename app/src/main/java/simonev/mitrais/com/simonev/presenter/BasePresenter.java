package simonev.mitrais.com.simonev.presenter;

import android.widget.RemoteViews;

import java.lang.ref.WeakReference;

public abstract class BasePresenter<V extends RemoteViews.RemoteView> {

    private WeakReference<V> view = null;

    public final void attachView(V view) {
        if (view == null) throw new NullPointerException("View must not be null");

        if(this.view != null) detachView(this.view.get());

        this.view = new WeakReference<V>(view);
    }

    public final void detachView(V view) {
        if (view == null) throw new NullPointerException("Detached view must not be null");

        this.view = null;
    }

    protected final boolean isViewAttached() {
        return view != null;
    }

    protected final V getView() {
        if (view == null) throw new NullPointerException("getView called when view is null. " +
                "Make sure to setView(View) is called first!");

        return view.get();
    }

}