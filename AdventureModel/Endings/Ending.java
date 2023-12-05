package AdventureModel.Endings;

import AdventureModel.Movement.ForcedQueue;

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

    /**
     * This method returns the filename of the picture associated with the Ending.
     * @return a filename String
     */
    String getMessage();

    /**
     * This method returns a description of the Ending.
     * @return a description String
     */
    String getPicture();

    /**
     * This method returns the Ending's respective queue.
     * @return Ending's queue attribute
     */
    ForcedQueue getQueue();
}
