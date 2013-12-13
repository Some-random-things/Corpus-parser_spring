package com.imilkaeu.sprcrp;

import org.springframework.core.io.FileSystemResource;

import java.io.*;
import java.util.HashMap;

/**
 * Created by imilka on 13.12.13.
 */
public class MetaHelper {
    public static HashMap<String, String> languageProperties = new HashMap<String, String>();
    private static String META_FILE_NAME = "/WEB-INF/parser_ru_meta.txt";

    public static void getMeta() {
        FileSystemResource resource = new FileSystemResource(META_FILE_NAME);
        File text = resource.getFile();
        try{
            BufferedReader br = new BufferedReader(new FileReader(text));
            String metaString;
            while ((metaString = br.readLine()) != null) {
                String[] metaStringSplitted = metaString.split(";");
                if(metaStringSplitted[1].matches("case")) metaStringSplitted[1]="`case`";
                languageProperties.put(metaStringSplitted[0],metaStringSplitted[1]);
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static boolean isPartOfSpeech(String a) {
        /*getMeta();
        if(!languageProperties.containsKey(a.toLowerCase())) return true;
        return false;*/
        if(!a.equals("S") && !a.equals("A") && !a.equals("ADV")
                && !a.equals("COM") && !a.equals("CONJ") && !a.equals("INTJ")
                && !a.equals("NID") && !a.equals("NUM") && !a.equals("PART")
                && !a.equals("PR") && !a.equals("V")) {
            return false;
        } return true;
    }

    public static String getDatabaseField(String a) {
        getMeta();
        return languageProperties.get(a.toLowerCase());
    }

}