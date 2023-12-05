package AdventureModel.Minigames.Battle;

import AdventureModel.AdventureGame;
import AdventureModel.Characters.Player;
import AdventureModel.Interpretations.BattleInterpretationFactory;
import AdventureModel.Interpretations.Interpretation;
import AdventureModel.Interpretations.InterpretationFactory;
import AdventureModel.Minigames.Minigame;
import javafx.animation.FadeTransition;
import javafx.animation.Interpolator;
import javafx.application.Platform;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.transform.Rotate;
import javafx.scene.transform.Translate;
import javafx.util.Duration;
import views.AdventureGameView;

import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Battle extends Minigame {
    public String minigameType;
    public double enemyHealth = 100;
    public double totalHealth = 100;
    private double accuracy = 1;
    private final int BUTTON_WIDTH = 60;
    private final int BUTTON_HEIGHT = 60;
    Button targetButton;
    Random random;
    Pane root;
    Rectangle background;
    boolean pressed = false;
    boolean firstIteration = true;
    int highestConsecutive = 0;
    int curConsecutive = 0;
    private BattleInterpretationFactory battleInterpretationFactory;
    private Double PerfSatCutoff;
    private Double SatMedCutoff;

    public Battle() {
        super("battle");
    }

    /**
     * Starts the game logic and display
     * @param adventureGameView
     */
    public void execute(AdventureGameView adventureGameView) {
        adventureGameView.playGame(this);
    }

    /**
     * Makes the main pane for displaying the gameplay
     * @param adventureGameView
     * @return Pane
     */
    @Override
    public Pane createGamePane(AdventureGameView adventureGameView) {
        ProgressBar playerHealthBar = new ProgressBar();
        playerHealthBar.setMinWidth(200); // Set the width of the health bar
        playerHealthBar.setProgress(1);
        playerHealthBar.getTransforms().setAll(
                new Translate(0, playerHealthBar.getHeight()),
                new Rotate(-90, 0, 0)
        );
        playerHealthBar.setStyle("-fx-accent: #cf94ff;");
        adventureGameView.getCurrentPane().add(playerHealthBar, 2, 1);

        ProgressBar enemyHealthBar = new ProgressBar();
        enemyHealthBar.setMinWidth(200); // Set the width of the health bar
        enemyHealthBar.setProgress(1);
        enemyHealthBar.getTransforms().setAll(
                new Rotate(-90, 0, 0)
        );
        enemyHealthBar.setStyle("-fx-accent: red;");
        adventureGameView.getCurrentPane().add(enemyHealthBar, 0, 1);

        Player player = adventureGameView.getModel().getPlayer();
        root = new Pane();
        root.setMaxSize(650,500);
        background = new Rectangle(650,500);
        background.setFill(Color.TRANSPARENT);
        root.getChildren().add(background);

        targetButton = new Button("ATTACK");
        targetButton.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        targetButton.setMaxSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        targetButton.setOnAction(e -> {
            enemyHealth -= 10; //TODO: Enemy takes damage
            Platform.runLater(() -> root.getChildren().remove(targetButton));
            flashScreen(background, "#09663e");
            enemyHealthBar.setProgress(enemyHealth/totalHealth);
            this.pressed = true;

            curConsecutive += 1;
            if (curConsecutive > highestConsecutive) {
                highestConsecutive = curConsecutive;
            }
        });
        targetButton.setStyle(
                "-fx-background-radius: 50em;" +
                        "-fx-background-color: #e35f73;"
        );
        random = new Random();

        int interval = 800; //TODO: Rate Attribute

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            if (enemyHealth > 0 && player.playerHealth > 0) {
                Platform.runLater(() -> {
                    relocateButton();
                    targetButton.setDisable(false);
                });
            } else {
                executorService.shutdown();
                adventureGameView.finishGame();
            }
            if (!pressed && !firstIteration) {
                Platform.runLater(() -> {
                    takeDamage(player);
                    playerHealthBar.setProgress(player.playerHealth / player.totalHealth);
                    curConsecutive = 0;
                    System.out.println(player.playerHealth);
                });
            }
            firstIteration = false;
        }, 0, interval, TimeUnit.MILLISECONDS);

        return root;
    }

    /**
     * Moves the button to a new random location
     */
    private void relocateButton() {
        root.getChildren().remove(targetButton);
        int x = Math.abs((int) (root.getWidth() - BUTTON_WIDTH));
        int y = Math.abs((int) (root.getHeight() - BUTTON_HEIGHT));
        if (x == 0 || y ==0) {
            if (x == 0) { x += 1; }
            if (y == 0) { y += 1; }
        }
        targetButton.setLayoutX(random.nextInt(x));
        targetButton.setLayoutY(random.nextInt(y));
        this.pressed = false;
        root.getChildren().add(targetButton);
    }

    /**
     * When the player takes damage, they lose health and the screen flashes red
     * @param player
     */
    private void takeDamage(Player player) {
        player.loseHealth(10); //TODO: Player loses health
        this.accuracy = player.getPlayerHealth()/ player.totalHealth;
        flashScreen(background, "#780727");
    }

    /**
     * creates a flashing effect depending on who takes damage
     * @param bg
     * @param color
     */
    private void flashScreen(Rectangle bg, String color) {
        Color flashColor = Color.web(color);

        // Fade in transition
        FadeTransition fadeInTransition = new FadeTransition(Duration.seconds(0.1), bg);
        fadeInTransition.setFromValue(0.0);
        fadeInTransition.setToValue(1.0);
        fadeInTransition.setInterpolator(Interpolator.EASE_BOTH);

        // Fade out transition
        FadeTransition fadeOutTransition = new FadeTransition(Duration.seconds(0.4), bg);
        fadeOutTransition.setFromValue(1.0);
        fadeOutTransition.setToValue(0.0);
        fadeOutTransition.setInterpolator(Interpolator.EASE_BOTH);

        fadeInTransition.setOnFinished(event -> {
            fadeOutTransition.setOnFinished(fadeOutEvent -> {
                bg.setFill(Color.TRANSPARENT);
            });
            fadeOutTransition.play();
        });

        bg.setFill(flashColor);
        fadeInTransition.play();
    }
    /**
     * This method notifies the PuzzleInterpretationFactory to create a corresponding
     * PuzzleInterpretation object.
     * @return an Interpretation object.
     */
    public Interpretation formInterpretation() {
        this.battleInterpretationFactory.accept(this);
        return this.battleInterpretationFactory.createInterpretation();
    }

    /**
     * Getter for the cutoff threshold between perfect and satisfactory
     * @return Double for threshold
     */
    public Double getPerfSatCutoff() {
        return this.PerfSatCutoff;
    }

    /**
     * Getter for the cutoff threshold between satisfactory and medium
     * @return Double for threshold
     */
    public Double getSatMedCutoff() {
        return this.SatMedCutoff;
    }
    /**
     * This method returns the Player's accuracy.
     * @return a Double value.
     */
    public Double getAccuracy() {
        return this.accuracy;
    }
    /**
     * The impact value will be determined as the ratio between the largest number of
     * consecutively correct inputs to the number of correct inputs in the user input.
     * This method returns the impact value.
     * @return a Double value
     */
    public Double consecutiveImpact() {
        return Double.valueOf(highestConsecutive);
    }
}