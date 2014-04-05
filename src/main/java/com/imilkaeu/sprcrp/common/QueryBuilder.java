package com.imilkaeu.sprcrp.common;

import com.imilkaeu.sprcrp.dao.MetaDAO;
import com.imilkaeu.sprcrp.models.input.BigramInputData;
import com.imilkaeu.sprcrp.models.input.InputPartOfSpeech;
import com.imilkaeu.sprcrp.models.input.Property;
import com.imilkaeu.sprcrp.models.input.PropertyValue;
import com.imilkaeu.sprcrp.models.output.BigramCombination;
import com.imilkaeu.sprcrp.models.output.PartOfSpeech;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by imilka on 12.12.13.
 */
public class QueryBuilder {

    public static List<String> buildRawQueryList(BigramInputData data){
        String query;
        int groupByCount;
        List<String> queryList = new ArrayList<String>();
        List<InputPartOfSpeech> mains = data.getMain();
        List<InputPartOfSpeech> deps = data.getDep();

        for(InputPartOfSpeech partOfSpeechMain : mains)
            for(InputPartOfSpeech partOfSpeechDep : deps){
                query="SELECT w1.partOfSpeech AS mainPartOfSpeech";
                groupByCount = 2;

                boolean selected;
                for(Property propertyMain : partOfSpeechMain.getContent()){
                    selected = false;
                    for(PropertyValue value : propertyMain.getValues()) {
                        if(value.isSelected()) {
                            selected = true;
                            break;
                        }
                    }

                    if(selected) {
                        query+=", w1."+propertyMain.getDbfield()+" AS main" + propertyMain.getDbfield() + " ";
                        groupByCount++;
                    }
                }

                query += ", w2.partOfSpeech AS depPartOfSpeech";

                for(Property propertyDep : partOfSpeechDep.getContent()){
                    selected = false;
                    for(PropertyValue value : propertyDep.getValues()) {
                        if(value.isSelected()) {
                            selected = true;
                            break;
                        }
                    }

                    if(selected) {
                        query+=", w2."+propertyDep.getDbfield()+" AS dep" + propertyDep.getDbfield() + " ";
                        groupByCount++;
                    }
                }
                //FROM
                query+=", COUNT(*) FROM words w1 JOIN words w2 ON w2.domid=w1.internalId ";
                //
                //WHERE
                //main
                query+="WHERE (w1.partOfSpeech='"+partOfSpeechMain.getPartOfSpeech()+"'";

                for(int i=0;i<partOfSpeechMain.getContent().size();i++) {
                    int j = 0;
                    for(PropertyValue propertyValueMain : partOfSpeechMain.getContent().get(i).getValues()){
                        if(!propertyValueMain.isSelected()) continue;

                        String add;
                        if(j == 0) add = " AND (";
                        else add = " OR ";
                        query+= add + "w1."+partOfSpeechMain.getContent().get(i).getDbfield()+"='"+propertyValueMain.getIdent()+"'";
                        j++;
                    }
                    if(j != 0) query += ")";
                }
                query+=") ";

                //dep
                query+="AND (w2.partOfSpeech='"+partOfSpeechDep.getPartOfSpeech()+"'";
                for(int i=0;i<partOfSpeechDep.getContent().size();i++) {
                    int j = 0;
                    for(PropertyValue propertyValueDep : partOfSpeechDep.getContent().get(i).getValues()){
                        if(!propertyValueDep.isSelected()) continue;

                        String add;
                        if(j == 0) add = " AND (";
                        else add = " OR ";
                        query+= add + "w2."+partOfSpeechDep.getContent().get(i).getDbfield()+"='"+propertyValueDep.getIdent()+"'";
                        j++;
                    }
                    if(j != 0) query += ")";
                }
                query+=") ";
                query+="AND w1.sentenceId=w2.sentenceId ";

                //GROUP BY
                query+="GROUP BY ";
                for(int i=0;i<groupByCount-1;i++)
                    query+=String.valueOf(i+1)+", ";
                query+=String.valueOf(groupByCount);
                //
                queryList.add(query);
            }
        return queryList;
    }

    public static List<String> buildSummaryQueryList(BigramInputData data){
        String query;
        int groupByCount;
        List<String> queryList = new ArrayList<String>();
        List<InputPartOfSpeech> mains = data.getMain();
        List<InputPartOfSpeech> deps = data.getDep();

        for(InputPartOfSpeech partOfSpeechMain : mains)
            for(InputPartOfSpeech partOfSpeechDep : deps){
                query="SELECT mainpartOfSpeech";
                groupByCount = 2;

                boolean selected;
                for(Property propertyMain : partOfSpeechMain.getContent()){
                    selected = false;
                    for(PropertyValue value : propertyMain.getValues()) {
                        if(value.isSelected()) {
                            selected = true;
                            break;
                        }
                    }

                    if(selected) {
                        query+=", main"+propertyMain.getDbfield()+" ";
                        groupByCount++;
                    }
                }

                query += ", deppartOfSpeech";

                for(Property propertyDep : partOfSpeechDep.getContent()){
                    selected = false;
                    for(PropertyValue value : propertyDep.getValues()) {
                        if(value.isSelected()) {
                            selected = true;
                            break;
                        }
                    }

                    if(selected) {
                        query+=", dep"+propertyDep.getDbfield()+" ";
                        groupByCount++;
                    }
                }
                //FROM
                query+=", direction, SUM(count) FROM combinations2 ";
                //
                //WHERE
                //main
                query+="WHERE (mainpartOfSpeech='"+partOfSpeechMain.getPartOfSpeech()+"'";

                for(int i=0;i<partOfSpeechMain.getContent().size();i++) {
                    int j = 0;
                    for(PropertyValue propertyValueMain : partOfSpeechMain.getContent().get(i).getValues()){
                        if(!propertyValueMain.isSelected()) continue;

                        String add;
                        if(j == 0) add = " AND (";
                        else add = " OR ";
                        query+= add + "main"+partOfSpeechMain.getContent().get(i).getDbfield()+"='"+propertyValueMain.getIdent()+"'";
                        j++;
                    }
                    if(j != 0) query += ")";
                }
                query+=") ";

                //dep
                query+="AND (deppartOfSpeech='"+partOfSpeechDep.getPartOfSpeech()+"'";
                for(int i=0;i<partOfSpeechDep.getContent().size();i++) {
                    int j = 0;
                    for(PropertyValue propertyValueDep : partOfSpeechDep.getContent().get(i).getValues()){
                        if(!propertyValueDep.isSelected()) continue;

                        String add;
                        if(j == 0) add = " AND (";
                        else add = " OR ";
                        query+= add + "dep"+partOfSpeechDep.getContent().get(i).getDbfield()+"='"+propertyValueDep.getIdent()+"'";
                        j++;
                    }
                    if(j != 0) query += ")";
                }
                query+=") ";

                //GROUP BY
                query+="GROUP BY ";
                for(int i=0;i<groupByCount-1;i++)
                    query+=String.valueOf(i+1)+", ";
                query+=String.valueOf(groupByCount);
                query+="," + String.valueOf(groupByCount+1);
                queryList.add(query);
            }
        return queryList;
    }

    public static String buildSentenceQuery(BigramCombination bigram, MetaDAO metaDAO) {
        PartOfSpeech main = bigram.getMain();
        PartOfSpeech dep = bigram.getDep();
        int direction = bigram.getDirection();

        String query;
        query="SELECT w1.word AS mainword, w2.word AS depword, s.id, s.sentence FROM words w1 JOIN words w2 on w2.domid = w1.internalId JOIN sentences s ON s.id = w1.sentenceId " +
                "WHERE w1.partOfSpeech = '" + main.getPartOfSpeech() + "' AND w2.partOfSpeech = '" + dep.getPartOfSpeech() + "'";

        if(direction == 0) query += " AND w1.internalId > w2.internalId";
        else query += " AND w1.internalId < w2.internalId";

        String dbfield;
        for(String property : main.getProperties()) {
            dbfield = metaDAO.getDatabaseField(property);
            query += " AND w1." + dbfield + " = '" + property + "'";
        }

        for(String property : dep.getProperties()) {
            dbfield = metaDAO.getDatabaseField(property);
            query += " AND w2." + dbfield + " = '" + property + "'";
        }

        query += " AND w1.sentenceId = w2.sentenceId LIMIT 100";
        return query;
    }

    public static String buildDecisionTreeQuery(BigramCombination bigram, MetaDAO metaDAO) {
        PartOfSpeech main = bigram.getMain();
        PartOfSpeech dep = bigram.getDep();

        /*String query = "SELECT new map(w1.partOfSpeech AS partOfSpeech1, w2.partOfSpeech AS partOfSpeech2, w1.count AS count1, w2.count AS count2, w1.gender AS gender1, w2.gender AS gender2, " +
                "w1.wordCase AS case1, w2.wordCase AS case2, w1.animacy AS animacy1, w2.animacy AS animacy2, w1.wordShort AS short1, w2.wordShort AS short2, w1.aspect AS aspect1, w2.aspect AS aspect2, " +
                "w1.mood AS mood1, w2.mood AS mood2, w1.tense AS tense1, w2.tense AS tense2, w1.person AS person1, w2.person AS person2, w1.form AS form1, w2.form AS form2, " +
                "w1.comparison AS comparison1, w2.comparison AS comparison2, w1.voice AS voice1, w2.voice AS voice2, w1.pledge AS pledge1, w2.pledge AS pledge2, " +
                "CASE WHEN w1.internalId > w2.internalId THEN 1 ELSE 0 END AS direction) " +
                "FROM Word as w1, Word as w2 " +
                "WHERE w2.domid = w1.internalId AND w1.partOfSpeech = '" + main.getPartOfSpeech() + "' AND w2.partOfSpeech = '" + dep.getPartOfSpeech() + "'";
        */

        String query = "SELECT w1.partOfSpeech AS partOfSpeech1, w2.partOfSpeech AS partOfSpeech2, w1.count AS count1, w2.count AS count2, w1.gender AS gender1, w2.gender AS gender2, " +
                "w1.wordCase AS case1, w2.wordCase AS case2, w1.animacy AS animacy1, w2.animacy AS animacy2, w1.wordShort AS short1, w2.wordShort AS short2, w1.aspect AS aspect1, w2.aspect AS aspect2, " +
                "w1.mood AS mood1, w2.mood AS mood2, w1.tense AS tense1, w2.tense AS tense2, w1.person AS person1, w2.person AS person2, w1.form AS form1, w2.form AS form2, " +
                "w1.comparison AS comparison1, w2.comparison AS comparison2, w1.voice AS voice1, w2.voice AS voice2, w1.pledge AS pledge1, w2.pledge AS pledge2, " +
                "w1.internalId > w2.internalId AS direction " +
                "FROM words w1 " +
                "JOIN words w2 ON w2.domid = w1.internalId " +
                "WHERE w1.partOfSpeech = '" + main.getPartOfSpeech() + "' AND w2.partOfSpeech = '" + dep.getPartOfSpeech() + "'";

        String dbfield;
        for(String property : main.getProperties()) {
            dbfield = metaDAO.getDatabaseField(property);
            query += " AND w1." + dbfield + " = '" + property + "'";
        }

        for(String property : dep.getProperties()) {
            dbfield = metaDAO.getDatabaseField(property);
            query += " AND w2." + dbfield + " = '" + property + "'";
        }

        query += " AND w1.sentenceId = w2.sentenceId";
        return query;
    }
}
