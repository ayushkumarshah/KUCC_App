package np.edu.ku.kucc.Events_Package;

import android.app.Activity;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.widget.ListView;
import android.widget.Toast;

import np.edu.ku.kucc.R;

/**
 * Created by ayush on 12/14/17.
 */

public class BackgroundTask extends AsyncTask<String,Events,String> {

    Context ctx;
    EventsAdapter eventsAdapter;
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
        EventsDatabase myDB=new EventsDatabase(ctx);
        if (method.equals("get_info"))
        {
            listView=(ListView)activity.findViewById(R.id.faculty_list);
            eventsAdapter =new EventsAdapter(ctx,R.layout.list_single_faculty);
            SQLiteDatabase db=myDB.getReadableDatabase();
            Cursor cursor=myDB.getInfo(db);
            String name,designation,email,link,imageURL;
            while (cursor.moveToNext()) {
                name = cursor.getString(cursor.getColumnIndex(EventsDatabase.COL_1));
                designation = cursor.getString(cursor.getColumnIndex(EventsDatabase.COL_2));
                email = cursor.getString(cursor.getColumnIndex(EventsDatabase.COL_3));
                link = cursor.getString(cursor.getColumnIndex(EventsDatabase.COL_4));
                imageURL = cursor.getString(cursor.getColumnIndex(EventsDatabase.COL_5));
                Events events =new Events(name,designation,email,link,imageURL);
                publishProgress(events);

            }
            return "get_info";
        }
        return null;
    }

    @Override
    public void onProgressUpdate(Events... values) {

        eventsAdapter.add(values[0]);

    }

    @Override
    public void onPostExecute(String result) {

        if (result.equals("get_info")) {
            if (eventsAdapter != null)
                listView.setAdapter(eventsAdapter);
            else
                Toast.makeText(ctx, "No offline data", Toast.LENGTH_LONG).show();
        }
        else
        {
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
        }
    }
}
