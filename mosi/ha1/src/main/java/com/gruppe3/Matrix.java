package com.gruppe3;

/**
 * Matrix class for matrix objects of experiments
 */
public class Matrix {

    private static String BORDERSYMBOL = "|";

    /** Dimension of the matrix */
    private int dim;
    /** Cells of the matrix */
    private Field[][] cells;

    /**
     * Matrix constructor with explicit dimension given and no configuration
     * @param n The dimension of the matrix
     * @return matrix object with initialized cell objects with state GRASSLAND
     */
    public Matrix(int n) {
        this(n, null);
        return;
    }

    /**
     * Matrix constructor with explicit dimension given and no configuration
     * @param n The dimension of the matrix
     * @param conf The configuration for the field states
     * @return matrix object with initialized cell objects
     */
    public Matrix(int n, FieldState[][] conf) {
        this.dim = n;
        this.cells = new Field[n][n];
        boolean confGiven = conf != null ? true : false;
        // for-loop initialization of the matrix with field objects
        for(int i=0; i<this.dim; i+=1) {
            for(int j=0; j<this.dim; j+=1) {
                try {
                    // if config is given try applying conf
                    if(confGiven) {
                        this.cells[i][j] = new Field(conf[i][j]);
                    } else {
                        this.cells[i][j] = new Field();
                    }
                } catch (Exception e) {
                    // if applying conf fails set cell to default
                    this.cells[i][j] = new Field();
                }
            }
        }
    }

    /**
     * Apply a given config to the matrix' cells overwriting the exisiting one
     * @param conf The new config to be applied
     * @return Whether the cells have changed
     */
    public boolean applyConfig(FieldState[][] conf) {
        boolean changed = false;
        for(int i=0; i<this.dim; i+=1) {
            for(int j=0; j<this.dim; j+=1) {
                try {
                    // try applying conf
                    changed = this.cells[i][j].setState(conf[i][j]) || changed;
                } catch (Exception e) {
                    // if applying conf fails do not change the cell
                }
            }
        }
        return changed;
    }

    /**
     * Prints the matrix on console with padding of one line space on top and bottom
     */
    public void printMatrix() {
        System.out.println();
        for(int i=0; i<this.dim; i+=1) {
            System.out.print(BORDERSYMBOL);
            for(int j=0; j<this.dim; j+=1) {
                System.out.print(this.cells[i][j].getSymbol());
            }
            System.out.println(BORDERSYMBOL);
        }
        System.out.println();
    }

    /**
     * Simulate cells interacting with each other for a number of given steps without printing
     * @param nSteps The number of steps to be simulated
     * @return The number of steps simulated
     */
    public int stepCells(int nSteps) {
        return this.stepCells(nSteps, false);
    }

    /**
     * Simulate cells interacting with each other for a number of given steps
     * @param nSteps The number of steps to be simulated
     * @param printSteps Whether the intermediate steps should be printed
     * @return The number of steps simulated
     */
    public int stepCells(int nSteps, boolean printSteps) {
        FieldState[][] nxtConf = new FieldState[this.dim][this.dim];
        for(int step = 0; step < nSteps; step += 1) {
            for(int i=0; i<this.dim; i+=1) {
                for(int j=0; j<this.dim; j+=1) {
                    // check all next states for the cells
                    nxtConf[i][j] = this.cells[i][j].nextState(this.countNbs(i, j));
                }
            }
            // apply the next configuration of states
            if(!this.applyConfig(nxtConf)) {
                System.out.println("Keine Veraenderung nach Schritt " + Integer.toString(step) + ".");
                return step+1;
            }
            if(printSteps) {
                System.out.println("Schritt " + Integer.toString(step+1) + ". :");
                this.printMatrix();
            }
        }
        return nSteps;
    }

    public int[] countNbs(int row, int column) {
        // neighbours [fireN, woodsN], grasslands are ignored as they are unimportant for the ruleset given
        int[] nbs = {0, 0};
        /*
            check neighbours based on moore-neighborhood:
            NW  N-  NE
            -W  CC  -E
            SW  S-  SE
        */
        for(int dy = -1; dy <= 1; dy += 1) {
            // skip some checks if cell is in row 0 or N
            if( (row == 0 && dy == -1) || (row == this.dim-1 && dy == 1) ) {
                continue;
            }
            for(int dx = -1; dx <= 1; dx += 1) {
                // skip some checks if cell is in column 0 or N
                if( (column == 0 && dx == -1) || (column == this.dim-1 && dx == 1) ) {
                    continue;
                }
                // check the cell at coords [row+dy][column+dx]
                FieldState neighbourState = this.cells[row+dy][column+dx].getState();
                if(neighbourState == FieldState.FIRE) {
                    nbs[0] += 1;
                } else if(neighbourState == FieldState.WOODS) {
                    nbs[1] += 1;
                }
            }
        }
        return nbs;
    }
}