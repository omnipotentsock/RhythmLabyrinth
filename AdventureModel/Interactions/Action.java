package AdventureModel.Interactions;

import AdventureModel.Minigames.*;

public class Action extends Interaction{

    /**
     * Minigame object to be executed
     */
    private final Minigame minigame = null;
    private String dialogueText;
    private String afterText;
    private boolean refreshing;

    public Action(String str){ // TODO: GET MINIGAME? IDK
        // Currently str = "Yes>Wolf is attacking you!&M001&He backs away."
        String[] parsed = str.split("&", -1);
        // parsed is ["Wolf is attacking you!","M001","He backs away."]
        this.dialogueText = parsed[0];
        this.afterText = parsed[2];

        String minigameID = parsed[1];
        this.refreshing = false;
    }

    // NOTE!! Action instances are never in Room.forcedQueue! They are executed after Option instance is executed!!
    public void execute(){
        if (this.minigame != null){
            this.minigame.execute(); //TODO: MAKE SURE WHAT THE METHOD FOR STARTING A MINIGAME WILL BE BEFORE MERGEREQ
        }
    }
    protected void setDialogueText(String text) {
        // TODO: IMPLEMENT SOMEHOW
    }
    protected void setRefreshing(boolean refresh) {
        // TODO: IMPLEMENT SOMEHOW
    }
    public boolean getRefreshing(){ return this.refreshing;}
    public String getDialogueText(String text){return this.dialogueText;}

}
