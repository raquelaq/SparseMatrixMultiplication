package SparseMatrix;

import SparseMatrix.SparseMatrixGenerator;

import java.util.List;

public class SparseMatrixMultiplication {

    public static int[][] multiplySparseMatrices(int[][] A, int[][] B) {
        int size = A.length;
        int[][] C = new int[size][size];

        List<int[]> nonZeroA = SparseMatrixGenerator.getNonZeroElements(A);
        List<int[]> nonZeroB = SparseMatrixGenerator.getNonZeroElements(B);

        for (int[] elementA : nonZeroA) {
            int i = elementA[0];
            int k = elementA[1];
            int valueA = elementA[2];

            for (int[] elementB : nonZeroB) {
                if (elementB[0] == k) {
                    int j = elementB[1];
                    int valueB = elementB[2];
                    C[i][j] += valueA * valueB;
                }
            }
        }

        return C;
    }
}
