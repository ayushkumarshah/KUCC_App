package np.edu.ku.kucc.Database;

public class Data {
    int id;
    String Year, Semester;

    public Data(int id, String year, String semester) {
        this.id = id;
        Year = year;
        Semester = semester;
    }

    public Data() {

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getYear() {
        return Year;
    }

    public void setYear(String year) {
        Year = year;
    }

    public String getSemester() {
        return Semester;
    }

    public void setSemester(String semester) {
        Semester = semester;
    }
}
