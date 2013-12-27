package com.imilkaeu.sprcrp.dao;

import com.imilkaeu.sprcrp.QueryBuilder;
import com.imilkaeu.sprcrp.models.output.BigramCombination;
import com.imilkaeu.sprcrp.models.output.OutputSentenceInfo;
import org.apache.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imilka on 13.12.13.
 */
@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class SentenceDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private MetaDAO metaDAO;

    private static final Logger logger = Logger.getLogger(SentenceDAO.class);

    @Transactional
    public List<OutputSentenceInfo> getSentenceByBigram(BigramCombination inputData) {
        Session session = sessionFactory.getCurrentSession();
        List<OutputSentenceInfo> resultData = new ArrayList<OutputSentenceInfo>();

        String query = QueryBuilder.buildSentenceQuery(inputData, metaDAO);
        logger.warn("Sentence query: " + query);
        List<Object[]> results = session.createSQLQuery(query).list();
        int id; String bigram, sentence;
        for(Object[] result: results) {
            bigram = result[0].toString();
            bigram += " > " + result[1].toString();
            id = (Integer) result[2];
            sentence = result[3].toString();

            resultData.add(new OutputSentenceInfo(id, bigram, sentence));
        }

        return resultData;
    }
}
