package GuiComponents.CustomComponents;

import GuiComponents.Resources.StoreColors;

import javax.swing.*;
import java.awt.*;

public class RoundedPasswordField extends JPasswordField {
    private boolean hasBorder;
    private Color frameColor;
    public RoundedPasswordField(Color backgroundColor, Color foregroundColor, Color frameColor) {
        this.frameColor = frameColor;
        this.setBackground(backgroundColor);
        this.setForeground(foregroundColor);
        this.setMargin(new Insets(0, 5, 0, 5));
        this.hasBorder = false;
    }

    public RoundedPasswordField(String text) {
        super(text);
        this.hasBorder = true;
    }



    public RoundedPasswordField(int columns) {
        super(columns);
        this.hasBorder = true;
    }

    public RoundedPasswordField(String text, int columns) {
        super(text, columns);
        this.hasBorder = true;
    }

    public void setHasBorder(boolean hasBorder) {
        this.hasBorder = hasBorder;
        this.repaint();
    }

    @Override
    protected void paintBorder(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(frameColor);
        g2.setStroke(new BasicStroke(3));
        g2.drawRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        g2.drawRect(0, 0, getWidth(), getHeight());

        if(hasBorder) {
            g2.setColor(frameColor);
            g2.setStroke(new BasicStroke(1));
            g2.drawRoundRect(0, 0, getWidth(), getHeight(), 10, 10);
        }
    }
}