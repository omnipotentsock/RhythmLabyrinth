package AdventureModel.Minigames;

import AdventureModel.Interpretations.Interpretation;
import javafx.scene.layout.Pane;
import views.AdventureGameView;

public abstract class MiniGame {
    public String minigameType;
    public MiniGame(String type) {
        this.minigameType = type;
    }
    public abstract void execute(AdventureGameView adventureGameView);

    public abstract Pane createGamePane(AdventureGameView adventureGameView);
    public abstract Interpretation formInterpretation();
}
