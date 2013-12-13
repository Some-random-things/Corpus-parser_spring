package com.imilkaeu.sprcrp;

import com.imilkaeu.sprcrp.models.input.BigramInputData;
import com.imilkaeu.sprcrp.models.input.PartOfSpeech;
import com.imilkaeu.sprcrp.models.input.Property;
import com.imilkaeu.sprcrp.models.input.PropertyValue;

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
        List<PartOfSpeech> mains = data.getMain();
        List<PartOfSpeech> deps = data.getDep();

        for(PartOfSpeech partOfSpeechMain : mains)
            for(PartOfSpeech partOfSpeechDep : deps){
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
        List<PartOfSpeech> mains = data.getMain();
        List<PartOfSpeech> deps = data.getDep();

        for(PartOfSpeech partOfSpeechMain : mains)
            for(PartOfSpeech partOfSpeechDep : deps){
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
                query+=", SUM(count) FROM combinations ";
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
                //
                queryList.add(query);
            }
        return queryList;
    }
}
