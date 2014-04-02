package com.imilkaeu.sprcrp;

//import org.apache.log4j.Logger;
import org.springframework.core.io.FileSystemResource;

import java.io.*;
import java.util.HashMap;
import java.util.logging.Logger;

/**
 * Created by imilka on 13.12.13.
 */
public class MetaHelper {
    public static HashMap<String, String> languageProperties = new HashMap<String, String>();
    private static String META_FILE_NAME = "parser_ru_meta.txt";

    private static final Logger logger = Logger.getLogger(String.valueOf(MetaHelper.class));

    public static HashMap<String, String> getMeta(Class<?> cl) {
        HashMap<String, String> ret = new HashMap<String, String>();
        FileSystemResource resource = new FileSystemResource(META_FILE_NAME);
        File text = resource.getFile();
        try{
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    cl.getClassLoader().getResourceAsStream(
                            "com/imilkaeu/sprcrp/dao/parser_ru_meta.txt")));
            String metaString;
            while ((metaString = br.readLine()) != null) {
                String[] metaStringSplitted = metaString.split(";");
                if(metaStringSplitted[1].matches("case")) metaStringSplitted[1]="`case`";
                ret.put(metaStringSplitted[0],metaStringSplitted[1]);
                logger.info("ADDING TO METAHASHMAP " + metaStringSplitted[0]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }

    public static boolean isPartOfSpeech(String a) {
        /*getMeta();
        if(!languageProperties.containsKey(a) return true;
        return false;*/
        if(!a.equals("S") && !a.equals("A") && !a.equals("ADV")
                && !a.equals("COM") && !a.equals("CONJ") && !a.equals("INTJ")
                && !a.equals("NID") && !a.equals("NUM") && !a.equals("PART")
                && !a.equals("PR") && !a.equals("V")) {
            return false;
        } return true;
    }

    public static String getDatabaseField(String a, Class<?> cl) {
        HashMap<String, String> meta = getMeta(cl);
       // logger.warn("GDF HASHMAP SIZE: " + meta.size());
        return meta.get(a);
    }

}