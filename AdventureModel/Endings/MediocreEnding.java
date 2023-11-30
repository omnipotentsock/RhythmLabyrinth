package AdventureModel.Endings;

import views.AdventureGameView;

import java.io.File;

public class MediocreEnding implements Ending {
    private AdventureGameView adventureGameView;
    private final String message = "The fate of the world rested in your hands, and you have let it to rot!";
    private final String picture = "Mediocre.png";
    /**
     * In this ending, the player has done poorly.
     *      - No extra MiniGames are rewarded
     *      - Display MediocreEnding Photo
     *      - Display MediocreEnding Text
     */
    public MediocreEnding(AdventureGameView adventureGameView) {
        this.adventureGameView = adventureGameView;
    }
    @Override
    public void executeEnding() {
        this.adventureGameView.setEndingSettings(picture, message);
    }
    @Override
    public String getID() {
        return "MEDIOCRE";
    }
}
