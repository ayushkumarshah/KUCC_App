package np.edu.ku.kucc.Account;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import np.edu.ku.kucc.R;
import np.edu.ku.kucc.Routine.routinelist;

public class RecyclerViewAdapterEvent extends RecyclerView.Adapter<RecyclerViewAdapterEvent.ViewHolder> {
    private FragmentActivity mCtx;
    private List<EventModel> meventlists;
    private int lastPosition = -1;

    public RecyclerViewAdapterEvent(FragmentActivity mCtx, List<EventModel> meventlists) {
        this.mCtx = mCtx;
        this.meventlists = meventlists;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater=LayoutInflater.from(mCtx);
        View view=inflater.inflate(R.layout.eventitem, null);
        ViewHolder holder=new ViewHolder(view, mCtx, meventlists);
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

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        private FragmentActivity ctx;
        private List<EventModel> eventlists = new ArrayList<EventModel>();

        TextView TV_EventID, TV_EventTitle, TV_EventVenue, TV_EventRoomNo, TV_Information, TV_Contact, TV_Date, TV_Time;

        public ViewHolder(View itemView, FragmentActivity ctx, List<EventModel> eventlists) {
            
            super(itemView);
            this.ctx=ctx;
            this.eventlists=eventlists;
            itemView.setOnClickListener(this);
            TV_EventID=(TextView)itemView.findViewById(R.id.eventid);
            TV_EventTitle=(TextView)itemView.findViewById(R.id.eventtitle);
            TV_EventVenue=(TextView)itemView.findViewById(R.id.venue);
            TV_EventRoomNo=(TextView)itemView.findViewById(R.id.roomno);
            TV_Information=(TextView)itemView.findViewById(R.id.information);
            TV_Contact=(TextView)itemView.findViewById(R.id.contact);
            TV_Date=(TextView)itemView.findViewById(R.id.date);
            TV_Time=(TextView)itemView.findViewById(R.id.time);


        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            EventModel eventModel=this.eventlists.get(position);
            Register register = new Register();
            Bundle args = new Bundle();
            args.putString("id", eventModel.getmEventID());
            args.putBoolean("update", true);
            args.putString("EventTitle", eventModel.getmTitle());
            args.putString("EventVenue", eventModel.getmVenue());
            args.putString("EventRoomNo", eventModel.getmRoomNo());
            args.putString("EventInformation", eventModel.getmInformation());
            args.putInt("EventContact", eventModel.getmContact());
            args.putString("EventDate", eventModel.getmDate());
            args.putString("EventTime", eventModel.getmTime());

            register.setArguments(args);
            changeFragment(register);


            /*Intent intent = new Intent(this.ctx, EditRegister.class);
            intent.putExtra("EventID", eventModel.getmEventID());
            intent.putExtra("EventTitle", eventModel.getmTitle());
            intent.putExtra("EventVenue", eventModel.getmVenue());
            intent.putExtra("EventRoomNo", eventModel.getmRoomNo());
            intent.putExtra("EventInformation", eventModel.getmInformation());
            intent.putExtra("EventContact", eventModel.getmContact());
            intent.putExtra("EventDate", eventModel.getmDate());
            intent.putExtra("EventTime", eventModel.getmTime());

            Toast.makeText(ctx, eventModel.getmInformation(),Toast.LENGTH_LONG).show();
            //this.ctx.startActivity(intent);
            /*

             */

        }
        private void changeFragment(Fragment targetFragment){
            ctx.getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.activity_main, targetFragment, "fragment")
                    .setTransitionStyle(FragmentTransaction.TRANSIT_FRAGMENT_FADE)

                    .commit();
        }




    }

}
