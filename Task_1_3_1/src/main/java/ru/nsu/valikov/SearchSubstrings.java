package ru.nsu.valikov;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * Class for finding substrings.
 */
public class SearchSubstrings {
    /**
     * String where stores all data from input.
     */
    private String inputString = "";
    private int lengthOfNeededString;
    /**
     * List with indexes of finding substrings.
     */
    private final List<Integer> result = new ArrayList<>();
    /**
     * Variable for result array.
     */
    private int charCounter = 0;

    /**
     * Read quantity characters from file.
     *
     * @param input    Buffered reader with a file
     * @param quantity how many elements should be read
     * @return false if file ended
     * @throws IOException if file doesn't exist
     */
    private boolean readExactNumberOfCharacters(BufferedReader input, int quantity)
            throws IOException {
        char[] charArray = new char[quantity];
        if (input.read(charArray) == -1) {
            return false;
        }
        inputString += (new String(charArray));
        return true;
    }

    /**
     * Default z-function algorithm.
     *
     * @param patternString substring
     */
    private void zfunction(String patternString) {
        int[] zlist = new int[inputString.length() / 2];
        int lengthL = 0;
        int lengthR = 0;
        for (int indexI = 0; indexI < inputString.length() / 2; indexI++) {
            zlist[indexI] = Math.max(0, Math.min(lengthR - indexI, zlist[indexI - lengthL]));
            while (zlist[indexI] < lengthOfNeededString
                    && indexI + zlist[indexI] < inputString.length()
                    && patternString.charAt(zlist[indexI])
                    == inputString.charAt(indexI + zlist[indexI])) {
                zlist[indexI]++;
            }
            if (indexI + zlist[indexI] > lengthR) {
                lengthL = indexI;
                lengthR = indexI + zlist[indexI];
            }
            if (zlist[indexI] == lengthOfNeededString) {
                result.add(charCounter);
            }
            charCounter++;
        }
        inputString = inputString.substring(inputString.length() / 2);
    }

    public List<Integer> getResult() {
        return result;
    }

    /**
     * Initialization and start of searching.
     *
     * @param inputFile     read from this file
     * @param patternString substring
     * @throws IOException if file doesn't exist
     */
    public SearchSubstrings(File inputFile, String patternString) throws IOException {
        if (patternString.length() == 0) {
            getResult();
            return;
        }
        final BufferedReader input = new BufferedReader(
                new InputStreamReader(new FileInputStream(inputFile), StandardCharsets.UTF_8));
        lengthOfNeededString = patternString.length();
        readExactNumberOfCharacters(input, 2 * lengthOfNeededString);
        zfunction(patternString);
        while (readExactNumberOfCharacters(input, lengthOfNeededString)) {
            zfunction(patternString);
        }
    }

}
