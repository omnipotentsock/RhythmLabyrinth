package AdventureModel.Interactions;

public class SingleDialogue extends Interaction {

    private String dialogueText; // String to hold dialogue text to be displayed
    private boolean refreshing; // Should the interaction be refreshed

    /**
     * Constructor with refresh value
     * @param text
     * Dialogue text
     *
     * @param refresh
     * Value to hold if refresh value exists
     */
    public SingleDialogue(String text, boolean refresh){
        this.setDialogueText(text);
        this.setRefreshing(refresh);
    }

    @Override
    public void execute() {
        System.out.println(dialogueText); // TODO: Display text on GUI
    }
    @Override
    protected void setDialogueText(String text) {
        this.dialogueText = text;
    }

    @Override
    protected void setRefreshing(boolean refresh) {
        this.refreshing = refresh;
    }
}