package co.globelist.atest;

import android.util.Log;

import org.json.JSONObject;

public class Callback {
    public void success(JSONObject jsonResponse) {
        Log.d("Callback", "callback is called: " + jsonResponse.toString());
    }

    public void error(String error) {
        Log.d("Callback", "Error is called: " + error);
    }


}
