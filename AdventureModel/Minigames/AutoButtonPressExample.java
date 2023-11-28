package AdventureModel.Minigames;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AutoButtonPressExample {
    private JButton button;
    private int[] buttonPressSequence = {1, 2, 3, 1, 2, 3}; // Example array of integers

    public AutoButtonPressExample() {
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
        for (int buttonIndex : buttonPressSequence) {
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

    private class ButtonPressListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Handle button press events if needed
            System.out.println("Button pressed!");
        }
    }

    public static void main(String[] args) {
        new AutoButtonPressExample();
    }
}