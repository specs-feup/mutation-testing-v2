package org.feup.Mutation_Testing_Backend_Final.Helper;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class OutputParsingHelper {

    public static Float extractTotalTime(String input) {
        int start = input.indexOf(":") + 2;
        int end = input.lastIndexOf("s") - 1;

        String timeString = input.substring(start, end);

        return Float.parseFloat(timeString);
    }

    public static float extractTimeElapsed(String inputString) {
        String timeElapsedStr = inputString.split("Time elapsed: ")[1].split(" ")[0];
        return Float.parseFloat(timeElapsedStr);
    }

    public static Float extractTotalTimeGradle(String totalTimeLine){
        String[] parts = totalTimeLine.split(" ");
        String timeString = parts[parts.length - 1];

        if (timeString.contains("ms")){
            return Float.parseFloat(timeString.replace("ms", ""))/1000;
        }else{
            return Float.parseFloat(timeString.replace("s", ""));
        }
    }

    public static float round(float number) {
        return BigDecimal.valueOf(number).setScale(3, BigDecimal.ROUND_HALF_UP).floatValue();
    }

    public static String getGradleClassName(String className){
        String pattern = "\\.(\\w+)$";

        Pattern regex = Pattern.compile(pattern);
        Matcher matcher = regex.matcher(className);

        if (matcher.find()) {
            String lastName = matcher.group(1);
            return lastName;
        } else {
            return "Error";
        }
    }
}
