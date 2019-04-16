package np.edu.ku.kucc.Account;

import com.google.firebase.database.FirebaseDatabase;

public class MyDatabaseUtils {


        private static FirebaseDatabase mDatabase;

        public static FirebaseDatabase getDatabase() {
            if (mDatabase == null) {
                mDatabase = FirebaseDatabase.getInstance();
                mDatabase.setPersistenceEnabled(true);
            }
            return mDatabase;
        }


}
