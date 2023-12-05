package AdventureModel.Endings;

import views.AdventureGameView;

import java.io.File;

public class PerformanceEnding implements Ending{
    private final String message = "You have performed well beyond what many others could ever imagine to achieve. A legendary hero!";
    private final String picture = "PerformanceEnding.png";
    /**
     * In this ending, the player has done exceptionally well.
     *      - Both a MiniGame and a Puzzle are rewarded
     *      - Afterward, display a PerformanceEnding message
     *      - Display PerformanceEnding picture
     */
    @Override
    public void executeEnding() {
        throw new UnsupportedOperationException();
    }
    @Override
    public String getID() {
        return "PERFORMANCE";
    }
    @Override
    public String getMessage() {
        return message;
    }
    @Override
    public String getPicture() {
        return picture;
    }
}
