package np.edu.ku.kucc.News_package;

/**
 * Created by ayush on 12/14/17.
 */

public class News {
    private String info,date,title,link,imageURL;
    public News(String title, String date, String info, String link,String imageURL){
        this.info=info;
        this.date=date;
        this.title=title;
        this.link=link;
        this.imageURL=imageURL;


    }
    public String getTitle()
    {
        return  title;
    }
    public String getDate()
    {
        return  date;
    }
    public String getInfo()
    {
        return  info;
    }
    public String getLink()
    {
        return  link;
    }
    public String getImageURL()
    {
        return  imageURL;
    }

}
