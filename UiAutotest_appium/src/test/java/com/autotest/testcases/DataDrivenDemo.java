package com.autotest.testcases;

import com.autotest.utils.CsvRead;
import com.autotest.utils.ExcelRead;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.Iterator;

public class DataDrivenDemo {

    @DataProvider(name = "data")
    public Object[][] dataprovide(){


        return new Object[][]{
                {"zhansan","1897878384734"},
                {"lisi","13278654773"},
                {"wangwu","18898654678"},
                {"zhaoliu","4343u3u898943"},
        };
    }

    @Test(dataProvider = "data")
    public void dataProvide(String name,String number){
        System.out.println("my name is :"+name);
        System.out.println("my tell is :"+number);
    }


    @DataProvider(name = "data1")
    public Iterator<Object[]> dataprovide1(){
        CsvRead csvRead = new CsvRead();
        String path = System.getProperty("user.dir")+"/src/main/resources/DataDriverDemo.csv";

        return csvRead.readDataFromCSV(path);

    }

    @Test(dataProvider = "data1")
    public void csvDataProvide(String name,String tell){

        System.out.println("my name is :"+name);
        System.out.println("my tell is :"+tell);

    }

    @DataProvider(name = "exceldata")

    public Object[][] exceldataprovide(){
        String path = System.getProperty("user.dir")+"/src/main/resources/ExcelRead.xls";
        ExcelRead excelRead = new ExcelRead();
        return excelRead.getDataFromExcel(path);
    }

    @Test(dataProvider = "exceldata")
    public void testDatademo(String name,String tell){
        System.out.println("my name is :"+name);
        System.out.println("my tell is :"+tell);


    }
}
