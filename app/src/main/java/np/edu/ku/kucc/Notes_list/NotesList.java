package np.edu.ku.kucc.Notes_list;




public class NotesList  {
    private String Course_ID, Course_Name, Course_Inst, Course_Link;

    public NotesList() {
    }


    public NotesList(String course_Id, String course_Name, String course_Inst, String course_Link) {
        Course_ID = course_Id;
        Course_Name = course_Name;
        Course_Inst = course_Inst;
        Course_Link = course_Link;

    }


    public String getCourse_Id() {
        return Course_ID;
    }

    public void setCourse_Id(String course_Id) {
        Course_ID = course_Id;
    }

    public String getCourse_Name() {
        return Course_Name;
    }

    public void setCourse_Name(String course_Name) {
        Course_Name = course_Name;
    }

    public String getCourse_Inst() {
        return Course_Inst;
    }

    public void setCourse_Inst(String course_Inst) {
        Course_Inst = course_Inst;
    }

    public String getCourse_Link() {
        return Course_Link;
    }

    public void setCourse_Link(String course_Link) {
        Course_Link = course_Link;
    }

}
