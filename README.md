# Sparse Matrix Multiplication Performance Analysis

## Introduction

Matrix multiplication is a core operation in various scientific and engineering applications, from machine learning to physical system simulations. Given its computational intensity, optimizing matrix multiplication can significantly improve performance in data-intensive applications. This project investigates and compares different optimization techniques for matrix multiplication, focusing on both dense and sparse matrices. Sparse matrices, characterized by a high proportion of zero elements, offer unique opportunities for optimization, as many computations can be skipped, reducing both computation time and memory usage.
Additionally, there is a scientific paper detailing the methodology and experiments conducted throughout this study, providing further insight into the procedures and results obtained. You can find it [here](SparseMatrixPaper.pdf).
## Optimization Techniques

The project implements and benchmarks several optimization techniques:
- **Basic Algorithm**: The standard approach to matrix multiplication, used as a baseline for performance comparisons.
- **Strassen's Algorithm**: A divide-and-conquer approach that reduces the computational complexity from \( O(n^3) \) to approximately \( O(n^{2.81}) \), aiming to improve performance for large matrices.
- **Loop Unrolling**: This technique optimizes the multiplication loop by processing multiple elements at once, reducing the overhead associated with loop control.
- **Cache Optimization**: Improves memory access patterns by blocking matrices into smaller segments, allowing better use of the cache and reducing cache misses.
- **Sparse Matrix Optimization**: Specifically optimized for sparse matrices, this approach leverages the high number of zero elements, skipping unnecessary computations and focusing only on non-zero values.

## Performance Metrics

Each algorithm was tested across different matrix sizes and sparsity levels (50%, 80%, and 90% zero elements). The following metrics were recorded for each test:
- **Execution Time (ms)**: Measures how long each algorithm takes to complete the matrix multiplication.
- **Memory Usage (MB)**: Tracks the memory consumption during execution.
- **CPU Usage (%)**: Records the CPU load, highlighting the efficiency of each approach in handling computational resources.

## Key Findings

1. **Execution Time**: 
   - The basic algorithm is consistent across all tested sizes.
   - Strassen’s algorithm and cache optimization perform well for specific sizes but show variable performance as matrix size increases.
   - Loop unrolling is efficient for small matrices but becomes ineffective for larger ones.

2. **Memory Usage**:
   - The basic algorithm and cache optimization are the most memory-efficient.
   - Strassen and loop unrolling show higher memory usage, especially for medium to large matrix sizes, due to recursive operations and loop overhead.

3. **CPU Usage**:
   - Cache optimization and Strassen's algorithm have higher CPU usage at certain matrix sizes, while the basic algorithm and loop unrolling remain more CPU-efficient across all sizes.

4. **Sparse Matrices**:
   - High sparsity levels (e.g., 90%) lead to significant improvements in all metrics (execution time, memory, and CPU usage), especially for large matrices, by minimizing operations on zero elements.

## Repository Structure

- `src/main/java`: Contains the source code for each algorithm, including classes for each optimization technique.
- `results`: Directory with CSV files containing the performance data for each algorithm, segmented by matrix size and sparsity level.
- `SparseMatrixPaper.pdf`: Documentation detailing the methodology, results, and analysis of this project.
- `pom.xml`: Configuration file for project dependencies and build settings.

## Conclusion

This project highlights the trade-offs of various optimization techniques for matrix multiplication. While the basic algorithm and cache optimization are reliable and memory-efficient, Strassen's algorithm and loop unrolling offer performance improvements only for specific matrix sizes and types. Sparse matrices benefit greatly from high sparsity levels, providing substantial resource savings. These findings offer a framework for selecting appropriate matrix multiplication techniques based on performance requirements in high-performance computing applications.

## How to Use

1. Clone the repository.
2. Use `mvn clean install` to build the project.
3. Run each algorithm class to record performance data.
4. Analyze the generated CSV files to compare results and identify the optimal approach for your application needs.

## Future Work

Potential future improvements include:
- Implementing additional optimization techniques, such as parallelization or GPU acceleration.
- Further exploring sparsity effects on different matrix sizes and patterns.
- Developing adaptive algorithms that dynamically select the best multiplication method based on matrix characteristics.

---

This documentation provides an overview of the structure, methodology, and findings of the matrix multiplication optimization project.
