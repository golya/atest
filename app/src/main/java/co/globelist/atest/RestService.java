package co.globelist.atest;

import android.util.Log;

import com.android.volley.NetworkResponse;

import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.HttpHeaderParser;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Map;


public class RestService {
    private String host = "http://192.168.1.2:3000/";
    public StringRequest getStringRequest(final Integer method, String path, final Map<String, String> params, final Callback callback) {
        final String url = host + path;

        Response.Listener<String> response = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                Log.d("RestService", "The result is: " + response);
                try {
                    JSONObject jsonResponse = new JSONObject(response);
                    callback.success(jsonResponse);
                } catch (JSONException e) {
                    callback.error("Format error!");
                }
            }
        };

        Response.ErrorListener error = new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        };

        return new StringRequest(method, url, response, error) {
            @Override
            protected Map<String, String> getParams()
            {
                return params;
            }
        };
    }
}
