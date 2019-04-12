package np.edu.ku.kucc.News_package;


import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
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
public class NewsFragment extends Fragment {

    Context context;
    Activity activity;
    ListView list;

    JSONArray jsonArray;
    JSONObject jsonObject;
    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview=inflater.inflate(R.layout.fragment_news, container, false);

        context = rootview.getContext();
        activity = this.getActivity();
        activity.setTitle("News");
       /* if (CheckInternetConnection(context))
        {

            getData();

        }
        else
        {
            BackgroundTask backgroundTask=new BackgroundTask(context);
            backgroundTask.execute("get_info");
        }*/
        BackgroundTask backgroundTask=new BackgroundTask(context);
        backgroundTask.execute("get_info");
//        getData();
        list = (ListView) rootview.findViewById(R.id.news_list);

        // Inflate the layout for this fragment
        return rootview;
    }

    public void getData(final Context cont) {

//        loading = ProgressDialog.show(context, "Please wait...", "Fetching...", false, false);

        String url = "http://ku.edu.np/kucc/database.json";
        Log.e("url:", url);

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
//                loading.dismiss();
                showJSON(response,cont);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        if (error != null && error.getMessage() != null) {
//                            Toast.makeText(context, error.getMessage().toString(), Toast.LENGTH_LONG).show();
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


    public void showJSON(String response,Context cont) {


        try {
            jsonObject = new JSONObject(response);
            Log.e("response:",(jsonObject.toString()));
            jsonArray=jsonObject.getJSONArray("news");
            Log.e("response:",(jsonArray.toString()));
            updateDataBase(jsonArray,cont);


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(cont, "Server Error", Toast.LENGTH_SHORT).show();
        }
    }
    public void updateDataBase(JSONArray json,Context cont) {
        NewsDatabase myDB=new NewsDatabase(cont);
        myDB.dropDatabase();
        Log.v("iamat","newsdatabase");

        Log.e("length",Integer.toString(json.length()));

        for (int i = 0; i < json.length(); i++) {

            try {
                Log.e("jsondata",json.getJSONObject(i).toString());
                //insertData(json.getJSONObject(i),i);
                myDB.insertData(json.getJSONObject(i));

                //Toast.makeText(activity, "Update Successfull", Toast.LENGTH_SHORT).show();

            } catch (JSONException e) {
                Toast.makeText(cont, "Server Error", Toast.LENGTH_SHORT).show();


            }
        }


    }

   /* public static boolean CheckInternetConnection(Context context) {
        ConnectivityManager connectivity =
                (ConnectivityManager) context.getSystemService(
                        Context.CONNECTIVITY_SERVICE);
        if (connectivity != null) {
            NetworkInfo[] info = connectivity.getAllNetworkInfo();
            if (info != null)
                for (int i = 0; i < info.length; i++)
                    if (info[i].getState() == NetworkInfo.State.CONNECTED) {
                        return true;
                    }
        }
        return false;
    }*/
}
