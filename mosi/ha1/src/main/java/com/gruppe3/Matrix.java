package com.gruppe3;

/**
 * Matrix class for matrix objects of experiments
 */
public class Matrix {

    /** Dimension of the matrix */
    private int dim;
    /** Cells of the matrix */
    private Field[][] cells;

    /**
     * Matrix constructor with explicit dimension given
     * @param n The dimension of the matrix
     * @return matrix object with initialized cell objects
     */
    public Matrix(int n) {
        this.dim = n;
        this.cells = new Field[n][n];
        // for-loop initialization of the matrix with field objects
        for(int i=0; i<this.dim; i+=1) {
            for(int j=0; j<this.dim; j+=1) {
                this.cells[i][j] = new Field();
            }
        }
        return;
    }


    /**
     * Prints the matrix on console
     */
    public void printMatrix() {
        for(int i=0; i<this.dim; i+=1) {
            System.out.print("|");
            for(int j=0; j<this.dim; j+=1) {
                System.out.print(this.cells[i][j].getSymbol());
            }
            System.out.println("|");
        }
    }
}