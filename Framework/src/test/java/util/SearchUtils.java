package util;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SearchUtils {

    public static int getMatchPercentage(String findName, List<String> results){
        int matchPercent=0;
        for(String s : results){
            matchPercent += FuzzySearch.ratio(findName,s);
        }
        return matchPercent/results.size();
    }

}
