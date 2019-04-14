package np.edu.ku.kucc.KUCCBoard_package;

/**
 * Created by ayush on 8/13/17.
 */


import android.app.Activity;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

import np.edu.ku.kucc.Communities_package.CommunitiesDatabase;
import np.edu.ku.kucc.Faculty_package.FacultyDatabase;
import np.edu.ku.kucc.News_package.NewsDatabase;


public class KUCCBoardDatabase extends SQLiteOpenHelper {
    public static final String DATABASE_NAME = "KUCC.db";
    public static final String TABLE_NAME = "KUCCBoard";
    public static final String COL_1 = "name";
    public static final String COL_2 = "post";
    public static final String COL_3 = "email";
    public static final String COL_4 = "contact";
    public static final String COL_5 = "imageURL";




    private Activity context;

    public KUCCBoardDatabase(Context context) {
        super(context, DATABASE_NAME, null, 18);
        Log.e("iamat","KUCCBoardDatabase");
//        SQLiteDatabase db = this.getWritableDatabase();


    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        Log.v("iamat", "dboncreate");
        String SQL_CREATE_TABLE_NAME = "CREATE TABLE " + NewsDatabase.TABLE_NAME + " (" +
                NewsDatabase.COL_1 + " TEXT NOT NULL, " +
                NewsDatabase.COL_2 + " TEXT NOT NULL, " +
                NewsDatabase.COL_3 + " TEXT NOT NULL, " +
                NewsDatabase.COL_4 + " TEXT NOT NULL, " +
                NewsDatabase.COL_5 + " TEXT NOT NULL " +

                " )";
        db.execSQL(SQL_CREATE_TABLE_NAME);

        Log.v("databasecreated", "oncreate");

        Log.v("iamat", "dboncreate");
        SQL_CREATE_TABLE_NAME = "CREATE TABLE " + FacultyDatabase.TABLE_NAME + " (" +
                FacultyDatabase.COL_1 + " TEXT NOT NULL, " +
                FacultyDatabase.COL_2 + " TEXT NOT NULL, " +
                FacultyDatabase.COL_3 + " TEXT NOT NULL, " +
                FacultyDatabase.COL_4 + " TEXT NOT NULL, " +
                FacultyDatabase.COL_5 + " TEXT NOT NULL " +

                " )";
        db.execSQL(SQL_CREATE_TABLE_NAME);

        Log.v("databasecreated", "oncreate");

        Log.v("iamat", "dboncreate");
        SQL_CREATE_TABLE_NAME = "CREATE TABLE " + KUCCBoardDatabase.TABLE_NAME + " (" +
                KUCCBoardDatabase.COL_1 + " TEXT NOT NULL, " +
                KUCCBoardDatabase.COL_2 + " TEXT NOT NULL, " +
                KUCCBoardDatabase.COL_3 + " TEXT NOT NULL, " +
                KUCCBoardDatabase.COL_4 + " TEXT NOT NULL, " +
                KUCCBoardDatabase.COL_5 + " TEXT NOT NULL " +

                " )";
        db.execSQL(SQL_CREATE_TABLE_NAME);

        Log.v("databasecreated", "oncreate");

        Log.v("iamat", "dboncreate");
        SQL_CREATE_TABLE_NAME = "CREATE TABLE " + CommunitiesDatabase.TABLE_NAME + " (" +
                CommunitiesDatabase.COL_1 + " TEXT NOT NULL, " +
                CommunitiesDatabase.COL_2 + " TEXT NOT NULL, " +
                CommunitiesDatabase.COL_3 + " TEXT NOT NULL, " +
                CommunitiesDatabase.COL_4 + " TEXT NOT NULL, " +
                CommunitiesDatabase.COL_5 + " TEXT NOT NULL " +

                " )";
        db.execSQL(SQL_CREATE_TABLE_NAME);

        Log.v("databasecreated", "oncreate");


    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void dropDatabase() {
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("delete from "+ TABLE_NAME);

    }

    public boolean insertData(JSONObject information) {

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        try {
            contentValues.put(COL_1,  information.getString(COL_1));
            contentValues.put(COL_2, information.getString(COL_2));
            contentValues.put(COL_3,  information.getString(COL_3));
            contentValues.put(COL_4,  information.getString(COL_4));
            contentValues.put(COL_5,  information.getString(COL_5));

            Log.v("iamat", "insertData");
        } catch (JSONException e) {
            Log.v("Error", "Database JsonException");
        }
        db.insert(TABLE_NAME, null, contentValues);
        Log.v("iamat", "onerowinserted");

        db.close();
        return true;
    }




    public Cursor getInfo(SQLiteDatabase db) {
        String SQL_READ_TABLE_NAME = "SELECT * FROM " + TABLE_NAME ;
        Cursor cursor = db.rawQuery(SQL_READ_TABLE_NAME, null);

        return cursor;
    }




}