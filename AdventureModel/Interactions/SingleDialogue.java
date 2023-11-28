package AdventureModel.Interactions;

import views.AdventureGameView;

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

    public void execute(AdventureGameView adventureGameView) {
        System.out.println(dialogueText); // TODO: Display text on GUI
        adventureGameView.updateScene(dialogueText);
    }
    protected void setDialogueText(String text) {
        this.dialogueText = text;
    }
    protected void setRefreshing(boolean refresh) {
        this.refreshing = refresh;
    }
    public boolean getRefreshing(){ return this.refreshing;}
    public String getDialogueText(){return this.dialogueText;}
}