//package org.example;
//
//import java.util.ArrayList;
//import java.util.List;
//import java.util.Map;
//import java.util.HashMap;
//import java.util.Random;
//
//class SparseMatrixMultiplier {
//    private SparseMatrixLoader sparseMatrix;
//
//    public SparseMatrixMultiplier(SparseMatrixLoader sparseMatrix) {
//        this.sparseMatrix = sparseMatrix;
//    }
//
//    // Método optimizado para obtener una submatriz dispersa
//    public List<SparseMatrixLoader.MatrixEntry> getRandomSparseSubmatrix(int size) {
//        Random random = new Random();
//        int rows = sparseMatrix.getRows();
//        int cols = sparseMatrix.getCols();
//
//        // Asegúrate de que el tamaño de la submatriz no exceda las dimensiones
//        int startRow = random.nextInt(rows - size + 1);
//        int startCol = random.nextInt(cols - size + 1);
//
//        List<SparseMatrixLoader.MatrixEntry> submatrixEntries = new ArrayList<>();
//        for (SparseMatrixLoader.MatrixEntry entry : sparseMatrix.getEntries()) {
//            if (entry.row >= startRow && entry.row < startRow + size &&
//                    entry.col >= startCol && entry.col < startCol + size) {
//                // Ajustar las coordenadas relativas a la submatriz
//                submatrixEntries.add(new SparseMatrixLoader.MatrixEntry(
//                        entry.row - startRow, entry.col - startCol, entry.value
//                ));
//            }
//        }
//
//        return submatrixEntries;
//    }
//
//    // Método optimizado para multiplicar dos submatrices dispersas
//    public List<SparseMatrixLoader.MatrixEntry> multiplySparseSubmatrices(
//            List<SparseMatrixLoader.MatrixEntry> A,
//            List<SparseMatrixLoader.MatrixEntry> B,
//            int size) {
//
//        // Usar un mapa para almacenar el resultado de manera dispersa
//        Map<String, Double> resultMap = new HashMap<>();
//
//        // Multiplicar las matrices dispersas
//        for (SparseMatrixLoader.MatrixEntry entryA : A) {
//            for (SparseMatrixLoader.MatrixEntry entryB : B) {
//                if (entryA.col == entryB.row) {
//                    String key = entryA.row + "," + entryB.col;
//                    resultMap.put(key, resultMap.getOrDefault(key, 0.0) + entryA.value * entryB.value);
//                }
//            }
//        }
//
//        // Convertir el mapa a una lista de entradas dispersas
//        List<SparseMatrixLoader.MatrixEntry> result = new ArrayList<>();
//        for (Map.Entry<String, Double> entry : resultMap.entrySet()) {
//            String[] indices = entry.getKey().split(",");
//            int row = Integer.parseInt(indices[0]);
//            int col = Integer.parseInt(indices[1]);
//            double value = entry.getValue();
//            if (value != 0) {
//                result.add(new SparseMatrixLoader.MatrixEntry(row, col, value));
//            }
//        }
//
//        return result;
//    }
//}
