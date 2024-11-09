package SparseMatrix;

import java.lang.management.ManagementFactory;
import com.sun.management.OperatingSystemMXBean;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        Runtime runtime = Runtime.getRuntime();
        OperatingSystemMXBean osBean = ManagementFactory.getPlatformMXBean(OperatingSystemMXBean.class);

        int[] sizes = {10, 50, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1024, 2048, 4096, 8192, 16384, 32768, 65536}; // Tamaños de matriz
        int[] sparsityLevels = {50, 80, 90}; // Niveles de dispersión en porcentaje

        for (int size : sizes) {
            for (int sparsityLevel : sparsityLevels) {
                int[][] A = SparseMatrixGenerator.generateSparseMatrix(size, sparsityLevel);
                int[][] B = SparseMatrixGenerator.generateSparseMatrix(size, sparsityLevel);

                System.out.printf("\nSparse Matrix Multiplication - Matrix size: %dx%d, Sparsity Level: %d%%\n", size, size, sparsityLevel);
                runtime.gc();
                long memoryBeforeSparse = runtime.totalMemory() - runtime.freeMemory();
                long startTimeSparse = System.currentTimeMillis();

                int[][] C_sparse = SparseMatrixMultiplication.multiplySparseMatrices(A, B);

                long endTimeSparse = System.currentTimeMillis();
                long memoryAfterSparse = runtime.totalMemory() - runtime.freeMemory();
                double executionTimeSparse = endTimeSparse - startTimeSparse;
                double cpuUsageSparse = osBean.getSystemCpuLoad() * 100;

                System.out.printf("Execution time (Sparse): %.3f milliseconds\n", executionTimeSparse);
                System.out.printf("Memory used (Sparse): %d bytes\n", (memoryAfterSparse - memoryBeforeSparse));
                System.out.printf("CPU Usage (Sparse): %.3f%%\n", cpuUsageSparse);
                System.out.printf("----------------------------------");

                try (FileWriter writer = new FileWriter("../benchmark_results.csv", true)) {
                    writer.write(String.format("Java Sparse,%dx%d,%d%%,%.3f,%.2f,%.3f\n", size, size, sparsityLevel, executionTimeSparse,
                            (double) (memoryAfterSparse - memoryBeforeSparse) / (1024 * 1024), cpuUsageSparse));
                } catch (IOException e) {
                    System.err.println("Error writing to CSV file: " + e.getMessage());
                }
            }
        }
    }
}
