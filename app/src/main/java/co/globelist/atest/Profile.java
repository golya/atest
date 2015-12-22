package co.globelist.atest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import java.util.Collections;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final RestService rest = new RestService();

        class UsersCallback extends Callback {
            @Override
            public void success(JSONObject jsonResponse) {
                super.success(jsonResponse);
                Log.d("UsersCallback", "my success" + jsonResponse);
            }

            @Override
            public void error(String error) {
                super.error(error);
                Log.d("UsersCallback", "my error: " + error);
            }
        }

        StringRequest getRequest = rest.getStringRequest(Request.Method.GET, "users/me", Collections.<String,String>emptyMap(), new UsersCallback());
        Volley.newRequestQueue(Profile.this).add(getRequest);

    }
}
