package np.edu.ku.kucc;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


/**
 * A simple {@link Fragment} subclass.
 */
public class Reviews extends Fragment {

    EditText name,message;
    Button button_send_review;
    FirebaseDatabase mdatabase;
    FirebaseAuth mauth;
    DatabaseReference mref;
Activity activity;
    View rootView;
    DrawerLayout drawerLayout;
    ActionBarDrawerToggle abdToggle;

    public Reviews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        rootView=inflater.inflate(R.layout.fragment_reviews, container, false);
        activity=this.getActivity();
        activity.setTitle("Review");

        drawerLayout = activity.findViewById(R.id.drawer_layout);
        abdToggle = new ActionBarDrawerToggle(activity, drawerLayout, R.string.openDrawer, R.string.closeDrawer);
        drawerLayout.addDrawerListener(abdToggle);
        abdToggle.syncState();
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        name = (EditText) view.findViewById(R.id.editText_name_review);
        message = (EditText) view.findViewById(R.id.editText_message_review);
        button_send_review = (Button) view.findViewById(R.id.Button_send_review);
        mdatabase = FirebaseDatabase.getInstance();
        mauth = FirebaseAuth.getInstance();
        //after creating profile
        //FirebaseUser user = mauth.getCurrentUser();
        //String uid = mauth.getUid();
        mref = mdatabase.getReference("reviews").child("id");
        button_send_review.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                storeTofirebase();
                useGmail();

            }
        });
    }


    private void useGmail() {

        Intent intent = new Intent(Intent.ACTION_SEND);
        String[] emailstr = {"andro.community07@gmail.com"};
        intent.putExtra(Intent.EXTRA_EMAIL,emailstr);
        intent.putExtra(Intent.EXTRA_SUBJECT,"REVIEW_KUCC_APP");
        intent.putExtra(Intent.EXTRA_TEXT,message.getText().toString());
        intent.setType("message/rfc822");
        startActivity(Intent.createChooser(intent,"Choose an email client"));
    }

    private void storeTofirebase() {
        mref.child("name:").setValue(name.getText().toString());
        mref.child("message:").setValue(message.getText().toString());
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
