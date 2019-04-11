package np.edu.ku.kucc.Events_Package;

/**
 * Created by ayush on 12/14/17.
 */

public class Events {
    private String name,designation,email,link,imageURL;
    public Events(String name, String designation, String email, String link, String imageURL){
        this.name=name;
        this.designation=designation;
        this.email=email;
        this.link=link;
        this.imageURL=imageURL;


    }
    public String getName()
    {
        return  name;
    }
    public String getDesignation()
    {
        return  designation;
    }
    public String getEmail()
    {
        return  email;
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
