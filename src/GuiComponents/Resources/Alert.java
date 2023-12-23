package GuiComponents.Resources;

import javax.swing.*;
import java.awt.event.*;

public class Alert {

    public static void alert(String alert) {

        JLabel label = new JLabel(alert, JLabel.CENTER);
        JOptionPane optionPane = new JOptionPane(label, JOptionPane.PLAIN_MESSAGE, JOptionPane.YES_NO_OPTION, null, new String[]{"OK"});
        JDialog dialog = optionPane.createDialog("Alert");
        optionPane.grabFocus();

        // Add KeyListener to the JDialog
        optionPane.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                // Do nothing
            }

            @Override
            public void keyPressed(KeyEvent e) {
            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyCode() == KeyEvent.VK_ENTER) {
                    // Close the dialog if Enter key is pressed
                    dialog.dispose();
                }
            }
        });

        dialog.setModal(true);
        dialog.setVisible(true);

    }

}
