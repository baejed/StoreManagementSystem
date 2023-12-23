package GuiComponents.DatabaseEditorPanels;

import Database.Database;
import GuiComponents.CustomComponents.LabeledInputField;
import GuiComponents.CustomComponents.RoundedButton;
import GuiComponents.Resources.Alert;
import GuiComponents.Resources.StoreColors;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.sql.SQLException;

public class AddItemPanel extends JPanel {

    final private static Point ITEM_NAME_INPUT_LOCATION = new Point(10, 0);
    final private static Point PRICE_INPUT_LOCATION = new Point(10, 55);
    final private static Point COST_PER_ITEM_INPUT = new Point(10, 110);

    final private static Color BACKGROUND_COLOR = StoreColors.LIGHT_MAROON;

    final private LabeledInputField itemNameInput = new LabeledInputField("Item Name:", ITEM_NAME_INPUT_LOCATION, false, BACKGROUND_COLOR);
    final private LabeledInputField priceInput = new LabeledInputField("Price:", PRICE_INPUT_LOCATION, true, BACKGROUND_COLOR);
    final private LabeledInputField costPerItemInput = new LabeledInputField("Cost Per Item:", COST_PER_ITEM_INPUT, true, BACKGROUND_COLOR);

    public AddItemPanel(int width, int height, int XLocation, int YLocation) {

        this.setSize(width, height);
        this.setLocation(XLocation, YLocation);
        this.setOpaque(false);
        this.setLayout(null);
        this.setVisible(false);

        this.add(itemNameInput);
        this.add(priceInput);
        this.add(costPerItemInput);

    }

    public void submit() throws SQLException {
        String itemNameInput = this.itemNameInput.getInput();
        String priceInputString = this.priceInput.getInput();
        String costPerItemInputString = this.costPerItemInput.getInput();

        if(itemNameInput.isEmpty() || priceInputString.isEmpty() || costPerItemInputString.isEmpty()){
            Alert.alert("Please fill all the fields");
            return;
        }

        if(Database.itemExists(itemNameInput)){
            Alert.alert("Item already exists");
            return;
        }

        double priceInput = Double.parseDouble(priceInputString);
        double costPerItemInput = Double.parseDouble(costPerItemInputString);

        Database.addItem(itemNameInput, priceInput, costPerItemInput);

        this.itemNameInput.clearInput();
        this.priceInput.clearInput();
        this.costPerItemInput.clearInput();

    }

}
