package np.edu.ku.kucc.Notes_list;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.ArrayList;
import java.util.List;

import np.edu.ku.kucc.MainActivity;
import np.edu.ku.kucc.R;
import np.edu.ku.kucc.Routine.Conversion;

import static np.edu.ku.kucc.MainActivity.mCourse;
import static np.edu.ku.kucc.MainActivity.mYearSem;


/**
 * A simple {@link Fragment} subclass.
 */
public class Notes extends Fragment implements AdapterView.OnItemSelectedListener {
    private List<NotesList> list;
    RecyclerView mRecyclerView;
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReference;
    Spinner Course,Year,Semester;
    TextView Notes_title;
    String course_value,year_value,semester_value;
    Button Search;
    Activity activity;
    View rootView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle abdToggle;

    public Notes() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
       activity=this.getActivity();
        activity.setTitle("Notes");

        FirebaseFirestore db=FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();

        db.setFirestoreSettings(settings);
        rootView=inflater.inflate(R.layout.fragment_notes, container, false);
        /*drawerLayout = activity.findViewById(R.id.drawer_layout);
        abdToggle = new ActionBarDrawerToggle(activity, drawerLayout, R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.addDrawerListener(abdToggle);
        abdToggle.syncState();*/
        mRecyclerView=(RecyclerView) rootView.findViewById(R.id.recyclernotes);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list=new ArrayList<NotesList>();
        Year = (Spinner) rootView.findViewById(R.id.year_value);
        Course = (Spinner) rootView.findViewById(R.id.course_value);
        Semester = (Spinner) rootView.findViewById(R.id.semester_value);
        Search=(Button) rootView.findViewById(R.id.search);
        Notes_title=(TextView)rootView.findViewById(R.id.notes_title);
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        MainActivity.Course= preferences.getString("Course", null);
        MainActivity.Year= preferences.getString("Year", null);
        MainActivity.Semester= preferences.getString("Semester", null);
        Conversion conversion=new Conversion();
        mYearSem= conversion.YearSem(MainActivity.Year,MainActivity.Semester);
        mCourse=conversion.Course(MainActivity.Course);
//        loadresults(mYearSem,mCourse,view);


        Log.e("course",MainActivity.Course);
        Log.e("year",MainActivity.Year);
        Log.e("sem",MainActivity.Semester);
        Log.e("sem",MainActivity.mYearSem);
        Log.e("sem",MainActivity.mCourse);

        Notes_title.setText(MainActivity.mCourse+" "+MainActivity.Year+" "+ MainActivity.Semester);

        mDatabase= FirebaseDatabase.getInstance();


        mDatabaseReference=mDatabase.getReference().child("Notes").child(mCourse).child(mYearSem);
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                    {
                        NotesList notesListobj=dataSnapshot1.getValue(NotesList.class);

                        list.add(notesListobj);
                        mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclernotes);
                        mRecyclerView.setHasFixedSize(true);

                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                        //creating recyclerview adapter
                        RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), list);

                        //setting adapter to recyclerview
                        mRecyclerView.setAdapter(adapter);

                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(), R.array.year, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        Year.setAdapter(adapter);
        Year.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(getContext(), R.array.course, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        Course.setAdapter(adapter2);
        Course.setOnItemSelectedListener(this);

        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter3 = ArrayAdapter.createFromResource(getContext(), R.array.semester, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        Semester.setAdapter(adapter3);
        Semester.setOnItemSelectedListener(this);



        Search.setOnClickListener(new View.OnClickListener() {

          @Override
          public void onClick(View v) {
              switch (v.getId()) {
                  case R.id.search:
                      list=new ArrayList<NotesList>();
                      year_value=Year.getSelectedItem().toString();
                      course_value=Course.getSelectedItem().toString();
                      semester_value=Semester.getSelectedItem().toString();
                      Log.e("course",course_value);
                      Log.e("year",year_value);
                      Log.e("sem",semester_value);
                      Conversion conversion=new Conversion();
                      String mYearSem=conversion.YearSem(year_value,semester_value);
                      String mCourse=conversion.Course(course_value);
                      Log.e("mcourse",mCourse);
                      Log.e("myear",mYearSem);
                      Notes_title.setText(mCourse+" "+year_value+" "+ semester_value);



                      mDatabaseReference=mDatabase.getReference().child("Notes").child(mCourse).child(mYearSem);
                      mDatabaseReference.addValueEventListener(new ValueEventListener() {
                          @Override
                          public void onDataChange(DataSnapshot dataSnapshot) {
                              if (dataSnapshot.exists()){
                                  for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                                  {
                                      NotesList notesListobj=dataSnapshot1.getValue(NotesList.class);

                                      list.add(notesListobj);
                                      mRecyclerView = (RecyclerView) rootView.findViewById(R.id.recyclernotes);
                                      mRecyclerView.setHasFixedSize(true);

                                      mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                                      //creating recyclerview adapter
                                      RecyclerViewAdapter adapter = new RecyclerViewAdapter(getContext(), list);

                                      //setting adapter to recyclerview
                                      mRecyclerView.setAdapter(adapter);

                                  }
                              }
                          }

                          @Override
                          public void onCancelled(DatabaseError databaseError) {

                          }
                      });
                            }
                        }
                     }
                    );
        //Firebase RealTime Database

        return rootView;

    }


    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }


    @Override
    public void onResume() {
        Log.e("apkflow","onResume CallLog_Fragment");
        super.onResume();
        rootView.setFocusableInTouchMode(true);
        rootView.requestFocus();
        rootView.setOnKeyListener(new View.OnKeyListener() {
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
