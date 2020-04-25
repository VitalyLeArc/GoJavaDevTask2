package repository;/*соединяется с БД
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

import domain.Project;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public final class QueryToDB {
    private static final String URL ="jdbc:mysql://localhost:3306/mysql?serverTimezone=UTC";
    private static String USERNAME = "root1";
    private static String PASSWORD = "root1";

    protected static Connection connection;
    protected static Statement statement;

    protected static void connectionBegin() throws SQLException {
        connection = DriverManager.getConnection(URL,USERNAME,PASSWORD);
        statement = connection.createStatement();
    }

    //что это за метод и что он тут делает?
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





}
