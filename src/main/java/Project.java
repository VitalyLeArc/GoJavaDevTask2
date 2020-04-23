import java.sql.Date;

public class Project {
    private int id;
    private String name;
    private Date startDate;
    private int countOfDevs;

    public Project(int id, String name, Date startDate, int countOfDevs) {
        this.id = id;
        this.name = name;
        this.startDate = startDate;
        this.countOfDevs = countOfDevs;
    }

    public String toString(){
        return "id: "+id+", name: "+name+", startDate: "+startDate.toString()+", countOfDevelopers: "+countOfDevs;
    }
}
