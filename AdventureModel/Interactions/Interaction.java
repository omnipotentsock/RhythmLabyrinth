package AdventureModel.Interactions;

public abstract class Interaction {


    abstract void execute();
    protected abstract void setDialogueText(String text);
    protected abstract void setRefreshing(boolean refresh);

}
