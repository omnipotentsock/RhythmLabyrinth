package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Random;

/**
 * The puzzle class.
 */
public class Puzzle {
    private Integer sequenceLength; // CPU Sequence that user must complete
    private ArrayList<Integer> sequenceArray = new ArrayList<Integer>(); // The array in which the sequence is stored
    private static String currDigit; // The current button that the user has selected
    private static ArrayList<Integer> userSequence = new ArrayList<Integer>(); // The array in which the user has input their sequence


    /**
     * Uses the button combination for puzzles.
     * A 3x3 grid of buttons will appear
     * and the user is expected to select the correct sequence of buttons.
     * If not, the user will fail the puzzle.
     */
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
                button.addActionListener(new views.SequenceButton.ButtonClickListener());
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



}

// TODO: * Find a way for the CPU to press the buttons on its own at first
// TODO: * Adjust the program so that the button can run in place
// TODO: * Add documentation on the methods using /** as the docstrings (refer to assignment for good example)
// TODO: * in order for the InterpretationFactory pattern to work, the Battle and Puzzle classes each need to have a respective BattleInterpretationFactory and PuzzleInterpretationFactory attribute (line 7).
// TODO: thus make sure to add that as a parameter or an attribute