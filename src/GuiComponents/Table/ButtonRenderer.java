package GuiComponents.Table;

import GuiComponents.Resources.StoreColors;

import javax.swing.*;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class ButtonRenderer extends JButton implements TableCellRenderer {

    public ButtonRenderer() {
        this.setOpaque(true);
        this.setBackground(StoreColors.MAROON);
        this.setForeground(StoreColors.TEXTFIELD_COLOR);
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        setText((value == null) ? "" : value.toString());
        return this;
    }
}
