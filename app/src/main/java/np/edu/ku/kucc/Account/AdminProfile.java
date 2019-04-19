package np.edu.ku.kucc.Account;


import android.app.Activity;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
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
    View rootView;
    Activity activity;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle abdToggle;


    public AdminProfile() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        rootView = inflater.inflate(R.layout.fragment_admin_profile, container, false);
        list = new ArrayList<>();
        activity=this.getActivity();
        activity.setTitle("Add Events");

        drawerLayout = activity.findViewById(R.id.drawer_layout);
        abdToggle = new ActionBarDrawerToggle(activity, drawerLayout, R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.addDrawerListener(abdToggle);
        abdToggle.syncState();
        FirebaseApp.initializeApp(getContext());
        firebaseAuth = FirebaseAuth.getInstance();
        btnSignOut=(Button) rootView.findViewById(R.id.btnLogout);
        btnFloatingActionButton=(FloatingActionButton) rootView.findViewById(R.id.floatingActionButton);
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
                        mRecyclerView=(RecyclerView) rootView.findViewById(R.id.recyclerView);
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

        return  rootView;
    }
    private void changeFragment(Fragment targetFragment){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)

                .commit();
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

                    if (drawerLayout.isDrawerOpen(GravityCompat.START)) {

                        drawerLayout.closeDrawer(GravityCompat.START);

                    }
                    else {
                        Log.e("apkflow","popBack");
                        getActivity().getSupportFragmentManager().popBackStack();
                        activity.setTitle("KUCC");
                    }
                    return true;
                }
                return false;
            }
        });
    }
}
