package models.projections;

import java.util.UUID;

public interface EmployeeProjection {
    UUID getUuid();
    String getFirstName();
    String getLastName();
    String getCompanyName();

}
