package GuiComponents.DatabaseEditorPanels;

import Database.Database;
import GuiComponents.CustomComponents.LabeledInputField;
import GuiComponents.Resources.Alert;
import GuiComponents.Resources.StoreColors;

import javax.swing.*;
import javax.xml.crypto.Data;
import java.awt.*;
import java.sql.SQLException;

public class DeleteItemPanel extends JPanel {

    final private static Point ITEM_NAME_INPUT_LOCATION = new Point(10, 0);

    final private static Color BACKGROUND_COLOR = StoreColors.LIGHT_MAROON;

    final private LabeledInputField itemNameInput = new LabeledInputField("Item Name:", ITEM_NAME_INPUT_LOCATION, false, BACKGROUND_COLOR);

    public DeleteItemPanel(int width, int height, int XLocation, int YLocation) {



        this.setSize(width, height);
        this.setLocation(XLocation, YLocation);
        this.setOpaque(false);
        this.setLayout(null);
        this.setVisible(false);

        this.add(itemNameInput);

    }

    public void submit() throws SQLException {
        String itemNameInput = this.itemNameInput.getInput();

        if(itemNameInput.isEmpty()){
            Alert.alert("Please enter the item name");
            return;
        }

        if(!Database.itemExists(itemNameInput)){
            Alert.alert("Item does not exist");
            return;
        }

        Database.deleteItem(itemNameInput);

        this.itemNameInput.clearInput();

    }

}
