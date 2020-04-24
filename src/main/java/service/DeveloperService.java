package service;

import domain.Developer;
import repository.DeveloperDAO;

import java.util.List;



public class DeveloperService {
    private final DeveloperDAO developerDAO= new DeveloperDAO();
    private void emptyQuerry(){
        System.out.println("Запрос ничего не вывел");
    }

    public void printDevelopersForProject(String projectName) {
        List<Developer> developers;
        developers = developerDAO.getDevelopersForProject(projectName);
        if (!developers.isEmpty()) {
            System.out.println("В проекте " + projectName + " участвуют:");
            for (Developer dev : developers) {
                System.out.println("\t\t" + dev.getName());
            }
        }
        else {
            emptyQuerry();
        }
    }

    public void printDevelopersForSkill(String skill) {
        List<Developer> developers;
        developers = developerDAO.getDevelopersForSkill(skill);
        if (!developers.isEmpty()) {
            System.out.println("Список разработчиков " + skill + ":");
            for (Developer dev : developers) {
                System.out.println("\t\t" + dev.getName());
            }
        }
        else {
            emptyQuerry();
        }
    }

    public void printDevelopersForGrade(String grade) {
        List<Developer> developers;
        developers=developerDAO.getDevelopersForGrade(grade);
        if (!developers.isEmpty()) {
            System.out.println("Список разработчиков с уровнем " + grade + ":");
            for (Developer dev : developers) {
                System.out.println("\t\t" + dev.getName());
            }
        }
        else {
            emptyQuerry();
        }
    }

    public void addDeveloper(String name,int age,String sex,int departmentID,float salary){
        if(developerDAO.addDeveloper(name,age,sex,departmentID,salary)){
            System.out.println("Данные добавлены в таблицу");
        }
        else{
            System.out.println("Что-то пошло не так");
        }
    }
}
