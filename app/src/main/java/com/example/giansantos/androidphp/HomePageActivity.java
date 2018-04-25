package com.example.giansantos.androidphp;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomePageActivity extends AppCompatActivity{
    private static final String PREF_FILE_NAME = "UserInfo";
    private TextView likes_view;
    private TextView followers_view;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.home_page);

        Button most_likes = (Button) findViewById(R.id.most_likes);
        Button most_followers = (Button) findViewById(R.id.most_followers);
        likes_view = (TextView) findViewById(R.id.likes_view);
        followers_view = (TextView) findViewById(R.id.followers_view);
        most_likes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query1();
            }
        });

        most_followers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                query2();
            }
        });

        query6();
    }

    private void query1() {
        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.URL_Query1, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                likes_view.setText(response);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(App.TAG, "Query1 Error: " + error.getMessage());
            }
        });
        App.getInstance().addToRequestQueue(strReq);
    }

    private void query2() {
        StringRequest strReq = new StringRequest(Request.Method.GET, AppConfig.URL_Query2, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                followers_view.setText(response);
            }
        }, new Response.ErrorListener(){
            @Override
            public void onErrorResponse(VolleyError error) {
                Log.e(App.TAG, "Query2 Error: " + error.getMessage());
            }
        });
        App.getInstance().addToRequestQueue(strReq);
    }

    private void query6() {
        final ArrayList<Messages> messages = new ArrayList<Messages>();
        StringRequest strReq = new StringRequest(Request.Method.POST,
                AppConfig.URL_Query6, new Response.Listener<String>() {

            @Override

            public void onResponse(String response) {
                Log.d("loco", "Login Response: " + response);
                try {
                    JSONArray obj = new JSONArray(response);
                    Log.d("HI", "BYe");
                    Log.d("ercy", obj.toString());
                    //JSONArray jArray = obj.getJSONArray("messages");
                    for(int i = 0; i < obj.length(); i++){
                        JSONObject messagesArray = obj.getJSONObject(i);
                        Log.d("fuck", messagesArray.toString());
                        messages.add(new Messages(messagesArray.getString("username"), messagesArray.getString("body"), messagesArray.getString("date")));
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
        }, new Response.ErrorListener() {

            @Override

            public void onErrorResponse(VolleyError error) {

                Log.e(App.TAG, "Login Error: " + error.getMessage());
            }
        }) {

            @Override

            protected Map<String, String> getParams() {
                // Posting parameters to login url

                SharedPreferences userinfo = getSharedPreferences(PREF_FILE_NAME, MODE_PRIVATE);
                String username = userinfo.getString("username", "");
                Log.d("Username", username);

                Map<String, String> params = new HashMap<>();
                params.put("username", username);

                return params;
            }
        };

        // Adding request to request queue

        App.getInstance().addToRequestQueue(strReq);
//        JsonArrayRequest jsonReq = new JsonArrayRequest(Request.Method.GET, AppConfig.URL_Query6, new Response.Listener<JSONArray>() {
//            @Override
//            public void onResponse(JSONArray response) {
//                try {
//                    for(int i = 0; i < response.length(); i++){
//                        JSONObject obj = response.getJSONObject(i);
//                        messages.add(new Messages(obj.getString("username"), obj.getString("body"), obj.getString("send_time")));
//                    }
//
//                } catch (JSONException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e(App.TAG, "Query 6 Error: " + error.getMessage());
//            }
//        });
        MessagesAdapter adapter = new MessagesAdapter(this, messages);
        ListView listview = (ListView) findViewById(R.id.listview);
        listview.setAdapter(adapter);
//        App.getInstance().addToRequestQueue(jsonReq);
    }
}
