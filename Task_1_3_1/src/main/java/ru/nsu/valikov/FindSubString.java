package ru.nsu.valikov;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class FindSubString {
    Scanner input;
    String neededString;

    public FindSubString(File inputFile, String inputString) throws FileNotFoundException {
        input = new Scanner(inputFile);
        input.useDelimiter("");
        neededString = inputString;
        zFunction();
    }

    private void zFunction() {
        while (input.hasNext()) {
            System.out.println(input.next());
        }
    }
}
