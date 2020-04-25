package domain;

public class Developer {
    private String name;
    private int age;
    private long id;
    String sex;
    int departmentId;
    float salary;

    public Developer(String name) {
        this.name = name;
    }

    public Developer(long id, String name) {
        this(name);
        this.id = id;
    }

    public Developer(String name, int age, String sex, int departmentId, float salary) {
        this.sex = sex;
        this.departmentId = departmentId;
        this.salary = salary;
        this.age = age;
    }

    public Developer(long id, String name, int age, String sex, int departmentId, float salary) {
        this(name, age, sex, departmentId, salary);
        this.id = id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public int getAge() {
        return age;
    }

    public String getSex() {
        return sex;
    }

    public int getDepartmentId() {
        return departmentId;
    }

    public float getSalary() {
        return salary;
    }
}
