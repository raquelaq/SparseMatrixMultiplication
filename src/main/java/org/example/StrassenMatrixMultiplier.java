//package org.example;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.ArrayList;
//import java.util.Map;
//import java.util.HashMap;
//
//class StrassenMatrixMultiplier {
//    private SparseMatrixLoader sparseMatrix;
//
//    public StrassenMatrixMultiplier(SparseMatrixLoader sparseMatrix) {
//        this.sparseMatrix = sparseMatrix;
//    }
//
//    // Método para obtener una submatriz dispersa
//    public List<SparseMatrixLoader.MatrixEntry> getRandomSparseSubmatrix(int size) {
//        List<SparseMatrixLoader.MatrixEntry> entries = sparseMatrix.getEntries();
//        List<SparseMatrixLoader.MatrixEntry> submatrixEntries = new ArrayList<>();
//        for (SparseMatrixLoader.MatrixEntry entry : entries) {
//            if (entry.row < size && entry.col < size) {
//                submatrixEntries.add(new SparseMatrixLoader.MatrixEntry(entry.row, entry.col, entry.value));
//            }
//        }
//        return submatrixEntries;
//    }
//
//    // Implementación de la multiplicación de matrices dispersas
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
//
