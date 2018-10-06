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
        if (year.equals("1st Year") && semester.equals("1st Semester"))
        {
            YearSem="1Y1S";
        }
        else if (year.equals("2nd Year") && semester.equals("1st Semester"))
        {
            YearSem="2Y1S";
        }
        else if (year.equals("3rd Year") && semester.equals("1st Semester"))
        {
            YearSem="3Y1S";
        }
        else if(year.equals("4th Year") && semester.equals("1st Semester"))
        {
            YearSem="4Y1S";
        }
        else
        {
            YearSem=null;
        }
        return YearSem;

    }

}
