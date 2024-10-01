package models.projections;

import lombok.Getter;

import java.util.UUID;

@Getter
public class EmployeeDTO {
    private UUID uuid;
    private String firstName;
    private String lastName;
    private String companyName;

    public EmployeeDTO(UUID uuid, String firstName, String lastName, String companyName) {
        this.uuid = uuid;
        this.firstName = firstName;
        this.lastName = lastName;
        this.companyName = companyName;
    }
}
