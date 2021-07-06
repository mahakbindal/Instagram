package com.example.instagram;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        Parse.initialize(new Parse.Configuration.Builder(this)
                .applicationId("LXGNwFEaGWvN8CKEg0kpLqvXyIypXufOBuymEWyb")
                .clientKey("KvKauJDCA8SPnG8FK57wcDBaxhhX0HV13zfiiqyL")
                .server("https://parseapi.back4app.com")
                .build()
        );
    }
}
