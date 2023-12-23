package GuiComponents.CustomComponents;

import javax.swing.*;
import java.awt.*;

public class UIPanel extends JPanel {

    public UIPanel(int width, int height, int XLocation, int YLocation, Color color){
        this.setVisible(true);
        this.setBackground(color);
        this.setOpaque(false);
        this.setSize(width, height);
        this.setLayout(null);
        this.setLocation(XLocation, YLocation);
    }

    public UIPanel(int width, int height, int XLocation, int YLocation){
        this.setVisible(true);
        this.setOpaque(false);
        this.setSize(width, height);
        this.setLayout(null);
        this.setLocation(XLocation, YLocation);
    }

}
