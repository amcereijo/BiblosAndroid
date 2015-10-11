package com.biblos.amcereijo.biblos;

import android.app.Application;
import android.content.Context;
import android.util.Log;

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
        String applicationId = "czKBuh1GPPruFLnqn7TqRBrahOAz67Kpbq9wD1L3";
        String clientKey = "FGnDZz3i32ZMu3CSxsY3axQSx2sjI66O1BlLMwrK";
       /* try {
            Properties prop = new Properties();
            prop.load(getResources().openRawResource(R.raw.parse));
            applicationId = prop.getProperty("applicationId");
            clientKey = prop.getProperty("clientKey");
        } catch (IOException e) {
            e.printStackTrace();
            Log.e("BiblosApplication", e.getMessage(), e);
        }*/
        Parse.initialize(this, applicationId, clientKey);
        ParseInstallation.getCurrentInstallation().saveInBackground();

    }
}
