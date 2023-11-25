package AdventureModel.Interactions;

import java.io.Serializable;

/**
 * Interaction class that is extended by all kinds of interactions that will go inside queue.
 *
 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Dialog.html
 */
public abstract class Interaction implements Serializable {


    abstract void execute();
    protected abstract void setDialogueText(String text);
    protected abstract void setRefreshing(boolean refresh);
    public abstract boolean getRefreshing();
    public abstract String getDialogueText(String text);

}
