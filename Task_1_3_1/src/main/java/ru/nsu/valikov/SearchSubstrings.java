package ru.nsu.valikov;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Class for finding substrings.
 */
public class SearchSubstrings {
    private int charCounter = 0;

    SearchSubstrings() {
    }

    /**
     * Read quantity characters from file.
     *
     * @param input    Buffered reader with a file
     * @param quantity how many elements should be read
     * @return false if file ended
     * @throws IOException if file doesn't exist
     */
    private String readExactNumberOfCharacters(BufferedReader input, String fileString,
                                               int quantity) throws IOException {
        char[] charArray = new char[quantity];
        if (input.read(charArray) == -1) {
            return null;
        }
        fileString += (new String(charArray));
        return fileString;
    }

    /**
     * Default algo for finding prefix string.
     *
     * @param zlist                list with maximal prefix, each index its start of prefix's string
     * @param indexI               zlist's index
     * @param lengthOfNeededString length of string, what we are looking for
     * @param fileString           state of reading file
     * @param patternString        what we are looking for
     */
    private void trivialZ(int[] zlist, int indexI, int lengthOfNeededString, String fileString,
                          String patternString) {
        while (zlist[indexI] < lengthOfNeededString && indexI + zlist[indexI] < fileString.length()
               && patternString.charAt(zlist[indexI]) == fileString.charAt(
                indexI + zlist[indexI])) {
            zlist[indexI]++;
        }
    }

    /**
     * Aad index of occurrence to result list.
     *
     * @param zlist                list with maximal prefix, each index its start of prefix's string
     * @param indexI               zlist's index
     * @param lengthOfNeededString length of string, what we are looking for
     * @param result               resulting list
     */
    private void addIndex(int[] zlist, int indexI, int lengthOfNeededString, List<Integer> result) {
        if (zlist[indexI] == lengthOfNeededString) {
            result.add(charCounter);
        }
        charCounter++;
    }

    /**
     * Default z-function algorithm.
     *
     * @param patternString substring
     */
    private String zfunction(String patternString, String fileString, int lengthOfNeededString,
                             List<Integer> result) {
        int[] zlist = new int[fileString.length() / 2];
        int lengthL = 0;
        int lengthR = 0;
        for (int indexI = 0; indexI < fileString.length() / 2; indexI++) {
            if (indexI + zlist[indexI] < lengthOfNeededString) {
                char charS = fileString.charAt(indexI + zlist[indexI]);
                if (charS == '\r' || charS == '\n') {
                    continue;
                }
            }
            zlist[indexI] = Math.max(0, Math.min(lengthR - indexI, zlist[indexI - lengthL]));
            trivialZ(zlist, indexI, lengthOfNeededString, fileString, patternString);
            if (indexI + zlist[indexI] > lengthR) {
                lengthL = indexI;
                lengthR = indexI + zlist[indexI];
            }
            addIndex(zlist, indexI, lengthOfNeededString, result);
        }
        fileString = fileString.substring(fileString.length() / 2);
        return fileString;
    }

    /**
     * Initialization and start of searching.
     *
     * @param inputFile     read from this file
     * @param patternString substring
     */
    public List<Integer> find(String inputFile, String patternString) {
        String inputString;
        if (patternString.length() == 0) {
            return new ArrayList<>();
        }
        List<Integer> result = new ArrayList<>();
        ClassLoader classLoader = getClass().getClassLoader();
        try (InputStream inputStream = classLoader.getResourceAsStream(inputFile);
             InputStreamReader streamReader = new InputStreamReader(
                     Objects.requireNonNull(inputStream), StandardCharsets.UTF_8);
             BufferedReader input = new BufferedReader(streamReader)) {
            int lengthOfNeededString = patternString.length();
            inputString = readExactNumberOfCharacters(input, "", 2 * lengthOfNeededString);
            if (inputString == null) {
                return new ArrayList<>();
            }
            inputString = zfunction(patternString, inputString, lengthOfNeededString, result);
            while ((inputString =
                            readExactNumberOfCharacters(input, inputString, lengthOfNeededString))
                   != null) {
                inputString = zfunction(patternString, inputString, lengthOfNeededString, result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

}
