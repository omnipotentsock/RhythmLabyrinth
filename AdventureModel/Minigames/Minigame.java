package AdventureModel.Minigames;

import AdventureModel.AdventureGame;
import AdventureModel.Characters.Player;
import AdventureModel.Interpretations.Interpretation;
import javafx.scene.layout.Pane;
import views.AdventureGameView;

import java.io.Serializable;

public abstract class Minigame implements Serializable {
    public String minigameType;
    public String minigameID;

    public Minigame(String type) {
        this.minigameType = type;
    }
    public abstract void execute(AdventureGameView adventureGameView);

    public abstract Pane createGamePane(AdventureGameView adventureGameView);

    public abstract Interpretation formInterpretation();
}
