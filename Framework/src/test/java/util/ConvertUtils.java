package util;

import me.xdrop.fuzzywuzzy.FuzzySearch;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ConvertUtils {
    public static double convertAllPriceToDouble(String price){
        Pattern pattern=Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
        Matcher matcher = pattern.matcher(price);
        int start = 0;
        double result =0;
        while (matcher.find(start)) {
            String value = price.substring(matcher.start(), matcher.end());
            value =value.replace(',', '.');
            result = Double.parseDouble(value);
            start = matcher.end();
        }
        return result;
    }
    public static double convertFirstPriceToDouble(String price){

        Pattern pattern=Pattern.compile("[-]?[0-9]+(.[0-9]+)?");
        Matcher matcher = pattern.matcher(price);
        int start = 0;
        double result =0;
        while (matcher.find(start)) {
            String value = price.substring(matcher.start(), matcher.end());
            value =value.replace(',', '.');
            result = Double.parseDouble(value);
            break;
        }
        return result;
    }
    public static List<Double> convertWebElementPriceListToListDouble(List<WebElement> webElementsList) {
        List<Double> values = new ArrayList<>();
        for(WebElement e : webElementsList){
            values.add(Double.parseDouble(e.getText().replaceAll("[^0-9]", "")));
        }
        return values;
    }
    public static List<String> convertWebElementPriceListToListString(List<WebElement> webElementsList) {
        List<String> values = new ArrayList<>();
        for(WebElement e : webElementsList){
            values.add(e.getText());
        }
        return values;
    }

}
