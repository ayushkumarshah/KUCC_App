package np.edu.ku.kucc.Routine;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import np.edu.ku.kucc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Monday extends Fragment {
    private RecyclerView mRecyclerView;
    private List<routinelist>list;
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReference;


    public Monday() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_monday, container, false);
        mRecyclerView=(RecyclerView) view.findViewById(R.id.recyclermonday);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list=new ArrayList<routinelist>();
        //Firebase RealTime Database
        mDatabase= MyDatabaseUtils.getDatabase();
        //CONVERSION
        SharedPreferences preferences = PreferenceManager.getDefaultSharedPreferences(getContext());
        String Course= preferences.getString("Course", null);
        String Year= preferences.getString("Year", null);
        String Semester= preferences.getString("Semester", null);
        Toast.makeText(getContext(), Course + " " + Year + " " + Semester,Toast.LENGTH_LONG).show();
        Conversion conversion=new Conversion();
        String mYearSem= conversion.YearSem(Year,Semester);
        String mCourse=conversion.Course(Course);
        Toast.makeText(getContext(), mYearSem + " " + mCourse ,Toast.LENGTH_LONG).show();

        mDatabaseReference=mDatabase.getReference().child("Routines").child(mCourse).child(mYearSem).child("Monday");
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                    {
                        routinelist routinelistobj=dataSnapshot1.getValue(routinelist.class);
                        list.add(routinelistobj);
                        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclermonday);
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
        return view;
    }

}

