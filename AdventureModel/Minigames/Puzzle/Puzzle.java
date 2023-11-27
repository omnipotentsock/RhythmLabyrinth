package AdventureModel.Minigames.Puzzle;

import AdventureModel.Interpretations.Interpretation;
import AdventureModel.Interpretations.PuzzleInterpretationFactory;
import AdventureModel.Minigames.MiniGame;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Random;


public class Puzzle implements MiniGame {
    /**
     * This is the Puzzle's associated InterpretationFactory.
     */
    private PuzzleInterpretationFactory puzzleInterpretationFactory;
    /**
     * This is the Puzzle's accuracy cutoff between PerformanceEnding and SatisfactoryEnding.
     */
    private Double PerfSatCutoff;
    /**
     * This is the Puzzle's accuracy cutoff between SatisfactoryEnding and MediocreEnding.
     */
    private Double SatMedCutoff;
    private Integer sequenceLength; // CPU Sequence that user must complete
    private ArrayList<Integer> sequenceArray = new ArrayList<Integer>(); // The array in which the sequence is stored
    private static String currDigit; // The current button that the user has selected
    private static ArrayList<Integer> userSequence = new ArrayList<Integer>(); // The array in which the user has input their sequence


    // The actual button being pressed
    public class SequenceButton {
        public static void main(String[] args) {
            JFrame frame = new JFrame("3x3 Button Grid");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 300);

            // Create a panel with a 3x3 grid layout
            JPanel panel = new JPanel(new GridLayout(3, 3));

            // Create buttons and add them to the panel
            for (int i = 1; i <= 9; i++) {
                JButton button = new JButton(Integer.toString(i));
                button.addActionListener(new SequenceButton.ButtonClickListener());
                panel.add(button);
            }

            // Add the panel to the frame
            frame.add(panel);

            // Set the frame properties
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        }

        static class ButtonClickListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                JButton source = (JButton) e.getSource();
                currDigit = source.getText();
                userSequence.add(Integer.parseInt(currDigit));
                System.out.println("Button " + source.getText() + " clicked");
            }
        }
    }

    private void creatingSequence() {
        Random random = new Random();

        int min = 1;
        int max = 20;
        sequenceLength = random.nextInt(max - min + 1) + min;

        for (int element = 1; element <= sequenceLength; element++) {
            Random randomElement = new Random();

            int minElement = 1;
            int maxElement = 9;
            int randomElementInRange = randomElement.nextInt(maxElement - minElement + 1) + minElement;

            sequenceArray.add(randomElementInRange);
        }

    }

    public class pressingCPUSequence {
        private JButton button;

        public void AutoButtonPressExample() {
            JFrame frame = new JFrame("Auto Button Press Example");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(300, 200);

            button = new JButton("Press Me!");
            button.addActionListener(new ButtonPressListener());

            frame.add(button);
            frame.setVisible(true);

            autoPressButtons(); // Simulate button presses automatically
        }

        private void autoPressButtons() {
            for (int buttonIndex : sequenceArray) {
                simulateButtonPress(buttonIndex);
            }
        }

        private void simulateButtonPress(final int buttonIndex) {
            // Simulate button press by triggering its ActionListener
            button.doClick();
            System.out.println("Button " + buttonIndex + " pressed!");
            try {
                Thread.sleep(1000); // Sleep for 1 second between button presses (for demonstration purposes)
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        static class ButtonPressListener implements ActionListener {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Handle button press events if needed
                System.out.println("Button pressed!");
            }
        }

        public void main(String[] args) {
            new pressingCPUSequence();
        }
    }


    private boolean checkSequence() {
        for (int element = 1; element <= sequenceLength; element++) {
            if (sequenceArray.get(element) != userSequence.get(element)) {
                return false;
            }

        }
        return true;
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
    public Double getPerfSatCutoff() {
        return this.PerfSatCutoff;
    }
    public Double getSatMedCutoff() {
        return this.SatMedCutoff;
    }

    /**
     * This method returns the Player's accuracy.
     * @return a Double value.
     */
    public Double accuracy() {
        int counter = 0;
        for (int i = 0; i < userSequence.size(); i++) {
            if (Objects.equals(userSequence.get(i), this.sequenceArray.get(i))) {
                counter++;
            }
        }
        return Double.valueOf( counter / this.sequenceLength);
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
            prevCorrect = currCorrect;
            //##################################################################################
            if (Objects.equals(userSequence.get(i), this.sequenceArray.get(i))) {
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
        return Double.valueOf(maxConsecutive / (this.accuracy() * this.sequenceLength));
    }
}

// TODO: * Find a way for the CPU to press the buttons on its own at first
// TODO: * Adjust the program so that the button can run in place
