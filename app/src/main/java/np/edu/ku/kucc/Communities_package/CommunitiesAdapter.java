package np.edu.ku.kucc.Communities_package;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spannable;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import np.edu.ku.kucc.PicassoImageGetter;
import np.edu.ku.kucc.R;

/**
 * Created by ayush on 12/14/17.
 */

public class CommunitiesAdapter extends ArrayAdapter {

    List list=new ArrayList();


    public CommunitiesAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }


    public void add(Communities object) {
        list.add(object);
        super.add(object);
    }


    @Override
    public int getCount() {
        return list.size();
    }

    @Nullable
    @Override
    public Object getItem(int position) {
        return list.get(position);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View row=convertView;
        CommunitiesHolder communitiesHolder;

        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.list_single_communities,parent,false);
            communitiesHolder=new CommunitiesHolder();
            communitiesHolder.Name= (TextView) row.findViewById(R.id.name);
            communitiesHolder.Post= (TextView) row.findViewById(R.id.designation);
            communitiesHolder.Email= (TextView) row.findViewById(R.id.email);
            communitiesHolder.Contact= (TextView) row.findViewById(R.id.contact);
            communitiesHolder.Image= (TextView) row.findViewById(R.id.image);
            row.setTag(communitiesHolder);
        }
        else
        {
            communitiesHolder=(CommunitiesHolder) row.getTag();
        }
        Communities communities=(Communities) getItem(position);
        communitiesHolder.Name.setText(communities.getName());
        communitiesHolder.Post.setText(communities.getPost());
        communitiesHolder.Email.setText(communities.getEmail());
        communitiesHolder.Contact.setText(communities.getContact());
        String imgurl;
        imgurl=communities.getImageURL();
        if (!imgurl.isEmpty()) {
            imgurl = "<img src=\"http://ku.edu.np/kucc/" + communities.getImageURL().substring(1, communities.getImageURL().length()) + "\"  >";
        }
        String size="fixed";
        PicassoImageGetter imageGetter = new PicassoImageGetter(communitiesHolder.Image,getContext(),size);
        Spannable imagehtml;

        Log.e("imgurl",imgurl);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            imagehtml = (Spannable) Html.fromHtml(imgurl, Html.FROM_HTML_MODE_LEGACY, imageGetter, null);
        } else {
            imagehtml = (Spannable) Html.fromHtml(imgurl, imageGetter, null);
        }
        Log.e("imagehtml",imagehtml.toString());
        communitiesHolder.Image.setText(imagehtml);

        return row;
    }

    static class CommunitiesHolder
    {
        TextView Name, Post,Email,Contact,Image;

    }
}
