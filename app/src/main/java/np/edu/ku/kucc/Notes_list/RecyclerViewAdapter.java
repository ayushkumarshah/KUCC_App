package np.edu.ku.kucc.Notes_list;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;

import java.util.List;
import java.util.Random;

import np.edu.ku.kucc.R;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private Context mCtx;
    private List<NotesList> mNotesLists;
    private int lastPosition = -1;

    public RecyclerViewAdapter(Context mCtx, List<NotesList> NotesLists) {
        this.mCtx = mCtx;
        this.mNotesLists = NotesLists;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.noteitem, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final NotesList NotesListobj=mNotesLists.get(position);
        holder.TV_CourseID.setText(NotesListobj.getCourse_Id());
        holder.TV_CourseName.setText(NotesListobj.getCourse_Name());
        holder.TV_CourseInstruc.setText(NotesListobj.getCourse_Inst());
        holder.TV_CourseLink.setText(NotesListobj.getCourse_Link());
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
        return mNotesLists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Context ctx;
        private List<NotesList> NotesLists;
        TextView TV_CourseID, TV_CourseName, TV_CourseInstruc, TV_CourseLink;

        public ViewHolder(View itemView) {
            super(itemView);
            TV_CourseID=(TextView)itemView.findViewById(R.id.courseid2);
            TV_CourseName=(TextView)itemView.findViewById(R.id.coursename2);
            TV_CourseInstruc=(TextView)itemView.findViewById(R.id.courseinstruc2);
            TV_CourseLink=(TextView)itemView.findViewById(R.id.link);
        }
    }
}
