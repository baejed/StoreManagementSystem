package GuiComponents.Table;

import GuiComponents.Resources.Alert;
import GuiComponents.Resources.StoreColors;
import StoreObjects.Item;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import StoreFunctions.CashieringFunctions;

public class CartTable extends JPanel {
    final private HashMap<String, Integer> existingItems = new HashMap<>(); // the value is the row location of the item
    final private DefaultTableModel tableModel = new DefaultTableModel();
    final private static int SCROLL_PANE_BASE_HEIGHT = 22;
    private double total = 0;
    final private JTable table;
    final private JLabel totalDisplay;
    final private JScrollPane scrollPane;
    final private static Border HEADER_BORDER = BorderFactory.createMatteBorder(2, 2, 2, 2, StoreColors.DARK_MAROON);
    final private static Border TABLE_BORDER = BorderFactory.createMatteBorder(1, 1, 1, 1, StoreColors.DARK_MAROON);
    final private static Border PANEL_BORDER = BorderFactory.createMatteBorder(0, 2, 1, 0, StoreColors.DARK_MAROON);

    public CartTable(String[] columnNames, Dimension tableSize, JLabel totalDisplay) {
        ButtonRenderer button = new ButtonRenderer();
        JCheckBox checkBox = new JCheckBox();
        this.table = new JTable(tableModel);
        ButtonEditor buttonEditor = new ButtonEditor(checkBox, tableModel, table, this);
        JTableHeader tableHeader = table.getTableHeader();

        this.totalDisplay = totalDisplay;

        for (String name : columnNames) {
            tableModel.addColumn(name);
        }

        for (int c = 0; c < table.getColumnCount(); c++) {
            Class<?> col_class = table.getColumnClass(c);
            table.setDefaultEditor(col_class, null);        // remove editor
        }

        table.setBackground(StoreColors.TEXTFIELD_COLOR);
        table.setForeground(StoreColors.MAROON);
        table.setBorder(TABLE_BORDER);

        tableHeader.setBackground(StoreColors.TEXTFIELD_COLOR);
        tableHeader.setForeground(StoreColors.MAROON);
        tableHeader.setBorder(HEADER_BORDER);

        tableModel.addColumn("Action");

        table.getColumn("Action").setCellRenderer(button);
        table.getColumn("Action").setCellEditor(buttonEditor);
        table.setColumnSelectionAllowed(false);
        table.getTableHeader().setReorderingAllowed(false);

        scrollPane = new JScrollPane(table);
        scrollPane.setSize(tableSize.width, SCROLL_PANE_BASE_HEIGHT);
        scrollPane.setBorder(TABLE_BORDER);

        this.setSize(tableSize);
        this.setBorder(PANEL_BORDER);
        this.setOpaque(true);
        this.setLayout(null);
        this.add(scrollPane);
    }

    public void addData(Item item, int quantity) {
        int tableRow = tableModel.getRowCount();
        int currentItemStocks = item.getStocks();
        String itemName = item.getItemName();
        double itemPrice = item.getPrice();

        quantity = Math.min(quantity, item.getStocks()); // ensures that the order quantity won't surpass the current stocks

        /*
            Can detect if the item already exists.
            Updates on top of the current values on the table if it already exists in the cart table
        */

        if (!existingItems.containsKey(itemName) && currentItemStocks > 0) {
            double subtotal = quantity * itemPrice;
            total += subtotal;
            String[] data = {itemName, "" + quantity, "₱" + CashieringFunctions.addCommas(subtotal)};

            tableModel.insertRow(tableRow, data);
            tableModel.setValueAt("Delete", tableRow, 3);
            existingItems.put(itemName, tableRow);
        } else if (currentItemStocks > 0) {
            /*
                qty = quantity
                column 0 = Item name
                colum 1 = Quantity
                column 2 = Subtotal
            */

            int rowLocation = existingItems.get(itemName); // returns the row location of the existing item
            int currentQty = getItemQuantity(itemName);
            int newQty = Math.min((currentQty + quantity), currentItemStocks); // ensures that the order quantity won't surpass the current stocks
            double newSubtotal = newQty * itemPrice;
            String newSubtotalString = "₱" + CashieringFunctions.addCommas(newSubtotal);

            total -= currentQty * itemPrice;
            total += newSubtotal;

            tableModel.setValueAt(newQty, rowLocation, 1);
            tableModel.setValueAt(newSubtotalString, rowLocation, 2);
            decrementHeight();
        } else {
            Alert.alert("Item out of stock");
            decrementHeight();
        }

    }

    public void updateExistingItems(int removedRow) {
        List<String> keys = new ArrayList<>(existingItems.keySet());
        for (String key : keys) {
            int currentRowLocation = existingItems.get(key);
            if (currentRowLocation == removedRow) existingItems.remove(key);
            if (currentRowLocation > removedRow) {
                existingItems.replace(key, currentRowLocation - 1);
            }
        }
    }

    public HashMap<String, Integer> getCartContents() {
        HashMap<String, Integer> cartContents = new HashMap<>();
        for (String key : existingItems.keySet()) {
            int rowLocation = existingItems.get(key);
            int itemQuantity = Integer.parseInt(table.getValueAt(rowLocation, 1).toString());
            cartContents.put(key, itemQuantity);
        }
        return cartContents;
    }

    public void setTotal(double total) {
        this.total = total;
        this.totalDisplay.setText("₱" + CashieringFunctions.addCommas(total));
    }

    public double getTotal() {
        return this.total;
    }

    public int getTotalQuantity() {
        int numOfRows = table.getRowCount();
        int totalQuantity = 0;

        for (int i = 0; i < numOfRows; i++) {
            String quantity = table.getValueAt(i, 1).toString();
            totalQuantity += Integer.parseInt(quantity);
        }

        return totalQuantity;
    }

    public void clear() {
        int rowCount = table.getRowCount();
        this.total = 0;
        for (int i = 0; i < rowCount; i++) {
            tableModel.removeRow(0);
            decrementHeight();
        }
        existingItems.clear();
    }

    private int getItemQuantity(String itemName) {
        int rowLocation = existingItems.get(itemName);
        String itemQuantity = tableModel.getValueAt(rowLocation, 1).toString(); //quantity is located at column 2

        return Integer.parseInt(itemQuantity);
    }

    public void incrementHeight(){
        int heightIncrement = 16;
        if(table.getRowCount() > 23) return;
        scrollPane.setSize(scrollPane.getWidth(), scrollPane.getHeight() + heightIncrement);
    }

    public void decrementHeight(){
        int heightDecrement = 16;
        if(table.getRowCount() >= 23) return;
        scrollPane.setSize(scrollPane.getWidth(), scrollPane.getHeight() - heightDecrement);
    }

    public boolean isEmpty(){
        return table.getRowCount() == 0;
    }

}
