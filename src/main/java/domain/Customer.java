package domain;

import java.util.Objects;

import lombok.*;

@AllArgsConstructor
@Data
@EqualsAndHashCode (of = "id")
@Getter
@Setter
public class Customer {
    private String name;
    private long id;
    private int minAge;
    private int maxAge;

    public Customer(String name,  int minAge, int maxAge) {
        this.name = name;
        this.minAge = minAge;
        this.maxAge = maxAge;
    }
    
}
