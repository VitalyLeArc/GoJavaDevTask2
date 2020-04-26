package domain;

import lombok.*;

import java.math.BigDecimal;
import java.sql.Date;

@AllArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
@Getter
@Setter
public class Project {
    private long id;
    private String name;
    private Date startDate;
    private String version;
    private BigDecimal cost;
    private int countOfDevs;

    public Project(String name) {
        this.name = name;
    }

    public Project(String name, String version, BigDecimal cost) {
        this(name);
        this.version = version;
        this.cost = cost;
    }

    public Project(String name, String version, BigDecimal cost, Date startDate) {
        this(name, version, cost);
        this.startDate = startDate;
    }

    public String toString() {
        return "id: " + id + ", name: " + name + " " + version + ", startDate: " + startDate.toString() + ", countOfDevelopers: " + countOfDevs;
    }
}

