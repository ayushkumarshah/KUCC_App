package np.edu.ku.kucc.Account;


import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import np.edu.ku.kucc.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class Login extends Fragment {
    //defining views
    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;


    //firebase auth object
    private FirebaseAuth firebaseAuth;

    //progress dialog
    private ProgressDialog progressDialog;


    public Login() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_login, container, false);
        //getting firebase auth object
        FirebaseApp.initializeApp(getContext());
        firebaseAuth = FirebaseAuth.getInstance();

        //if the objects getcurrentuser method is not null
        //means user is already logged in
        if(firebaseAuth.getCurrentUser() != null){
            //close this activity
            //getActivity().finish();
            //opening profile activity
           // startActivity(new Intent(getContext(), AdminProfile.class));
            changeFragment(new AdminProfile());
        }

        editTextEmail = (EditText) view.findViewById(R.id.editTextEmail);
        editTextPassword = (EditText) view.findViewById(R.id.editTextPassword);
        buttonSignIn = (Button) view.findViewById(R.id.btnLogin);

        progressDialog = new ProgressDialog(getContext());
        buttonSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AdminLogin(v);
            }
        });

        return view;
    }
    private void changeFragment(Fragment targetFragment){
        getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)

                .commit();
    }


    public void AdminLogin(View view) {
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();


        //checking if email and passwords are empty
        if(TextUtils.isEmpty(email)){
            Toast.makeText(getActivity(),"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }

        if(TextUtils.isEmpty(password)){
            Toast.makeText(getActivity(),"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage("Logining In Please Wait...");
        progressDialog.show();

        //logging in the user
        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(getActivity(), new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();
                        //if the task is successfull
                        if(task.isSuccessful()){
                            //start the profile activity
                             // getActivity().finish();
                            //startActivity(new Intent(getActivity(), AdminProfile.class));
                            changeFragment(new AdminProfile());
                        }
                        else
                        {
                            Toast.makeText(getActivity(),"ERROR",Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }
}
