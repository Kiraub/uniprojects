package com.gruppe3;

/**
 * Matrix class for matrix objects of experiments
 */
public class Matrix {

    private static boolean VERBOSE = false;

    private static String BORDERSYMBOL = "|";
    private static String CORNERSYMBOL = "#";

    /** Dimension of the matrix */
    private int dim;
    /** Cells of the matrix */
    private Field[][] cells;

    /**
     * Matrix constructor with explicit dimension given and no configuration
     * @param n The dimension of the matrix
     * @return matrix object with non initialized cells
     */
    public Matrix(int n) {
        this.dim = n;
        this.cells = new Field[this.dim][this.dim];
    }

    public int getDim() {
        return this.dim;
    }

    public void setCell(int row, int column, FieldState state) {
        if( row > 0 && row < this.dim && column > 0 && column < this.dim && state != null) {
            try {
                this.cells[row][column].setState(state);
            } catch (Exception e) {
                // Should not happen
                System.out.println("Trying to set cell failed");
                System.out.println(Integer.toString(row) + "; " + Integer.toString(column));
                System.exit(1);
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
                    // if applying conf fails (re-)init cell
                    this.cells[i][j] = new Field();
                    changed = this.cells[i][j].setState(conf[i][j]) || changed;
                }
            }
        }
        return changed;
    }

    /**
     * Divide the cells in left half and right half with given field states
     * @param left The state of the left half
     * @param right The state of the right half
     */
    public void applyConfig(FieldState left, FieldState right) {
        for(int i=0; i<this.dim; i+=1) {
            for(int j=0; j<this.dim; j+=1) {
                this.cells[i][j] = j <= (this.dim/2) ? new Field(left) : new Field(right);
            }
        }
    }

    /**
     * Prints the matrix on console with padding of one line space on top and bottom
     */
    public void printMatrix() {
        System.out.println();
        System.out.print(CORNERSYMBOL);
        for(int k=1; k<=this.dim;k+=1) {
            System.out.print(Integer.toString(k%10));
        }
        System.out.println(CORNERSYMBOL);
        for(int i=0; i<this.dim; i+=1) {
            System.out.print(BORDERSYMBOL);
            for(int j=0; j<this.dim; j+=1) {
                System.out.print(this.cells[i][j].getSymbol());
            }
            System.out.println(BORDERSYMBOL);
        }
        System.out.print(CORNERSYMBOL);
        for(int k=1; k<=this.dim;k+=1) {
            System.out.print(Integer.toString(k%10));
        }
        System.out.println(CORNERSYMBOL);
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
                if(VERBOSE) System.out.println("Keine Veraenderung nach Schritt " + Integer.toString(step) + ".\n");
                return step+1;
            }
            if(printSteps) {
                System.out.println("Schritt " + Integer.toString(step+1) + ". :");
                this.printMatrix();
            }
        }
        return nSteps;
    }

    /**
     * Counts the number of fire and forest cells around a given cell
     * @param row Row of the given cell
     * @param column Column of the given cell
     * @return Array with [0] # of fire and [1] # of forest cells
     */
    public int[] countNbs(int row, int column) {
        // neighbours [fireN, forestN], grasslands are ignored as they are unimportant for the ruleset given
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
                // skip some checks if cell is in column 0 or N; also do not check the cell itself
                if( (dx == 0 && dy == 0) || (column == 0 && dx == -1) || (column == this.dim-1 && dx == 1) ) {
                    continue;
                }
                // check the cell at coords [row+dy][column+dx]
                FieldState neighbourState = this.cells[row+dy][column+dx].getState();
                if(neighbourState == FieldState.FIRE) {
                    nbs[0] += 1;
                } else if(neighbourState == FieldState.FOREST) {
                    nbs[1] += 1;
                }
            }
        }
        return nbs;
    }
}