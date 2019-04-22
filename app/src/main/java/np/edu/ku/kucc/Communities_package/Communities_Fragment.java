package np.edu.ku.kucc.Communities_package;


import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.NetworkError;
import com.android.volley.NetworkResponse;
import com.android.volley.NoConnectionError;
import com.android.volley.ParseError;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.ServerError;
import com.android.volley.TimeoutError;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Objects;

import np.edu.ku.kucc.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Communities_Fragment extends Fragment {

    Context context;
    Activity activity;
    ListView list;

    JSONArray jsonArray;
    JSONObject jsonObject;
    View rootview;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle abdToggle;
    public Communities_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootview = inflater.inflate(R.layout.fragment_communities, container, false);
        context = rootview.getContext();
        activity = this.getActivity();
        activity.setTitle("Community Coordinators");


        BackgroundTask backgroundTask = new BackgroundTask(context);
        backgroundTask.execute("get_info");

        return rootview;
    }

    public void getData(final Context cont) {


        String url = "http://ku.edu.np/kucc/database.json";
        Log.e("url:", url);

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                showJSON(response, cont);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null && error.getMessage() != null) {
                            Toast.makeText(cont, error.getMessage(), Toast.LENGTH_LONG).show();
                        }

                        else{
                            Toast.makeText(cont,"Something went wrong", Toast.LENGTH_LONG).show();

                        }
                        NetworkResponse networkResponse = null;
                        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.KITKAT) {
                            networkResponse = Objects.requireNonNull(error).networkResponse;
                        }
                        if (networkResponse != null) {
                            Log.e("Volley", "Error. HTTP Status Code:"+networkResponse.statusCode);
                        }

                        if (error instanceof TimeoutError) {
                            Log.e("Volley", "TimeoutError");
                        }else if(error instanceof NoConnectionError){
                            Log.e("Volley", "NoConnectionError");
                            Toast.makeText(cont,"No internet connection", Toast.LENGTH_SHORT).show();
//                            BackgroundTask backgroundTask=new BackgroundTask(context);
//                            backgroundTask.execute("get_info");

                        } else if (error instanceof AuthFailureError) {
                            Log.e("Volley", "AuthFailureError");
                        } else if (error instanceof ServerError) {
                            Log.e("Volley", "ServerError");
                        } else if (error instanceof NetworkError) {
                            Log.e("Volley", "NetworkError");
                        } else if (error instanceof ParseError) {
                            Log.e("Volley", "ParseError");
                        }

                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(cont);
        requestQueue.add(stringRequest);
    }


    public void showJSON(String response, Context cont) {


        try {
            jsonObject = new JSONObject(response);
            Log.e("response:", (jsonObject.toString()));
            jsonArray = jsonObject.getJSONArray("communityCoordinators");
            Log.e("response:", (jsonArray.toString()));
            updateDataBase(jsonArray, cont);


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(cont, "Server Error", Toast.LENGTH_SHORT).show();
        }
    }

    public void updateDataBase(JSONArray json, Context cont) {
        CommunitiesDatabase myDB = new CommunitiesDatabase(cont);
        myDB.dropDatabase();
        Log.v("iamat", "communitiesdatabase");

        Log.e("length", Integer.toString(json.length()));

        for (int i = 0; i < json.length(); i++) {

            try {
                Log.e("jsondata", json.getJSONObject(i).toString());
                //insertData(json.getJSONObject(i),i);
                myDB.insertData(json.getJSONObject(i));

                //Toast.makeText(activity, "Update Successfull", Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                Toast.makeText(cont, "Server Error", Toast.LENGTH_SHORT).show();


            }
        }
    }
    @Override
    public void onResume() {
        Log.e("apkflow","onResume CallLog_Fragment");
        super.onResume();
        rootview.setFocusableInTouchMode(true);
        rootview.requestFocus();
        rootview.setOnKeyListener(new View.OnKeyListener() {
            @Override
            public boolean onKey(View v, int keyCode, KeyEvent event) {
                if (event.getAction() == KeyEvent.ACTION_UP && keyCode == KeyEvent.KEYCODE_BACK){
                    Log.e("apkflow","CallLog_Fragment back Clicked");


                        Log.e("apkflow","popBack");
                        getActivity().getSupportFragmentManager().popBackStack();
                        activity.setTitle("KUCC");

                    return true;
                }
                return false;
            }
        });
    }
}
