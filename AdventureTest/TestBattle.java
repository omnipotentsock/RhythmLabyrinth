package AdventureTest;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class TestBattle {

    private final int BUTTON_WIDTH = 100;
    private final int BUTTON_HEIGHT = 20;
    private int buttonTimer = 1000; // The time that the button will stay there
    private int buttonPressed; // Counts the # of times user has pressed a button
    private int buttonMissed; // Counts the # of times user has missed a button

    public TestBattle() {
        JFrame frame = new JFrame();
        frame.setLayout(null);

        Timer timer = new Timer(1000, new ActionListener() {
            Random random = new Random();

            @Override
            public void actionPerformed(ActionEvent arg0) {
                JButton button = new JButton("Click me!");
                button.setBounds(random.nextInt(frame.getContentPane().getWidth() - BUTTON_WIDTH),
                        random.nextInt(frame.getContentPane().getHeight() - BUTTON_HEIGHT), BUTTON_WIDTH,
                        BUTTON_HEIGHT);

                for (int tries = 0; tries < 50; tries++) {
                    // TODO: Change this for loop to have the following:
                    // * Only have one button present at a time
                    // * Have a button be replaced by another button depending on whether
                    // user clicked or enough time has passed
                    // * Keep a count variable of the button pressed and the button missed

                    //if (timer == 1000) {}

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
                new TestBattle();
            }
        });
    }
}