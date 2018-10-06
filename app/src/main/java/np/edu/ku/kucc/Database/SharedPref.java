package np.edu.ku.kucc.Database;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;

import np.edu.ku.kucc.Routine.Batch;

public class SharedPref {
    //Creating sharedpreference file
    SharedPreferences sharedPreferences;
    //To Edit sharedpreference file
    SharedPreferences.Editor editor;
    //Context to pass reference to other classes
    Context context;
    int mode=0;
    String FileName="database";
    String Data="b";
    //COstructor  to pass memory at runtime to sharedfile

    public SharedPref(Context context) {
        this.context=context;
        sharedPreferences=context.getSharedPreferences(FileName,mode);
        editor=sharedPreferences.edit();

    }
    //for running second time
    public void suse(){
        editor.putBoolean(Data,true);
        editor.commit();
    }
    //for running first time
    public void fuser(){
        if (!this.firstpage()){
            Intent intent = new Intent(context, Batch.class);
            intent.setFlags(intent.FLAG_ACTIVITY_NEW_TASK);
            intent.addFlags(intent.FLAG_ACTIVITY_CLEAR_TOP);
            context.startActivity(intent);

        }

    }
    private boolean firstpage(){
        return sharedPreferences.getBoolean(Data,false);
    }
}
