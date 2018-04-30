package com.gruppe3;

/**
 * Field class for cell objects of a Matrix Object
 */
public class Field {

    // default state should a null been given at initialization
    private static FieldState DEFAULTSTATE = FieldState.GRASSLAND;
    private static String GRASSLANDSYMBOL = "G";
    private static String FORESTSYMBOL = "W";
    private static String FIRESYMBOL = "F";
    private static String ERRSYMBOL = "-";

    public static void printSymbolLegend() {
        System.out.print("\nLegende:\n\t" + GRASSLANDSYMBOL + " = Wiese\n\t" + FORESTSYMBOL + " = Wald\n\t" + FIRESYMBOL + " = Feuer\n\n");
    }

    /** State of the field */
    private FieldState fState;

    /**
     * Field constructor with no parameters given
     * @return Field with state GRASSLAND
     */
    public Field() {
        this(null);
        return;
    }

    /**
     * Field constructor with an explicit initial state given
     * @param state the initial state of the field
     * @return field object
     */
    public Field(FieldState state) {
        // avoid null states being set by setting a default
        if(state != null) {
            this.fState = state;
        } else {
            // default
            this.fState = DEFAULTSTATE;
        }
        return;
    }

    // Getter and Setter for fState
    public FieldState getState() {
        return this.fState;
    }
    /**
     * @return Whether the state has changed
     */
    public boolean setState(FieldState state) {
        boolean changed = false;
        // do not allow null states to be set
        if(state != null) {
            if(this.fState != state) {
                changed = true;
            }
            this.fState = state;
        }
        return changed;
    }

    /**
     * Function for convenience of use
     * @see nextState(int,int)
     */
    public FieldState nextState(int[] counts) {
        return this.nextState(counts[0], counts[1]);
    }

    /**
     * Defines a set of rules that decide the next state given the current state and neighbouring cells without applying the next state
     * @param fireCnt The number of neighbouring fire cells
     * @param forestCnt The number of neighbouring forest cells
     * @return Predicted next state
     */
    public FieldState nextState(int fireCnt, int forestCnt) {
        if(this.fState == FieldState.GRASSLAND) {
            /*
                First rule to be checked for GRASSLAND
                If a grassland has at least one fire as neighbour, the next state is fire
                This rule has priority over the second one, meaning if both are true the next state is fire
            */
            boolean grass2fire = fireCnt >= 1 ? true : false;
            /*
                Second rule to be checked for GRASSLAND
                If a grassland has at least two FOREST as neighbours, the next state is FOREST
            */
            boolean grass2forest = forestCnt >= 2 ? true : false;

            // Check the rules
            if(grass2fire) {
                return FieldState.FIRE;
            } else if(grass2forest) {
                return FieldState.FOREST;
            }
        } else if(this.fState == FieldState.FOREST) {
            /*
                Only rule to be checked for FIRE
                If FOREST have at least three fire as neighbours, the next state is fire
            */
            boolean forest2fire = fireCnt >= 3 ? true : false;

            // Check the rule
            if(forest2fire) {
                return FieldState.FIRE;
            }
        }
        // if the current state is fire or no rules apply simply return current state as next state
        return this.fState;
    }

    /**
     * Returns a character based on the current state of the field
     * @return One character string representing the state
     */
    public String getSymbol() {
        switch(this.fState) {
            case FIRE: {
                return FIRESYMBOL;
            }
            case FOREST: {
                return FORESTSYMBOL;
            }
            case GRASSLAND: {
                return GRASSLANDSYMBOL;
            }
        }
        return ERRSYMBOL;
    }

}