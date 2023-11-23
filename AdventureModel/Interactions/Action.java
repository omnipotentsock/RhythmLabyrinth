package AdventureModel.Interactions;

import AdventureModel.Minigames.*;

public class Action extends Interaction{

    private final Minigame minigame;
    private String dialogueText;
    private boolean refreshing;

    public Action(){
        this.minigame = null;
    }

    public Action(Minigame minigame, boolean refresh){ // TODO: GET MINIGAME? IDK
        this.minigame = minigame;
        this.dialogueText = "";
        this.refreshing = true;
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
