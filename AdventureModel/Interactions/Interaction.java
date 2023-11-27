package AdventureModel.Interactions;

import views.AdventureGameView;

import java.io.Serializable;

/**
 * Interaction class that is extended by all kinds of interactions that will go inside queue.
 *
 * https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Dialog.html
 */
public abstract class Interaction implements Serializable {


    public abstract void execute(AdventureGameView adventureGameView);
    protected abstract void setDialogueText(String text);
    protected abstract void setRefreshing(boolean refresh);
    public abstract boolean getRefreshing();
    public abstract String getDialogueText();

}
