package org.DenseMatrix;

public class CacheOptimizedMatrixMultiplication {

    private static final int BLOCK_SIZE = 32; // Tamaño del bloque, ajustable según el sistema y tamaño de la caché

    public static int[][] cacheOptimizedMultiply(int[][] A, int[][] B) {
        int n = A.length;
        int[][] C = new int[n][n];

        // Multiplicación de matrices con optimización de caché
        for (int ii = 0; ii < n; ii += BLOCK_SIZE) {
            for (int jj = 0; jj < n; jj += BLOCK_SIZE) {
                for (int kk = 0; kk < n; kk += BLOCK_SIZE) {
                    // Multiplicación en bloques
                    for (int i = ii; i < Math.min(ii + BLOCK_SIZE, n); i++) {
                        for (int j = jj; j < Math.min(jj + BLOCK_SIZE, n); j++) {
                            int sum = C[i][j];  // Usar el valor actual de C[i][j]
                            for (int k = kk; k < Math.min(kk + BLOCK_SIZE, n); k++) {
                                sum += A[i][k] * B[k][j];
                            }
                            C[i][j] = sum;
                        }
                    }
                }
            }
        }

        return C;
    }
}
