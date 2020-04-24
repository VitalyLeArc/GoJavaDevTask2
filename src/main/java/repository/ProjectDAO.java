package repository;
import domain.Project;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static repository.QueryToDB.*;

public class ProjectDAO {
    private final String selectSumSalaryForProject = "select sum(salary) as value from gosqltask1.developers dev " +
        "inner join gosqltask1.link_developers_projects ldp on ldp.dev_id=dev.id " +
        "inner join gosqltask1.projects pr on pr.id=ldp.project_id " +
        "where pr.project_name = ";
    private final String selectAllProjectsInfo = "select pr.id,pr.project_name as name,pr.datebegin,count(dev.id)as devcount " +
        "from gosqltask1.projects pr "+
        "join gosqltask1.link_developers_projects ldp on ldp.project_id=pr.id " +
        "join gosqltask1.developers dev on ldp.dev_id=dev.id " +
        "group by pr.id "+
        "order by pr.id";
    private final String selectProjectName = "select project_name from gosqltask1.projects";
//1
    public Optional<Project> getProjectSumSalary(String projectName) {
    Optional<Project> optional;
    Project project=null;
        try {
            int sumSalary;
            connectionBegin();
            ResultSet resultSet = statement.executeQuery(
                    selectSumSalaryForProject +"'"+ projectName + "'");
            if (resultSet.next()) {
                project=new Project();
                project.setSumSalary(resultSet.getFloat("value"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
    optional= Optional.ofNullable(project);
    return optional;
}

//5
    public List<Project> getAllProjectsInfo(){
    List<Project> list = new ArrayList<>();
    try {
        connectionBegin();
        ResultSet resultSet=statement.executeQuery(selectAllProjectsInfo);
        while(resultSet.next()){
            list.add(new Project(resultSet.getInt("id"),
                    resultSet.getString("name"),
                    resultSet.getDate("datebegin"),
                    resultSet.getInt("devcount")));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return list;
}
    public List<Project> getProjectsName() {
        List<Project> projects = new ArrayList<>();
        try {
            connectionBegin();
            ResultSet resultSet=statement.executeQuery(selectProjectName);
            while(resultSet.next()){
                projects.add(new Project(resultSet.getString("project_name")));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projects;
    }

    public boolean addProject(String name, String version, float cost,Date date){
        boolean isSuccess=false;
        try {
            connectionBegin();
            isSuccess=statement.execute("insert into gosqltask1.projects (project_name,version,cost,datebegin) values " +
                    "('"+name+"','"+version+"',"+cost+","+date+");");
        } catch (SQLException e) {
            System.out.println("Ошибка в запросе");
        }
        return isSuccess;
    }
    public boolean addProject(String name, String version, float cost,String date){
        boolean isSuccess=false;
        try {
            connectionBegin();
            isSuccess=statement.execute("insert into gosqltask1.projects (project_name,version,cost,datebegin) values " +
                    "('"+name+"','"+version+"',"+cost+","+date+");");
        } catch (SQLException e) {
            System.out.println("Ошибка в запросе");
        }
        return isSuccess;
    }
}
