package repository;

import domain.Project;

import java.math.BigDecimal;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

import static repository.QueryToDB.*;

@Slf4j
public class ProjectDAO {
    private final String selectSumSalaryForProject = "select sum(salary) as value from gosqltask1.developers dev " +
            "inner join gosqltask1.link_developers_projects ldp on ldp.dev_id=dev.id " +
            "inner join gosqltask1.projects pr on pr.id=ldp.project_id " +
            "where pr.project_name = ";
    private final String selectAllProjectsInfo = "select pr.id,pr.project_name as name,pr.version,pr.datebegin,count(dev.id)as devcount " +
            "from gosqltask1.projects pr " +
            "join gosqltask1.link_developers_projects ldp on ldp.project_id=pr.id " +
            "join gosqltask1.developers dev on ldp.dev_id=dev.id " +
            "group by pr.id " +
            "order by pr.id";
    private final String selectProjectName = "select project_name from gosqltask1.projects";

    /*Эта функция - затычка, т.к. в таких запросах как sum,avg resultSet.next() и !resultSet.wasNull()
     * всегда возвращают ТРУ, а valueOf(resultSet.getFloat) вообще тупо не парится и ставит число 0.0
     * хотя запрос в бд уверенно даёт null. Может косяк мускла, может такая особенность агрегирующих ф-ций */
    private boolean isPresentProjectWithName(String projectName) {
        try {
            connectionBegin();
            ResultSet resultSet = statement.executeQuery("select id from gosqltask1.projects where project_name='" + projectName + "'");
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
    private void wrongQuery(){
        log.error("Ошибка в запросе");
    }
    //1
    /* Пусть метод возвращает сразу сумму, число, не нужен целый проект, см. коммент в ProjectService
     * Float - плохой выбор для работы с данными, которые требуют точности, например, деньги
     * У нас же есть класс BigDecimal для этого
     */
    //пофиксил
    public BigDecimal getProjectSumSalary(String projectName) {
        BigDecimal sumSalary = null;
        if (isPresentProjectWithName(projectName)) {
            try {
                connectionBegin();
                ResultSet resultSet = statement.executeQuery(
                        selectSumSalaryForProject + "'" + projectName + "'");
                if (resultSet.next()) {
                    sumSalary = BigDecimal.valueOf(resultSet.getFloat("value"));
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        return sumSalary;
    }

    //5
    public List<Project> getAllProjectsInfo() {
        List<Project> list = new ArrayList<>();
        try {
            connectionBegin();
            ResultSet resultSet = statement.executeQuery(selectAllProjectsInfo);
            while (resultSet.next()) {
                Project project = new Project(resultSet.getString("name"));
                project.setId(resultSet.getLong("id"));
                project.setVersion(resultSet.getString("version"));
                project.setCountOfDevs(resultSet.getInt("devcount"));
                project.setStartDate(resultSet.getDate("datebegin"));
                list.add(project);
            }
        } catch (SQLException e) {
            wrongQuery();
        }
        return list;
    }

    /*тебе опять не нужен целый объект, ты собираешься вывести только названия
     */
    //пофиксил
    public List<String> getProjectsName() {
        List<String> projectsName = new ArrayList<>();
        try {
            connectionBegin();
            ResultSet resultSet = statement.executeQuery(selectProjectName);
            while (resultSet.next()) {
                projectsName.add(resultSet.getString("project_name"));
            }
        } catch (SQLException e) {
            wrongQuery();
        }
        return projectsName;
    }

    public boolean addProject(String name, String version, float cost, Date date) {
        boolean isSuccess = false;
        try {
            connectionBegin();
            statement.execute("insert into gosqltask1.projects (project_name,version,cost,datebegin) values " +
                    "('" + name + "','" + version + "'," + cost + "," + date + ")");
            isSuccess = true;
        } catch (SQLException e) {
            wrongQuery();
        }
        return isSuccess;
    }

    /*не боишься что придет некорретный формат даты в строке?
    *почитай про DateTimeFormatter, убедись, что передаешь в БД валидную дату*/
    //была идея(мысль/замысел) что использование даты из import java.sql.Date; задаст валидность на вызове ф-ции.
    public boolean addProject(String name, String version, float cost, String sqlDateFunction) {
        boolean isSuccess = false;
        try {
            connectionBegin();
            statement.execute("insert into gosqltask1.projects (project_name,version,cost,datebegin) values " +
                    "('" + name + "','" + version + "'," + cost + "," + sqlDateFunction + ")");
            isSuccess=true;
        } catch (SQLException e) {
            wrongQuery();
        }
        return isSuccess;
    }
}
