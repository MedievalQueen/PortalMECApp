package com.example.hednisk.portalmec2;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;

/**
 * Created by hednisk on 10/12/15.
 */
public class Session {
    private SharedPreferences prefs;

    public Session(Context cntx) {
        // TODO Auto-generated constructor stub
        prefs = PreferenceManager.getDefaultSharedPreferences(cntx);
    }

    public void setusername(String username) {
        prefs.edit().putString("username", username).commit();
        prefs.edit().putBoolean("isLogged", true).commit();
    }

    public void removeSession() {
        prefs.edit().putString("username", null).commit();
        prefs.edit().putBoolean("isLogged", false).commit();
    }

    public String getusername() {
        String username = prefs.getString("username", "");
        return username;
    }
    public boolean isLogged() {
        boolean isL = prefs.getBoolean("isLogged",false);
        return isL;
    }
}
