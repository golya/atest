package co.globelist.atest;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
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
                Snackbar.make(view, "Edit profile", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        final RestService rest = new RestService();

        class UsersCallback extends Callback {
            @Override
            public void success(JSONObject jsonResponse) {
                super.success(jsonResponse);
                try {
                    String name = jsonResponse.getString("name");
                    EditText fname = (EditText)findViewById(R.id.name);
                    fname.setText(name);

                    String username = jsonResponse.getString("username");
                    EditText fusername = (EditText)findViewById(R.id.username);
                    fusername.setText(username);

                    String country = jsonResponse.getString("country");
                    EditText fcountry = (EditText)findViewById(R.id.country);
                    fcountry.setText(country);

                } catch (JSONException e) {
                    e.printStackTrace();
                }

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
