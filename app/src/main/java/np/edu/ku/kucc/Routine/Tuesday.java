package np.edu.ku.kucc.Routine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import np.edu.ku.kucc.R;

import static np.edu.ku.kucc.MainActivity.mCourse;
import static np.edu.ku.kucc.MainActivity.mYearSem;

/**
 * A simple {@link Fragment} subclass.
 */
public class Tuesday extends Fragment {
    private List<routinelist> list;
    RecyclerView mRecyclerView;
    FirebaseDatabase mDatabase;
    DatabaseReference mDatabaseReference;


    public Tuesday() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view=inflater.inflate(R.layout.fragment_tuesday, container, false);
        mRecyclerView=(RecyclerView) view.findViewById(R.id.recyclertuesday);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list=new ArrayList<routinelist>();
        //Firebase RealTime Database
        mDatabase= FirebaseDatabase.getInstance();

        mDatabaseReference=mDatabase.getReference().child("Routines").child(mCourse).child(mYearSem).child("Tuesday");
        mDatabaseReference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()){
                    for (DataSnapshot dataSnapshot1:dataSnapshot.getChildren())
                    {
                        routinelist routinelistobj=dataSnapshot1.getValue(routinelist.class);
                        list.add(routinelistobj);
                        mRecyclerView = (RecyclerView) view.findViewById(R.id.recyclertuesday);
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