package np.edu.ku.kucc.Routine;


import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.RequiresApi;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;
import android.widget.Toolbar;

import np.edu.ku.kucc.MainActivity;
import np.edu.ku.kucc.R;

import static np.edu.ku.kucc.MainActivity.Course;
import static np.edu.ku.kucc.MainActivity.Semester;
import static np.edu.ku.kucc.MainActivity.Year;
import static np.edu.ku.kucc.MainActivity.mCourse;
import static np.edu.ku.kucc.MainActivity.mYearSem;


/**
 * A simple {@link Fragment} subclass.
 */
public class Routines extends Fragment {
Activity activity;

    public Routines() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_routines, container, false);
        activity=this.getActivity();
        activity.setTitle("Routine");
        TabLayout tabLayout = (TabLayout) view.findViewById(R.id.tab_layout);
        tabLayout.addTab(tabLayout.newTab().setText("Sun"));
        tabLayout.addTab(tabLayout.newTab().setText("Mon"));
        tabLayout.addTab(tabLayout.newTab().setText("Tues"));
        tabLayout.addTab(tabLayout.newTab().setText("Wed"));
        tabLayout.addTab(tabLayout.newTab().setText("Thur"));
        tabLayout.addTab(tabLayout.newTab().setText("Fri"));

        tabLayout.setTabGravity(TabLayout.GRAVITY_FILL);



        final ViewPager viewPager = (ViewPager) view.findViewById(R.id.pager);
        final PagerAdapter adapter = new PagerAdapter
                (getChildFragmentManager(), tabLayout.getTabCount());
        viewPager.setAdapter(adapter);
        viewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
        SharedPreferences preferences= PreferenceManager.getDefaultSharedPreferences(getContext());
        Course= preferences.getString("Course", null);
        Year= preferences.getString("Year", null);
        Semester= preferences.getString("Semester", null);
        Conversion conversion=new Conversion();
        mYearSem= conversion.YearSem(Year,Semester);
        mCourse=conversion.Course(Course);


        Log.e("course", MainActivity.Course);
        Log.e("year",MainActivity.Year);
        Log.e("sem",MainActivity.Semester);
        Log.e("sem",MainActivity.mYearSem);
        Log.e("sem",MainActivity.mCourse);
        tabLayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                viewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        return view;
    }

}
