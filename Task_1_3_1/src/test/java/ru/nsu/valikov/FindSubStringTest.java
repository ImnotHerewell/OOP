package ru.nsu.valikov;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import org.junit.jupiter.api.Test;

class FindSubStringTest {

    @Test
    void zFunctionTest() throws FileNotFoundException {
        File file = new File("./resources/12kb.txt");
        Scanner scanner = new Scanner(file);
        FindSubString test = new FindSubString(file, "Podrick");

    }

}