# CompIntel_2024_2025
Computational Intelligence (ΜΥΕ035) team project for year 2024 - 2025
---

## Table of Contents
- [Project Overview](#project-overview)
- [Exercises](#exercises)
- [Directory Structure](#directory-structure)
- [Compilation and Execution](#compilation-and-execution)

---

## Project Overview

The project comprises two main tasks:
1. **Classification using Multi-Layer Perceptrons (MLPs)**:
   - Implementation of two MLP-based classifiers:
     - **PT2**: MLP with two hidden layers.
     - **PT3**: MLP with three hidden layers.
   - Analyze generalization performance under various configurations.
   
2. **Clustering using K-Means Algorithm**:
   - Implementation of a clustering program with a user-defined number of clusters (\( M \)).
   - Execution and analysis for different cluster counts (\( M = 4, 6, 8, 10, 12 \)).

---

## Exercises

### **Exercise 1: Classification**
- **Dataset**:
  - Synthetic dataset for a four-class classification problem.
  - Training and testing sets with 4000 points each.
- **Tasks**:
  - Implement PT2 and PT3 with configurable parameters:
    - Hidden layer neurons (\( H_1, H_2, H_3 \)).
    - Activation functions (`tanh` and `sigmoid`).
  - Train and evaluate models using gradient descent with mini-batches.
  - Compare and analyze performance.

### **Exercise 2: Clustering**
- **Dataset**:
  - Synthetic dataset with eight clusters and 1000 points.
- **Tasks**:
  - Implement K-Means with random initialization.
  - Perform multiple runs and identify the solution with minimal error.
  - Analyze how clustering error varies with the number of clusters.
  - Visualize cluster assignments and centroids.

---

## Directory Structure
The project has 4 folders:

1. **data/**: Initializes the required datasets:
   - **SDT**: Dataset for the classification problem.
   - **SDO**: Dataset for the clustering problem.

2. **ex1/**: Contains the implementation for Exercise 1 (classification using Multi-Layer Perceptrons).

3. **ex2/**: Contains the implementation for Exercise 2 (clustering using the K-Means algorithm).

4. **main/**: Contains the main method (as implemented in Java). The execution of the program starts here.


