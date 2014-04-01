package com.imilkaeu.sprcrp.dao;

import com.imilkaeu.sprcrp.models.HashQuery;
import org.hibernate.Query;
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
public class HashDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Transactional
    public List<HashQuery> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List queries = session.createSQLQuery("SELECT * FROM words").list();
        return queries;
    }

    @Transactional
    public List<HashQuery> findByHash(String hash) {
        Session session = sessionFactory.getCurrentSession();
        String hql = "from HashQuery hq where hq.hash=:hash";

        Query query = session.createQuery(hql);
        query.setString("hash", hash);
        return query.list();
    }

    @Transactional
    public void insertQuery(String hash, String query) {
        HashQuery hq = new HashQuery();
        hq.setHash(hash); hq.setQuery(query);

        Session session = sessionFactory.getCurrentSession();
        session.save(hq);
    }
}
