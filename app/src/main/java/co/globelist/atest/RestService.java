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
import java.util.HashMap;
import java.util.Map;


public class RestService {
    private String host = "localhost";
    public StringRequest getStringRequest(Integer method, String path, final Map<String, String> params, final Callback callback) {
        String url = host + path;

        Response.Listener<String> response = new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                try {
                    JSONObject jsonResponse = new JSONObject(response).getJSONObject("form");
                    String site = jsonResponse.getString("site"),
                            network = jsonResponse.getString("network");
                    callback.success();
                } catch (JSONException e) {
                    Log.d("RestService", "The result is: " + response);
                    e.printStackTrace();
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
            protected Response<String> parseNetworkResponse(NetworkResponse response) {
                String parsed;
                Log.d("RestService", "Response headers: " + response.headers);
                try {
                    parsed = new String(response.data, HttpHeaderParser.parseCharset(response.headers));
                } catch (UnsupportedEncodingException e) {
                    parsed = new String(response.data);
                }
                return Response.success(parsed, HttpHeaderParser.parseCacheHeaders(response));
            }
            @Override
            protected Map<String, String> getParams()
            {
                return params;
            }
        };
    }
}
