package np.edu.ku.kucc;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.Bundle;
import android.util.Log;
import android.view.Window;


import np.edu.ku.kucc.Faculty_package.Faculty_fragment;
import np.edu.ku.kucc.News_package.NewsDatabase;
import np.edu.ku.kucc.News_package.NewsFragment;


public class splashScreen extends Activity {
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        Window window = getWindow();
        window.setFormat(PixelFormat.RGBA_8888);
    }

    NewsDatabase myDB;
    Context context;


    @SuppressLint("StaticFieldLeak")
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        context = getApplicationContext();
        NewsFragment newsFragment=new NewsFragment();
        newsFragment.getData(this);
        Faculty_fragment faculty_fragment=new Faculty_fragment();
        faculty_fragment.getData(this);
        /*NewsFragment newsFragment=new NewsFragment();
        newsFragment.getData(this);
        NewsFragment newsFragment=new NewsFragment();
        newsFragment.getData(this);
*/

        Thread myThread = new Thread() {
            @Override
            public void run() {
                try {
                    sleep(2000);
                    Intent i = new Intent(context, MainActivity.class);
                    startActivity(i);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();
    }


}
