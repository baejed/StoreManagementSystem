package GuiComponents.Table;

import Database.Database;
import GuiComponents.Resources.StoreColors;
import StoreFunctions.CashieringFunctions;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.JTableHeader;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class InventoryTable extends JPanel {

    final private static int SCROLL_PANE_WIDTH = 500;
    final private static int SCROLL_PANE_BASE_HEIGHT = 22;

    final private static String[] COLUMN_NAMES = {"Item name", "Price", "Stocks", "Cost to buy", "Items Sold"};
    final private DefaultTableModel tableModel = new DefaultTableModel();
    final private JTable table;
    final private static Border HEADER_BORDER = BorderFactory.createMatteBorder(2, 1, 2, 0, StoreColors.DARK_MAROON);
    final private static Border TABLE_BORDER = BorderFactory.createMatteBorder(1, 0, 1, 1, StoreColors.DARK_MAROON);
    final private static Border PANEL_BORDER = BorderFactory.createMatteBorder(0, 2, 1, 0, StoreColors.DARK_MAROON);
    final private JScrollPane scrollPane;

    public InventoryTable(Dimension tableSize) {


        table = new JTable(tableModel);
        table.getTableHeader().setReorderingAllowed(false);

        JTableHeader tableHeader = table.getTableHeader();

        for (String name : COLUMN_NAMES) {
            tableModel.addColumn(name);
        }

        for (int c = 0; c < table.getColumnCount(); c++) {
            Class<?> col_class = table.getColumnClass(c);
            table.setDefaultEditor(col_class, null);        // remove editor
        }

        tableHeader.setBorder(HEADER_BORDER);
        tableHeader.setBackground(StoreColors.TEXTFIELD_COLOR);
        tableHeader.setForeground(StoreColors.MAROON);

        table.setBorder(TABLE_BORDER);
        table.setBackground(StoreColors.TEXTFIELD_COLOR);
        table.setForeground(StoreColors.MAROON);

        scrollPane = new JScrollPane(table);
        scrollPane.setSize(SCROLL_PANE_WIDTH, SCROLL_PANE_BASE_HEIGHT);
        scrollPane.setBorder(TABLE_BORDER);

        this.setSize(tableSize);

        this.setLayout(null);
        this.add(scrollPane);

    }

    public void updateInventoryTable(ResultSet resultSet) throws SQLException {
        clearTable();
        while (resultSet.next()) {
            int currentRow = table.getRowCount();

            String itemName = resultSet.getString("item_name");
            double price = resultSet.getDouble("price");
            int stocks = resultSet.getInt("stocks");
            double costToBuy = resultSet.getDouble("cost_to_buy");
            int itemsSold = resultSet.getInt("items_sold");

            String[] data = {
                    itemName,
                    "₱" + CashieringFunctions.addCommas(price),
                    CashieringFunctions.addCommas(stocks),
                    "₱" + CashieringFunctions.addCommas(costToBuy),
                    CashieringFunctions.addCommas(itemsSold)
            };

            tableModel.insertRow(currentRow, data);
            incrementHeight();
        }
    }

    public void clearTable(){
        int rowCount = table.getRowCount();
        for(int i = 0; i < rowCount; i++){
            tableModel.removeRow(0);
            decrementHeight();
        }
    }

    public void incrementHeight(){
        int heightIncrement = 16;
        if(table.getRowCount() > 32) return;
        scrollPane.setSize(scrollPane.getWidth(), scrollPane.getHeight() + heightIncrement);
    }

    public void decrementHeight(){
        int heightDecrement = 16;
        if(table.getRowCount() >= 32) return;
        scrollPane.setSize(scrollPane.getWidth(), scrollPane.getHeight() - heightDecrement);
    }

}
