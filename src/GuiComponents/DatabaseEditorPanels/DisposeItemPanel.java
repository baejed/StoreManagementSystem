package GuiComponents.DatabaseEditorPanels;

import Database.Database;
import GuiComponents.CustomComponents.LabeledInputField;
import GuiComponents.Resources.Alert;
import GuiComponents.Resources.StoreColors;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;

public class DisposeItemPanel extends JPanel {

    final private static Point ITEM_NAME_INPUT_LOCATION = new Point(10, 0);
    final private static Point AMOUNT_TO_DISPOSE_INPUT_LOCATION = new Point(10, 55);

    final private static Color BACKGROUND_COLOR = StoreColors.LIGHT_MAROON;

    final private LabeledInputField itemNameInput = new LabeledInputField("Item Name:", ITEM_NAME_INPUT_LOCATION, false, BACKGROUND_COLOR);
    final private LabeledInputField amountToDisposeInput = new LabeledInputField("Amount To Dispose:", AMOUNT_TO_DISPOSE_INPUT_LOCATION, true, BACKGROUND_COLOR);

    public DisposeItemPanel(int width, int height, int XLocation, int YLocation) {

        this.setSize(width, height);
        this.setLocation(XLocation, YLocation);
        this.setOpaque(false);
        this.setLayout(null);
        this.setVisible(false);

        this.add(itemNameInput);
        this.add(amountToDisposeInput);

    }

    public void submit() throws SQLException {
        String itemNameInput = this.itemNameInput.getInput();
        String amountToDisposeInput = this.amountToDisposeInput.getInput();

        if(itemNameInput.isEmpty() || amountToDisposeInput.isEmpty()){
            Alert.alert("Please fill all the fields");
            return;
        }

        int amountToDispose = Integer.parseInt(amountToDisposeInput);

        if(!Database.itemExists(itemNameInput)){
            Alert.alert("Item does not exist");
            return;
        }

        Database.disposeItem(itemNameInput, amountToDispose);

        this.itemNameInput.clearInput();
        this.amountToDisposeInput.clearInput();

    }

}
