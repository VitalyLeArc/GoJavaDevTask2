package service;

import domain.Project;
import lombok.extern.slf4j.Slf4j;
import repository.ProjectDAO;

import java.sql.Date;
import java.util.List;
import java.util.Optional;

@Slf4j
public class ProjectService {
    private final ProjectDAO projectDAO = new ProjectDAO();
    private void emptyQuery(){
        //Была опечатка в названии метода
        //Добавил в проект 2 зависимости - Ломбок и slf4j - бибилиотека для логирования
        //Прекращаем использовать sout, см. пример:
        log.error("Запрос ничего не вывел");
    }

    public void printSumSalaryForProject(String projectName){
        //Нам даровали Дженерики не для того, чтобы мы писали такой код
        //Optional<Project> optional = ...
        Optional optional = projectDAO.getProjectSumSalary(projectName);
        if(optional.isPresent()){
            //с дженериками строгое приведение не нужно будет
            Project project = (Project)optional.get();
            //тебе не нужен проект. Ты сетишь число в объект, а потом берешь число из объекта
            //Так зачем нам целый объект? Пусть метод getProjectSumSalary вернет число
            System.out.println("Сумма всех разработчиков в проекте " + projectName + " = " + project.getSumSalary());
        }
        else{
            emptyQuery();
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
            emptyQuery();
        }
    }

    //Аргумент float salary нигде не используется. Зачем он?
    /*
    * Плохо создавать объект с миллионом параметов. Если я добавлю еще 5 параметров в объект,
    * то мне нужно добавлять еще параметров в метод.
    * Такой метод должен принимать на вход либо объект, который надо сохранить,
    * либо JSON String, либо путь к файлу с JSON как вариант
     */
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
