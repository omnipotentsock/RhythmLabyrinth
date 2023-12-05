package AdventureModel.Minigames;

import AdventureModel.AdventureGame;
import AdventureModel.Interpretations.Interpretation;
import AdventureModel.Interpretations.InterpretationFactory;
import AdventureModel.Interpretations.PuzzleInterpretationFactory;
import javafx.application.Platform;
import javafx.embed.swing.SwingNode;
import javafx.geometry.Pos;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import views.AdventureGameView;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class Puzzle extends Minigame {
    public String minigameType;
    private Integer sequenceLength; // CPU Sequence that user must complete
    private static String currDigit; // The current button that the user has selected
    public static ArrayList<Integer> sequenceArray = new ArrayList<>(); // The array in which the sequence is stored
    private static ArrayList<Integer> userSequence = new ArrayList<>(); // The array in which the user has input their sequence
    private static ArrayList<Button> buttons = new ArrayList<>();
    private static int currentIndex = 0;
    private static int maxSequence = 5;
    private double accuracy = 0.0;
    public int correctNums = 0;
    private PuzzleInterpretationFactory puzzleInterpretationFactory;
    private Double PerfSatCutoff;
    private Double SatMedCutoff;

    public Puzzle() {
        super("puzzle");
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
        Pane root = new Pane();
        root.setMaxSize(650,650);
        Platform.runLater(() -> {
            GridPane panel = createButtonGrid(adventureGameView);

            root.getChildren().add(panel);
            generateSequence();
            runCPUSequence();
        });
        return root;
    }

    /**
     * createButtonGrid
     * ----------------
     * Forms the actual 3x3 grid containing the buttons in GUI form
     * @param adventureGameView
     * @return
     */
    private GridPane createButtonGrid(AdventureGameView adventureGameView) {
        //Three columns, three rows for the GridPane
        ColumnConstraints column1 = new ColumnConstraints(150);
        ColumnConstraints column2 = new ColumnConstraints(150);
        ColumnConstraints column3 = new ColumnConstraints(150);
        column3.setHgrow( Priority.SOMETIMES ); //let some columns grow to take any extra space
        column1.setHgrow( Priority.SOMETIMES );

        // Row constraints
        RowConstraints row1 = new RowConstraints(150);
        RowConstraints row2 = new RowConstraints( 150 );
        RowConstraints row3 = new RowConstraints(150);
        row1.setVgrow( Priority.SOMETIMES );
        row3.setVgrow( Priority.SOMETIMES );
        GridPane panel = new GridPane();
        panel.getColumnConstraints().addAll( column1 , column2 , column3 );
        panel.getRowConstraints().addAll( row1 , row2 , row3 );

        int counter = 0;
        for (int i = 1; i <= 9; i++) {
            if ((i-1)%3 == 0){
                counter += 1;
            }
            Button button = new Button(Integer.toString(i));
            button.setOnAction(e -> {
                actionPerformed(button, adventureGameView);
            });
            button.setStyle("-fx-background-color: #fff0f3;" + "-fx-padding: 5em;");
            button.setOnMousePressed(e -> button.setStyle("-fx-background-color: #a89ea0;" + "-fx-padding: 5em;"));
            button.setOnMouseReleased(e -> button.setStyle("-fx-background-color: #fff0f3;" + "-fx-padding: 5em;"));
            buttons.add(button);
            panel.add(button, (i-1)%3, counter);
        }
        panel.setAlignment(Pos.CENTER);
        return panel;
    }

    /**
     * generateSequence
     * ----------------
     * Allows the CPU to generate a randomized sequence for the round
     */
    private void generateSequence() {
        Random random = new Random();
        int randomNum = ThreadLocalRandom.current().nextInt(1, maxSequence + 1); // TODO: seqLength and maxSeq
        for (int i = 0; i < randomNum; i++) {  // Adjust the sequence length as needed
            int randomIndex = random.nextInt(9);
            sequenceArray.add(randomIndex);
        }
    }

    /**
     * runCPUSequence
     * --------------
     * The actual process for the CPU generating a sequence
     */
    private void runCPUSequence() {
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentIndex < sequenceArray.size()) {
                    int buttonIndex = sequenceArray.get(currentIndex);
                    flashButtonColor(buttons.get(buttonIndex));
                    currentIndex++;
                } else {
                    ((Timer) e.getSource()).stop();
                }
            }
        });
        timer.start();
    }

    /**
     * flashButtonColour
     * -----------------
     * Flashes the colour red whenever the CPU chooses a specific button.
     * @param button
     */
    private void flashButtonColor(Button button) {
        button.setStyle("-fx-background-color: #f0627f;" + "-fx-padding: 5em;");

        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setStyle("-fx-background-color: #fff0f3;" + "-fx-padding: 5em;");
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * actionPerformed
     * -------------------
     * Records the button index and source pressed in the user sequence
     */
    public void actionPerformed(Button source, AdventureGameView adventureGameView) {
        int buttonIndex = Integer.parseInt(source.getText()) - 1;
        userSequence.add(buttonIndex);
        checkUserSequence(adventureGameView);
    }


    /**
     * ComparisonSequence
     * ------------------
     * Compares both the CPU sequence and the user sequence and returns the comparison value
     */
    private static class ComparisonSequence {
        public static boolean compare(ArrayList<Integer> sequenceArray, ArrayList<Integer> userSequence) {
            if (sequenceArray.size() != userSequence.size()) {
                return false;
            }
            for (int i = 0; i < sequenceArray.size(); i++) {
                if (!userSequence.get(i).equals(sequenceArray.get(i))) {
                    return false;
                }
            }
            return true;
        }

    }

    /**
     * checkUserSequence
     * -----------------
     * Process that actually runs the check on both the user and CPU sequence
     * and outputs a statement regarding the result
     */
    private void checkUserSequence(AdventureGameView adventureGameView) {
        if (Puzzle.ComparisonSequence.compare(sequenceArray, userSequence)) {
            System.out.println("Congratulations! Sequences match!");
            this.accuracy = 1;
            adventureGameView.finishGame();
        } else {
            if (sequenceArray.size() == userSequence.size()) {
                System.out.println("Incorrect sequence. Try again.");
                for (int i = 0; i < sequenceArray.size(); i++) {
                    if (userSequence.get(i) == sequenceArray.get(i)) {
                        this.correctNums += 1;
                    }
                }
                this.accuracy = correctNums/sequenceArray.size();
                adventureGameView.finishGame();
            }
        }
    }

    /**
     * This method notifies the PuzzleInterpretationFactory to create a corresponding
     * PuzzleInterpretation object.
     * @return an Interpretation object.
     */
    @Override
    public Interpretation formInterpretation() {
        this.puzzleInterpretationFactory.accept(this);
        return this.puzzleInterpretationFactory.createInterpretation();
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
        int correctCounter = 0;
        int index = 0;
        boolean currCorrect = false;
        boolean prevCorrect;
        int maxConsecutive = 0;
        for (int i = 0; i < userSequence.size(); i++) {
            // If 'current' was correct the last time, then that means previous is now correct.
            if (currCorrect) {
                prevCorrect = true;
            } else {
                prevCorrect = false;
            }
            //##################################################################################
            if (this.userSequence.get(i) == this.sequenceArray.get(i)) {
                correctCounter++;
                // If they match, current is correct again.
                currCorrect = true;
            } else {
                // Otherwise, current is now false. Update maxConsecutive, and reassign index.
                if ((i - index) > maxConsecutive) {
                    maxConsecutive = i - index;
                }
                index = i;
                currCorrect = false;
            }
            //##################################################################################
            if (currCorrect && !prevCorrect) {
                index = i;
            }
        }
        if (currCorrect) {
            if ((userSequence.size() - index) > maxConsecutive) {
                maxConsecutive = userSequence.size() - index;
            }
        }
        return Double.valueOf(maxConsecutive / (double) correctCounter);
    }

}