package AdventureModel.Interactions;

import java.io.Serializable;

public abstract class Interaction implements Serializable {

    abstract void execute();
    protected abstract void setDialogueText(String text);
    protected abstract void setRefreshing(boolean refresh);
    public abstract boolean getRefreshing();
    public abstract String getDialogueText(String text);

}
