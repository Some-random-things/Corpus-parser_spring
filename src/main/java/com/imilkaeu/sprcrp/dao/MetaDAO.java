package com.imilkaeu.sprcrp.dao;

import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by imilka on 13.12.13.
 */
@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class MetaDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = Logger.getLogger(MetaDAO.class);

    @Transactional
    public String getDatabaseField(String property) {
        Session session = sessionFactory.getCurrentSession();
        List<String> results = session.createSQLQuery("SELECT dbfield FROM meta WHERE property = '" + property + "'").list();
        return results.get(0);
    }
}
