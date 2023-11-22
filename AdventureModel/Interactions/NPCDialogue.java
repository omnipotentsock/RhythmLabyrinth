package AdventureModel.Interactions;

import AdventureModel.Characters.NPC;

public class NPCDialogue extends Interaction{

    private String dialogueText; // String to hold dialogue text to be displayed
    private boolean refreshing; // Should the interaction be refreshed

    private NPC speaker;

    /**
     * Constructor with refresh value
     * @param text
     * Dialogue text
     *
     * @param refresh
     * Value to hold if refresh value exists
     */
    public NPCDialogue(String text, boolean refresh, NPC npc){
        this.setDialogueText(text);
        this.setRefreshing(refresh);
        this.speaker = npc;
    }

    @Override
    public void execute() {
        System.out.println("Name: Jimothy"); // TODO: Update picture to NPC picture
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
