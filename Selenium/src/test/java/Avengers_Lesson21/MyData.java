package Avengers_Lesson21;

import org.testng.annotations.DataProvider;

public class MyData {


    @DataProvider(name = "avengers-data")
    public static Object[][] getDataProvider(){

        return new Object[][]{
                {0,"IronMan","IronManRocks","7.9"},
                {1,"CaptainAmerica","CaptainAmericaRocks","6.9"},
                {2,"TheHulk","TheHulkRocks","6.6"},
                {3,"Thor","ThorRocks","7.0"}
        };
    }

}
