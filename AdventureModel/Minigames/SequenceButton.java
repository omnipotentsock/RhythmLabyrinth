package AdventureModel.Minigames;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;



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
            button.addActionListener(new ButtonClickListener());
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
            System.out.println("Button " + source.getText() + " clicked");
        }
    }
}