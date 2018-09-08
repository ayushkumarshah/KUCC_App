package np.edu.ku.kucc.Routine;

public class routinelist {
    private String Course_Id, Course_Name, Course_Inst, Course_Time;

    public routinelist() {
    }

    public routinelist(String course_Id, String course_Name, String course_Inst, String course_Time) {
        Course_Id = course_Id;
        Course_Name = course_Name;
        Course_Inst = course_Inst;
        Course_Time = course_Time;
    }

    public String getCourse_Id() {
        return Course_Id;
    }

    public void setCourse_Id(String course_Id) {
        Course_Id = course_Id;
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

    public String getCourse_Time() {
        return Course_Time;
    }

    public void setCourse_Time(String course_Time) {
        Course_Time = course_Time;
    }
}
