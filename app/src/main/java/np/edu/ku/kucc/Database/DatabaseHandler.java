package np.edu.ku.kucc.Database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class DatabaseHandler extends SQLiteOpenHelper {
    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "KUCC";
    private static final String TABLE_BATCH = "batch";
    private static final String KEY_ID = "id";
    private static final String KEY_YEAR = "year";
    private static final String KEY_SEM = "semester";

    public DatabaseHandler(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_TABLE = "CREATE TABLE " + TABLE_BATCH + "("
                + KEY_ID + " INTEGER PRIMARY KEY ," + KEY_YEAR + " TEXT,"
                + KEY_SEM + " TEXT" + ")";
        db.execSQL(CREATE_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_BATCH);

        // Create tables again
        onCreate(db);

    }
    // Insertion
    void addData(Data data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_YEAR, data.getYear()); // Contact Name
        values.put(KEY_SEM, data.getSemester()); // Contact Phone

        // Inserting Row
        db.insert(TABLE_BATCH, null, values);
        //2nd argument is String containing nullColumnHack
        db.close(); // Closing database connection
    }
    // code to get the single contact
    Data getData(int id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(TABLE_BATCH, new String[] { KEY_ID,
                        KEY_YEAR, KEY_SEM }, KEY_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Data data = new Data(Integer.parseInt(cursor.getString(0)),
                cursor.getString(1), cursor.getString(2));
        // return contact
        return data;
    }
    // code to update the single contact
    public int updateData(Data data) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(KEY_YEAR, data.getYear());
        values.put(KEY_SEM, data.getSemester());

        // updating row
        return db.update(TABLE_BATCH, values, KEY_ID + " = ?",
                new String[] { String.valueOf(data.getId()) });
    }
}
