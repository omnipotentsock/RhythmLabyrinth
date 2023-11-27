package views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

/**
 * The battle class.
 */
public class Battle {

    /**
     * Uses the button combination for battles.
     * A button will randomly generate somewhere in the GUI
     * and will be removed and placed somewhere else regardless
     * if user clicks on button on time or not.
     */
    public class RandomButton {

        private final int BUTTON_WIDTH = 100;
        private final int BUTTON_HEIGHT = 20;

        public RandomButton() {
            JFrame frame = new JFrame();
            frame.setLayout(null);

            Timer timer = new Timer(100, new ActionListener() {
                Random random = new Random();

                @Override
                public void actionPerformed(ActionEvent arg0) {
                    JButton button = new JButton("Button");
                    button.setBounds(random.nextInt(frame.getContentPane().getWidth() - BUTTON_WIDTH),
                            random.nextInt(frame.getContentPane().getHeight() - BUTTON_HEIGHT), BUTTON_WIDTH,
                            BUTTON_HEIGHT);
                    for (int tries = 0; tries < 50; tries++) {
                        if (intersectsComponent(button, frame.getContentPane().getComponents())) {
                            button.setBounds(random.nextInt(frame.getContentPane().getWidth() - BUTTON_WIDTH),
                                    random.nextInt(frame.getContentPane().getHeight() - BUTTON_HEIGHT), BUTTON_WIDTH,
                                    BUTTON_HEIGHT);
                        } else {
                            frame.add(button);
                            break;
                        }
                    }
                    frame.revalidate();
                    frame.repaint();
                }
            });
            timer.start();

            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            frame.setVisible(true);
        }

        public boolean intersectsComponent(Component component, Component[] components) {
            for (Component c : components) {
                if (c.getBounds().intersects(component.getBounds())) {
                    return true;
                }
            }
            return false;
        }

        public static void main(String[] args) {
            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new views.RandomButton();
                }
            });
        }
    }
}

// Todo: Find a way so that it only displays the current button
// todo: and that the button only changes when you click on it and/or the time window passes
// TODO: Add documentation on the methods using /** as the docstrings (refer to assignment for good example)
// TODO: * in order for the InterpretationFactory pattern to work, the Battle and Puzzle classes each need to have a respective BattleInterpretationFactory and PuzzleInterpretationFactory attribute (line 7).
// TODO: thus make sure to add that as a parameter or an attribute
