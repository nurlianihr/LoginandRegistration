package app;

import android.app.Application;

import java.lang.ref.ReferenceQueue;

public class AppController extends Application {
    public static final String TAG = AppController.class.getSimpleName();

    private ReferenceQueue mRequestQueue;

    private static AppController mInstance;

    @Override
    public void onCreate() {
    super.onCreate();
    mInstance=this;
    }

    public static synchronized AppController getInstance(){
        return mInstance;
    }

    public RequestQueue getRequestQueue(){
        if (mRequestQueue == null) {
            mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        }
        return mRequestQueue;
    }

    public <T> void addToRequestQueue(Request<T> req) {
        req.setTag(TAG);
        getRequestQueue().add(req);
    }

    public void cancelPendingRequests(Object tag) {
        if (mRequestQueue != null) {
            mRequestQueue.cancelAll(tag);
        }
    }
}
