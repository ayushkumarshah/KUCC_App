package np.edu.ku.kucc.KUCCBoard_package;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import np.edu.ku.kucc.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class KUCCBoard_Fragment extends Fragment {


    public KUCCBoard_Fragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_kucc_board, container, false);
    }

}