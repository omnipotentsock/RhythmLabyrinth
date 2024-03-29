package AdventureModel.Interactions;

import views.AdventureGameView;

import java.util.ArrayList;

public class Choice extends Interaction{

    String dialogueText; // String holding text that shows above options
    boolean refreshing = true; // Should the interaction be refreshed

    ArrayList<ChoiceOption> options = new ArrayList<ChoiceOption>(); // List of options

    /**
     * Constructor for the Choice object
     *
     * @param choiceString
     * String to be delimited and parsed for the dialogueText, refreshing, and options attributes
     */
    public Choice(String choiceString){

        // Choice: Do you interrupt him?-false-Yes>Wolf is attacking you!&M001&He backs away./>No&NULL&They do not notice you
        // At this point, choiceString should be "Do you interrupt him?-false-Yes>Wolf is attacking you!&M001&He backs away./>No&NULL&They do not notice you"

        String[] choice = choiceString.split("-", 3); // ["Do you want to attack him?", "false", "Yes>Wolf is attacking you!&M001&He backs away./>No&NULL&They do not notice you"]

        String dialogueText = choice[0];
        boolean refresh = Boolean.parseBoolean(choice[1]);

        this.setDialogueText(dialogueText);
        this.setRefreshing(refresh);

        String[] options = choice[2].split("/");

        // Over here, options should be ["Yes>Wolf is attacking you!&M001&He backs away./No>&NULL&They do not notice you"]
        for (String option : options){
            this.options.add(new ChoiceOption(option));
        }
    }

    /**
     * Displays choice dialogue with input (buttons or textField) to choose option
     */
    @Override
    public void execute(AdventureGameView adventureGameView) {
        String s = this.dialogueText;
        adventureGameView.updateScene(s, this);
    }

    protected void setDialogueText(String text) {this.dialogueText = text;}
    protected void setRefreshing(boolean refresh) {this.refreshing = refresh;}
    public boolean getRefreshing(){ return this.refreshing;}
    public String getDialogueText(){return this.dialogueText;}
    public ArrayList<ChoiceOption> getOptions() { return this.options; }
}
