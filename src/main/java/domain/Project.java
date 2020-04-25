package domain;

import java.sql.Date;


public class Project {
    private long id;
    private String name;
    private Date startDate;
    private String version;
    private float cost;
    private int countOfDevs;

    public Project(String name) {
        this.name = name;
    }

    public Project(String name, String version, float cost) {
        this(name);
        this.version = version;
        this.countOfDevs = countOfDevs;
    }

    public Project(String name, String version, float cost, Date startDate) {
        this(name, version, cost);
        this.startDate = startDate;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public void setCost(float cost) {
        this.cost = cost;
    }

    public void setCountOfDevs(int countOfDevs) {
        this.countOfDevs = countOfDevs;
    }

    public String getName() {
        return name;
    }


    public String toString() {
        return "id: " + id + ", name: " + name + " " + version + ", startDate: " + startDate.toString() + ", countOfDevelopers: " + countOfDevs;
    }

    public long getId() {
        return id;
    }

    public Date getStartDate() {
        return startDate;
    }

    public String getVersion() {
        return version;
    }

    public float getCost() {
        return cost;
    }

    public int getCountOfDevs() {
        return countOfDevs;
    }
}

