import models.Company;
import models.Employee;
import models.projections.EmployeeDTO;
import models.projections.EmployeeProjection;
import org.hibernate.Session;
import org.hibernate.query.Query;
import util.HibernateUtil;

import java.util.Objects;
import java.util.UUID;

public class Main {
    public static void main(String[] args) {
        Session session = HibernateUtil.getSession();

        try {
            session.beginTransaction();
            session.setDefaultReadOnly(true);
            UUID userUUID = UUID.fromString("d1997f9e-de4f-43c7-971d-24aed8ab44bf");

            String hql = """
                    SELECT e.uuid AS uuid, e.firstName AS firstName, e.lastName as lastName, c.name AS companyName
                    FROM Employee e JOIN Company c
                    WHERE e.uuid = :userUUID
                    """;

            Query<EmployeeProjection> query = session.createQuery(hql, EmployeeProjection.class);
            query.setParameter("userUUID", userUUID);

            System.out.println(query.getSingleResult());

            //EmployeeProjection employeeProjection = query.getSingleResult();
            //System.out.println(employeeProjection);

            session.getTransaction().commit();
        } catch (Exception e) {
            if (Objects.nonNull(session.getTransaction())) {
                session.getTransaction().rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
