package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class SparseMatrixLoader {

    public static class MatrixEntry {
        private final int row;
        private final int col;
        private double value;

        public MatrixEntry(int row, int col, double value) {
            this.row = row;
            this.col = col;
            this.value = value;
        }

        public int getRow() {
            return row;
        }

        public int getCol() {
            return col;
        }

        public double getValue() {
            return value;
        }

        public void setValue(double value) {
            this.value = value;
        }
    }

    private final List<MatrixEntry> entries = new ArrayList<>();
    private int rows;
    private int cols;

    public void loadMatrix(String filePath) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            boolean headerSkipped = false;

            while ((line = reader.readLine()) != null) {
                // Ignora comentarios y encabezado (comienza con "%")
                if (line.startsWith("%")) {
                    continue;
                }

                // La primera línea sin comentario contiene el tamaño de la matriz
                if (!headerSkipped) {
                    String[] dimensions = line.trim().split("\\s+");
                    try {
                        rows = Integer.parseInt(dimensions[0]);
                        cols = Integer.parseInt(dimensions[1]);
                        headerSkipped = true;
                    } catch (NumberFormatException e) {
                        throw new IOException("Formato incorrecto en el tamaño de la matriz", e);
                    }
                } else {
                    // Lee cada línea de la matriz
                    String[] parts = line.trim().split("\\s+");

                    // Si solo hay dos partes, es un archivo de tipo "pattern"
                    if (parts.length == 2) {
                        int row = Integer.parseInt(parts[0]) - 1; // Índice base 0
                        int col = Integer.parseInt(parts[1]) - 1;
                        entries.add(new MatrixEntry(row, col, 1.0)); // Valor asumido 1.0
                    } else if (parts.length == 3) {
                        int row = Integer.parseInt(parts[0]) - 1;
                        int col = Integer.parseInt(parts[1]) - 1;
                        double value = Double.parseDouble(parts[2]);
                        entries.add(new MatrixEntry(row, col, value));
                    } else {
                        throw new IOException("Formato incorrecto en las entradas de la matriz");
                    }
                }
            }
        }
    }

    public List<MatrixEntry> getEntries() {
        return new ArrayList<>(entries);
    }

    public int getRows() {
        return rows;
    }

    public int getCols() {
        return cols;
    }
}
