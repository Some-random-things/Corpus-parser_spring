package com.imilkaeu.sprcrp.dao;

import com.imilkaeu.sprcrp.common.QueryBuilder;
import com.imilkaeu.sprcrp.models.output.Bigram;
import com.imilkaeu.sprcrp.models.output.BigramCombination;
import com.imilkaeu.sprcrp.models.output.OutputSentenceInfo;
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

    @Transactional
    public List<OutputSentenceInfo> getSentenceByBigram(BigramCombination inputData) {
        Session session = sessionFactory.getCurrentSession();
        List<OutputSentenceInfo> resultData = new ArrayList<OutputSentenceInfo>();

        String query = QueryBuilder.buildSentenceQuery(inputData, metaDAO);
        List<Object[]> results = session.createSQLQuery(query).list();
        int id; Bigram bigram; String sentence;
        for(Object[] result: results) {
            bigram = new Bigram(result[0].toString(), result[1].toString(), inputData.getDirection());
            id = (Integer) result[2];
            sentence = result[3].toString();
            resultData.add(new OutputSentenceInfo(id, bigram, sentence));
        }

        return resultData;
    }
}
