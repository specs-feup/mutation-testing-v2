package org.feup.Mutation_Testing_Backend_Final.Helper;

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
}
