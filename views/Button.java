package views;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JFrame {
    private static final long serialVersionUID = 1L;

    public static void main(String[] args) {
        //Creatign the frame
        Button frame = new Button();
        //Creating the button with the label "Click me!"
        JButton button = new JButton("Click me!");
        //Adding an action listener so we can assign some logic to be executed when this button is clicked on (this is using an anonymous inner class in future versions of Java this will be replaced by the MUCH cleaner Lamba approach, keep an eye out for that)
        button.addActionListener(new ActionListener() {
            //Keep a variable to store how many times the button is clicked.  This shows that the action listener stays running in between clicks)
            private int count = 0;
            //While technically optional thew @Override annotation helps if you update interfaces, get into the habit of doing this to make future work easier, things like Eclipse will insert it for you
            @Override
            public void actionPerformed(ActionEvent e) {
                //Bump up the count variable
                this.count++;
                //And print it to System.out
                System.out.println("Pressed "+this.count + " times");
            }
        });
        //Add the button (with it's listener) to the frame
        frame.add(button);
        //Tell Swing to resize the frame to fit the requested size of all of it's contained widgets
        frame.pack();
        //Tell Swing to show the frame
        frame.setVisible(true);
    }
}