package org.example;

import java.util.List;
import java.util.ArrayList;

public class MatrixMultiplication {

    public static List<SparseMatrixLoader.MatrixEntry> matrixMultiplicationSparse(
            List<SparseMatrixLoader.MatrixEntry> A,
            List<SparseMatrixLoader.MatrixEntry> B,
            int rowsA, int colsA, int colsB) {

        List<SparseMatrixLoader.MatrixEntry> C = new ArrayList<>();

        List<List<SparseMatrixLoader.MatrixEntry>> columnsB = new ArrayList<>(colsB);
        for (int i = 0; i < colsB; i++) {
            columnsB.add(new ArrayList<>());
        }
        for (SparseMatrixLoader.MatrixEntry entry : B) {
            columnsB.get(entry.getCol()).add(entry);
        }

        for (SparseMatrixLoader.MatrixEntry aEntry : A) {
            int row = aEntry.getRow();
            int colA = aEntry.getCol();
            double aValue = aEntry.getValue();

            for (SparseMatrixLoader.MatrixEntry bEntry : columnsB.get(colA)) {
                int col = bEntry.getCol();
                double bValue = bEntry.getValue();

                boolean found = false;
                for (SparseMatrixLoader.MatrixEntry cEntry : C) {
                    if (cEntry.getRow() == row && cEntry.getCol() == col) {
                        cEntry.setValue(cEntry.getValue() + aValue * bValue);;
                        found = true;
                        break;
                    }
                }

                if (!found) {
                    C.add(new SparseMatrixLoader.MatrixEntry(row, col, aValue * bValue));
                }
            }
        }
        return C;
    }
}
