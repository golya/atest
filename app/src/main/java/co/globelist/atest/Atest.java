package co.globelist.atest;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.android.volley.toolbox.StringRequest;
import com.android.volley.Request;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.net.CookieHandler;
import java.net.CookieManager;

import java.net.CookieStore;
import java.util.HashMap;
import java.util.Map;

public class Atest extends AppCompatActivity {
    private View mContentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setUpUI();
        setUpLogin();
    }

    private void setUpLogin() {

        final CookieManager manager = setUpCookieManager();
        final RestService rest = new RestService();

        class AuthCallback extends Callback {
            @Override
            public void success(JSONObject jsonResponse) {
                super.success(jsonResponse);
                Log.d("AuthCallback", "my success");
            }

            @Override
            public void error(String error) {
                super.error(error);
                CookieStore store = manager.getCookieStore();

                Intent i = new Intent(getApplicationContext(), Profile.class);
                startActivity(i);
            }
        }

        Button clickButton = (Button) findViewById(R.id.login);
        clickButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText fEmail = (EditText)findViewById(R.id.email);
                EditText fPass = (EditText)findViewById(R.id.password);
                Map<String, String> params = new HashMap<>();
                params.put("email", fEmail.getText().toString());
                params.put("password", fPass.getText().toString());
                StringRequest postRequest = rest.getStringRequest(Request.Method.POST, "auth", params, new AuthCallback());
                Volley.newRequestQueue(Atest.this).add(postRequest);
            }
        });
    }

    @NonNull
    private CookieManager setUpCookieManager() {
        final CookieManager manager = new CookieManager();
        CookieHandler.setDefault(manager);
        return manager;
    }

    private void setUpUI() {
        setContentView(R.layout.activity_atest);

        mContentView = findViewById(R.id.fullscreen_content);
        mContentView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LOW_PROFILE
                | View.SYSTEM_UI_FLAG_FULLSCREEN
                | View.SYSTEM_UI_FLAG_LAYOUT_STABLE
                | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                | View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                | View.SYSTEM_UI_FLAG_HIDE_NAVIGATION);
    }

}
