package com.autotest.utils;

import org.testng.SkipException;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CsvRead {

    public Iterator<Object[]> readDataFromCSV(String path){
        List<Object[]> list = new ArrayList<Object[]>();
        try {
            FileReader is = new FileReader(new File(path));
            BufferedReader br = new BufferedReader(is);
            while(br.ready()){
                list.add(br.readLine().split(","));
            }
            return list.iterator();
        }catch(Exception ex){
            throw new SkipException(ex.getMessage());
        }
    }
}
