package np.edu.ku.kucc.Routine;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.List;
import java.util.Random;

import np.edu.ku.kucc.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context mCtx;
    private List<routinelist> mroutinelists;
    private int lastPosition = -1;

    public RecyclerViewAdapter(Context mCtx, List<routinelist> routinelists) {
        this.mCtx = mCtx;
        this.mroutinelists = routinelists;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.routineitem, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final routinelist routinelistobj=mroutinelists.get(position);
        holder.TV_CourseID.setText(routinelistobj.getCourse_Id());
        holder.TV_CourseName.setText(routinelistobj.getCourse_Name());
        holder.TV_CouseInstruc.setText(routinelistobj.getCourse_Inst());
        holder.TV_CouseTime.setText(routinelistobj.getCourse_Time());
        setAnimation(holder.itemView, position);

    }
    private void setAnimation(View viewToAnimate, int position)
    {
        if (position > lastPosition) {
            ScaleAnimation anim = new ScaleAnimation(0.0f, 1.0f, 0.0f, 1.0f, Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF, 0.5f);
            anim.setDuration(new Random().nextInt(501));//to make duration random number between [0,501)
            viewToAnimate.startAnimation(anim);
            lastPosition = position;
        }
    }

    @Override
    public int getItemCount() {
        return mroutinelists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Context ctx;
        private List<routinelist> routinelists;
        TextView TV_CourseID, TV_CourseName, TV_CouseInstruc, TV_CouseTime;

        public ViewHolder(View itemView) {
            super(itemView);
            TV_CourseID=(TextView)itemView.findViewById(R.id.courseid);
            TV_CourseName=(TextView)itemView.findViewById(R.id.coursename);
            TV_CouseInstruc=(TextView)itemView.findViewById(R.id.courseinstruc);
            TV_CouseTime=(TextView)itemView.findViewById(R.id.time);
        }
    }
}
