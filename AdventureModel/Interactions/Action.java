package AdventureModel.Interactions;

import AdventureModel.Minigames.*;

public class Action extends Interaction{

    private final Minigame minigame;

    public Action(){
        this.minigame = null;
    }

    public Action(Minigame minigame){
        this.minigame = minigame;
    }

    // NOTE!! Action instances are never in Room.forcedQueue! They are executed after Option instance is executed!!
    public void execute(){
        if (this.minigame != null){
            this.minigame.execute(); //TODO: MAKE SURE WHAT THE METHOD FOR STARTING A MINIGAME WILL BE BEFORE MERGEREQ
        }
    }

    @Override
    protected void setDialogueText(String text) {
        // TODO: IMPLEMENT SOMEHOW
    }

    @Override
    protected void setRefreshing(boolean refresh) {
        // TODO: IMPLEMENT SOMEHOW
    }

}
