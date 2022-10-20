package valikov.grlib.representation;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Matrix class for graph.
 */
public class Matrix {
    private List<List<Integer>> listOfLists = new ArrayList<>();

    /**
     * Construct an empty matrix with rowCount rows and columnCount columns.
     *
     * @param rowCount - quantity of rows in matrix.
     * @param columnCount quantity of columns in matrix.
     */
    public Matrix(int rowCount, int columnCount) {
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

    /**
     * Matrix's initialization.
     *
     * @param scanner   from where we should read data.
     * @param nodeCount node's count in graph.
     * @param edgeCount edge's count in graph.
     */
    public void init(Scanner scanner, Integer nodeCount, Integer edgeCount) {
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
