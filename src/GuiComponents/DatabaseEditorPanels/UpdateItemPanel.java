package GuiComponents.DatabaseEditorPanels;

import Database.Database;
import GuiComponents.CustomComponents.LabeledInputField;
import GuiComponents.Resources.Alert;
import GuiComponents.Resources.StoreColors;
import StoreObjects.Item;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.sql.SQLException;

public class UpdateItemPanel extends JPanel {

    final private static Point ITEM_NAME_INPUT_LOCATION = new Point(10, 0);
    final private static Point NEW_ITEM_NAME_INPUT_LOCATION = new Point(10, 55);
    final private static Point NEW_PRICE_INPUT_LOCATION = new Point(10, 110);
    final private static Point NEW_COST_PER_ITEM_INPUT_LOCATION = new Point(10, 165);

    final private static Color BACKGROUND_COLOR = StoreColors.LIGHT_MAROON;

    final private LabeledInputField itemNameInput = new LabeledInputField("Item Name:", ITEM_NAME_INPUT_LOCATION, false, BACKGROUND_COLOR);
    final private LabeledInputField newItemNameInput = new LabeledInputField("New Item Name:", NEW_ITEM_NAME_INPUT_LOCATION, false, BACKGROUND_COLOR);
    final private LabeledInputField newPriceInput = new LabeledInputField("New Item Price:", NEW_PRICE_INPUT_LOCATION, true, BACKGROUND_COLOR);
    final private LabeledInputField newCostPerItemInput = new LabeledInputField("New Cost Per Item:", NEW_COST_PER_ITEM_INPUT_LOCATION, true, BACKGROUND_COLOR);

    public UpdateItemPanel(int width, int height, int XLocation, int YLocation) {

        this.setSize(width, height);
        this.setLocation(XLocation, YLocation);
        this.setOpaque(false);
        this.setLayout(null);
        this.setVisible(false);

        this.add(itemNameInput);
        this.add(newItemNameInput);
        this.add(newPriceInput);
        this.add(newCostPerItemInput);

    }

    public void submit() throws SQLException {
        String itemNameInput = this.itemNameInput.getInput();
        String newItemNameInput = this.newItemNameInput.getInput();
        String newPriceInputString = this.newPriceInput.getInput();
        String newCostPerItemInputString = this.newCostPerItemInput.getInput();

        if (itemNameInput.isEmpty()) {
            Alert.alert("Enter item name");
            return;
        }

        if(!Database.itemExists(itemNameInput)){
            Alert.alert("Item does not exist");
            return;
        }

        Item item = Database.getItemDetails(itemNameInput);

        newItemNameInput = (newItemNameInput.isEmpty())
                ? item.getItemName()
                : newItemNameInput;

        double newPriceInput = (newPriceInputString.isEmpty())
                ? item.getPrice()
                : Double.parseDouble(newPriceInputString);

        double newCostPerItemInput = (newCostPerItemInputString.isEmpty())
                ? item.getCostToBuy()
                : Double.parseDouble(newCostPerItemInputString);

        Database.updateItem(itemNameInput, newItemNameInput, newPriceInput, newCostPerItemInput);

        this.itemNameInput.clearInput();
        this.newItemNameInput.clearInput();
        this.newPriceInput.clearInput();
        this.newCostPerItemInput.clearInput();
    }

}
