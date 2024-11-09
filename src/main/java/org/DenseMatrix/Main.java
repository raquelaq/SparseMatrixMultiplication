package org.DenseMatrix;

import static org.DenseMatrix.matrixMultiplication.*;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;

public class Main {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

        int[] sizes = {10, 50, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1024, 2048, 4096, 8192, 16384, 32768, 65536};
        for (int size : sizes) {
            ROWS = size;
            COLS = size;
            int[][] A = new int[ROWS][COLS];
            int[][] B = new int[ROWS][COLS];

            generateMatrix(A, 1, 9);
            generateMatrix(B, 1, 9);

            // Multiplicación de matrices básica
            System.out.printf("\n\nBasic Matrix Multiplication - Matrix size: %dx%d\n", size, size);
            runtime.gc();
            long memoryBeforeBasic = runtime.totalMemory() - runtime.freeMemory();
            long startTimeBasic = System.currentTimeMillis();

            int[][] C_basic = matrixMultiplication(A, B);

            long endTimeBasic = System.currentTimeMillis();
            long memoryAfterBasic = runtime.totalMemory() - runtime.freeMemory();
            double executionTimeBasic = endTimeBasic - startTimeBasic;
            double cpuUsageBasic = osBean.getSystemCpuLoad() * 100;

            System.out.printf("Execution time (Basic): %.3f milliseconds\n", executionTimeBasic);
            System.out.printf("Memory used (Basic): %d bytes\n", (memoryAfterBasic - memoryBeforeBasic));
            System.out.printf("CPU Usage (Basic): %.3f%%\n", cpuUsageBasic);
            System.out.printf("----------------------------------");

//            try (FileWriter writer = new FileWriter("../benchmark_results.csv", true)) {
//                writer.write(String.format("Java Basic,%dx%d,%.3f,%.2f,%.3f\n", size, size, executionTimeBasic,
//                        (double) (memoryAfterBasic - memoryBeforeBasic) / (1024 * 1024), cpuUsageBasic));
//            } catch (IOException e) {
//                System.err.println("Error writing to CSV file: " + e.getMessage());
//            }

            // Multiplicación de matrices con el algoritmo de Strassen
            System.out.printf("\nStrassen Matrix Multiplication - Matrix size: %dx%d\n", size, size);
            runtime.gc();
            long memoryBeforeStrassen = runtime.totalMemory() - runtime.freeMemory();
            long startTimeStrassen = System.currentTimeMillis();

            int[][] C_strassen = StrassenMatrixMultiplication.strassenMultiply(A, B);

            long endTimeStrassen = System.currentTimeMillis();
            long memoryAfterStrassen = runtime.totalMemory() - runtime.freeMemory();
            double executionTimeStrassen = endTimeStrassen - startTimeStrassen;
            double cpuUsageStrassen = osBean.getSystemCpuLoad() * 100;

            System.out.printf("Execution time (Strassen): %.3f milliseconds\n", executionTimeStrassen);
            System.out.printf("Memory used (Strassen): %d bytes\n", (memoryAfterStrassen - memoryBeforeStrassen));
            System.out.printf("CPU Usage (Strassen): %.3f%%\n", cpuUsageStrassen);
            System.out.printf("----------------------------------");

//            try (FileWriter writer = new FileWriter("../benchmark_results.csv", true)) {
//                writer.write(String.format("Java Strassen,%dx%d,%.3f,%.2f,%.3f\n", size, size, executionTimeStrassen,
//                        (double) (memoryAfterStrassen - memoryBeforeStrassen) / (1024 * 1024), cpuUsageStrassen));
//            } catch (IOException e) {
//                System.err.println("Error writing to CSV file: " + e.getMessage());
//            }

            System.out.printf("\nUnrolled Matrix Multiplication - Matrix size: %dx%d\n", size, size);
            runtime.gc();
            long memoryBeforeUnrolled = runtime.totalMemory() - runtime.freeMemory();
            long startTimeUnrolled = System.currentTimeMillis();

            int[][] C_unrolled = UnrolledMatrixMultiplication.unrolledMatrixMultiplication(A, B);

            long endTimeUnrolled = System.currentTimeMillis();
            long memoryAfterUnrolled = runtime.totalMemory() - runtime.freeMemory();
            double executionTimeUnrolled = endTimeUnrolled - startTimeUnrolled;
            double cpuUsageUnrolled = osBean.getSystemCpuLoad() * 100;

            System.out.printf("Execution time (Unrolled): %.3f milliseconds\n", executionTimeUnrolled);
            System.out.printf("Memory used (Unrolled): %d bytes\n", (memoryAfterUnrolled - memoryBeforeUnrolled));
            System.out.printf("CPU Usage (Unrolled): %.3f%%\n", cpuUsageUnrolled);
            System.out.printf("----------------------------------");

            // Multiplicación de matrices con Optimización de Caché
            System.out.printf("\nCache-Optimized Matrix Multiplication - Matrix size: %dx%d\n", size, size);
            runtime.gc();
            long memoryBeforeCacheOpt = runtime.totalMemory() - runtime.freeMemory();
            long startTimeCacheOpt = System.currentTimeMillis();

            int[][] C_cacheOptimized = CacheOptimizedMatrixMultiplication.cacheOptimizedMultiply(A, B);

            long endTimeCacheOpt = System.currentTimeMillis();
            long memoryAfterCacheOpt = runtime.totalMemory() - runtime.freeMemory();
            double executionTimeCacheOpt = endTimeCacheOpt - startTimeCacheOpt;
            double cpuUsageCacheOpt = osBean.getSystemCpuLoad() * 100;

            System.out.printf("Execution time (Cache-Optimized): %.3f milliseconds\n", executionTimeCacheOpt);
            System.out.printf("Memory used (Cache-Optimized): %d bytes\n", (memoryAfterCacheOpt - memoryBeforeCacheOpt));
            System.out.printf("CPU Usage (Cache-Optimized): %.3f%%\n", cpuUsageCacheOpt);
            System.out.printf("----------------------------------");
        }
    }

    private static void generateMatrix(int[][] matrix, int min, int max) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (int) (Math.random() * (max - min + 1) + min);
            }
        }
    }
}
