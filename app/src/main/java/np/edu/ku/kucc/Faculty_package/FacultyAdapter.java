package np.edu.ku.kucc.Faculty_package;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Html;
import android.text.Spannable;
import android.text.method.LinkMovementMethod;
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

public class FacultyAdapter extends ArrayAdapter {

    List list=new ArrayList();


    public FacultyAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }


    public void add(Faculty object) {
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
            facultyHolder.Link= (TextView) row.findViewById(R.id.link);
            facultyHolder.Image= (TextView) row.findViewById(R.id.image);

            row.setTag(facultyHolder);
        }
        else
        {
            facultyHolder=(FacultyHolder) row.getTag();
        }
        Faculty faculty=(Faculty)getItem(position);
        facultyHolder.Name.setText(faculty.getName());
        facultyHolder.Designation.setText(faculty.getDesignation());
        facultyHolder.Email.setText(faculty.getEmail());
        String url;
        url=faculty.getLink();
        url="<a href=\""+url+"\">"+url+"</a>";
        Spannable html;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            html = (Spannable) Html.fromHtml(url, Html.FROM_HTML_MODE_LEGACY);
        } else {
            html = (Spannable) Html.fromHtml(url);
        }
        facultyHolder.Link.setText(html);
        facultyHolder.Link.setMovementMethod(LinkMovementMethod.getInstance());

        String imgurl,content;
        imgurl=faculty.getImageURL();
        if (!imgurl.isEmpty()) {
            imgurl = "<img src=\"http://ku.edu.np/kucc/" + faculty.getImageURL().substring(1, faculty.getImageURL().length()) + "\"  >";
        }
        String size="fixed";
        PicassoImageGetter imageGetter = new PicassoImageGetter(facultyHolder.Image,getContext(),size);
        Spannable imagehtml;

        Log.e("imgurl",imgurl);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            imagehtml = (Spannable) Html.fromHtml(imgurl, Html.FROM_HTML_MODE_LEGACY, imageGetter, null);
        } else {
            imagehtml = (Spannable) Html.fromHtml(imgurl, imageGetter, null);
        }
        Log.e("imagehtml",imagehtml.toString());
        facultyHolder.Image.setText(imagehtml);

        return row;
    }

    static class FacultyHolder
    {
        TextView Name, Designation,Email,Link,Image;

    }
}
