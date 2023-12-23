import Database.Database;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;

public class Test {

    public static void main(String[] args) throws SQLException {

        JFrame frame = new JFrame();
        JLabel label = new JLabel();
        JButton button = new JButton();

        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setLayout(null);
        frame.setSize(500, 500);

        label.setSize(100, 100);
        label.setLocation(200, 200);
        label.setBackground(Color.red);
        label.setOpaque(true);

        button.setLocation(10, 10);
        button.setSize(100, 100);

        frame.add(label);
        frame.add(button);

        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                label.setSize(label.getWidth() + 10, label.getHeight() + 10);
            }
        });
    }

}