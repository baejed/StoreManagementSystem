package GuiComponents.MainWindowPanels;

import Database.Database;
import GuiComponents.CustomComponents.LabelButton;
import GuiComponents.CustomComponents.UIPanel;
import GuiComponents.DatabaseEditorPanels.*;
import GuiComponents.Resources.Alert;
import GuiComponents.Resources.Images;
import GuiComponents.Resources.StoreColors;
import GuiComponents.Table.InventoryTable;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;
import java.util.HashMap;

public class InventoryPanel extends JPanel {

    // region STATIC VARIABLES
    final private static Border BORDER = BorderFactory.createMatteBorder(0, 3, 0, 0, StoreColors.DARK_MAROON);

    final private static int TABLE_PANEL_WIDTH = 500;
    final private static int TABLE_PANEL_HEIGHT = 537;
    final private static int TABLE_PANEL_X = 3;
    final private static int TABLE_PANEL_Y = 0;

    final private static int EDITING_PANEL_WIDTH = 305;
    final private static int EDITING_PANEL_HEIGHT = 537;
    final private static int EDITING_PANEL_X = 503;
    final private static int EDITING_PANEL_Y = 0;

    final private static Dimension INVENTORY_TABLE_SIZE = new Dimension(502, 537);
    final private static Point INVENTORY_TABLE_LOCATION = new Point(0, 0);

    final private static int ADD_ITEM_BUTTON_WIDTH = 95;
    final private static int ADD_ITEM_BUTTON_HEIGHT = 30;
    final private static Point ADD_ITEM_BUTTON_LOCATION = new Point(5, 5);

    final private static int UPDATE_ITEM_BUTTON_WIDTH = 95;
    final private static int UPDATE_ITEM_BUTTON_HEIGHT = 30;
    final private static Point UPDATE_ITEM_BUTTON_LOCATION = new Point(105, 5);

    final private static int DELETE_ITEM_BUTTON_WIDTH = 95;
    final private static int DELETE_ITEM_BUTTON_HEIGHT = 30;
    final private static Point DELETE_ITEM_BUTTON_LOCATION = new Point(205, 5);

    final private static int RESTOCK_BUTTON_WIDTH = 145;
    final private static int RESTOCK_BUTTON_HEIGHT = 30;
    final private static Point RESTOCK_BUTTON_LOCATION = new Point(5, 40);

    final private static int DISPOSE_BUTTON_WIDTH = 145;
    final private static int DISPOSE_BUTTON_HEIGHT_WIDTH = 30;
    final private static Point DISPOSE_BUTTON_LOCATION = new Point(155, 40);

    final private static int EDITING_SUB_PANELS_WIDTH = 305;
    final private static int EDITING_SUB_PANELS_HEIGHT = 350;
    final private static int EDITING_SUB_PANELS_X = 0;
    final private static int EDITING_SUB_PANELS_Y = 100;

    final private static int SUBMIT_BUTTON_WIDTH = 305;
    final private static int SUBMIT_BUTTON_HEIGHT = 80;
    final private static Point SUBMIT_BUTTON_LOCATION = new Point(0, 452);

    final private static HashMap<LabelButton, JPanel> associateButtonAndPanels = new HashMap<>();
    // endregion

    final private InventoryTable inventoryTable;
    private CashieringPanel cashieringPanel;

    public InventoryPanel(int width, int height, int XLocation, int YLocation, Color backgroundColor, StatisticsPanel statisticsPanel) throws SQLException {

        UIPanel tablePanel = new UIPanel(TABLE_PANEL_WIDTH, TABLE_PANEL_HEIGHT, TABLE_PANEL_X, TABLE_PANEL_Y, Color.red);
        UIPanel editingPanel = new UIPanel(EDITING_PANEL_WIDTH, EDITING_PANEL_HEIGHT, EDITING_PANEL_X, EDITING_PANEL_Y, StoreColors.LIGHT_MAROON);

        LabelButton submitButton = new LabelButton(SUBMIT_BUTTON_WIDTH, SUBMIT_BUTTON_HEIGHT, Images.SUBMIT_BUTTON, Images.SUBMIT_BUTTON_HOVER);

        AddItemPanel addItemPanel = new AddItemPanel(
                EDITING_SUB_PANELS_WIDTH,
                EDITING_SUB_PANELS_HEIGHT,
                EDITING_SUB_PANELS_X,
                EDITING_SUB_PANELS_Y
        );

        UpdateItemPanel updateItemPanel = new UpdateItemPanel(
                EDITING_SUB_PANELS_WIDTH,
                EDITING_SUB_PANELS_HEIGHT,
                EDITING_SUB_PANELS_X,
                EDITING_SUB_PANELS_Y
        );
        DeleteItemPanel deleteItemPanel = new DeleteItemPanel(
                EDITING_SUB_PANELS_WIDTH,
                EDITING_SUB_PANELS_HEIGHT,
                EDITING_SUB_PANELS_X,
                EDITING_SUB_PANELS_Y
        );
        RestockItemPanel restockItemPanel = new RestockItemPanel(
                EDITING_SUB_PANELS_WIDTH,
                EDITING_SUB_PANELS_HEIGHT,
                EDITING_SUB_PANELS_X,
                EDITING_SUB_PANELS_Y
        );
        DisposeItemPanel disposeItemPanel = new DisposeItemPanel(
                EDITING_SUB_PANELS_WIDTH,
                EDITING_SUB_PANELS_HEIGHT,
                EDITING_SUB_PANELS_X,
                EDITING_SUB_PANELS_Y
        );

        LabelButton addItemButton = new LabelButton(
                ADD_ITEM_BUTTON_WIDTH,
                ADD_ITEM_BUTTON_HEIGHT,
                Images.ADD_BUTTON,
                Images.ADD_BUTTON_HOVER,
                Images.ADD_BUTTON_SELECTED,
                associateButtonAndPanels
        );
        LabelButton updateItemButton = new LabelButton(
                UPDATE_ITEM_BUTTON_WIDTH,
                UPDATE_ITEM_BUTTON_HEIGHT,
                Images.UPDATE_BUTTON,
                Images.UPDATE_BUTTON_HOVER,
                Images.UPDATE_BUTTON_SELECTED,
                associateButtonAndPanels
        );
        LabelButton deleteItemButton = new LabelButton(
                DELETE_ITEM_BUTTON_WIDTH,
                DELETE_ITEM_BUTTON_HEIGHT,
                Images.DELETE_BUTTON,
                Images.DELETE_BUTTON_HOVER,
                Images.DELETE_BUTTON_SELECTED,
                associateButtonAndPanels
        );

        LabelButton restockButton = new LabelButton(
                RESTOCK_BUTTON_WIDTH,
                RESTOCK_BUTTON_HEIGHT,
                Images.RESTOCK_BUTTON,
                Images.RESTOCK_BUTTON_HOVER,
                Images.RESTOCK_BUTTON_SELECTED,
                associateButtonAndPanels
        );
        LabelButton disposeButton = new LabelButton(
                DISPOSE_BUTTON_WIDTH,
                DISPOSE_BUTTON_HEIGHT_WIDTH,
                Images.DISPOSE_BUTTON,
                Images.DISPOSE_BUTTON_HOVER,
                Images.DISPOSE_BUTTON_SELECTED,
                associateButtonAndPanels
        );

        submitButton.setLocation(SUBMIT_BUTTON_LOCATION);

        // Sets the default opened editing panel
        addItemButton.select();
        addItemPanel.setVisible(true);

        associateButtonAndPanels.put(addItemButton, addItemPanel);
        associateButtonAndPanels.put(updateItemButton, updateItemPanel);
        associateButtonAndPanels.put(deleteItemButton, deleteItemPanel);
        associateButtonAndPanels.put(restockButton, restockItemPanel);
        associateButtonAndPanels.put(disposeButton, disposeItemPanel);

        addItemButton.setAssociatedButtonAndPanels(associateButtonAndPanels);
        updateItemButton.setAssociatedButtonAndPanels(associateButtonAndPanels);
        deleteItemButton.setAssociatedButtonAndPanels(associateButtonAndPanels);
        restockButton.setAssociatedButtonAndPanels(associateButtonAndPanels);
        disposeButton.setAssociatedButtonAndPanels(associateButtonAndPanels);

        inventoryTable = new InventoryTable(INVENTORY_TABLE_SIZE);
        inventoryTable.setLocation(INVENTORY_TABLE_LOCATION);
        inventoryTable.setBackground(StoreColors.TEXTFIELD_COLOR);

        editingPanel.setOpaque(true);

        addItemButton.setLocation(ADD_ITEM_BUTTON_LOCATION);
        updateItemButton.setLocation(UPDATE_ITEM_BUTTON_LOCATION);
        deleteItemButton.setLocation(DELETE_ITEM_BUTTON_LOCATION);

        restockButton.setLocation(RESTOCK_BUTTON_LOCATION);
        disposeButton.setLocation(DISPOSE_BUTTON_LOCATION);

        this.setVisible(false);
        this.setBackground(backgroundColor);
        this.setOpaque(true);
        this.setSize(width, height);
        this.setLocation(XLocation, YLocation);
        this.setLayout(null);
        this.setBorder(BORDER);

        updateInventoryTable();

        tablePanel.add(inventoryTable);

        editingPanel.add(addItemButton);
        editingPanel.add(updateItemButton);
        editingPanel.add(deleteItemButton);
        editingPanel.add(restockButton);
        editingPanel.add(disposeButton);
        editingPanel.add(addItemPanel);
        editingPanel.add(updateItemPanel);
        editingPanel.add(deleteItemPanel);
        editingPanel.add(restockItemPanel);
        editingPanel.add(disposeItemPanel);
        editingPanel.add(submitButton);

        this.add(tablePanel);
        this.add(editingPanel);

        submitButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                if (addItemButton.isSelected()) {
                    try {
                        addItemPanel.submit();
                    } catch (SQLException ignored) {
                    }
                }

                if (deleteItemButton.isSelected() && cashieringPanel.cartIsEmpty()) {
                    try {
                        deleteItemPanel.submit();
                    } catch (SQLException ignored) {
                    }
                }

                if (disposeButton.isSelected() && cashieringPanel.cartIsEmpty()) {
                    try {
                        disposeItemPanel.submit();
                    } catch (SQLException ignored) {
                    }
                }

                if (restockButton.isSelected()) {
                    try {
                        restockItemPanel.submit();
                    } catch (SQLException ignored) {
                    }
                }

                if(updateItemButton.isSelected() && cashieringPanel.cartIsEmpty()){
                    try {
                        updateItemPanel.submit();
                    } catch (SQLException ignored) {
                    }
                }

                if(!cashieringPanel.cartIsEmpty() && !(restockButton.isSelected() || addItemButton.isSelected()))
                    Alert.alert("Can only execute query when cart is empty");

                try {
                    updateInventoryTable();
                    statisticsPanel.updateStats();
                } catch (SQLException ignored) {
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    public void updateInventoryTable() throws SQLException {
        inventoryTable.updateInventoryTable(Database.getInventoryData());
    }

    public void setCashieringPanel (CashieringPanel cashieringPanel){
        this.cashieringPanel = cashieringPanel;
    }

}
