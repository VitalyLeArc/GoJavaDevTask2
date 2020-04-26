package service;

import domain.Developer;
import lombok.extern.slf4j.Slf4j;
import repository.DeveloperDAO;

import java.util.List;

@Slf4j
public class DeveloperService {
    private final DeveloperDAO developerDAO = new DeveloperDAO();

    protected static void emptyQuery() {
        log.error("Запрос ничего не вывел");
    }

    public void printDevelopersForProject(String projectName) {
        List<Developer> developers = developerDAO.getDevelopersForProject(projectName);
        if (!developers.isEmpty()) {
            System.out.println("В проекте " + projectName + " участвуют:");
            for (Developer dev : developers) {
                System.out.println("\t\t" + dev.getName());
            }
        } else {
            emptyQuery();
        }
    }

    public void printDevelopersForSkill(String skill) {
        List<Developer> developers = developerDAO.getDevelopersForSkill(skill);
        if (!developers.isEmpty()) {
            System.out.println("Список разработчиков " + skill + ":");
            for (Developer dev : developers) {
                System.out.println("\t\t" + dev.getName());
            }
        } else {
            emptyQuery();
        }
    }

    public void printDevelopersForGrade(String grade) {
        List<Developer> developers = developerDAO.getDevelopersForGrade(grade);
        if (!developers.isEmpty()) {
            System.out.println("Список разработчиков с уровнем " + grade + ":");
            for (Developer dev : developers) {
                System.out.println("\t\t" + dev.getName());
            }
        } else {
            emptyQuery();
        }
    }

    public void addDeveloper(Developer dev) {
        if (developerDAO.addDeveloper(dev)) {
            log.info("Данные успешно добавлены");
        }
    }
}
