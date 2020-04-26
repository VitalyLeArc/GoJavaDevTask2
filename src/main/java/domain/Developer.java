package domain;

import lombok.*;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Developer {
    private String name;
    private int age;
    private long id;
    String sex;
    BigDecimal salary;

    public Developer(String name) {
        this.name = name;
    }

    public Developer(long id, String name) {
        this(name);
        this.id = id;
    }

    public Developer(String name, int age, String sex, BigDecimal salary) {
        this(name);
        this.sex = sex;
        this.salary = salary;
        this.age = age;
    }

}
