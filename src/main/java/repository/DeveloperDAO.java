package repository;

import domain.Developer;
import lombok.extern.slf4j.Slf4j;

import static repository.QueryToDB.*;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
public class DeveloperDAO {
    //2
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
            log.error("Ошибка в запросе "+e.getMessage());
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
            log.error("Ошибка в запросе "+e.getMessage());
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
            log.error("Ошибка в запросе "+e.getMessage());
        }
        return list;
    }

    //6.2
    public boolean addDeveloper(Developer dev) {
        try {
            connectionBegin();
            log.debug("Добавление в БД ",dev);
            statement.execute("insert into gosqltask1.developers (name,age,sex,salary) values " +
                    "('" + dev.getName() + "'," + dev.getAge() + ",'" + dev.getSex() + "'," + dev.getSalary() + ")");
            return true;
        } catch (SQLException e) {
            log.error("Ошибка в запросе "+e.getMessage());
            return false;
        }
    }
}
