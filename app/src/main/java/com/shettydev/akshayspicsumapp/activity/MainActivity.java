package com.shettydev.akshayspicsumapp.activity;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.widget.GridView;
import android.widget.RelativeLayout;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;

import com.shettydev.akshayspicsumapp.R;
import com.shettydev.akshayspicsumapp.Utils.utils;
import com.shettydev.akshayspicsumapp.adapter.ListAdapter;
import com.shettydev.akshayspicsumapp.model.ListResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static android.view.View.GONE;

public class MainActivity extends AppCompatActivity {
    protected static String TAG = MainActivity.class.getSimpleName();

    private final String JSON_URL = "https://picsum.photos/list";
    private JsonArrayRequest request;
    private RequestQueue requestQueue;
    private List<ListResponse> listDataArray;
    protected RelativeLayout rlContainer;
    protected GridView gridView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        listDataArray = new ArrayList<>();
         gridView = findViewById(R.id.gridview);
        rlContainer=findViewById(R.id.rlContainer);
        if (utils.isConnected(MainActivity.this)) {
            APICall();

        } else {
            utils.showSmallAlert(rlContainer, getString(R.string.no_internet));
        }
    }



    private void APICall() {

        request = new JsonArrayRequest(JSON_URL, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {

                JSONObject jsonObject = null;

                for (int i = 0; i < response.length(); i++) {


                    try {
                        jsonObject = response.getJSONObject(i);
                        ListResponse listResponse = new ListResponse();
                        listResponse.setId(jsonObject.getString("id"));
                        listResponse.setAuthor(jsonObject.getString("author"));
                        listResponse.setFilename(jsonObject.getString("filename"));
                        Log.d("@@AK id", jsonObject.getString("id"));
                     
                        listDataArray.add(listResponse);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }


                }

                setupGridView(listDataArray);

            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {


            }
        });


        requestQueue = Volley.newRequestQueue(MainActivity.this);
        requestQueue.add(request);


    }

    private void setupGridView(List<ListResponse> listDataArray) {
        ListAdapter baseAdapter = new ListAdapter(this, listDataArray);
        gridView.setAdapter(baseAdapter);
    }

}
