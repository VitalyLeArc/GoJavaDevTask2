package repository;

import domain.Project;
import lombok.extern.slf4j.Slf4j;

import static repository.QueryToDB.*;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    /*Эта функция - затычка, т.к. в таких запросах как sum,avg - resultSet.next() и !resultSet.wasNull()
     * всегда возвращают ТРУ, а valueOf(resultSet.getFloat) вообще тупо не парится и ставит число 0.0
     * хотя запрос в бд уверенно даёт null. Может косяк мускла, может такая особенность агрегирующих ф-ций */
    private boolean isPresentProjectWithName(String projectName) {
        try {
            connectionBegin();
            ResultSet resultSet = statement.executeQuery("select id from gosqltask1.projects where project_name='" + projectName + "'");
            return resultSet.next();
        } catch (SQLException e) {
            log.error("Ошибка в запросе "+e.getMessage());
        }
        return false;
    }

    //1
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
                log.error("Ошибка в запросе " + e.getMessage());
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
            log.error("Ошибка в запросе " + e.getMessage());
        }
        return list;
    }

    //6.1.1
    public boolean addProject(Project proj) {
        try {
            connectionBegin();
            log.debug("Добавление в БД ",proj);
            statement.execute("insert into gosqltask1.projects (project_name,version,cost,datebegin) values " +
                    "('" + proj.getName() + "','" + proj.getVersion() + "'," + proj.getCost() + "," + proj.getStartDate() + ")");
            return true;
        } catch (SQLException e) {
            log.error("Ошибка в запросе " + e.getMessage());
            return false;
        }
    }

    //6.1.2
    public boolean addProject(Project proj, String sqlDateFunction) {
        try {
            connectionBegin();
            log.debug("Добавление в БД ",proj);
            statement.execute("insert into gosqltask1.projects (project_name,version,cost,datebegin) values " +
                    "('" + proj.getName() + "','" + proj.getVersion() + "'," + proj.getCost() + "," + sqlDateFunction + ")");
            return true;
        } catch (SQLException e) {
            log.error("Ошибка в запросе " + e.getMessage());
            return false;
        }
    }
}
