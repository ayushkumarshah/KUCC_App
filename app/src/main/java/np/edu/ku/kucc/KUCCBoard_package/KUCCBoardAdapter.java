package np.edu.ku.kucc.KUCCBoard_package;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import np.edu.ku.kucc.R;

/**
 * Created by ayush on 12/14/17.
 */

public class KUCCBoardAdapter extends ArrayAdapter {

    List list=new ArrayList();


    public KUCCBoardAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }


    public void add(KUCCBoard object) {
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
        FacultyHolder facultyHolder;

        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.list_single_faculty,parent,false);
            facultyHolder=new FacultyHolder();
            facultyHolder.Name= (TextView) row.findViewById(R.id.name);
            facultyHolder.Designation= (TextView) row.findViewById(R.id.designation);
            facultyHolder.Email= (TextView) row.findViewById(R.id.email);
            row.setTag(facultyHolder);
        }
        else
        {
            facultyHolder=(FacultyHolder) row.getTag();
        }
        KUCCBoard KUCCBoard =(KUCCBoard)getItem(position);
        facultyHolder.Name.setText(KUCCBoard.getName());
        facultyHolder.Designation.setText(KUCCBoard.getDesignation());
        facultyHolder.Email.setText(KUCCBoard.getEmail());

        /*String url;
        url=news.getLink();
        url="<a href=\""+url+"\">"+url+"</a>";
        String imgurl,content;
        imgurl=news.getImageURL();
        if (!imgurl.isEmpty()) {
            imgurl = "<img src=\"http://ku.edu.np/kucc/" + news.getImageURL().substring(1, news.getImageURL().length()) + "\"  >";
            content = imgurl + "<br><br>" + news.getInfo() + "\n<br><br>Source :" + url;
        }
        else
            content = news.getInfo() + "\n<br><br>Source :" + url;

        PicassoImageGetter imageGetter = new PicassoImageGetter(newsHolder.Info,getContext());
        Spannable html;

        Log.e("content",content);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            html = (Spannable) Html.fromHtml(content, Html.FROM_HTML_MODE_LEGACY, imageGetter, null);
        } else {
            html = (Spannable) Html.fromHtml(content, imageGetter, null);
        }
        Log.e("html",html.toString());
        newsHolder.Info.setText(html);

        newsHolder.Info.setMovementMethod(LinkMovementMethod.getInstance());
        *//*newsHolder.Content.setText(Html.fromHtml(news.getContent().replaceAll("<img.+?>", "")));
        newsHolder.Content.setMovementMethod(LinkMovementMethod.getInstance());*/
        return row;
    }

    static class FacultyHolder
    {
        TextView Name, Designation,Email;

    }
}
