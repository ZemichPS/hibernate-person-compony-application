package models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.id.UUIDGenerator;

import java.util.UUID;

@Entity
@Table(schema = "application")
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID uuid;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    @ManyToOne(
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "company_uuid", referencedColumnName = "uuid")
    private Company company;

    public Employee addCompany(Company company){
        this.company = company;
        return this;
    }

}
