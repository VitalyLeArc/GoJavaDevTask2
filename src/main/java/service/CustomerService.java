package service;

import domain.Customer;

import lombok.extern.slf4j.Slf4j;
import repository.CustomersDAO;

@Slf4j
public class CustomerService {
    private final CustomersDAO customersDAO = new CustomersDAO();

    public void addCustomer(Customer customer) {
        if (customersDAO.addCustomer(customer)) {
            log.info("Данные успешно добавлены");
        }
    }
}
