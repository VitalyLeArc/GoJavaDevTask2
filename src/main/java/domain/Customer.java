package domain;

import java.util.Objects;

public class Customer {
    String name;
    int id;
    int minAge;
    int maxAge;

    public Customer(String name, int id, int minAge, int maxAge) {
        this.name = name;
        this.id = id;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }

    public String getName() {
        return name;
    }
    public int getId() {
        return id;
    }
    public int getMinAge() {
        return minAge;
    }
    public int getMaxAge() {
        return maxAge;
    }

    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Customer customer = (Customer)obj;
        return id == customer.id && minAge == customer.minAge &&
                maxAge == customer.maxAge && Objects.equals(name, customer.name);
    }

}
