package ru.nsu.valikov;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SearchSubstrings {
    private String inputString = "";
    private int lengthOfNeededString;
    private final List<Integer> result = new ArrayList<>();
    private int charCounter = 0;

    private boolean readExactNumberOfCharacters(BufferedReader input, int quantity) throws IOException {
        char[] charArray = new char[quantity];
        if (input.read(charArray) == -1) {
            return false;
        }
        inputString += (new String(charArray));
        return true;
    }

    private void zFunction(String patternString) {
        int[] zList = new int[inputString.length() / 2];
        int lengthL = 0;
        int lengthR = 0;
        for (int indexI = 0; indexI < inputString.length() / 2; indexI++) {
            zList[indexI] = Math.max(0, Math.min(lengthR - indexI, zList[indexI - lengthL]));
            while (zList[indexI] < lengthOfNeededString
                    && indexI + zList[indexI] < inputString.length()
                    && patternString.charAt(zList[indexI]) == inputString.charAt(indexI + zList[indexI])) {
                zList[indexI]++;
            }
            if (indexI + zList[indexI] > lengthR) {
                lengthL = indexI;
                lengthR = indexI + zList[indexI];
            }
            if (zList[indexI] == lengthOfNeededString) {
                result.add(charCounter);
            }
            charCounter++;
        }
        inputString = inputString.substring(inputString.length() / 2);
    }

    public SearchSubstrings(File inputFile, String patternString) throws IOException {
        if (patternString.length() == 0) {
            getResult();
            return;
        }
        FileReader file = new FileReader(inputFile);
        final BufferedReader input = new BufferedReader(file);
        lengthOfNeededString = patternString.length();
        readExactNumberOfCharacters(input, 2 * lengthOfNeededString);
        zFunction(patternString);
        while (readExactNumberOfCharacters(input, lengthOfNeededString)) {
            zFunction(patternString);
        }
    }

    public List<Integer> getResult() {
        return result;
    }
}
