package com.example.instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    public static final String APP_ID = "LXGNwFEaGWvN8CKEg0kpLqvXyIypXufOBuymEWyb";
    public static final String CLIENT_KEY = "KvKauJDCA8SPnG8FK57wcDBaxhhX0HV13zfiiqyL";
    public static final String SERVER = "https://parseapi.back4app.com";

    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId(APP_ID)
                .clientKey(CLIENT_KEY)
                .server(SERVER)
                .build()
        );
    }
}
