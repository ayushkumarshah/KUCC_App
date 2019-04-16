package np.edu.ku.kucc.Routine;


import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
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

import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.ArrayList;
import java.util.List;

import np.edu.ku.kucc.MainActivity;

import np.edu.ku.kucc.R;

import static np.edu.ku.kucc.MainActivity.mCourse;
import static np.edu.ku.kucc.MainActivity.mYearSem;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sunday extends Fragment {
    private List<routinelist> list;


    RecyclerView mRecyclerView;
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReference;

    public Sunday() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        FirebaseFirestore db=FirebaseFirestore.getInstance();
        FirebaseFirestoreSettings settings = new FirebaseFirestoreSettings.Builder()
                .setPersistenceEnabled(true)
                .build();
        db.setFirestoreSettings(settings);
        final View view=inflater.inflate(R.layout.fragment_sunday, container, false);;
        mRecyclerView=(RecyclerView) view.findViewById(R.id.recyclersunday);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list=new ArrayList<routinelist>();


        /*

        list.add(
                new routinelist(
                        "COMP 201",
                        "computer",
                        "Ayush",
                        "9:00"
                ));

        list.add(
                new routinelist(
                        "COMP 301",
                        "Math",
                        "Shoaib",
                        "10:00"
                ));

        list.add(
                new routinelist(
                        "COMP 401",
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 inch, Silver, 1.35 kg",
                        "11:00"
                ));


        //mRecyclerView.setAdapter(adapter1);
        //Document Reference
        */
        /*
        db.collection("/Routines/CE/1Y1S/Sunday/1")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        for (DocumentSnapshot querySnapshot:task.getResult())
                        {
                            routinelist routinelistobj=new routinelist(querySnapshot.getString("Course_Id"),
                                    querySnapshot.getString("Course_Name"),
                                    querySnapshot.getString("Course_Inst"),
                                    querySnapshot.getString("Course_Time"));
                            list.add(routinelistobj);
                            RecyclerViewAdapterEvent adapter1=new RecyclerViewAdapterEvent(getContext(),list);
                            mRecyclerView.setAdapter(adapter1);
                        }

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(getContext(),"Error Fetching routine",Toast.LENGTH_LONG).show();

                    }
                });
           */

        //TESTING

        Toast.makeText(getContext(), mYearSem + " " + mCourse ,Toast.LENGTH_LONG).show();

        //Firebase RealTime Database
        mDatabase= MyDatabaseUtils.getDatabase();
        mDatabaseReference=mDatabase.getReference().child("Routines").child(mCourse).child(mYearSem).child("Sunday");
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                    {
                        routinelist routinelistobj=dataSnapshot1.getValue(routinelist.class);
                        list.add(routinelistobj);
                        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclersunday);
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
