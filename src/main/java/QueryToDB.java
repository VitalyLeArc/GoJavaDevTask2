/*соединяется с БД
        позволяет выполнять CRUD (CREATE, READ, UPDATE, DELETE) операции;
        вывести на консоль:
        1 зарплату(сумму) всех разработчиков отдельного проекта;
        2 список разработчиков отдельного проекта;
        3 список всех Java разработчиков;
        4 список всех middle разработчиков;
        5 список проектов в следующем формате: дата создания - название проекта
            - количество разработчиков на этом проекте.

        6 Также: создать заготовки операций(закомментированные query)
            для создания новых проектов, разработчиков, клиентов.
*/

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class QueryToDB {
    private static final String URL ="jdbc:mysql://localhost:3306/mysql?serverTimezone=UTC";
    private static String USERNAME = "root1";
    private static String PASSWORD = "root1";

    private static Connection connection;
    private static Statement statement;

    private static void emptyQuerry(){
        System.out.println("Запрос ничего не вывел");
    }
    private static void connectionBegin() throws SQLException {
        connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        statement = connection.createStatement();
    }
    private static List<String> readProjectsName(){
        List<String> list = new ArrayList<>();
        try {
            String name;
            connectionBegin();
            ResultSet resultSet=statement.executeQuery(
                    "select pr.project_name as name from gosqltask1.projects pr");
            while(resultSet.next()) {
                name = resultSet.getString("name");
                list.add(name);
            }
        } catch (SQLException e) {
            System.out.println("Проблемы с запросом на БД");
        }
        return list;
    }
    private static boolean findProjectByName(String projectName){
        try {
            connectionBegin();
            ResultSet resultSet = statement.executeQuery("select id from gosqltask1.projects where project_name='"+projectName+"'");
            return resultSet.next();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }
// 1
    public static void printSumSalaryOf (String projectName){
        if(findProjectByName(projectName)){
            try {
                int sumSalary;
                connectionBegin();
                ResultSet resultSet=statement.executeQuery(
                        "select sum(salary) as value from gosqltask1.developers dev " +
                                                "inner join gosqltask1.link_developers_projects ldp on ldp.dev_id=dev.id "+
                                                "inner join gosqltask1.projects pr on pr.id=ldp.project_id "+
                                                "where pr.project_name ='"+projectName+"'");
                if(resultSet.next()){
                    sumSalary=resultSet.getInt("value");
                    System.out.println("Сумма всех разработчиков в проекте "+projectName+" = "+sumSalary);
                }
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        else{
            emptyQuerry();
        }
    }
// 2
    public static void printDevelopersOf (String projectName){
        if(findProjectByName(projectName)) {
            List<String> list = new ArrayList<>();
            try {
                connectionBegin();
                ResultSet resultSet = statement.executeQuery(
                        "select dev.name from gosqltask1.developers dev " +
                                "join gosqltask1.link_developers_projects ldp on dev.id=ldp.dev_id " +
                                "join gosqltask1.projects pr on pr.id=ldp.project_id " +
                                "where pr.project_name='"+ projectName +"'");
                while (resultSet.next()){
                    list.add(resultSet.getString("name"));
                }
                if(!list.isEmpty()){
                    System.out.println("В проекте "+projectName+" участвуют:");
                    for(String s:list) {
                        System.out.println("\t\t"+s);
                    }
                }
                System.out.println();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        else{
            emptyQuerry();
        }
    }
//3
    public static void printAllDevelopersOfSkill(String skill){
        List<String> list = new ArrayList<>();
        try {
            connectionBegin();
            ResultSet resultSet=statement.executeQuery(
                    "select name from gosqltask1.developers dev " +
                                "join gosqltask1.link_developers_skills lds on dev.id=lds.dev_id " +
                                "join gosqltask1.skills sk on sk.id=lds.skill_id " +
                                "where sk.skill_name='"+skill+"'");
            while(resultSet.next()){
                list.add(resultSet.getString("name"));
            }
            if(!list.isEmpty()){
                System.out.println("Список разработчиков "+skill+":");
                for(String s:list){
                    System.out.println("\t\t"+s);
                }
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        System.out.println();
    }
//4
    public static void printAllDevelopersOfGrade(String grade){
        List<String> list = new ArrayList<>();
        try {
            connectionBegin();
            ResultSet resultset= statement.executeQuery(
                        "select distinct name from gosqltask1.developers dev " +
                                "join gosqltask1.link_developers_skills lds on dev.id=lds.dev_id " +
                                "join gosqltask1.skills sk on sk.id=lds.skill_id " +
                                "where sk.grade ='"+grade+"'");
            while(resultset.next()){
                list.add(resultset.getString("name"));
            }
        } catch (SQLException e) {
            System.out.println(e);
        }
        if(!list.isEmpty()){
            System.out.println("Список разработчиков с уровнем "+grade+":");
            for(String name:list){
                System.out.println("\t\t"+name);
            }
        }
        else{
            emptyQuerry();
        }
    }
//5.1
    private static void addColumnDateInProjects() throws SQLException {
        connectionBegin();
        statement.execute("alter table gosqltask1.projects add column datebegin date");
    }
    @SuppressWarnings("checked")
    private static void dropColumnDateInProjects() throws SQLException{
        connectionBegin();
        statement.execute("alter table gosqltask1.projects drop column datebegin");
    }
//5.2
    @SuppressWarnings("checked")
    private static void insertIntoProjectsColumnDate() throws SQLException {
        connectionBegin();
        statement.executeUpdate("update gosqltask1.projects pr set datebegin=adddate(now(),interval -5 day) where pr.id = 1");
        statement.executeUpdate("update gosqltask1.projects pr set datebegin=adddate(now(),interval -3 month) where pr.id = 2");
        statement.executeUpdate("update gosqltask1.projects pr set datebegin=adddate(now(),interval -2 month) where pr.id = 3");
        statement.executeUpdate("update gosqltask1.projects pr set datebegin=adddate(now(),interval -7 month) where pr.id = 4");
        statement.executeUpdate("update gosqltask1.projects pr set datebegin=adddate(now(),interval -4 month) where pr.id = 5");
        statement.executeUpdate("update gosqltask1.projects pr set datebegin=adddate(now(),interval -1 month) where pr.id = 6");
        statement.executeUpdate("update gosqltask1.projects pr set datebegin=adddate(now(),interval -11 month) where pr.id = 7");
    }
//5.3
    public static void printAllProjectsInfo(){
        List<Project> list = new ArrayList<>();
        try {
            addColumnDateInProjects();
            insertIntoProjectsColumnDate();
            connectionBegin();
            ResultSet resultSet=statement.executeQuery(
                                 "select pr.id,pr.project_name as name,pr.datebegin,count(dev.id)as devcount " +
                                    "from gosqltask1.projects pr " +
                                    "join gosqltask1.link_developers_projects ldp on ldp.project_id=pr.id " +
                                    "join gosqltask1.developers dev on ldp.dev_id=dev.id " +
                                    "group by pr.id " +
                                    "order by pr.id");
            while(resultSet.next()){
                list.add(new Project(resultSet.getInt("id"),
                                    resultSet.getString("name"),
                                    resultSet.getDate("datebegin"),
                                    resultSet.getInt("devcount")));
            }
        //чисто для возможности повторного запуска без ерроров
            dropColumnDateInProjects();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        if(!list.isEmpty()){
            System.out.println("Информация о проектах имеющихся в БД: ");
            for(Project project:list) {
                System.out.println("\t\t"+project.toString());
            }
        }
        else{
            emptyQuerry();
        }
    }
//6.1
/*  private String query1="insert into developers (name,age,sex,department_id,salary) values " +
                                                "('BellaGates',37,'female',1,2400)";
    private String query2="insert into projects (project_name,version,cost,datebegin) values " +
                                                "('Windows','Vista:revenge',41000,now())";
    private String query3="insert into customers (customer_name,minage,maxage) values " +
                                                "('OfficeWorkers',22,60)";
    public String getQuery1(){return query1;}
    public String getQuery1(){return query2;}
    public String getQuery1(){return query3;}
//6.2
    public static void justDoItsQuery(String query){
        try {
            connectionBegin();
            statement.execute(query);
        } catch (SQLException e) {
            System.out.println("Ошибка в запросе");
        }
    }
*/
    public static void printProjectsName() {
        List<String> list = readProjectsName();
        System.out.println("Список всех проектов: ");
        for (String name : list) {
            System.out.println("\t\t"+name);
        }
    }
}
