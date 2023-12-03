package AdventureModel.Minigames;

import AdventureModel.AdventureGame;
import AdventureModel.Characters.Player;
import javafx.scene.layout.Pane;
import views.AdventureGameView;

public abstract class Minigame {
    public String minigameType;
    public Minigame(String type) {
        this.minigameType = type;
    }
    public abstract void execute(AdventureGameView adventureGameView);

    public abstract Pane createGamePane(AdventureGameView adventureGameView);
}
