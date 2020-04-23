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
    private static List<String> list = new ArrayList<>();

    private static void connectionBegin() throws SQLException {
        connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        statement = connection.createStatement();
    }
    private static void readProjectsName(){
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
    }
// 1
    public static void printSumSalaryOf (String projectName){
        readProjectsName();
        if(list.contains(projectName)){
            list.clear();
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
                System.out.println("Проблемы с запросом на БД");
            }
        }
        else{
            System.out.println("Запрос ничего не вывел");
        }
    }
// 2
    public static void printDevelopersOf (String projectName){
        readProjectsName();
        if(list.contains(projectName)) {
            try {
                connectionBegin();
                ResultSet resultSet = statement.executeQuery()
                while (resultSet.next()){
                    System.out.println();
                }
                System.out.println();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
    public static void printAllJavaDevelopers(){

        System.out.println();
    }
    public static void printAllMiddleDevelopers(){

        System.out.println();
    }
    public static void printAllProjectsInfo(){

        System.out.println();
    }
    public static void printProjectsName() {
        readProjectsName();
        System.out.println("Список всех проектов: ");
        for (String name : list) {
            System.out.print("\t\t");
            System.out.println(name);
        }
        list.clear();
    }
}
