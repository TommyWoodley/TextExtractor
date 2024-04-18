package com.example.ocrprocessor.utils;

import lombok.experimental.UtilityClass;
import java.util.Map;

@UtilityClass
public class TextNormalisation {
    Map<Character, Character> REPLACEMENTS = Map.of(
            '\r', '\n',
            '\u000E', '\n',
            '\u0010', '\n',
            '\u000C', '\n',
            '\u00A0', ' '
    );

    public String normalise(String text) {

        StringBuilder output = new StringBuilder(text);
        for (int i = 0; i < text.length(); i++) {
            if (REPLACEMENTS.containsKey(output.charAt(i))) {
                output.setCharAt(i, REPLACEMENTS.get(output.charAt(i)));
            }
        }

        return output.toString();
    }
}
