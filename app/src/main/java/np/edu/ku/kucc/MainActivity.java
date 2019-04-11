package np.edu.ku.kucc;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.webkit.WebView;

import np.edu.ku.kucc.Account.Profile;
import np.edu.ku.kucc.Communities_package.Communities_Fragment;
import np.edu.ku.kucc.Database.SharedPref;
import np.edu.ku.kucc.Faculty_package.Faculty_fragment;
import np.edu.ku.kucc.KUCCBoard_package.KUCCBoard_Fragment;
import np.edu.ku.kucc.News_package.NewsFragment;
import np.edu.ku.kucc.Notes_list.Notes;
import np.edu.ku.kucc.Routine.Routines;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    public static String Course,Year,Semester,mYearSem,mCourse;
    private WebView web;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


       /* View headerView = navigationView.getHeaderView(0);
        web=(WebView)headerView.findViewById(R.id.web);
        web.setVerticalScrollBarEnabled(true);
        web.setHorizontalScrollBarEnabled(true);
        web.getSettings().setJavaScriptEnabled(true);
        web.setWebViewClient(new WebViewClient());
        web.getSettings().setLoadWithOverviewMode(true);
        web.getSettings().setUseWideViewPort(true);
//        web.loadUrl("file:///android_asset/sample.html");
        web.loadUrl("http://192.168.100.71:8080/index.svg");
*/

        changeFragment(new NewsFragment());
    }
    @Override
    protected void onStart() {

        super.onStart();
        SharedPref sharedPref=new SharedPref(getApplicationContext());
        sharedPref.fuser();
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            changeFragment(new Settings());
        }
        else if(id==R.id.action_About){
            changeFragment(new About());
        }

        else if(id==R.id.action_Exit){
            finish();
            System.exit(0);
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

       if (id == R.id.nav_calendar) {
           changeFragment(new Calendar());
        } else if (id == R.id.nav_news) {
           changeFragment(new NewsFragment());
        }
       else if (id == R.id.nav_notes) {
           changeFragment(new Notes());
       }
       else if (id == R.id.nav_events) {
           changeFragment(new Events());
        } else if (id == R.id.nav_routines) {
           changeFragment(new Routines());
        } else if (id == R.id.nav_courseinfo) {
           changeFragment(new Course_info());
        } else if (id == R.id.nav_teacherinfo) {
           changeFragment(new Faculty_fragment());
        }
        else if (id == R.id.nav_aboutkucc) {
           changeFragment(new KUCCBoard_Fragment());
        }
         else if (id == R.id.nav_share) {

            Intent sharingIntent = new Intent(Intent.ACTION_SEND);
            sharingIntent.setType("text/plain");
            sharingIntent.putExtra(Intent.EXTRA_TEXT, "Check out KUCC App at: https://play.google.com/store/apps/details?id=" + getPackageName());
            startActivity(Intent.createChooser(sharingIntent, "Share using"));
        }
       else if (id == R.id.nav_profile) {
           changeFragment(new Profile());
       } else if (id == R.id.nav_visit) {

       }
       else if (id == R.id.nav_reviews) {
           changeFragment(new Reviews());
       }
       else if (id == R.id.nav_communities) {
           changeFragment(new Communities_Fragment());
       }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void changeFragment(Fragment targetFragment){
        getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.activity_main, targetFragment, "fragment")
                .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)
                .commit();
    }
}
