package com.luisjuarez.sistemavu.utils;

/**
 *
 * @author conta
 */
public class StringUtil {
    public static String toCapitalize(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }
        String[] words = str.split("\\s+");
        StringBuilder capitalizedPhrase = new StringBuilder();

        for (String word : words) {
            if (word.length() > 0) {
                capitalizedPhrase.append(Character.toUpperCase(word.charAt(0)));
                capitalizedPhrase.append(word.substring(1).toLowerCase());
            }
            capitalizedPhrase.append(" ");
        }

        return capitalizedPhrase.toString().trim();
    }

}
