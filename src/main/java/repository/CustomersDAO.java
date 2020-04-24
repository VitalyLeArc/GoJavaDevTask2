package repository;

import domain.Customer;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

import static repository.QueryToDB.*;

public class CustomersDAO {

//6.3
    public boolean addCustomer(String customerName,int minAge,int maxAge){
        boolean isSuccess=false;
        try {
            connectionBegin();
            isSuccess=statement.execute("insert into gosqltask1.customers (customer_name,minage,maxage) values " +
                    "('"+customerName+"',"+minAge+","+maxAge+");");
        } catch (SQLException e) {
            System.out.println("Ошибка в запросе");
        }
        return isSuccess;
    }

    public Optional<Customer> getCustomerByName(String name){
        Optional<Customer> optional;
        Customer customer = null;
        try {
            connectionBegin();
            ResultSet resultSet = statement.executeQuery("select id,customer_name,minage,maxage " +
                                                        "from gosqltask1.customers where customer_name='"+name+"';");
            customer= new Customer(resultSet.getString("customer_name"),resultSet.getInt("id"),
                                    resultSet.getInt("minage"),resultSet.getInt("maxage"));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        optional = Optional.ofNullable(customer);
        return optional;
    }

}
