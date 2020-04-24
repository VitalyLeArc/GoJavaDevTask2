package service;

import repository.CustomersDAO;

public class CustomerService {
    private final CustomersDAO customersDAO= new CustomersDAO();

    public void addCustomer(String cuntomerName,int minage,int maxage){
        if(customersDAO.addCustomer(cuntomerName,minage,maxage)){
            System.out.println("Данные добавлены в таблицу");
        }
        else{
            System.out.println("Что-то пошло не так");
        }
    }
}
