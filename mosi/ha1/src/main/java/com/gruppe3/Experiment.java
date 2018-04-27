package com.gruppe3;

public class Experiment {

    private Matrix mx;
    private int stepSize;
    private boolean printSteps;
    private String message;

    /**
     * Experiment constructor with empty message
     * @see Experiment(int,FieldState[][],int,boolean,String)
     */
    public Experiment(int dim, FieldState[][] conf, int stepSize, boolean printSteps) {
        this(dim, conf, stepSize, printSteps, null);
    }

    /**
     * Experiment are capsulating matrices and counting simulation steps
     * @param dim Dimension of the capsulated matrix
     * @param conf Init-configuration of the capsulated matrix
     * @param stepRun Number of steps to simulate in one simulation run
     * @param printSteps Whether to print intermediate steps of the simulation
     * @param message Optional message to print in front of the simulation
     */
    public Experiment(int dim, FieldState[][] conf, int stepSize, boolean printSteps, String message) {
        this.mx = new Matrix(dim, conf);
        this.stepSize = stepSize;
        this.printSteps = printSteps;
        this.message = message;
    }

    /**
     * Simulates the set number of steps of the capsulated matrix' cells
     */
    public void runExp() {
        if(this.message!=null) {
            System.out.println(this.message);
        }
        System.out.println("Start:");
        this.mx.printMatrix();
        this.mx.stepCells(this.stepSize, this.printSteps);
        if( ! this.printSteps) {
            System.out.println("Schritt " + Integer.toString(this.stepSize) + ". :");
            this.mx.printMatrix();
        }
    }

}