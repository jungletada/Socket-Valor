package Test;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.event.*;
import java.awt.event.MouseEvent;


public class CFrame implements ActionListener {
    public JFrame frame = new JFrame("Control");
    public JButton b1 = new JButton("Hero A");
    public JButton b2 = new JButton("Hero B");
    public JButton b3 = new JButton("Hero C");
    public JButton b4 = new JButton("Hero D");
    public JButton b5 = new JButton("Hero E");
    public JButton b6 = new JButton("Computer");
    public JPanel panel = new JPanel();
    public int Choose = -1;

    public CFrame() {
        frame.setSize(300, 300);
        panel.add(b1);
        panel.add(b2);
        panel.add(b3);
        panel.add(b4);
        panel.add(b5);
        panel.add(b6);
        frame.add(panel);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void actionPerformed(ActionEvent e) {
        if (b1 == e.getSource()) {
            Choose = 1;
            System.out.println(Choose);
        }

        if (b2 == e.getSource()) {
            Choose = 2;
            System.out.println(Choose);
        }

        if (b3 == e.getSource()) {
            Choose = 3;
            System.out.println(Choose);
        }

        if (b4 == e.getSource()) {
            Choose = 4;
            System.out.println(Choose);
        }

        if (b5 == e.getSource()) {
            Choose = 5;
            System.out.println(Choose);
        }

        if (b6 == e.getSource()) {
            Choose = 0;
            System.out.println(Choose);
        }

    }
}
