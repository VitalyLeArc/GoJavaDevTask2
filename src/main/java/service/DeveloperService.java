package service;

import domain.Developer;
import lombok.extern.slf4j.Slf4j;
import repository.DeveloperDAO;

import java.util.List;

@Slf4j
public class DeveloperService {
    private final DeveloperDAO developerDAO = new DeveloperDAO();

    private void emptyQuerry() {
        log.error("Запрос ничего не вывел");
    }

    public void printDevelopersForProject(String projectName) {
        /*зачем 2 строки?
        почему сразу не инициализировать?*/
        //пофиксил
        List<Developer> developers = developerDAO.getDevelopersForProject(projectName);
        if (!developers.isEmpty()) {
            System.out.println("В проекте " + projectName + " участвуют:");
            for (Developer dev : developers) {
                System.out.println("\t\t" + dev.getName());
            }
        } else {
            emptyQuerry();
        }
    }

    public void printDevelopersForSkill(String skill) {
        /*та же беда
        */
        //пофиксил
        List<Developer> developers = developerDAO.getDevelopersForSkill(skill);
        if (!developers.isEmpty()) {
            System.out.println("Список разработчиков " + skill + ":");
            for (Developer dev : developers) {
                System.out.println("\t\t" + dev.getName());
            }
        } else {
            emptyQuerry();
        }
    }

    public void printDevelopersForGrade(String grade) {
        /*то же
        */
        //пофиксил
        List<Developer> developers = developerDAO.getDevelopersForGrade(grade);
        if (!developers.isEmpty()) {
            System.out.println("Список разработчиков с уровнем " + grade + ":");
            for (Developer dev : developers) {
                System.out.println("\t\t" + dev.getName());
            }
        } else {
            emptyQuerry();
        }
    }

    /*см. коммент в ProjectService - проблемы с аргументами метода
    */
    //пофиксил, думаю
    public void addDeveloper(Developer dev) {
        if (developerDAO.addDeveloper(dev.getName(), dev.getAge(), dev.getSex(), dev.getDepartmentId(), dev.getSalary())) {
            System.out.println("Данные добавлены в таблицу");
        } else {
            System.out.println("Что-то пошло не так");
        }
    }
}
