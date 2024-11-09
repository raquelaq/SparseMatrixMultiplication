package org.DenseMatrix;

public class UnrolledMatrixMultiplication {

    public static int[][] unrolledMatrixMultiplication(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];

        // Multiplicación de matrices con desenrollado de bucles en el bucle interno
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int sum = 0;
                int k;

                // Procesar el bucle en bloques de 4 elementos para el desenrollado
                for (k = 0; k <= n - 4; k += 4) {
                    sum += A[i][k] * B[k][j]
                            + A[i][k + 1] * B[k + 1][j]
                            + A[i][k + 2] * B[k + 2][j]
                            + A[i][k + 3] * B[k + 3][j];
                }

                // Procesar los elementos restantes si el tamaño de la matriz no es múltiplo de 4
                for (; k < n; k++) {
                    sum += A[i][k] * B[k][j];
                }

                C[i][j] = sum;
            }
        }

        return C;
    }
}
