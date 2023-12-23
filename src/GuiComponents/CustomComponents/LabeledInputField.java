package GuiComponents.CustomComponents;

import Database.Database;
import GuiComponents.Resources.StoreColors;
import StoreFunctions.CashieringFunctions;
import StoreObjects.Item;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.SQLException;

public class LabeledInputField extends JPanel {
    final private static Font BOLD_FONT = new Font(Font.SANS_SERIF, Font.BOLD, 15);
    final private static Font PLAIN_FONT = new Font(Font.SANS_SERIF, Font.PLAIN, 20);
    final private static int PANEL_WIDTH = 260;
    final private static int PANEL_HEIGHT = 50;
    final private static int LABEL_WIDTH = 200;
    final private static int LABEL_HEIGHT = 15;
    final private static int TEXT_FIELD_WIDTH = 250;
    final private static int TEXT_FIELD_HEIGHT = 30;
    final private static int TEXT_FIELD_Y = 16;
    private RoundedTextField textField = new RoundedTextField(StoreColors.TEXTFIELD_COLOR, StoreColors.MAROON, StoreColors.BACKGROUND_COLOR);
    private boolean rejectChars = false;

    public LabeledInputField(String label, Point location, boolean rejectChars, Color panelColor) {
        JLabel labelName = new JLabel(label);
        this.textField = new RoundedTextField(StoreColors.TEXTFIELD_COLOR, StoreColors.MAROON, panelColor);
        this.rejectChars = rejectChars;

        this.setVisible(true);
        this.setLayout(null);
        this.setLocation(location);
        this.setOpaque(false);
        this.setSize(PANEL_WIDTH, PANEL_HEIGHT);

        labelName.setFont(BOLD_FONT);
        labelName.setLocation(0, 0);
        labelName.setSize(LABEL_WIDTH, LABEL_HEIGHT);
        labelName.setForeground(StoreColors.DARKEST_MAROON);

        textField.setFont(PLAIN_FONT);
        textField.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        textField.setLocation(0, TEXT_FIELD_Y);

        this.add(labelName);
        this.add(textField);

        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {
                char input = e.getKeyChar();
                boolean maxCapacity = getInput().replaceAll(",", "").length() == 9;

                if ((!Character.isDigit(input) || maxCapacity) && rejectChars) {
                    e.consume();
                }

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                char input = e.getKeyChar();
                if ((Character.isDigit(input) || input == KeyEvent.VK_BACK_SPACE) && rejectChars) {
                    String currentInput = textField.getText().replaceAll(",", "");

                    System.out.println(currentInput);

                    if (!currentInput.isEmpty())
                        currentInput = CashieringFunctions.addCommas(Integer.parseInt(currentInput));

                    textField.setText(currentInput);
                }
            }
        });
    }

    public LabeledInputField(String label, Point location, DescriptionLabel itemName, DescriptionLabel itemPrice, DescriptionLabel itemStocks, Color panelColor) {
        JLabel labelName = new JLabel(label);

        textField = new RoundedTextField(StoreColors.TEXTFIELD_COLOR, StoreColors.MAROON, panelColor);

        this.setVisible(true);
        this.setLayout(null);
        this.setLocation(location);
        this.setOpaque(false);
        this.setSize(PANEL_WIDTH, PANEL_HEIGHT);

        labelName.setFont(BOLD_FONT);
        labelName.setLocation(0, 0);
        labelName.setSize(LABEL_WIDTH, LABEL_HEIGHT);
        //labelName.setForeground(StoreColors.MAROON);
        labelName.setForeground(StoreColors.DARKEST_MAROON);

        textField.setFont(PLAIN_FONT);
        textField.setSize(TEXT_FIELD_WIDTH, TEXT_FIELD_HEIGHT);
        textField.setLocation(0, TEXT_FIELD_Y);

        this.add(labelName);
        this.add(textField);

        textField.addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                String itemNameInput = textField.getText();
                try {
                    if (Database.itemExists(itemNameInput)) {
                        Item item = Database.getItemDetails(itemNameInput);
                        itemName.setText(item.getItemName());
                        itemPrice.setText("₱" + CashieringFunctions.addCommas(item.getPrice()));
                        itemStocks.setText(CashieringFunctions.addCommas(item.getStocks()));
                    } else {
                        itemName.setText("");
                        itemPrice.setText("");
                        itemStocks.setText("");
                    }
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
    }

    public String getInput() {
        if (rejectChars)
            return textField.getText().replaceAll(",", "");

        return textField.getText();
    }

    public void clearInput() {
        textField.setText("");
    }

    public JTextField getTextField() {
        return textField;
    }

}
