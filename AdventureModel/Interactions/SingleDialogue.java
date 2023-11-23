package AdventureModel.Interactions;

public class SingleDialogue extends Interaction {

    private String dialogueText; // String to hold dialogue text to be displayed
    private boolean refreshing; // Should the interaction be refreshed

    /**
     * Constructor
     * @param str
     * Input String to be parsed
     */
    public SingleDialogue(String str){

        // SDialogue: There was once a pale man with dark hair who was very lonely.-true
        String[] dialogue = str.split("-");
        String text = dialogue[0];
        boolean refresh = Boolean.parseBoolean(dialogue[1]);

        this.setDialogueText(text); this.setRefreshing(refresh);
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