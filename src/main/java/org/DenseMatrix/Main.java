package org.DenseMatrix;

import static org.DenseMatrix.matrixMultiplication.*;
import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Main {
    private static final int EXECUTION_TIME_THRESHOLD_MS = 5000;

    public static void main(String[] args) {
        executeAlgorithm("Java Basic", Main::basicMultiplication, "../basic_results.csv");
        executeAlgorithm("Java Strassen", StrassenMatrixMultiplication::strassenMultiply, "../strassen_results.csv");
        executeAlgorithm("Java Unrolled", UnrolledMatrixMultiplication::unrolledMatrixMultiplication, "../unrolled_results.csv");
        executeAlgorithm("Java Cache", CacheOptimizedMatrixMultiplication::cacheOptimizedMultiply, "../cache_results.csv");
    }

    private static void executeAlgorithm(String algorithmName, MatrixMultiplicationAlgorithm algorithm, String outputPath) {
        Runtime runtime = Runtime.getRuntime();
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

        int[] sizes = {10, 50, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1024, 2048, 4096, 8192, 16384};

        File file = new File(outputPath);
        boolean fileExists = file.exists();

        try (FileWriter writer = new FileWriter(file, true)) {
            if (!fileExists) {
                writer.write("MatrixSize,ExecutionTime,MemoryUsage,CPUUsage\n");
            }

            for (int size : sizes) {
                System.out.printf("\n%s - Matrix size: %dx%d\n", algorithmName, size, size);
                int[][] A = new int[size][size];
                int[][] B = new int[size][size];
                generateMatrix(A, 1, 9);
                generateMatrix(B, 1, 9);

                runtime.gc();
                long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
                long startTime = System.currentTimeMillis();

                int[][] C = algorithm.multiply(A, B);

                long endTime = System.currentTimeMillis();
                long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
                double executionTime = endTime - startTime;
                double cpuUsage = osBean.getSystemCpuLoad() * 100;
                double memoryUsed = (double) (memoryAfter - memoryBefore) / (1024 * 1024);

                System.out.printf("Execution time (%s): %.3f milliseconds\n", algorithmName, executionTime);
                System.out.printf("Memory used (%s): %.2f MB\n", algorithmName, memoryUsed);
                System.out.printf("CPU Usage (%s): %.3f%%\n", algorithmName, cpuUsage);
                System.out.printf("----------------------------------\n");

                writer.write(String.format("%d,%.3f,%.2f,%.3f\n", size, executionTime, memoryUsed, cpuUsage));

                if (executionTime > EXECUTION_TIME_THRESHOLD_MS) {
                    System.out.printf("Execution time exceeded threshold for %s at matrix size %dx%d. Skipping further sizes.\n", algorithmName, size, size);
                    break;
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }

    private static int[][] basicMultiplication(int[][] A, int[][] B) {
        return matrixMultiplication(A, B);
    }

    private static void generateMatrix(int[][] matrix, int min, int max) {
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                matrix[i][j] = (int) (Math.random() * (max - min + 1) + min);
            }
        }
    }

    @FunctionalInterface
    interface MatrixMultiplicationAlgorithm {
        int[][] multiply(int[][] A, int[][] B);
    }
}
