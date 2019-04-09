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

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import np.edu.ku.kucc.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewsFragment extends Fragment {

    Context context;
    Activity activity;
    ListView list;

    private ProgressDialog loading;
    JSONArray jsonArray;
    JSONObject jsonObject;
private WebView webView;
    public NewsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootview=inflater.inflate(R.layout.fragment_news, container, false);
//        webView=(WebView)root.findViewById(R.id.webview);
//        webView.getSettings().setJavaScriptEnabled(true);
//        webView.setWebViewClient(new WebViewClient());
//        webView.loadUrl("http://ku.edu.np/kucc/#/news");

        context = rootview.getContext();
        activity = getActivity();
        getData();
        list = (ListView) rootview.findViewById(R.id.news_list);

        // Inflate the layout for this fragment
        return rootview;
    }

    private void getData() {

        loading = ProgressDialog.show(context, "Please wait...", "Fetching...", false, false);

        String url = "http://ku.edu.np/kucc/database.json";
        Log.e("url:", url);

        StringRequest stringRequest = new StringRequest(url, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                loading.dismiss();
                showJSON(response);
            }
        },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        Toast.makeText(context, error.getMessage().toString(), Toast.LENGTH_LONG).show();
                    }
                });

        RequestQueue requestQueue = Volley.newRequestQueue(context);
        requestQueue.add(stringRequest);
    }


    private void showJSON(String response) {


        try {
            jsonObject = new JSONObject(response);
            Log.e("response:",(jsonObject.toString()));
            jsonArray=jsonObject.getJSONArray("news");
            Log.e("response:",(jsonArray.toString()));
            updateDataBase(jsonArray);


        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(context, "Server Error", Toast.LENGTH_SHORT).show();
        }
    }
    private void updateDataBase(JSONArray json) {
        NewsDatabase myDB=new NewsDatabase(this.activity);
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
                Toast.makeText(activity, "Server Error", Toast.LENGTH_SHORT).show();


            }
        }
        BackgroundTask backgroundTask=new BackgroundTask(context);
        backgroundTask.execute("get_info");

    }

    public static boolean CheckInternetConnection(Context context) {
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
    }
}
