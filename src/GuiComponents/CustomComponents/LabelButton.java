package GuiComponents.CustomComponents;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.HashMap;

public class LabelButton extends JLabel {

    private boolean selected = false;
    final private ImageIcon baseImage;
    final private ImageIcon selectImage;

    HashMap<LabelButton, JPanel> associatedButtonAndPanels;

    public LabelButton(
            int width,
            int height,
            ImageIcon baseImage,
            ImageIcon hoverImage,
            ImageIcon selectImage,
            HashMap<LabelButton, JPanel> associatedButtonAndPanels
    ) {
        this.associatedButtonAndPanels = associatedButtonAndPanels;

        this.baseImage = baseImage;
        this.selectImage = selectImage;
        this.setSize(width, height);
        this.setIcon(baseImage);
        this.setVisible(true);


        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                select();
                buttonPressed();
                System.out.println("Mouse clicked");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!selected) setIcon(hoverImage);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!selected) setIcon(baseImage);
            }
        });

    }

    public LabelButton(
            int width,
            int height,
            ImageIcon baseImage,
            ImageIcon hoverImage,
            ImageIcon selectImage
    ) {
        this.baseImage = baseImage;
        this.selectImage = selectImage;
        this.setSize(width, height);
        this.setIcon(baseImage);
        this.setVisible(true);


        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
            }

            @Override
            public void mousePressed(MouseEvent e) {
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                select();
                System.out.println("Mouse clicked");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                if (!selected) setIcon(hoverImage);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!selected) setIcon(baseImage);
            }
        });

    }

    public LabelButton(int width, int height, ImageIcon baseImage, ImageIcon hoverImage) {
        this.baseImage = baseImage;
        this.selectImage = null;
        this.setSize(width, height);
        this.setIcon(baseImage);
        this.setVisible(true);


        this.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                setIcon(hoverImage);
            }

            @Override
            public void mousePressed(MouseEvent e) {
                setIcon(baseImage);
            }

            @Override
            public void mouseReleased(MouseEvent e) {

                System.out.println("Mouse clicked");
            }

            @Override
            public void mouseEntered(MouseEvent e) {
                setIcon(hoverImage);
                setCursor(new Cursor(Cursor.HAND_CURSOR));
            }

            @Override
            public void mouseExited(MouseEvent e) {
                setIcon(baseImage);
            }
        });

    }

    public void deselect() {
        selected = false;
        this.setIcon(baseImage);
    }

    public void select() {
        this.setIcon(selectImage);
        this.selected = true;
    }

    public void buttonPressed() {
        for(LabelButton button : associatedButtonAndPanels.keySet()){
            if(this != button){
                button.deselect();
                associatedButtonAndPanels.get(button).setVisible(false);
            }else{
                associatedButtonAndPanels.get(button).setVisible(true);
            }
        }
    }

    public void setAssociatedButtonAndPanels(HashMap<LabelButton, JPanel> associatedButtonAndPanels){
        this.associatedButtonAndPanels = associatedButtonAndPanels;
    }

    public boolean isSelected(){
        return this.selected;
    }

}
