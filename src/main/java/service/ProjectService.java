package service;

import domain.Project;
import lombok.extern.slf4j.Slf4j;
import repository.ProjectDAO;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
public class ProjectService {
    private final ProjectDAO projectDAO = new ProjectDAO();

    protected static void emptyQuery() {
        log.error("Запрос ничего не вывел");
    }

    public void printSumSalaryForProject(String projectName) {

        BigDecimal sumSalary = projectDAO.getProjectSumSalary(projectName);
        if (sumSalary != null) {
            System.out.println("Сумма всех разработчиков в проекте " + projectName + " = " + sumSalary);
        } else {
            emptyQuery();
        }
    }

    public void printAllProjectsInfo() {
        List<Project> projects = projectDAO.getAllProjectsInfo();
        if (!projects.isEmpty()) {
            try{
            System.out.println("Информация о проектах имеющихся в БД: ");
            for (Project project : projects) {
                System.out.println("\t\t" + project.toString());
            }
            }catch (NullPointerException e){
                log.error("Null in some value"+e.getMessage());
            }
        } else {
            emptyQuery();
        }
    }

    public void addProject(Project proj, String isToday) {
        if ((isToday.toLowerCase() == "today" || isToday.toLowerCase() == "сегодня" || isToday.toLowerCase() == "да" || isToday.toLowerCase() == "yes") &&
                projectDAO.addProject(proj, "now()")) {
            log.info("Данные успешно добавлены");
        }
    }

    public void addProject(Project proj) {
        if (projectDAO.addProject(proj)) {
            log.info("Данные успешно добавлены");
        }
    }
}
