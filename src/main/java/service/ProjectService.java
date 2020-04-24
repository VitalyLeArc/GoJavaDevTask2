package service;

import domain.Project;
import repository.ProjectDAO;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

public class ProjectService {
    private final ProjectDAO projectDAO = new ProjectDAO();
    private void emptyQuerry(){
        System.out.println("Запрос ничего не вывел");
    }

    public void printSumSalaryForProject(String projectName){
        Optional optional = projectDAO.getProjectSumSalary(projectName);
        if(optional.isPresent()){
            Project project = (Project)optional.get();
            System.out.println("Сумма всех разработчиков в проекте " + projectName + " = " + project.getSumSalary());
        }
        else{
            emptyQuerry();
        }
    }

    public void printAllProjectsInfo(){
        List<Project> projects = projectDAO.getAllProjectsInfo();
        if(!projects.isEmpty()){
            System.out.println("Информация о проектах имеющихся в БД: ");
            for(Project project:projects) {
                System.out.println("\t\t"+project.toString());
            }
        }
        else{
            emptyQuerry();
        }
    }
    public void addProject(String name,String version,float cost,float salary,String date){
        if(projectDAO.addProject(name,version,cost,date)){
            System.out.println("Данные добавлены в таблицу");
        }
        else{
            System.out.println("Что-то пошло не так");
        }
    }
    public void addProject(String name, String version, float cost, float salary, Date date){
        if(projectDAO.addProject(name,version,cost,date)){
            System.out.println("Данные добавлены в таблицу");
        }
        else{
            System.out.println("Что-то пошло не так");
        }
    }
}
