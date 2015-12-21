package co.globelist.atest;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import android.webkit.CookieManager;

import java.net.CookieStore;
import java.util.Collections;

public class Welcome extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.welcome);

        //final CookieManager manager = CookieManager.getInstance();
        final RestService rest = new RestService();

        class UsersCallback extends Callback {
            @Override
            public void success(JSONObject jsonResponse) {
                super.success(jsonResponse);
                Log.d("UsersCallback", "my success");
            }

            @Override
            public void error(String error) {
                super.error(error);
                Log.d("UsersCallback", "my error: " + error);
            }
        }

        StringRequest getRequest = rest.getStringRequest(Request.Method.GET, "users/me", Collections.<String,String>emptyMap(), new UsersCallback());
        Volley.newRequestQueue(Welcome.this).add(getRequest);
    }
}