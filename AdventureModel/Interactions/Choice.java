package AdventureModel.Interactions;

import java.util.ArrayList;

public class Choice extends Interaction{

    String dialogueText; // String holding text that shows above options
    boolean refreshing = true; // Should the interaction be refreshed

    ArrayList<ChoiceOption> options = new ArrayList<ChoiceOption>(); // List of options

    public Choice(String choiceString){

        // Choice: Do you want to attack him?-true-Yes>M0001/No>NULL
        // At this point, choiceString should be "Do you want to attack him?-true-Yes>M0001/No>NULL"

        String[] choice = choiceString.split("-"); // ["Do you want to attack him?", "true", "Yes>M0001/No>NULL"]

        String dialogueText = choice[0];
        boolean refresh = Boolean.parseBoolean(choice[1]);

        this.setDialogueText(dialogueText);
        this.setRefreshing(refresh);

        String[] options = choice[2].split("/");

        // Over here, options should be ["Yes>M0001","No>NULL"]
        for (String option : options){
            this.options.add(new ChoiceOption(option));
        }
    }
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