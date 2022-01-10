package com.company.jmixtest1;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SubstringSearch {
    public static List<String> search(String[] array1, String[] array2) {
        return Arrays.stream(array1)
                .filter(word -> (Arrays.stream(array2).anyMatch(e -> e.contains(word))))
                .distinct()
                .sorted(String::compareToIgnoreCase)
                .collect(Collectors.toList());
    }
}
