package np.edu.ku.kucc.Account;
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
import np.edu.ku.kucc.Routine.routinelist;

public class RecyclerViewAdapterEvent extends RecyclerView.Adapter<RecyclerViewAdapterEvent.ViewHolder> {
    private Context mCtx;
    private List<EventModel> meventlists;
    private int lastPosition = -1;

    public RecyclerViewAdapterEvent(Context mCtx, List<EventModel> meventlists) {
        this.mCtx = mCtx;
        this.meventlists = meventlists;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.eventitem, null);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final EventModel eventobj=meventlists.get(position);
        holder.TV_EventID.setText(String.valueOf(eventobj.getmEventID()));
        holder.TV_EventTitle.setText(eventobj.getmTitle());
        holder.TV_EventVenue.setText(eventobj.getmVenue());
        holder.TV_EventRoomNo.setText(eventobj.getmRoomNo());
        holder.TV_Contact.setText(String.valueOf(eventobj.getmContact()));
        holder.TV_Information.setText(eventobj.getmInformation());
        holder.TV_Date.setText(eventobj.getmDate());
        holder.TV_Time.setText(eventobj.getmTime());


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
        return meventlists.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private Context ctx;
        private List<routinelist> routinelists;
        TextView TV_EventID, TV_EventTitle, TV_EventVenue, TV_EventRoomNo, TV_Information, TV_Contact, TV_Date, TV_Time;

        public ViewHolder(View itemView) {
            super(itemView);
            TV_EventID=(TextView)itemView.findViewById(R.id.eventid);
            TV_EventTitle=(TextView)itemView.findViewById(R.id.eventtitle);
            TV_EventVenue=(TextView)itemView.findViewById(R.id.venue);
            TV_EventRoomNo=(TextView)itemView.findViewById(R.id.roomno);
            TV_Information=(TextView)itemView.findViewById(R.id.information);
            TV_Contact=(TextView)itemView.findViewById(R.id.contact);
            TV_Date=(TextView)itemView.findViewById(R.id.date);
            TV_Time=(TextView)itemView.findViewById(R.id.time);


        }
    }
}
