package np.edu.ku.kucc.News_package;

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

public class NewsAdapter extends ArrayAdapter {

    List list=new ArrayList();


    public NewsAdapter(@NonNull Context context, int resource) {
        super(context, resource);
    }


    public void add(News object) {
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
        NewsHolder newsHolder;

        if(row==null)
        {
            LayoutInflater layoutInflater=(LayoutInflater)this.getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            row=layoutInflater.inflate(R.layout.list_single_news,parent,false);
            newsHolder=new NewsHolder();
            newsHolder.Title= (TextView) row.findViewById(R.id.name);
            newsHolder.Date= (TextView) row.findViewById(R.id.date);
            newsHolder.Info= (TextView) row.findViewById(R.id.content);
            row.setTag(newsHolder);
        }
        else
        {
            newsHolder=(NewsHolder)row.getTag();
        }
        News news=(News)getItem(position);
        newsHolder.Title.setText(news.getTitle());
        newsHolder.Date.setText(news.getDate());
        String url;
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

        PicassoImageGetter imageGetter = new PicassoImageGetter(newsHolder.Info,getContext(),"original");
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
        /*newsHolder.Content.setText(Html.fromHtml(news.getContent().replaceAll("<img.+?>", "")));
        newsHolder.Content.setMovementMethod(LinkMovementMethod.getInstance());*/
        return row;
    }

    static class NewsHolder
    {
        TextView Title, Date,Info;

    }
}
