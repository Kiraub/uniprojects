/**
 * Field class for cell objects of a Matrix Object
 */
public class Field {

    /** State of the field */
    private FieldState fState;

    /**
     * Field constructor with an explicit initial state given
     * @param state the initial state of the field
     * @return field object
     */
    public Field(FieldState state) {
        this.fState = state;

        return;
    }

    public FieldState getState() {
        return this.fState;
    }
    public void setState(FieldState state) {
        this.fState = state;
        return;
    }

}

/**
 * Enumeration of possible states the field can be in
 */
public enum FieldState {
    GRASSLAND, WOODS, FIRE;
}