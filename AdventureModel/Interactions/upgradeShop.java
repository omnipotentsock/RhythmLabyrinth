package AdventureModel.Interactions;

import AdventureModel.Characters.Player;
import views.AdventureGameView;

import java.util.ArrayList;
import java.util.HashMap;

public class upgradeShop extends Interaction {

    HashMap<Integer,String> upgrades = new HashMap<>();
    private static String dialogueText = "Engage in activity to enhance your ability!"; // String holding text that shows above options
    boolean refreshing = true; // Should the interaction be refreshed


    ArrayList<ChoiceOption> options = new ArrayList<ChoiceOption>(); // List of options

    /**
     * Constructor for the Choice object
     *
     * @param choiceString
     * String to be delimited and parsed for the dialogueText, refreshing, and options attributes
     */
    public upgradeShop(String choiceString){

        // Upgrade: 1 HP-10,3 DMG-8,4 HP-20
        // At this point, choiceString should be "1 HP-10,3 DMG-8,4 HP-20"

        String[] choice = choiceString.split(","); // ["1 HP-10", "3 DMG-8", "4 HP-20"]

        for (String s : choice){
            String[] key = s.split(" ");
            this.upgrades.put(Integer.parseInt(key[0]), key[1]);
        }

        this.setRefreshing(true);

        String[] options = choice[2].split("/");

    }

    /**
     * Displays choice dialogue with input (buttons or textField) to choose option
     */
    @Override
    public void execute(AdventureGameView adventureGameView) {

        String str = "";

        if (this.upgrades.isEmpty()) {
            setRefreshing(false);
            adventureGameView.updateScene("No more upgrades remain.");
        } else {
            int played = adventureGameView.getModel().player.getOutcome().miniGamesCompleted();
            System.out.println(played);
            for (int i = played; i > 0; i--) {
                if (this.upgrades.containsKey(i)) {
                    upgrade(this.upgrades.get(i), adventureGameView.getModel().player);
                    str += "Upgraded " + this.upgrades.get(i).split("-")[0] + "\n";
                    this.upgrades.remove(i);
                }
            }
            str = dialogueText + "\n" + str;
            adventureGameView.updateScene(str);
        }
    }

    private void upgrade(String s, Player player){
        String[] key = s.split("-");
        if (key[0].equals("HP")){
            player.totalHealth += Integer.parseInt(key[1]);
            player.refreshHealth();
        } else if (key[0].equals("DMG")){
            player.damage += Double.parseDouble(key[1]);
        }
    }

    protected void setDialogueText(String text) {this.dialogueText = text;}
    protected void setRefreshing(boolean refresh) {this.refreshing = refresh;}
    public boolean getRefreshing(){ return this.refreshing;}
    public String getDialogueText(){return this.dialogueText;}
    public ArrayList<ChoiceOption> getOptions() { return this.options; }
}
