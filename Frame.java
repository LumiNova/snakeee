package main;

import javax.swing.*;

public class Frame extends JFrame {

    Frame() {
        this.add(new Panel());
        this.setTitle("Snakey");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        this.setLocationRelativeTo(null);
        this.setAlwaysOnTop(true);
        this.setVisible(true);
   }
}
