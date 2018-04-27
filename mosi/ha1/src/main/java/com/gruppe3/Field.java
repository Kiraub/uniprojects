package com.gruppe3;

/**
 * Field class for cell objects of a Matrix Object
 */
public class Field {

    /** State of the field */
    private FieldState fState;

    public Field() {
        this(FieldState.GRASSLAND);
        return;
    }

    /**
     * Field constructor with an explicit initial state given
     * @param state the initial state of the field
     * @return field object
     */
    public Field(FieldState state) {
        this.fState = state;
        return;
    }

    // Getter and Setter for fState
    public FieldState getState() {
        return this.fState;
    }
    public void setState(FieldState state) {
        this.fState = state;
        return;
    }

    /**
     * Returns a character based on the current state of the field; FIRE-F, WOODS-W, GRASSLAND-G
     * @return One character string representing the state
     */
    public String getSymbol() {
        switch(this.fState) {
            case FIRE: {
                return "F";
            }
            case WOODS: {
                return "W";
            }
            case GRASSLAND: {
                return "G";
            }
        }
        return "-";
    }

}