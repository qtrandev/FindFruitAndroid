package com.qtrandev.findfruit;

import android.app.Application;

import com.parse.Parse;

public class ParseApplication extends Application {

  @Override
  public void onCreate() {
    super.onCreate();

    // Enable Local Datastore.
    Parse.enableLocalDatastore(this);

    // Add your initialization code here
    Parse.initialize(this, "TS3SQRc5387Pu6sYsXhE80w68K0P98Dr7Hjvawu3", "zmUSx09EefUa2UaMxU60kIAwfwLpYBUjPFUjU3qn");

  }
}
