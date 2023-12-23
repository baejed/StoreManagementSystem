package GuiComponents.CustomComponents;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class RoundedButton extends JButton {

    private Color backgroundColor;

    public RoundedButton(String text, Color hoverColor) {
        super(text);
        setContentAreaFilled(false);

        setBorder(
                BorderFactory.createCompoundBorder(
                        new EmptyBorder(6, 6, 6, 6),
                        new LineBorder(Color.WHITE, 0, true)
                )
        );

        this.addMouseListener(new MouseListener() {

            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {
                backgroundColor = getBackground();
                setBackground(hoverColor);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setBackground(backgroundColor);
            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {
        g.setColor(this.getBackground());
        g.fillRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        super.paintComponent(g);
    }
}
