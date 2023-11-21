package Endings;

public interface Ending {
    /**
     * This method executes the consequences associated with the specific ending.
     */
    void executeEnding();

    /**
     * This method returns a string representation of which kind of ending it is.
     * @return String representation
     */
    String getID();
}
