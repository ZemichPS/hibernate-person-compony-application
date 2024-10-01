package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(schema = "application")
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Getter
    private UUID uuid;
    @Getter
    @Setter
    private String name;
    @Getter
    @Setter
    private String address;
    @OneToMany(
            fetch = FetchType.LAZY,
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            mappedBy = "company"
    )
    private List<Employee> employees = new ArrayList<>();

    public Company addEmployee(Employee employee) {
        employee.addCompany(this);
        employees.add(employee);
        return this;
    }
}