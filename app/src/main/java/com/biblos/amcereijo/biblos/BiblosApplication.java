package com.biblos.amcereijo.biblos;

import android.app.Application;
import android.content.Context;

import com.parse.Parse;
import com.parse.ParseInstallation;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by amcereijo on 08/08/15.
 */
public class BiblosApplication extends Application{
    private static BiblosApplication application = new BiblosApplication();

    public BiblosApplication() {
        application = this;
    }

    public static Context getContext() {
        return application.getApplicationContext();
    }

    @Override
    public void onCreate() {
        super.onCreate();
        String applicationId = "";
        String clientKey = "";
        try {
            Properties prop = new Properties();
            prop.load(getResources().openRawResource(R.raw.parse));
            applicationId = prop.getProperty("applicationId");
            clientKey = prop.getProperty("clientKey");
        } catch (IOException e) {
            e.printStackTrace();
        }
        ;
        Parse.initialize(this, applicationId, clientKey);
        ParseInstallation.getCurrentInstallation().saveInBackground();

    }
}
