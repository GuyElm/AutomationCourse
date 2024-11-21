package DataDrivenEx;

import org.testng.annotations.DataProvider;

public class GlobalProvider {
    @DataProvider(name = "urls-data")
    public static Object[][] getDataObject(){
        return new Object[][]{
                {"https://www.google.com","Google"},
                {"https://www.imdb.com","IMDb: Ratings, Reviews, and Where to Watch the Best Movies & TV Shows"},
                {"https://www.bing.com","Bing"},
                {"https://www.instagram.com","Instagram"}
        };
    }

}
