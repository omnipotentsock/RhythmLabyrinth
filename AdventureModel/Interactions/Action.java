package AdventureModel.Interactions;

import AdventureModel.Minigames.*;
import AdventureModel.Minigames.Battle.Battle;
import views.AdventureGameView;

import java.util.HashMap;

public class Action extends Interaction{

    /**
     * Minigame object to be executed
     */
    private final String minigameID;
//    private final Minigame minigame = null;
    private String dialogueText;
    private String afterText;
    private boolean refreshing;

    public Action(String str){
        // Currently str = "Yes>Wolf is attacking you!&M001&He backs away."
        String[] parsed = str.split("&", -1);
        // parsed is ["Wolf is attacking you!","M001","He backs away."]
        this.dialogueText = parsed[0];
        this.afterText = parsed[2];
        this.minigameID = parsed[1];

        this.refreshing = false;
    }

    // NOTE!! Action instances are never in Room.forcedQueue! They are executed after Option instance is executed!!
    public void execute(AdventureGameView adventureGameView){
        HashMap<String, Minigame> minigames = adventureGameView.getModel().getMinigames();
        if (minigames.containsKey(this.minigameID)){
            Minigame minigame = minigames.get(this.minigameID);
            adventureGameView.updateScene(this.dialogueText + this.afterText, minigame.minigameType);
            minigame.execute(adventureGameView);
        } else if (minigameID.equals("NULL")) {
            adventureGameView.getModel().getPlayer().getCurrentRoom().getQueue().refresh();
            adventureGameView.updateScene(this.afterText);
        } else {System.out.println("MINIGAME " + this.minigameID + " does not exist!");}
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
