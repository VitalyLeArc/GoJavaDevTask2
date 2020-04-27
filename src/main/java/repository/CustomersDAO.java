package repository;

import static repository.QueryToDB.*;

import domain.Customer;
import lombok.extern.slf4j.Slf4j;

import java.sql.SQLException;

@Slf4j
public class CustomersDAO {
    //6.3
    public boolean addCustomer(Customer customer) {
        try {
            connectionBegin();
            log.debug("Добавление в БД ",customer);
            statement.execute("insert into godevtask2.customers (customer_name,minage,maxage) values " +
                    "('" + customer.getName() + "'," + customer.getMinAge() + "," + customer.getMaxAge() + ")");
            return true;
        } catch (SQLException e) {
            log.error("Ошибка в запросе "+e.getMessage());
            return false;
        }
    }

}
