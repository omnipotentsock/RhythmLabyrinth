package AdventureModel.Interactions;

import AdventureModel.Characters.NPC;

public class NPCDialogue extends Interaction{

    private String dialogueText; // String to hold dialogue text to be displayed
    private boolean refreshing; // Should the interaction be refreshed

    private NPC speaker;

    /**
     * Constructor with refresh value
     * @param str
     * Input string to be parsed
     */
    public NPCDialogue(String str){

        // NPCDialogue: Why was it lonely?-true-WOLF
        String[] dialogue = str.split("-");

        String text = dialogue[0];
        boolean refresh = Boolean.parseBoolean(dialogue[1]);
        NPC speaker = new NPC(); // TODO: Make it find NPC based on NPC token

        this.setDialogueText(text); this.setRefreshing(refresh); this.speaker = speaker;
    }

    public void execute() {
        System.out.println("Name: Jimothy"); // TODO: Update picture to NPC picture
        System.out.println(dialogueText); // TODO: Display text on GUI
    }

    protected void setDialogueText(String text) {
        this.dialogueText = text;
    }
    protected void setRefreshing(boolean refresh) {
        this.refreshing = refresh;
    }
    public boolean getRefreshing(){ return this.refreshing;}
    public String getDialogueText(String text){return this.dialogueText;}
}
