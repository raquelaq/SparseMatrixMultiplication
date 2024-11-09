package org.DenseMatrix;

public class StrassenMatrixMultiplication {

    public static int[][] strassenMultiply(int[][] A, int[][] B) {
        int n = A.length;

        // Caso base
        if (n == 1) {
            int[][] C = {{A[0][0] * B[0][0]}};
            return C;
        }

        // Dividir las matrices en submatrices
        int newSize = n / 2;
        int[][] A11 = new int[newSize][newSize];
        int[][] A12 = new int[newSize][newSize];
        int[][] A21 = new int[newSize][newSize];
        int[][] A22 = new int[newSize][newSize];

        int[][] B11 = new int[newSize][newSize];
        int[][] B12 = new int[newSize][newSize];
        int[][] B21 = new int[newSize][newSize];
        int[][] B22 = new int[newSize][newSize];

        splitMatrix(A, A11, 0, 0);
        splitMatrix(A, A12, 0, newSize);
        splitMatrix(A, A21, newSize, 0);
        splitMatrix(A, A22, newSize, newSize);

        splitMatrix(B, B11, 0, 0);
        splitMatrix(B, B12, 0, newSize);
        splitMatrix(B, B21, newSize, 0);
        splitMatrix(B, B22, newSize, newSize);

        // Calcula las matrices de Strassen
        int[][] M1 = strassenMultiply(addMatrices(A11, A22), addMatrices(B11, B22));
        int[][] M2 = strassenMultiply(addMatrices(A21, A22), B11);
        int[][] M3 = strassenMultiply(A11, subtractMatrices(B12, B22));
        int[][] M4 = strassenMultiply(A22, subtractMatrices(B21, B11));
        int[][] M5 = strassenMultiply(addMatrices(A11, A12), B22);
        int[][] M6 = strassenMultiply(subtractMatrices(A21, A11), addMatrices(B11, B12));
        int[][] M7 = strassenMultiply(subtractMatrices(A12, A22), addMatrices(B21, B22));

        // Calcula las submatrices resultantes
        int[][] C11 = addMatrices(subtractMatrices(addMatrices(M1, M4), M5), M7);
        int[][] C12 = addMatrices(M3, M5);
        int[][] C21 = addMatrices(M2, M4);
        int[][] C22 = addMatrices(subtractMatrices(addMatrices(M1, M3), M2), M6);

        // Combina las submatrices en el resultado final
        int[][] C = new int[n][n];
        joinMatrix(C11, C, 0, 0);
        joinMatrix(C12, C, 0, newSize);
        joinMatrix(C21, C, newSize, 0);
        joinMatrix(C22, C, newSize, newSize);

        return C;
    }

    // Método para dividir la matriz en submatrices
    private static void splitMatrix(int[][] source, int[][] destination, int row, int col) {
        for (int i = 0; i < destination.length; i++) {
            for (int j = 0; j < destination.length; j++) {
                destination[i][j] = source[i + row][j + col];
            }
        }
    }

    // Método para unir las submatrices en la matriz resultado
    private static void joinMatrix(int[][] source, int[][] destination, int row, int col) {
        for (int i = 0; i < source.length; i++) {
            for (int j = 0; j < source.length; j++) {
                destination[i + row][j + col] = source[i][j];
            }
        }
    }

    // Método para sumar dos matrices
    private static int[][] addMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] + B[i][j];
            }
        }
        return C;
    }

    // Método para restar dos matrices
    private static int[][] subtractMatrices(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                C[i][j] = A[i][j] - B[i][j];
            }
        }
        return C;
    }
}
