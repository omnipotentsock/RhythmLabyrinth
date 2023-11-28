package AdventureModel.Interactions;

import AdventureModel.Characters.NPC;
import views.AdventureGameView;

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
        String npcToken = dialogue[2];
        NPC speaker = new NPC(npcToken); // TODO: Make it find NPC based on NPC token

        this.setDialogueText(text); this.setRefreshing(refresh); this.speaker = speaker;
    }

    public void execute(AdventureGameView adventureGameView) {
        String s = "Jimothy:\n" + dialogueText; // TODO: Update picture to NPC picture, Display text on GUI
        adventureGameView.updateScene(s);
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
