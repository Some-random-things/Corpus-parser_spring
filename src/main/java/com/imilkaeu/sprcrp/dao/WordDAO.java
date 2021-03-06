package com.imilkaeu.sprcrp.dao;

import com.imilkaeu.sprcrp.common.DecisionTreeBuilder;
import com.imilkaeu.sprcrp.common.MetaHelper;
import com.imilkaeu.sprcrp.common.QueryBuilder;
import com.imilkaeu.sprcrp.models.HashQuery;
import com.imilkaeu.sprcrp.models.Word;
import com.imilkaeu.sprcrp.models.input.BigramInputData;
import com.imilkaeu.sprcrp.models.output.*;
import org.apache.log4j.Logger;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.AliasToEntityMapResultTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by imilka on 13.12.13.
 */
@Repository
@SuppressWarnings({"unchecked", "rawtypes"})
public class WordDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Autowired
    private HashDAO hashDAO;

    @Autowired
    private MetaDAO metaDAO;

    private static final Logger logger = Logger.getLogger(WordDAO.class);

    @Transactional
    public List<Word> findAll() {
        Session session = sessionFactory.getCurrentSession();
        List words = session.createSQLQuery("SELECT * FROM words").list();
        return words;
    }

    @Transactional
    public BigramCombinationInfo getBigramStats(String hash) {
        List<HashQuery> hqs = hashDAO.findByHash(hash);
        List<String> queries = new ArrayList<String>();
        for(HashQuery hq: hqs) {
            queries.add(hq.getQuery());
        }

        return getBigramStats(queries, false);
    }

    @Transactional
    public BigramCombinationInfo getBigramStats(BigramInputData inputData) {
        List<String> queries;
        if(!inputData.isRawRequest()) queries = QueryBuilder.buildSummaryQueryList(inputData);
        else queries = QueryBuilder.buildRawQueryList(inputData);

        return getBigramStats(queries, inputData.isRawRequest());
    }

    @Transactional
    public BigramCombinationInfo getBigramStats(List<String> queries, boolean isRawRequest) {
        Session session = sessionFactory.getCurrentSession();
        List<BigramCombination> resultData = new ArrayList<BigramCombination>();

        String hash = Long.toHexString(Double.doubleToLongBits(Math.random()));

        for(String query:queries) {
            hashDAO.insertQuery(hash, query);

            //logger.info("Query: " + query);
            List<Object[]> results = session.createSQLQuery(query).list();
            //logger.info("Results size: " + results.size());

            Integer count;
            PartOfSpeech main;
            PartOfSpeech dep;
            List<String> properties = new ArrayList<String>();
            boolean isMainCurrent;

            for(Object[] result:results) {
                if(isRawRequest) count = ((BigInteger) result[result.length-1]).intValue();
                else count = ((BigDecimal) result[result.length-1]).intValue();

                main = new PartOfSpeech(); dep = new PartOfSpeech();
                properties.clear(); isMainCurrent = true;

                for(int i = 0; i < result.length-1; i++) {
                    if(MetaHelper.isPartOfSpeech(result[i].toString())) {
                        if(isMainCurrent) {
                            main.setPartOfSpeech(result[i].toString());
                            isMainCurrent = false;
                        } else {
                            main.setProperties(properties.toArray(new String[properties.size()]));
                            dep.setPartOfSpeech(result[i].toString());
                            properties.clear();
                        }
                    } else {
                        properties.add(result[i].toString());
                    }
                }

                String direction = properties.get(properties.size()-1);
                properties.remove(properties.size()-1);

                dep.setProperties(properties.toArray(new String[properties.size()]));
                BigramCombination bc = new BigramCombination();
                bc.setCount(count); bc.setDep(dep); bc.setMain(main);
                bc.setDirection(Integer.valueOf(direction));
                resultData.add(bc);
            }
        }

        BigramCombinationInfo returnInfo = new BigramCombinationInfo();
        returnInfo.setHash(hash);
        returnInfo.setCombinations(resultData);
        return returnInfo;
    }

    @Transactional
    public DecisionTreeNode getDecisionTreeVisualization(BigramCombination inputData) {
        Session session = sessionFactory.getCurrentSession();

        String queryString = QueryBuilder.buildDecisionTreeQuery(inputData, metaDAO);
        Query query = session.createSQLQuery(queryString);
        query.setResultTransformer(AliasToEntityMapResultTransformer.INSTANCE);

        List<Map<String,Object>> results = query.list();
        return new DecisionTreeBuilder(results).startParse().convertForOutput();
    }
}
