package np.edu.ku.kucc.KUCCBoard_package;

/**
 * Created by ayush on 12/14/17.
 */

public class KUCCBoard {
    private String name,post,email,contact,imageURL;
    public KUCCBoard(String name, String post, String email, String contact, String imageURL){
        this.name=name;
        this.post=post;
        this.email=email;
        this.contact=contact;
        this.imageURL=imageURL;


    }
    public String getName()
    {
        return  name;
    }
    public String getPost()
    {
        return  post;
    }
    public String getEmail()
    {
        return  email;
    }
    public String getContact()
    {
        return  contact;
    }
    public String getImageURL()
    {
        return  imageURL;
    }

}
