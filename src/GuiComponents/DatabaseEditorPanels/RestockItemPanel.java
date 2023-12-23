package GuiComponents.DatabaseEditorPanels;

import Database.Database;
import GuiComponents.CustomComponents.LabeledInputField;
import GuiComponents.Resources.Alert;
import GuiComponents.Resources.StoreColors;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.sql.SQLException;

public class RestockItemPanel extends JPanel {

    final private static Point ITEM_NAME_INPUT_LOCATION = new Point(10, 0);
    final private static Point AMOUNT_TO_RESTOCK_INPUT_LOCATION = new Point(10, 55);

    final private static Color BACKGROUND_COLOR = StoreColors.LIGHT_MAROON;

    final private LabeledInputField itemNameInput = new LabeledInputField("Item Name:", ITEM_NAME_INPUT_LOCATION, false, BACKGROUND_COLOR);
    final private LabeledInputField amountToRestockInput = new LabeledInputField("Amount To Restock:", AMOUNT_TO_RESTOCK_INPUT_LOCATION, true, BACKGROUND_COLOR);

    public RestockItemPanel(int width, int height, int XLocation, int YLocation) {

        this.setSize(width, height);
        this.setLocation(XLocation, YLocation);
        this.setOpaque(false);
        this.setLayout(null);
        this.setVisible(false);

        this.add(itemNameInput);
        this.add(amountToRestockInput);

    }

    public void submit() throws SQLException {
        String itemNameInput = this.itemNameInput.getInput();
        String amountToRestockInputString = this.amountToRestockInput.getInput();

        if(itemNameInput.isEmpty() || amountToRestockInputString.isEmpty()){
            Alert.alert("Please fill all the fields");
            return;
        }

        int amountToRestockInput = Integer.parseInt(amountToRestockInputString);

        if(!Database.itemExists(itemNameInput)){
            Alert.alert("Item does not exist");
            return;
        }

        Database.restockItem(itemNameInput, amountToRestockInput);

        this.itemNameInput.clearInput();
        this.amountToRestockInput.clearInput();

    }

}
