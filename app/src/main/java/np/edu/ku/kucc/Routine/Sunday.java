package np.edu.ku.kucc.Routine;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import np.edu.ku.kucc.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class Sunday extends Fragment {
    private List<routinelist> list;
    RecyclerView mRecyclerView;


    public Sunday() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view=inflater.inflate(R.layout.fragment_sunday, container, false);;
        mRecyclerView=(RecyclerView) view.findViewById(R.id.recyclersunday);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        list=new ArrayList<routinelist>();

        list.add(
                new routinelist(
                        "COMP 201",
                        "computer",
                        "Ayush",
                        "9:00"
                ));

        list.add(
                new routinelist(
                        "COMP 301",
                        "Math",
                        "Shoaib",
                        "10:00"
                ));

        list.add(
                new routinelist(
                        "COMP 401",
                        "Microsoft Surface Pro 4 Core m3 6th Gen - (4 GB/128 GB SSD/Windows 10)",
                        "13.3 inch, Silver, 1.35 kg",
                        "11:00"
                ));

        RecyclerViewAdapter adapter1=new RecyclerViewAdapter(getContext(),list);
        mRecyclerView.setAdapter(adapter1);
        Log.d("LISTTT", list.toString());
        //Toast.makeText(getContext(),"sad",Toast.LENGTH_LONG).show();
        return view;
    }

}
