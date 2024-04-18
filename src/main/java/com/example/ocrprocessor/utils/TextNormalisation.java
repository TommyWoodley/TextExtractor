package com.example.ocrprocessor.utils;

import lombok.experimental.UtilityClass;

import java.util.HashMap;
import java.util.Map;

@UtilityClass
public class TextNormalisation {
    Map<Character, Character> REPLACEMENTS = Map.of(
            '\r', '\n',
            '\u00A0', ' '
    );

    public String normalise(String text) {

        StringBuilder output = new StringBuilder();
        for (char c : text.toCharArray()) {
            if (REPLACEMENTS.containsKey(c)) {
                output.append(REPLACEMENTS.get(c));
            } else {
                output.append(c);
            }
        }

        return output.toString();
    }
}
