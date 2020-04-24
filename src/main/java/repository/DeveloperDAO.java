package repository;

import domain.Developer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static repository.QueryToDB.*;

public class DeveloperDAO {
    private boolean findProjectByName(String projectName) {
        try {
            connectionBegin();
            ResultSet resultSet = statement.executeQuery("select id from gosqltask1.projects where project_name='" + projectName + "'");
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Developer> getDevelopersForProject(String projectName) {
        List<Developer> list = new ArrayList<>();
        try {
            connectionBegin();
            ResultSet resultSet = statement.executeQuery(
                    "select dev.name from gosqltask1.developers dev " +
                            "join gosqltask1.link_developers_projects ldp on dev.id=ldp.dev_id " +
                            "join gosqltask1.projects pr on pr.id=ldp.project_id " +
                            "where pr.project_name='" + projectName + "'");
            while (resultSet.next()) {
                list.add(new Developer(resultSet.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //3
    public List<Developer> getDevelopersForSkill(String skill) {
        List<Developer> list = new ArrayList<>();
        try {
            connectionBegin();
            ResultSet resultSet = statement.executeQuery(
                    "select name from gosqltask1.developers dev " +
                            "join gosqltask1.link_developers_skills lds on dev.id=lds.dev_id " +
                            "join gosqltask1.skills sk on sk.id=lds.skill_id " +
                            "where sk.skill_name='" + skill + "'");
            while (resultSet.next()) {
                list.add(new Developer(resultSet.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    //4
    public List<Developer> getDevelopersForGrade(String grade) {
        List<Developer> list = new ArrayList<>();
        try {
            connectionBegin();
            ResultSet resultset = statement.executeQuery(
                    "select distinct name from gosqltask1.developers dev " +
                            "join gosqltask1.link_developers_skills lds on dev.id=lds.dev_id " +
                            "join gosqltask1.skills sk on sk.id=lds.skill_id " +
                            "where sk.grade ='" + grade + "'");
            while (resultset.next()) {
                list.add(new Developer(resultset.getString("name")));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        return list;
    }

    public boolean addDeveloper(String name, int age,String sex, int departmentId,float salary) {
        boolean isSuccess = false;
        try{
            connectionBegin();
            isSuccess = statement.execute("insert into gosqltask1.developers (name,age,sex,department_id,salary) values " +
                    "('"+name+"',"+age+","+"'"+sex+"',"+departmentId+","+salary+");");
        } catch(SQLException e){
            System.out.println("Ошибка в запросе");
        }
        return isSuccess;
    }
}