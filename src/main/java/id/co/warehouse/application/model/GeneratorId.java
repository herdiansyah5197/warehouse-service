package id.co.warehouse.application.model;

import id.co.warehouse.application.repository.OrderRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import org.hibernate.Session;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.Optional;

@Component
public class GeneratorId implements IdentifierGenerator {


    private static final String PREFIX = "O";

    @Override
    public Serializable generate(SharedSessionContractImplementor session, Object object) {
        Session hibernateSession = (Session) session;
        EntityManager entityManager = hibernateSession.getEntityManagerFactory().createEntityManager();

        String maxId = (String) entityManager.createNativeQuery("SELECT MAX(o.\"ORDER_ID\") FROM DVF.\"ORDERS\" o")
                .getSingleResult();

        int nextId = (maxId == null) ? 1 : Integer.parseInt(maxId.substring(1)) + 1;

        return PREFIX + nextId;
    }
}
