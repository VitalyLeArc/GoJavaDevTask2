package domain;

public class Developer {
    private String name;
    private long id;
    String sex;
    int departmentId;
    float salary;

    public Developer (String name){
        this.name=name;
    }
    public Developer (long id,String name){
        this(name);
        this.id=id;
    }
    public Developer (long id,String name,String sex, int departmentId,float salary){
        this(id,name);
        this.sex = sex;
        this.departmentId=departmentId;
        this.salary=salary;
    }

    public void setId(long id) {
        this.id = id;
    }
    public void setName(String name){
        this.name=name;
    }

    public String getName() {
        return name;
    }
    public long getId(){
        return id;
    }

}
