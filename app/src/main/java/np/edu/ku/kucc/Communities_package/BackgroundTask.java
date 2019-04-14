package np.edu.ku.kucc.Communities_package;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.util.Log;
import android.widget.ListView;
import android.widget.Toast;

import np.edu.ku.kucc.R;

/**
 * Created by ayush on 12/14/17.
 */

public class BackgroundTask extends AsyncTask<String,Communities,String> {

    Context ctx;
    CommunitiesAdapter communitiesAdapter;
    Activity activity;
    ListView listView;
    BackgroundTask(Context ctx)
    {
        this.ctx=ctx;
        activity=(Activity)ctx;
    }
    @Override
    public void onPreExecute() {

    }

    @Override
    public String doInBackground(String... params) {

        String method=params[0];
        CommunitiesDatabase myDB=new CommunitiesDatabase(ctx);
        if (method.equals("get_info"))
        {

            listView=(ListView)activity.findViewById(R.id.communities_list);
            communitiesAdapter =new CommunitiesAdapter(ctx,R.layout.list_single_communities);
            SQLiteDatabase db=myDB.getReadableDatabase();
            Cursor cursor=myDB.getInfo(db);
            String name,post,email,contact,imageURL;
            while (cursor.moveToNext()) {
                name = cursor.getString(cursor.getColumnIndex(CommunitiesDatabase.COL_1));
                post = cursor.getString(cursor.getColumnIndex(CommunitiesDatabase.COL_2));
                email = cursor.getString(cursor.getColumnIndex(CommunitiesDatabase.COL_3));
                contact = cursor.getString(cursor.getColumnIndex(CommunitiesDatabase.COL_4));
                imageURL = cursor.getString(cursor.getColumnIndex(CommunitiesDatabase.COL_5));
                Communities communities =new Communities(name,post,email,contact,imageURL);
                publishProgress(communities);

            }
            return "get_info";
        }
        return null;
    }

    @Override
    public void onProgressUpdate(Communities... values) {

        communitiesAdapter.add(values[0]);

    }

    @Override
    public void onPostExecute(String result) {

        if (result.equals("get_info")) {
            Log.e("communitiesdata",communitiesAdapter.toString());
            if (communitiesAdapter != null)
                listView.setAdapter(communitiesAdapter);
            else
                Toast.makeText(ctx, "No offline data", Toast.LENGTH_LONG).show();
        }
        else
        {
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
        }
    }
}
