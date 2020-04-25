package service;

import domain.Customer;
import lombok.extern.slf4j.Slf4j;
import repository.CustomersDAO;

@Slf4j
public class CustomerService {
    private final CustomersDAO customersDAO= new CustomersDAO();

    //см. коммент в ProjectService - проблемы с аргументами метода
    public void addCustomer(Customer customer){
        if(customersDAO.addCustomer(customer.getName(),customer.getMinAge(),customer.getMaxAge())){
            System.out.println("Данные добавлены в таблицу");
        }
        else{
            log.error("Что-то пошло не так");
        }
    }
}
