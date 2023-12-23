package GuiComponents.CustomComponents;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;

import GuiComponents.Resources.*;

public class DescriptionLabel extends JPanel {

    final private static Border BORDER = BorderFactory.createLineBorder(StoreColors.MAROON);
    final private static int LABEL_CONTENT_WIDTH = 310;
    final private static int LABEL_CONTENT_HEIGHT = 30;
    final private static int LABEL_NAME_WIDTH = 150;
    final private static int LABEL_NAME_HEIGHT = 30;
    final private static int PANEL_WIDTH = 440;
    final private static int PANEL_HEIGHT = 30;
    final private static int LABEL_CONTENT_X = 120;
    final private static int DEFAULT_LOC = 0;
    final private JLabel labelContent = new JLabel();
    private JLabel labelName;

    public DescriptionLabel(String label, Point location, int labelContentX, int labelContentWidth, int panelWidth, boolean enableBorder) {
        this.labelName = new JLabel(label);

        this.setVisible(true);
        this.setLayout(null);
        this.setLocation(location);
        this.setOpaque(false);
        this.setSize(panelWidth, PANEL_HEIGHT);

        labelName.setLocation(DEFAULT_LOC, DEFAULT_LOC);
        labelName.setFont(Fonts.FONT_BOLD);
        labelName.setSize(LABEL_NAME_WIDTH, LABEL_NAME_HEIGHT);
        labelName.setForeground(StoreColors.MAROON);

        labelContent.setSize(labelContentWidth, LABEL_CONTENT_HEIGHT);
        labelContent.setLocation(labelContentX, DEFAULT_LOC);
        if (enableBorder) labelContent.setBorder(BORDER);
        labelContent.setFont(Fonts.FONT_PLAIN);
        labelContent.setHorizontalAlignment(SwingConstants.LEFT);
        labelContent.setForeground(StoreColors.DARK_MAROON);

        this.add(labelName);
        this.add(labelContent);
    }

    public DescriptionLabel(String label, Point location, int labelContentX, int labelContentWidth, int panelWidth, int labelNameWidth, boolean enableBorder) {
        this.labelName = new JLabel(label);

        this.setVisible(true);
        this.setLayout(null);
        this.setLocation(location);
        this.setOpaque(false);
        this.setSize(panelWidth, PANEL_HEIGHT);

        labelName.setLocation(DEFAULT_LOC, DEFAULT_LOC);
        labelName.setFont(Fonts.FONT_BOLD);
        labelName.setSize(labelNameWidth, LABEL_NAME_HEIGHT);
        labelName.setForeground(StoreColors.MAROON);

        labelContent.setSize(labelContentWidth, LABEL_CONTENT_HEIGHT);
        labelContent.setLocation(labelContentX, DEFAULT_LOC);
        if (enableBorder) labelContent.setBorder(BORDER);
        labelContent.setFont(Fonts.FONT_PLAIN);
        labelContent.setHorizontalAlignment(SwingConstants.LEFT);
        labelContent.setForeground(StoreColors.DARK_MAROON);

        this.add(labelName);
        this.add(labelContent);
    }

    public DescriptionLabel(String label, Point location) {
        JLabel labelName = new JLabel(label);

        this.setVisible(true);
        this.setLayout(null);
        this.setLocation(location);
        this.setOpaque(false);
        this.setSize(PANEL_WIDTH, PANEL_HEIGHT);

        labelName.setLocation(DEFAULT_LOC, DEFAULT_LOC);
        labelName.setFont(Fonts.FONT_BOLD);
        labelName.setSize(LABEL_NAME_WIDTH, LABEL_NAME_HEIGHT);
        labelName.setForeground(StoreColors.DARKEST_MAROON);

        labelContent.setSize(LABEL_CONTENT_WIDTH, LABEL_CONTENT_HEIGHT);
        labelContent.setLocation(LABEL_CONTENT_X, DEFAULT_LOC);
        labelContent.setBorder(BORDER);
        labelContent.setFont(Fonts.FONT_PLAIN);
        labelContent.setHorizontalAlignment(SwingConstants.CENTER);
        labelContent.setForeground(StoreColors.MAROON);
        labelContent.setBackground(StoreColors.TEXTFIELD_COLOR);
        labelContent.setOpaque(true);

        this.add(labelName);
        this.add(labelContent);
    }

    public void setText(String text) {
        labelContent.setText(text);
    }

    public void clearText(){
        labelContent.setText("");
    }

}
