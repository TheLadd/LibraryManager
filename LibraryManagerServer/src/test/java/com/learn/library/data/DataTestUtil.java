package com.learn.library.data;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.StoredProcedureQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DataTestUtil {
    private static DataTestUtil instance;

//    @PersistenceContext
//    private EntityManager entityManager;

//    StoredProcedureQuery setKnownGoodStateStoredProcedure;

    @Autowired
    private JdbcTemplate jdbcTemplate;

//    public DataTestUtil(EntityManager entityManager) {
//        this.entityManager = entityManager;
//        this.setKnownGoodStateStoredProcedure = entityManager.createStoredProcedureQuery("set_known_good_state");
//    }

    public DataTestUtil(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void setKnownGoodState() {
//        setKnownGoodStateStoredProcedure.execute();
        jdbcTemplate.update("call set_known_good_state()");
    }
}
