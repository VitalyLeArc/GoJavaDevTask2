package repository;

import domain.Developer;
import lombok.extern.slf4j.Slf4j;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static repository.QueryToDB.*;

@Slf4j
public class DeveloperDAO {
    private void wrongQuery() {
        log.error("Ошибка в запросе");
    }

    /*что этот метод делает в DeveloperDAO?
     *почему он возвращает boolean.
     *Если ты просто проверяешь, существует такой проект или нет, то назови это checkProjectExistsWithName
     *А вообще лучше сделать метод, который будет именно возвращать optional проект по имени и бросать исключение/выводить сообщение в консоль, если такого нет*/
    //исправил

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
            wrongQuery();
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
            wrongQuery();
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
            wrongQuery();
        }
        return list;
    }

    public boolean addDeveloper(String name, int age, String sex, int departmentId, float salary) {
        boolean isSuccess = false;
        try {
            connectionBegin();
            statement.execute("insert into gosqltask1.developers (name,age,sex,department_id,salary) values " +
                    "('" + name + "'," + age + "," + "'" + sex + "'," + departmentId + "," + salary + ")");
            isSuccess = true;
        } catch (SQLException e) {
            wrongQuery();
        }
        return isSuccess;
    }
}
