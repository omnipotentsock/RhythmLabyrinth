package AdventureModel.Endings;

import views.AdventureGameView;

import java.io.File;

public class SatisfactoryEnding implements Ending{
    private AdventureGameView adventureGameView;
    private final String message = "You have saved the world, young one. Well done!";
    private final String picture = "SatisfactoryEnding";
    /**
     * In this ending, the player achieves at least the minimum requirements to win the game.
     *      - Play either a Puzzle or Battle
     *      - Afterward, display picture
     *      - Display message
     */
    public SatisfactoryEnding(AdventureGameView adventureGameView) {
        this.adventureGameView = adventureGameView;
    }
    @Override
    public void executeEnding() {
        throw new UnsupportedOperationException();
    }
    @Override
    public String getID() {
        return "SATISFACTORY";
    }
}
