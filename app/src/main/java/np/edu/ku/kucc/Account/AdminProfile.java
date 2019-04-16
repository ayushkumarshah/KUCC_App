package np.edu.ku.kucc.Account;


import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import np.edu.ku.kucc.R;
import np.edu.ku.kucc.Routine.RecyclerViewAdapter;
import np.edu.ku.kucc.Routine.routinelist;

import static android.content.ContentValues.TAG;

/**
 * A simple {@link Fragment} subclass.
 */
public class AdminProfile extends Fragment {
    Button btnSignOut;
    FloatingActionButton btnFloatingActionButton;
    private FirebaseAuth firebaseAuth;
    private List<EventModel> list;
    RecyclerView mRecyclerView;
    DatabaseReference mDatabase;



    public AdminProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_admin_profile, container, false);
        list = new ArrayList<>();
        FirebaseApp.initializeApp(getContext());
        firebaseAuth = FirebaseAuth.getInstance();
        btnSignOut=(Button) view.findViewById(R.id.btnLogout);
        btnFloatingActionButton=(FloatingActionButton) view.findViewById(R.id.floatingActionButton);
        btnSignOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                firebaseAuth.signOut();
                changeFragment(new Login());

            }
        });
        btnFloatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                changeFragment(new Register());
            }
        });

        //Retrieve From Database
        mDatabase = FirebaseDatabase.getInstance().getReference("Events");

        mDatabase.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                        //routinelist routinelistobj = dataSnapshot1.getValue(routinelist.class);

                        EventModel eventModel = dataSnapshot1.getValue(EventModel.class);
                       // Toast.makeText(getContext(),eventModel.getmTitle(),Toast.LENGTH_LONG).show();

                        //RECYCLER VIEW

                        list.add(eventModel);
                        mRecyclerView=(RecyclerView) view.findViewById(R.id.recyclerView);
                        mRecyclerView.setHasFixedSize(true);
                        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                        //creating recyclerview adapter
                        RecyclerViewAdapterEvent adapter= new RecyclerViewAdapterEvent(getContext(),list);

                        //setting adapter to recyclerview
                        mRecyclerView.setAdapter(adapter);


                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });

        return  view;
    }
    private void changeFragment(Fragment targetFragment){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)

                .commit();
    }

}
