package AdventureModel.Minigames.Battle;

import AdventureModel.AdventureGame;
import AdventureModel.Characters.Player;
import AdventureModel.Interpretations.BattleInterpretationFactory;
import AdventureModel.Interpretations.Interpretation;
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

import java.io.Serializable;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Battle extends Minigame implements Serializable {

    private final BattleInterpretationFactory battleInterpretationFactory = new BattleInterpretationFactory();
    public String minigameType;
    public String minigameID;
    public double totalHealth; // TODO: To parse
    public double enemyHealth;
    public int damage;
    /**
     * This is the Battle's accuracy cutoff between PerformanceEnding and SatisfactoryEnding.
     */
    private Double PerfSatCutoff;
    /**
     * This is the Battle's accuracy cutoff between SatisfactoryEnding and MediocreEnding.
     */
    private Double SatMedCutoff;

    private Double playerHP;
    private final int BUTTON_WIDTH = 60;
    private final int BUTTON_HEIGHT = 60;
    Button targetButton;
    Random random;
    Pane root;
    Rectangle background;
    boolean pressed = false;
    boolean firstIteration = true;
    private int interval = 300;

    public Battle() {
        super("battle");
    }

    public Battle(String ID, String[] parameters, String[] thresholds) {
        super("battle");
        minigameID = ID;
        this.totalHealth = Integer.parseInt(parameters[2]);
        this.enemyHealth = Integer.parseInt(parameters[2]);
        this.interval = Integer.parseInt(parameters[0]);
        this.damage = Integer.parseInt(parameters[1]);

        this.PerfSatCutoff = Double.parseDouble(thresholds[1]);
        this.SatMedCutoff = Double.parseDouble(thresholds[0]);
    }
    public void execute(AdventureGameView adventureGameView) {
        adventureGameView.playGame(this);
    }
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
//        VBox pHealth = new VBox(playerHealthBar);
//        pHealth.setAlignment(Pos.TOP_CENTER);
        adventureGameView.getCurrentPane().add(playerHealthBar, 2, 1);

        ProgressBar enemyHealthBar = new ProgressBar();
        enemyHealthBar.setMinWidth(200); // Set the width of the health bar
        enemyHealthBar.setProgress(1);
        enemyHealthBar.getTransforms().setAll(
//                new Translate(enemyHealthBar.getWidth(), enemyHealthBar.getHeight()),
                new Rotate(-90, 0, 0)
        );
        enemyHealthBar.setStyle("-fx-accent: red;");
//        VBox eHealth = new VBox(enemyHealthBar);
//        pHealth.setAlignment(Pos.BOTTOM_CENTER);
        adventureGameView.getCurrentPane().add(enemyHealthBar, 0, 1);

        Player player = adventureGameView.getModel().getPlayer();
        playerHP = player.getPlayerHealth();
        root = new Pane();
        root.setMaxSize(650,500);
        background = new Rectangle(650,500);
        background.setFill(Color.TRANSPARENT);
        root.getChildren().add(background);

        targetButton = new Button("ATTACK");
        targetButton.setMinSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        targetButton.setMaxSize(BUTTON_WIDTH, BUTTON_HEIGHT);
        targetButton.setOnAction(e -> {
            enemyHealth -= adventureGameView.getModel().player.damage; // Damage taken by enemy
            Platform.runLater(() -> root.getChildren().remove(targetButton));
            flashScreen(background, "#09663e");
            enemyHealthBar.setProgress(enemyHealth/totalHealth);
            this.pressed = true;
        });
        targetButton.setStyle(
                "-fx-background-radius: 50em;" +
                        "-fx-background-color: #e35f73;"
        );
        random = new Random();

        int interval = this.interval; //TODO: Rate Attribute

        ScheduledExecutorService executorService = Executors.newScheduledThreadPool(1);
        executorService.scheduleAtFixedRate(() -> {
            if (enemyHealth > 0 && player.playerHealth > 0) {
                Platform.runLater(() -> {
                    relocateButton();
                    targetButton.setDisable(false);
                });
            } else {
                executorService.shutdown();
                adventureGameView.addMinigame(this);
                adventureGameView.finishGame();
            }
            if (!pressed && !firstIteration) {
                Platform.runLater(() -> {
                    takeDamage(player);
                    playerHP = player.getPlayerHealth();
                    playerHealthBar.setProgress(player.playerHealth / player.totalHealth);
                    System.out.println(player.playerHealth);
                });
            }
            firstIteration = false;
        }, 0, interval, TimeUnit.MILLISECONDS);

        return root;
    }

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

    private void takeDamage(Player player) {
        player.loseHealth(this.damage); //TODO: Player loses health
        flashScreen(background, "#780727");
    }

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

    @Override
    public Interpretation formInterpretation() {
        this.battleInterpretationFactory.accept(this);
        return this.battleInterpretationFactory.createInterpretation();
    }
    public Double getPlayerHealth() {
        return Double.valueOf((double) playerHP / totalHealth);
    }
    public void setPlayerHP(Double hp) {
        this.playerHP = hp;
    }
    public Double getPerfSatCutoff() {
        return this.PerfSatCutoff;
    }

    public Double getSatMedCutoff() {
        return this.SatMedCutoff;
    }
//    private
}