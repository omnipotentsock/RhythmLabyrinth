package AdventureModel.Minigames.Puzzle;

import AdventureModel.AdventureGame;
import AdventureModel.Minigames.Minigame;
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
import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;


public class Puzzle extends Minigame implements Serializable {
    private String minigameID = "NULL";
    public String minigameType;
    /**
     * This is the Puzzle's accuracy cutoff between PerformanceEnding and SatisfactoryEnding.
     */
    private Double PerfSatCutoff;
    /**
     * This is the Puzzle's accuracy cutoff between SatisfactoryEnding and MediocreEnding.
     */
    private Double SatMedCutoff;
    private int attempts = 0;

    private int streak = 0;
    private Double highestStreak = 0.0;

    private String currDigit; // The current button that the user has selected
    public ArrayList<Integer> sequenceArray = new ArrayList<>(); // The array in which the sequence is stored
    private ArrayList<Integer> userSequence = new ArrayList<>(); // The array in which the user has input their sequence
    private ArrayList<Button> buttons = new ArrayList<>();
    private int currentIndex = 0;
    private int sequencesComplete = 0;
    public int sequenceLength;
    public int sequences;
    public Puzzle() {
        super("puzzle");
    }
    public Puzzle(String id, String[] parameters, String[] thresholds) {
        super("puzzle");
        this.minigameID = id;
        sequences = Integer.parseInt(parameters[0]);
        sequenceLength = Integer.parseInt(parameters[1]);

        this.PerfSatCutoff = Double.parseDouble(thresholds[1]);
        this.SatMedCutoff = Double.parseDouble(thresholds[0]);
    }
    public void execute(AdventureGameView adventureGameView) {
        adventureGameView.playGame(this);

    }
    @Override
//    public Pane createGamePane(AdventureGameView adventureGameView) {return null;};
    // The actual button being pressed
//    public static void main(String[] args) {
//
//    }

    /**
     * createButtonGrid
     * ----------------
     * Forms the actual 3x3 grid containing the buttons in GUI form
     */
    public Pane createGamePane(AdventureGameView adventureGameView) {
//        JFrame frame = new JFrame("Color Button Grid");
//        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Pane root = new Pane();
        root.setMaxSize(650,650);
//        SwingNode frame = new SwingNode();
//        frame.setSize(300, 300);
//        SwingUtilities.invokeLater(() -> {
//            JPanel panel = createButtonGrid();
//            frame.setContent(panel);
////            Puzzle puzzle = new Puzzle();
////            puzzle.createGamePane(adventureGameView);
//            root.getChildren().add(frame);
//            generateSequence();
//            runCPUSequence();
//        });
        Platform.runLater(() -> {
            GridPane panel = createButtonGrid(adventureGameView);
            root.getChildren().add(panel);

            generateSequence();
            runCPUSequence();
        });


//        frame.setLocationRelativeTo(null);
//        frame.setVisible(true);

//        Rectangle background = new Rectangle(650,500);
//        background.setFill(javafx.scene.paint.Color.TRANSPARENT);
//        root.getChildren().add(background);

        return root;
    }
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
//            SwingUtilities.invokeLater(() -> {
//
//            });
            if ((i-1)%3 == 0){
                counter += 1;
            }
            Button button = new Button(Integer.toString(i));
//            button.addActionListener(new Puzzle.ButtonClickListener());
            button.setOnAction(e -> {
                actionPerformed(button, adventureGameView);
            });
            button.setStyle("-fx-background-color: #fff0f3;" + "-fx-padding: 5em;");
            button.setOnMousePressed(e -> button.setStyle("-fx-background-color: #a89ea0;" + "-fx-padding: 5em;"));
            button.setOnMouseReleased(e -> button.setStyle("-fx-background-color: #fff0f3;" + "-fx-padding: 5em;"));
            buttons.add(button);
            panel.add(button, (i-1)%3, counter);
//            button.repaint();
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
        for (int i = 0; i < this.sequenceLength; i++) {  // Adjust the sequence length as needed
            int randomIndex = random.nextInt(9);
            sequenceArray.add(randomIndex);
        }
        System.out.println(sequenceArray);
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
        timer.restart();
    }


    /**
     * flashButtonColour
     * -----------------
     * Flashes the colour red whenever the CPU chooses a specific button.
     * @param button
     */
    private void flashButtonColor(Button button) {
//        button.setBackground(Background.fill(Color.RED));  // Adjust the color as needed
        button.setStyle("-fx-background-color: #f0627f;" + "-fx-padding: 5em;");
//        button.setOpaque(true);

        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setStyle("-fx-background-color: #fff0f3;" + "-fx-padding: 5em;");

//                    button.setOpaque(false);
//                SwingUtilities.invokeLater(() -> {
//                    button.repaint();
//                });
            }
        });
        timer.setRepeats(false);
        timer.start();
    }

    /**
     * ButtonClickListener
     * -------------------
     * Records the button index and source pressed in the user sequence
     */
//    private class ButtonClickListener implements ActionListener {
//        @Override
//    }
    public void actionPerformed(Button source, AdventureGameView adventureGameView) {
//        Button source = (Button) e.getSource();
//            int buttonIndex = buttons.indexOf(source);
        int buttonIndex = Integer.parseInt(source.getText()) - 1;
        // ==BELOW LINE==: records the button that was pressed
//            System.out.println("Button " + (buttonIndex + 1) + " clicked");
        userSequence.add(buttonIndex);
//            System.out.println(sequenceArray);
//            System.out.println(userSequence);
        checkUserSequence(adventureGameView);
    }

    public Double accuracy() {
        return (this.sequences / this.attempts) / 1.0;
    }

    public Double getPerfSatCutoff() {
        return this.PerfSatCutoff;
    }

    public Double getSatMedCutoff() {
        return this.SatMedCutoff;
    }

    public Double consecutiveImpact() {
        return this.highestStreak / this.sequences;
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
            this.attempts++;
            this.sequencesComplete++;
            this.streak++;
            if (this.sequencesComplete != this.sequences){
                createGamePane(adventureGameView);
            } else {
                System.out.println("Congratulations! Sequences match! Completed in " + this.attempts + " attempts!");
                if (this.streak > this.highestStreak){
                    this.highestStreak = (double) (this.streak);
                }
                adventureGameView.addMinigame(this);
                adventureGameView.finishGame();
            }
        } else {
            if (sequenceArray.size() == userSequence.size()) {
                System.out.println("Incorrect sequence. Try again.");
                this.userSequence = (ArrayList<Integer>) this.sequenceArray.clone();
                this.attempts++;
                if (this.streak > this.highestStreak){
                    this.highestStreak = (double) this.streak;
                }
                this.streak = 0;
                createGamePane(adventureGameView);
            }
        }
    }

}