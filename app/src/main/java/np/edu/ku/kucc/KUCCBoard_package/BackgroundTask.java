package np.edu.ku.kucc.KUCCBoard_package;

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

public class BackgroundTask extends AsyncTask<String,KUCCBoard,String> {

    Context ctx;
    KUCCBoardAdapter kUCCBoardAdapter;
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
        KUCCBoardDatabase myDB=new KUCCBoardDatabase(ctx);
        if (method.equals("get_info"))
        {
            listView=(ListView)activity.findViewById(R.id.kuccboard_list);
            kUCCBoardAdapter =new KUCCBoardAdapter(ctx,R.layout.list_single_kucc_board);
            SQLiteDatabase db=myDB.getReadableDatabase();
            Cursor cursor=myDB.getInfo(db);
            String name,post,email,contact,imageURL;
            while (cursor.moveToNext()) {
                name = cursor.getString(cursor.getColumnIndex(KUCCBoardDatabase.COL_1));
                post = cursor.getString(cursor.getColumnIndex(KUCCBoardDatabase.COL_2));
                email = cursor.getString(cursor.getColumnIndex(KUCCBoardDatabase.COL_3));
                contact = cursor.getString(cursor.getColumnIndex(KUCCBoardDatabase.COL_4));
                imageURL = cursor.getString(cursor.getColumnIndex(KUCCBoardDatabase.COL_5));
                KUCCBoard kUCCBoard =new KUCCBoard(name,post,email,contact,imageURL);
                publishProgress(kUCCBoard);

            }
            return "get_info";
        }
        return null;
    }

    @Override
    public void onProgressUpdate(KUCCBoard... values) {

        kUCCBoardAdapter.add(values[0]);

    }

    @Override
    public void onPostExecute(String result) {

        if (result.equals("get_info")) {
            if (kUCCBoardAdapter != null)
                listView.setAdapter(kUCCBoardAdapter);
            else
                Toast.makeText(ctx, "No offline data", Toast.LENGTH_LONG).show();
        }
        else
        {
        Toast.makeText(ctx,result,Toast.LENGTH_LONG).show();
        }
    }
}
