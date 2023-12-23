package GuiComponents.Table;

import GuiComponents.Resources.StoreColors;
import StoreFunctions.CashieringFunctions;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class ButtonEditor extends DefaultCellEditor {
    private String label;
    private final JButton button;

    public ButtonEditor(JCheckBox checkBox, DefaultTableModel tableModel, JTable table, CartTable cartTable) {
        super(checkBox);
        button = new JButton();
        button.setOpaque(true);
        button.setBackground(StoreColors.MAROON);
        button.setForeground(StoreColors.TEXTFIELD_COLOR);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = table.getSelectedRow();
                int currentRowCount = table.getRowCount();

                System.out.println("Selected " + selectedRow);
                System.out.println("current row " + currentRowCount);

                selectedRow = (selectedRow == -1) ? 0 : selectedRow;
                //selectedRow = (selectedRow == -1 && currentRowCount == 1) ? 1 : selectedRow;

                String removedItemPriceString = table.getValueAt(selectedRow, 2).toString();
                removedItemPriceString = removedItemPriceString.replaceAll(",", "");

                double currentTotal = cartTable.getTotal();
                double removedItemPrice = Double.parseDouble(removedItemPriceString.substring(1));
                double newTotal = currentTotal - removedItemPrice;

//                selectedRow = (selectedRow == -1) ? 1 : selectedRow;

                tableModel.removeRow(selectedRow);
                cartTable.updateExistingItems(selectedRow);
                cartTable.setTotal(newTotal);
                cartTable.decrementHeight();

                if (currentRowCount > 1 && selectedRow == currentRowCount - 1)
                    table.setRowSelectionInterval(selectedRow - 1, selectedRow - 1);
                else if (currentRowCount > 1)
                    table.setRowSelectionInterval(selectedRow, selectedRow);
            }
        });
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        label = (value == null) ? "" : value.toString();
        button.setText(label);
        return button;
    }

    @Override
    public Object getCellEditorValue() {
        return label;
    }

    @Override
    public boolean stopCellEditing() {
        return true;
    }
}