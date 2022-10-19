package valikov.grlib;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Matrix {
    private List<List<Integer>> listOfLists = new ArrayList<>();

    Matrix(int rowCount, int columnCount) {
        for (int indexRow = 0; indexRow < rowCount; indexRow++) {
            listOfLists.add(new ArrayList<>());
            for (int indexColumn = 0; indexColumn < columnCount; indexColumn++) {
                listOfLists.get(indexRow).add(0);
            }
        }
    }

    Matrix(List<List<Integer>> listOfLists) {
        this.listOfLists = listOfLists;
    }

    public void init(Scanner scanner, Integer nodeCount, Integer edgeCount) throws IOException {
        for (int indexRow = 0; indexRow < nodeCount; indexRow++) {
            for (int indexColumn = 0; indexColumn < edgeCount; indexColumn++) {
                Integer value = scanner.nextInt();
                set(indexRow, indexColumn, value);
            }
        }
    }

    public Integer get(Integer indexRow, Integer indexColumn) {
        return listOfLists.get(indexRow).get(indexColumn);
    }

    public void set(Integer indexRow, Integer indexColumn, Integer value) {
        listOfLists.get(indexRow).set(indexColumn, value);
    }

    public Integer matrixRowCount() {
        return listOfLists.size();
    }

    public Integer matrixColumnCount() {
        return listOfLists.get(0).size();
    }
}
