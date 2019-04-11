package np.edu.ku.kucc.Faculty_package;

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

public class BackgroundTask extends AsyncTask<String,Faculty,String> {

    Context ctx;
    FacultyAdapter facultyAdapter;
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
        FacultyDatabase myDB=new FacultyDatabase(ctx);
        if (method.equals("get_info"))
        {
            listView=(ListView)activity.findViewById(R.id.faculty_list);
            facultyAdapter=new FacultyAdapter(ctx,R.layout.list_single_faculty);
            SQLiteDatabase db=myDB.getReadableDatabase();
            Cursor cursor=myDB.getInfo(db);
            String name,designation,email,link,imageURL;
            while (cursor.moveToNext()) {
                name = cursor.getString(cursor.getColumnIndex(FacultyDatabase.COL_1));
                designation = cursor.getString(cursor.getColumnIndex(FacultyDatabase.COL_2));
                email = cursor.getString(cursor.getColumnIndex(FacultyDatabase.COL_3));
                link = cursor.getString(cursor.getColumnIndex(FacultyDatabase.COL_4));
                imageURL = cursor.getString(cursor.getColumnIndex(FacultyDatabase.COL_5));
                Faculty faculty=new Faculty(name,designation,email,link,imageURL);
                publishProgress(faculty);

            }
            return "get_info";
        }
        return null;
    }

    @Override
    public void onProgressUpdate(Faculty... values) {

        facultyAdapter.add(values[0]);

    }

    @Override
    public void onPostExecute(String result) {

        if (result.equals("get_info")) {
            if (facultyAdapter != null)
                listView.setAdapter(facultyAdapter);
            else
                Toast.makeText(ctx, "No offline data", Toast.LENGTH_LONG).show();
        }
        else
        {
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
        }
    }
}
