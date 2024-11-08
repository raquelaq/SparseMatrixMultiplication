package org.example;

import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            SparseMatrixLoader loaderA = new SparseMatrixLoader();
            SparseMatrixLoader loaderB = new SparseMatrixLoader();

            loaderA.loadMatrix("./matrices/delaunay_n12.mtx");
            loaderB.loadMatrix("./matrices/delaunay_n12.mtx");

            List<SparseMatrixLoader.MatrixEntry> matrixA = loaderA.getEntries();
            List<SparseMatrixLoader.MatrixEntry> matrixB = loaderB.getEntries();

            int rowsA = loaderA.getRows();
            int colsA = loaderA.getCols();
            int colsB = loaderB.getCols();

            List<SparseMatrixLoader.MatrixEntry> result = MatrixMultiplication.matrixMultiplicationSparse(
                    matrixA, matrixB, rowsA, colsA, colsB);

            System.out.println("Resultado de la multiplicación de matrices dispersas:");
            for (SparseMatrixLoader.MatrixEntry entry : result) {
                System.out.printf("Fila: %d, Columna: %d, Valor: %f\n", entry.getRow(), entry.getCol(), entry.getValue());
            }

        } catch (IOException e) {
            System.err.println("Error al cargar las matrices: " + e.getMessage());
        }
    }
}
