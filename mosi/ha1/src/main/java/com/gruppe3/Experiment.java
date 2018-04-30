package com.gruppe3;

import java.util.TreeMap;

public class Experiment {

    private Matrix mx;
    private String message;

    private int[][] stepsUntilHalt;
    private TreeMap<Integer,Integer> histoData;

    /**
     * Experiment constructor for task 1.2.2
     */
    public Experiment(int dim, String message) {
        this.mx = new Matrix(dim);
        this.message = message;
    }

    /**
     * Experiment constructor for task 1.2.1
     * @param dim Dimension of the capsulated matrix
     * @param conf Init-configuration of the capsulated matrix
     * @param message Optional message to print in front of the simulation
     */
    public Experiment(int dim, String message, FieldState[][] conf) {
        this.mx = new Matrix(dim);
        this.mx.applyConfig(conf);
        this.message = message;
    }

    public Experiment(int dim, int grassCount, int forestCount, int fireCount) {
        // generate random matrix with given number of cell counts
    }

    /**
     * Simulates the set number of steps of the capsulated matrix' cells
     * @param printSteps Whether to print intermediate steps of the simulation
     */
    public void runExpStep(int step, boolean printSteps) {
        if(this.message!=null) {
            System.out.println(this.message);
        }
        System.out.println("Start:");
        this.mx.printMatrix();
        this.mx.stepCells(step, printSteps);
        if( ! printSteps) {
            System.out.println("Schritt " + Integer.toString(step) + ". :");
            this.mx.printMatrix();
        }
    }

    /**
     * Simulates the matrix until it does not change anymore
     * @param maxStep Upper limit for steps to avoid infinite loops
     * @return Number of steps it took for the simulation to halt
     */
    public int runExpUntilStop(int maxStep) {
        if(this.message!=null) {
            System.out.println(this.message);
        }
        //System.out.println("Start:");
        //this.mx.printMatrix();
        return this.mx.stepCells(maxStep);
    }

    /**
     * Simulate all starting positions of one fire cell and save number of steps to halt
     */
    public void simulateAllFirePos(FieldState left, FieldState right) {
        int dim = this.mx.getDim();
        int steps;
        this.histoData = new TreeMap<>();
        this.stepsUntilHalt = new int[dim][dim];
        if(this.message!=null) {
            System.out.println(this.message);
            this.message = null;
        }
        for(int i = 0; i < dim; i += 1) {
            for(int j = 0; j < dim; j += 1) {
                this.mx.applyConfig(left, right);
                this.mx.setCell(i, j, FieldState.FIRE);
                steps = this.runExpUntilStop(1000);
                this.stepsUntilHalt[i][j] = steps;
                if(this.histoData.containsKey(steps)) {
                    this.histoData.put(steps, this.histoData.get(steps)+1);
                } else {
                    this.histoData.put(steps, 1);
                }
            }
        }
    }

    /**
     * Print out "histogram" (just a table for now)
     */
    public void printHistogram() {
        int totalSteps = 0;
        int totalRuns = this.mx.getDim();
        double median;
        double sqrdev = 0;
        totalRuns *= totalRuns;
        System.out.println("Schritt#\tVorkommen");
        for (Integer key : this.histoData.navigableKeySet()) {
            System.out.print("\t" + Integer.toString(key) + ":\t" + this.histoData.get(key) + "\n");
            totalSteps += key * this.histoData.get(key);
        }
        median = (double) totalSteps / totalRuns;
        for (Integer key : this.histoData.navigableKeySet()) {
            sqrdev += Math.pow( ((double)key)-median, 2) * this.histoData.get(key);
        }
        sqrdev = Math.sqrt(sqrdev/totalRuns);
        System.out.printf(
            "%nGesamtschrittanzahl:\t\t\t%d"+"%nAnzahl der Durchlaufe:\t\t\t%d"+"%nDurchschnittliche Schrittanzahl:\t%.3f%n"+"Standardabweichung:\t\t\t%.3f%n%n",
            totalSteps, totalRuns, median, sqrdev
        );
    }

}