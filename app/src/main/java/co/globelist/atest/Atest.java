package co.globelist.atest;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.Request;
import com.android.volley.toolbox.Volley;

import java.net.CookieHandler;
import java.net.CookieManager;

import java.net.CookieStore;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class Atest extends AppCompatActivity {
    private View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_atest);

        mContentView = findViewById(R.id.fullscreen_content);
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);


        final CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);
        final RestService rest = new RestService();
        class AuthCallback extends Callback {
            @Override
            public void success() {
                super.success();
                Log.d("AuthCallback", "my success");
            }

            @Override
            public void error(String error) {
                super.error(error);
                Log.d("AuthCallback", "my error: "+error);
            }
        }

        RestService rest = new RestService();

        Map<String, String> params = new HashMap<>();
        params.put("email", "test@example.com");
        params.put("password", "test");
        StringRequest postRequest = rest.getStringRequest(Request.Method.POST, "auth", params, new AuthCallback());
        Volley.newRequestQueue(this).add(postRequest);

    }




}
