package com.imilkaeu.sprcrp.dao;

import com.imilkaeu.sprcrp.QueryBuilder;
import com.imilkaeu.sprcrp.models.Word;
import com.imilkaeu.sprcrp.models.input.BigramInputData;
import com.imilkaeu.sprcrp.models.output.BigramCombination;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by imilka on 13.12.13.
 */
@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class WordDAO {
    @Autowired
    private SessionFactory sessionFactory;

    private static final Logger logger = Logger.getLogger(WordDAO.class);

    @Transactional
    public List<Word> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List words = session.createSQLQuery("SELECT * FROM words").list();
        return words;
    }

    @Transactional
    public List<BigramCombination> getBigramStats(BigramInputData inputData) {
        Session session = sessionFactory.getCurrentSession();
        List<BigramCombination> resultData = new ArrayList<BigramCombination>();

        List<String> queries;
        if(!inputData.isRawRequest()) queries = QueryBuilder.buildSummaryQueryList(inputData);
        else queries = QueryBuilder.buildRawQueryList(inputData);
        for(String query:queries) {
            logger.info("Query: " + query);
            List<Object[]> results = session.createSQLQuery(query).list();
            logger.info("Results size: " + results.size());
            Integer count; List<String> properties;
            for(Object[] result:results) {
                properties = new ArrayList<String>();
                for(int i = 0; i < result.length-1; i++) properties.add(result[i].toString());
                if(inputData.isRawRequest()) count = ((BigInteger) result[result.length-1]).intValue();
                else count = ((BigDecimal) result[result.length-1]).intValue();
                resultData.add(new BigramCombination(count.intValue(), properties));
            }
        }

        return resultData;
    }
}
