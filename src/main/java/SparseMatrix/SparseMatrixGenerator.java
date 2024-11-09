package SparseMatrix;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SparseMatrixGenerator {

    public static int[][] generateSparseMatrix(int size, int sparsityLevel) {
        int[][] matrix = new int[size][size];
        Random random = new Random();

        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                // Decidir si el elemento será cero o no según el nivel de dispersión
                if (random.nextInt(100) < sparsityLevel) {
                    matrix[i][j] = 0; // Nivel de dispersión
                } else {
                    matrix[i][j] = random.nextInt(9) + 1; // Número aleatorio entre 1 y 9
                }
            }
        }

        return matrix;
    }

    public static List<int[]> getNonZeroElements(int[][] matrix) {
        List<int[]> nonZeroElements = new ArrayList<>();
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                if (matrix[i][j] != 0) {
                    nonZeroElements.add(new int[]{i, j, matrix[i][j]});
                }
            }
        }
        return nonZeroElements;
    }
}
