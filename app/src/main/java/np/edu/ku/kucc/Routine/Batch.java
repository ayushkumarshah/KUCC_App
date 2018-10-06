package np.edu.ku.kucc.Routine;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import np.edu.ku.kucc.Database.SharedPref;
import np.edu.ku.kucc.MainActivity;
import np.edu.ku.kucc.R;

public class Batch extends AppCompatActivity {
    private String mYear, mSemester, mCourse;
    private TextView mSetYear, mSetSemester, mSetCourse;
    private Button mSave;

    public Batch() {
        // Required empty public constructor
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_batch);
        //Default Value
        mCourse="Computer Science";
        mSemester="1st Sem";
        mYear="1st Year";






            mSetCourse=(TextView)findViewById(R.id.set_course);
            mSetYear=(TextView)findViewById(R.id.set_year);
            mSetSemester=(TextView)findViewById(R.id.set_sem);
            mSave=(Button) findViewById(R.id.button2);
            mSave.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (mYear.length()!=0 && mSemester.length()!=0 && mCourse.length()!=0){
                        Intent intent=new Intent(Batch.this, MainActivity.class);
                        startActivity(intent);
                        finish();
                        SharedPref sharedPref= new SharedPref(Batch.this);
                        sharedPref.suse();


                    }
                    else if (mCourse.length()==0)
                    {
                        mSetCourse.setError("Course cannot be empty");
                    }
                    else if (mYear.length()==0)
                    {
                        mSetYear.setError("Year cannot be empty");
                    }
                    else if (mSemester.length()==0)
                    {
                        mSetSemester.setError("Semester cannot be empty");
                    }

                }
            });

        }
        public void selectCourse(View v){
            final String[] items = new String[2];

            items[0] = "Computer Science";
            items[1] = "Computer Engineering";

            // Create List Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(Batch.this);
            builder.setTitle("Select Course");
            builder.setItems(items, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int item) {

                    mCourse = items[item];
                    mSetCourse.setText(mCourse);
                    // Access Shared Preferences
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Batch.this);
                    SharedPreferences.Editor editor = preferences.edit();

                    // Save to SharedPreferences
                    editor.putString("Course", mCourse);
                    editor.apply();

                }
            });
            AlertDialog alert = builder.create();
            alert.show();

        }
        public void selectYear(View v){
            final String[] items = new String[4];

            items[0] = "1st Year";
            items[1] = "2nd Year";
            items[2] = "3rd Year";
            items[3] = "4th Year";

            // Create List Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(Batch.this);
            builder.setTitle("Select Year");
            builder.setItems(items, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int item) {

                    mYear = items[item];
                    mSetYear.setText(mYear);
                    // Access Shared Preferences
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Batch.this);
                    SharedPreferences.Editor editor = preferences.edit();

                    // Save to SharedPreferences
                    editor.putString("Year", mYear);
                    editor.apply();

                }
            });
            AlertDialog alert = builder.create();
            alert.show();

        }
        public void selectSemester(View v){
            final String[] items = new String[2];

            items[0] = "1st Sem";
            items[1] = "2nd Sem";

            // Create List Dialog
            AlertDialog.Builder builder = new AlertDialog.Builder(Batch.this);
            builder.setTitle("Select Semester");
            builder.setItems(items, new DialogInterface.OnClickListener() {

                public void onClick(DialogInterface dialog, int item) {

                    mSemester = items[item];
                    mSetSemester.setText(mSemester);
                    // Access Shared Preferences
                    SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(Batch.this);
                    SharedPreferences.Editor editor = preferences.edit();

                    // Save to SharedPreferences
                    editor.putString("Semester", mSemester);
                    editor.apply();

                }
            });
            AlertDialog alert = builder.create();
            alert.show();

        }



    }

