package SparseMatrix;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

public class Main {
    private static final int EXECUTION_TIME_THRESHOLD_MS = 5000; // Umbral de 5 segundos

    public static void main(String[] args) {
        executeSparseMultiplication("../results/sparse_results.csv");
    }

    private static void executeSparseMultiplication(String outputPath) {
        Runtime runtime = Runtime.getRuntime();
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

        int[] sizes = {10, 50, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1024, 2048, 4096, 8192, 16384}; // Tamaños de matriz
        int[] sparsityLevels = {50, 80, 90}; // Niveles de dispersión en porcentaje

        File file = new File(outputPath);
        boolean fileExists = file.exists();

        try (FileWriter writer = new FileWriter(file, true)) {
            if (!fileExists) {
                writer.write("MatrixSize,SparsityLevel,ExecutionTime,MemoryUsage,CPUUsage\n");
            }

            for (int sparsityLevel : sparsityLevels) {
                for (int size : sizes) {
                    System.out.printf("\nSparse Matrix Multiplication - Matrix size: %dx%d, Sparsity Level: %d%%\n", size, size, sparsityLevel);

                    int[][] A = SparseMatrixGenerator.generateSparseMatrix(size, sparsityLevel);
                    int[][] B = SparseMatrixGenerator.generateSparseMatrix(size, sparsityLevel);

                    runtime.gc();
                    long memoryBefore = runtime.totalMemory() - runtime.freeMemory();
                    long startTime = System.currentTimeMillis();

                    int[][] C = SparseMatrixMultiplication.multiplySparseMatrices(A, B);

                    long endTime = System.currentTimeMillis();
                    long memoryAfter = runtime.totalMemory() - runtime.freeMemory();
                    double executionTime = endTime - startTime;
                    double cpuUsage = osBean.getSystemCpuLoad() * 100;
                    double memoryUsed = (double) (memoryAfter - memoryBefore) / (1024 * 1024);

                    System.out.printf("Execution time (Sparse): %.3f milliseconds\n", executionTime);
                    System.out.printf("Memory used (Sparse): %.2f MB\n", memoryUsed);
                    System.out.printf("CPU Usage (Sparse): %.3f%%\n", cpuUsage);
                    System.out.printf("----------------------------------\n");

                    writer.write(String.format("%d,%d,%.3f,%.2f,%.3f\n", size, sparsityLevel, executionTime, memoryUsed, cpuUsage));

                    if (executionTime > EXECUTION_TIME_THRESHOLD_MS) {
                        System.out.printf("Execution time exceeded threshold at matrix size %dx%d with sparsity level %d%%. Skipping further sizes.\n", size, size, sparsityLevel);
                        break;
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error writing to CSV file: " + e.getMessage());
        }
    }
}
