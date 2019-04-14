package np.edu.ku.kucc.KUCCBoard_package;

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
        KUCCBoardHolder kuccBoardHolder;

        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.list_single_kucc_board,parent,false);
            kuccBoardHolder=new KUCCBoardHolder();
            kuccBoardHolder.Name= (TextView) row.findViewById(R.id.name);
            kuccBoardHolder.Post= (TextView) row.findViewById(R.id.designation);
            kuccBoardHolder.Email= (TextView) row.findViewById(R.id.email);
            kuccBoardHolder.Contact= (TextView) row.findViewById(R.id.contact);
            kuccBoardHolder.Image= (TextView) row.findViewById(R.id.image);
            row.setTag(kuccBoardHolder);
        }
        else
        {
            kuccBoardHolder=(KUCCBoardHolder) row.getTag();
        }
        KUCCBoard kuccBoard=(KUCCBoard) getItem(position);
        kuccBoardHolder.Name.setText(kuccBoard.getName());
        kuccBoardHolder.Post.setText(kuccBoard.getPost());
        kuccBoardHolder.Email.setText(kuccBoard.getEmail());
        kuccBoardHolder.Contact.setText(kuccBoard.getContact());
        String imgurl;
        imgurl=kuccBoard.getImageURL();
        if (!imgurl.isEmpty()) {
            imgurl = "<img src=\"http://ku.edu.np/kucc/" + kuccBoard.getImageURL().substring(1, kuccBoard.getImageURL().length()) + "\"  >";
        }
        String size="fixed";
        PicassoImageGetter imageGetter = new PicassoImageGetter(kuccBoardHolder.Image,getContext(),size);
        Spannable imagehtml;

        Log.e("imgurl",imgurl);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            imagehtml = (Spannable) Html.fromHtml(imgurl, Html.FROM_HTML_MODE_LEGACY, imageGetter, null);
        } else {
            imagehtml = (Spannable) Html.fromHtml(imgurl, imageGetter, null);
        }
        Log.e("imagehtml",imagehtml.toString());
        kuccBoardHolder.Image.setText(imagehtml);

        return row;
    }

    static class KUCCBoardHolder
    {
        TextView Name, Post,Email,Contact,Image;

    }
}
