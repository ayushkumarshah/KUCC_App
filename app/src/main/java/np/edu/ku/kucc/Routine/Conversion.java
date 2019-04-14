package np.edu.ku.kucc.Routine;

import android.widget.Toast;

public class Conversion {
    public String Course(String course) {
        String CourseInfo=null;
        if (course.equals("Computer Science"))
        {
            CourseInfo="CS";

        }
        else if (course.equals("Computer Engineering"))
        {
            CourseInfo="CE";
        }
        return CourseInfo;

    }

    public String YearSem(String year, String semester) {
        String YearSem = null;
        String Year=null;
        String Sem=null;
        Year=year.substring(0,1);
        Sem=semester.substring(0,1);
        YearSem=Year+'Y'+Sem+'S';
        return YearSem;



    }

}
