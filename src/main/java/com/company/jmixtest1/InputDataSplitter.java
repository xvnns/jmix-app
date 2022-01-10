package com.company.jmixtest1;

import java.util.Arrays;

public class InputDataSplitter {
    public static String[] splitInput(String str) {
        String[] strArr = str.split("[A-Za-z]\\d\\s+\\=\\s+");
        return Arrays.stream(strArr)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
    }

    public static String[] stringInArray(String str) {
        String delim = "[\\Q ,[]\\E]";
        String[] strArr = str.split(delim);
        return Arrays.stream(strArr)
                .filter(s -> !s.isEmpty())
                .toArray(String[]::new);
    }
}
