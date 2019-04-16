package np.edu.ku.kucc.Account;


import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreSettings;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import np.edu.ku.kucc.R;
import np.edu.ku.kucc.Routine.RecyclerViewAdapter;
import np.edu.ku.kucc.Routine.routinelist;

import static java.lang.Integer.parseInt;


/**
 * A simple {@link Fragment} subclass.
 */
public class Register extends Fragment {
    private String mTime;
    private String mDate;
    private String mTitle, mVenue, mRoomNo, mInformation;
    private int mContact;
    private EditText mDateText, mTimeText;
    private EditText mTitleText, mVenueText, mRoomNoText, mInformationText, mContactText;
    private Button btnAddEvent;
    private List<EventModel> mEventList;
    private DatabaseReference mDatabase;







    public Register() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        final View view = inflater.inflate(R.layout.fragment_register, container, false);
        mTitleText=(EditText)view.findViewById(R.id.editText2);
        mVenueText=(EditText)view.findViewById(R.id.editText3);
        mRoomNoText=(EditText)view.findViewById(R.id.editText4);
        mInformationText=(EditText)view.findViewById(R.id.editText7);
        mContactText=(EditText)view.findViewById(R.id.editText6);
        mTimeText = (EditText) view.findViewById(R.id.editText9);
        btnAddEvent = (Button) view.findViewById(R.id.button3);

        mEventList=new ArrayList<>();




        mTimeText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(getContext(), new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        mTimeText.setText( selectedHour + ":" + selectedMinute);
                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
        mDateText = (EditText) view.findViewById(R.id.editText10);
        mDateText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Calendar cldr = Calendar.getInstance();
                int day = cldr.get(Calendar.DAY_OF_MONTH);
                int month = cldr.get(Calendar.MONTH);
                int year = cldr.get(Calendar.YEAR);
                // date picker dialog
                DatePickerDialog picker = new DatePickerDialog(getContext(),
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                mDateText.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);
                            }
                        }, year, month, day);
                picker.show();

            }
        });
        //DATABASE
        mDatabase = FirebaseDatabase.getInstance().getReference("Events");

        //addevent
        btnAddEvent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTitle=mTitleText.getText().toString();
                mVenue=mVenueText.getText().toString();
                mRoomNo=mRoomNoText.getText().toString();
                mInformation=mInformationText.getText().toString();
                mContact = Integer.parseInt(mContactText.getText().toString());
                mDate=mDateText.getText().toString();
                mTime=mTimeText.getText().toString();

                EventModel mEventModelObj = new EventModel(1, mTitle, mVenue, mRoomNo, mInformation, mContact, mDate, mTime);
                mEventList.add(mEventModelObj);
                String userId = mDatabase.push().getKey();
                // creating user object

// pushing user to 'users' node using the userId
                mDatabase.child(userId).setValue(mEventModelObj);
                Toast.makeText(getContext(),"SUCCESSFULLY ADDED EVENT", Toast.LENGTH_LONG).show();
                changeFragment(new AdminProfile());



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
}
