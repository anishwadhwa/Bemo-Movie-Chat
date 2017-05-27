package bemo.moviechat;

import android.app.Application;

import com.google.firebase.FirebaseApp;

/**
 * Created by anish wadhwa on 5/27/2017.
 */

public class MovieApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
    }
}
