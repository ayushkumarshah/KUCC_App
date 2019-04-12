package np.edu.ku.kucc;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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

    public Reviews() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_reviews, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        activity=this.getActivity();
        activity.setTitle("Review");

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

}
