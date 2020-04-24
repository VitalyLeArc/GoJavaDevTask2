package domain;

import java.sql.Date;
import java.util.List;

public class Project {
    private int id;
    private String name;
    private Date startDate;
    private int countOfDevs;
    private float sumSalary;

    public Project(){}
    public Project(String name){
        this.name=name;
    }
    public Project(int id, String name, Date startDate, int countOfDevs) {
        this(name);
        this.id = id;
        this.startDate = startDate;
        this.countOfDevs = countOfDevs;
    }

    public void setSumSalary(float sumSalary){
        this.sumSalary=sumSalary;
    }
    public String getName(){
        return name;
    }
    public float getSumSalary(){
        return sumSalary;
    }
    public String toString(){
        return "id: "+id+", name: "+name+", startDate: "+startDate.toString()+", countOfDevelopers: "+countOfDevs;
    }
}
