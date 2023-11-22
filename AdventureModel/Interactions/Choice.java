package AdventureModel.Interactions;

import java.util.ArrayList;

public class Choice extends Interaction{

    String dialogueText; // String holding text that shows above options
    boolean refreshing = true; // Should the interaction be refreshed

    ArrayList<ChoiceOption> options; // List of options
    @Override
    public void execute() {

        System.out.println(this.dialogueText); // TODO: Display choice dialogue and options retrieved below

        for (ChoiceOption option : this.options){
            System.out.println(option.getOptionText()); // TODO: Populate optionsView with option buttons
        }

        // TODO: Feel free to add imports to AdventureGame or whatever to make sure you can figure out what option is clicked or however you can make this work
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
