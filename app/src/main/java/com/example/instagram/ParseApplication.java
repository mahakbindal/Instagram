package com.example.instagram;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseObject;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        // Register your parse models
        ParseObject.registerSubclass(Post.class);

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("LXGNwFEaGWvN8CKEg0kpLqvXyIypXufOBuymEWyb")
                .clientKey("KvKauJDCA8SPnG8FK57wcDBaxhhX0HV13zfiiqyL")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
