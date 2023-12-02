package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Class Puzzle
 * ------------
 * This is the class that will visualize the puzzle component of the minigame.
 * It features a 3x3 grid of labelled buttons in which a CPU flashes a certain sequence
 * and the player must do their best in matching up to said sequence.
 */
public class Puzzle {
    public static ArrayList<Integer> sequenceArray = new ArrayList<>();
    private static ArrayList<Integer> userSequence = new ArrayList<>();
    private static ArrayList<JButton> buttons = new ArrayList<>();
    private static int currentIndex = 0;
    private static int maxSequence = 5;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Puzzle puzzle = new Puzzle();
            puzzle.createButtonGrid();
            puzzle.generateSequence();
            puzzle.runCPUSequence();
        });
    }

    /**
     * createButtonGrid
     * ----------------
     * Forms the actual 3x3 grid containing the buttons in GUI form
     */
    private void createButtonGrid() {
        JFrame frame = new JFrame("Color Button Grid");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        JPanel panel = new JPanel(new GridLayout(3, 3));

        for (int i = 1; i <= 9; i++) {
            JButton button = new JButton(Integer.toString(i));
            button.addActionListener(new ButtonClickListener());
            buttons.add(button);
            panel.add(button);
        }

        frame.add(panel);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    /**
     * generateSequence
     * ----------------
     * Allows the CPU to generate a randomized sequence for the round
     */
    private void generateSequence() {
        Random random = new Random();
        int randomNum = ThreadLocalRandom.current().nextInt(1, maxSequence + 1);
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
    private void flashButtonColor(JButton button) {
        button.setBackground(Color.RED);  // Adjust the color as needed
        button.setOpaque(true);

        Timer timer = new Timer(500, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                button.setBackground(null);
                button.setOpaque(false);
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
    private class ButtonClickListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JButton source = (JButton) e.getSource();
            int buttonIndex = buttons.indexOf(source);
            // ==BELOW LINE==: records the button that was pressed
            System.out.println("Button " + (buttonIndex + 1) + " clicked");
            userSequence.add(buttonIndex);
            System.out.println(sequenceArray);
            System.out.println(userSequence);
            checkUserSequence();
        }
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
    private void checkUserSequence() {
        if (ComparisonSequence.compare(sequenceArray, userSequence)) {
            System.out.println("Congratulations! Sequences match!");
        } else {
            System.out.println("Incorrect sequence. Try again.");
        }
    }

}