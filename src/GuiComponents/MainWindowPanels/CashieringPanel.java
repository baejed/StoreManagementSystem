package GuiComponents.MainWindowPanels;

import GuiComponents.CustomComponents.LabelButton;
import GuiComponents.Resources.Fonts;
import GuiComponents.Resources.Images;
import GuiComponents.Resources.StoreColors;
import GuiComponents.Table.CartTable;
import GuiComponents.CustomComponents.DescriptionLabel;
import GuiComponents.CustomComponents.LabeledInputField;
import GuiComponents.CustomComponents.UIPanel;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.sql.SQLException;

import StoreFunctions.CashieringFunctions;

public class CashieringPanel extends JPanel {

    //region Static Variables
    final private static int PRODUCT_DISPLAY_WIDTH = 500;
    final private static int PRODUCT_DISPLAY_HEIGHT = 400;
    final private static int PRODUCT_DISPLAY_X = 0;
    final private static int PRODUCT_DISPLAY_Y = 0;

    final private static int COMPUTATIONS_DISPLAY_WIDTH = 500;
    final private static int COMPUTATIONS_DISPLAY_HEIGHT = 137; //150
    final private static int COMPUTATIONS_DISPLAY_X = 0;
    final private static int COMPUTATIONS_DISPLAY_Y = 400;

    final private static int LOG_DISPLAY_WIDTH = 320;
    final private static int LOG_DISPLAY_HEIGHT = 400;
    final private static int LOG_DISPLAY_X = 500;
    final private static int LOG_DISPLAY_Y = 0;

    final private static int CHECKOUT_DISPLAY_WIDTH = 320;
    final private static int CHECKOUT_DISPLAY_HEIGHT = 150;
    final private static int CHECKOUT_DISPLAY_X = 500;
    final private static int CHECKOUT_DISPLAY_Y = 400;

    final private static Point ADD_TO_CART_BUTTON_LOCATION = new Point(340, 240);
    final private static int ADD_TO_CART_BUTTON_WIDTH = 100;
    final private static int ADD_TO_CART_BUTTON_HEIGHT = 100;
    final private static ImageIcon ADD_TO_CART_BUTTON_TEXTURE = Images.ADD_TO_CART;
    final private static ImageIcon ADD_TO_CART_BUTTON_HOVER_TEXTURE = Images.ADD_TO_HOVER;

    final private static Point CHECKOUT_BUTTON_LOCATION = new Point(1, 61);
    final private static int CHECKOUT_BUTTON_WIDTH = 307;
    final private static int CHECKOUT_BUTTON_HEIGHT = 76;
    final private static ImageIcon CHECKOUT_BUTTON_TEXTURE = Images.CHECKOUT;
    final private static ImageIcon CHECKOUT_BUTTON_HOVER_TEXTURE = Images.CHECKOUT_HOVER;

    final private static Point TABLE_LOCATION = new Point(0, 0);
    final private static Dimension CART_TABLE_SIZE = new Dimension(309, 400);

    final private static Point ITEM_NAME_DL_LOCATION = new Point(30, 30);
    final private static Point PRICE_DL_LOCATION = new Point(30, 70);
    final private static Point STOCKS_DL_LOCATION = new Point(30, 110);

    final private static Point ITEM_NAME_INPUT_FIELD_LOCATION = new Point(30, 200);
    final private static Point QUANTITY_INPUT_FIELD_LOCATION = new Point(30, 255);
    final private static Point CASH_INPUT_FIELD_LOCATION = new Point(30, 310);

    final private static Dimension TOTAL_LABEL_SIZE = new Dimension(303, 60);
    final private static Point TOTAL_LABEL_LOCATION = new Point(5, 0);

    final private static Point TOTAL_DUE_DL_LOCATION = new Point(10, 0);
    final private static Point TOTAL_ITEMS_DL_LOCATION = new Point(10, 30);
    final private static Point CASH_DL_LOCATION = new Point(10, 60);
    final private static Point CHANGE_DL_LOCATION = new Point(10, 90);

    final private static int COMPUTATIONS_DL_LABEL_WIDTH = 240;
    final private static int COMPUTATIONS_DL_LABEL_X = 250; //x location of the label
    final private static int COMPUTATIONS_DL_PANEL_WIDTH = 494;

    final private static Border BORDER = BorderFactory.createMatteBorder(1, 3, 1, 1, StoreColors.DARK_MAROON);
    final private static Border CHECKOUT_BORDER = BorderFactory.createMatteBorder(1, 2, 1, 1, StoreColors.DARK_MAROON);
    final private static String[] COLUMN_NAMES = {"Item", "Quantity", "Subtotal"};
    final private static Color BACKGROUND_COLOR = StoreColors.LIGHT_MAROON;
    final private CartTable cartTable;

    // endregion
    public CashieringPanel(int width, int height, int XLocation, int YLocation, Color color, InventoryPanel inventoryPanel, StatisticsPanel statisticsPanel) {

        UIPanel productDisplay;
        UIPanel computationsDisplay;
        UIPanel logDisplay;
        UIPanel checkoutDisplay;

        DescriptionLabel itemNameDL = new DescriptionLabel("Item name", ITEM_NAME_DL_LOCATION);
        DescriptionLabel priceDL = new DescriptionLabel("Price", PRICE_DL_LOCATION);
        DescriptionLabel stocksDL = new DescriptionLabel("Stocks", STOCKS_DL_LOCATION);

        LabeledInputField itemNameInput = new LabeledInputField("Enter item name", ITEM_NAME_INPUT_FIELD_LOCATION, itemNameDL, priceDL, stocksDL, BACKGROUND_COLOR);
        LabeledInputField quantityInput = new LabeledInputField("Enter quantity of items", QUANTITY_INPUT_FIELD_LOCATION, true, BACKGROUND_COLOR);
        LabeledInputField cashInput = new LabeledInputField("Customer's cash", CASH_INPUT_FIELD_LOCATION, true, BACKGROUND_COLOR);

        LabelButton addToCartButton = new LabelButton(
                ADD_TO_CART_BUTTON_WIDTH,
                ADD_TO_CART_BUTTON_HEIGHT,
                ADD_TO_CART_BUTTON_TEXTURE,
                ADD_TO_CART_BUTTON_HOVER_TEXTURE
        );
        LabelButton checkoutButton = new LabelButton(
                CHECKOUT_BUTTON_WIDTH,
                CHECKOUT_BUTTON_HEIGHT,
                CHECKOUT_BUTTON_TEXTURE,
                CHECKOUT_BUTTON_HOVER_TEXTURE
        );

        JLabel totalLabel = new JLabel("₱0.00");

        cartTable = new CartTable(COLUMN_NAMES, CART_TABLE_SIZE, totalLabel);

        DescriptionLabel totalDueDL = new DescriptionLabel(
                "Total Due:",
                TOTAL_DUE_DL_LOCATION,
                COMPUTATIONS_DL_LABEL_X,
                COMPUTATIONS_DL_LABEL_WIDTH,
                COMPUTATIONS_DL_PANEL_WIDTH,
                false
        );
        DescriptionLabel totalItemsDL = new DescriptionLabel(
                "Quantity:",
                TOTAL_ITEMS_DL_LOCATION,
                COMPUTATIONS_DL_LABEL_X,
                COMPUTATIONS_DL_LABEL_WIDTH,
                COMPUTATIONS_DL_PANEL_WIDTH,
                false
        );
        DescriptionLabel cashDL = new DescriptionLabel(
                "Cash:",
                CASH_DL_LOCATION,
                COMPUTATIONS_DL_LABEL_X,
                COMPUTATIONS_DL_LABEL_WIDTH,
                COMPUTATIONS_DL_PANEL_WIDTH,
                false
        );
        DescriptionLabel changeDL = new DescriptionLabel(
                "Change:",
                CHANGE_DL_LOCATION,
                COMPUTATIONS_DL_LABEL_X,
                COMPUTATIONS_DL_LABEL_WIDTH,
                COMPUTATIONS_DL_PANEL_WIDTH,
                false
        );

        totalLabel.setFont(Fonts.FONT_PLAIN_LARGE);
        totalLabel.setSize(TOTAL_LABEL_SIZE);
        totalLabel.setLocation(TOTAL_LABEL_LOCATION);
        totalLabel.setForeground(StoreColors.MAROON);

        cartTable.setLocation(TABLE_LOCATION);
        cartTable.setOpaque(true);
        cartTable.setBackground(StoreColors.TEXTFIELD_COLOR);

        addToCartButton.setLocation(ADD_TO_CART_BUTTON_LOCATION);

        checkoutButton.setLocation(CHECKOUT_BUTTON_LOCATION);

        this.setVisible(true);
        this.setBackground(color);
        this.setOpaque(true);
        this.setSize(width, height);
        this.setLocation(XLocation, YLocation);
        this.setLayout(null);

        productDisplay = new UIPanel(
                PRODUCT_DISPLAY_WIDTH,
                PRODUCT_DISPLAY_HEIGHT,
                PRODUCT_DISPLAY_X,
                PRODUCT_DISPLAY_Y,
                BACKGROUND_COLOR
        );
        computationsDisplay = new UIPanel(
                COMPUTATIONS_DISPLAY_WIDTH,
                COMPUTATIONS_DISPLAY_HEIGHT,
                COMPUTATIONS_DISPLAY_X,
                COMPUTATIONS_DISPLAY_Y,
                Color.WHITE
        );
        logDisplay = new UIPanel(
                LOG_DISPLAY_WIDTH,
                LOG_DISPLAY_HEIGHT,
                LOG_DISPLAY_X,
                LOG_DISPLAY_Y,
                Color.WHITE
        );
        checkoutDisplay = new UIPanel(
                CHECKOUT_DISPLAY_WIDTH,
                CHECKOUT_DISPLAY_HEIGHT,
                CHECKOUT_DISPLAY_X,
                CHECKOUT_DISPLAY_Y,
                StoreColors.TEXTFIELD_COLOR
        );

        productDisplay.setOpaque(true);
        checkoutDisplay.setOpaque(true);

        productDisplay.setBorder(BORDER);
        computationsDisplay.setBorder(BORDER);
        logDisplay.setBorder(BORDER);
        checkoutDisplay.setBorder(CHECKOUT_BORDER);

        productDisplay.add(itemNameDL);
        productDisplay.add(stocksDL);
        productDisplay.add(priceDL);
        productDisplay.add(itemNameInput);
        productDisplay.add(quantityInput);
        productDisplay.add(cashInput);
        productDisplay.add(addToCartButton);

        logDisplay.add(cartTable);

        checkoutDisplay.add(totalLabel);
        checkoutDisplay.add(checkoutButton);

        computationsDisplay.add(totalDueDL);
        computationsDisplay.add(totalItemsDL);
        computationsDisplay.add(cashDL);
        computationsDisplay.add(changeDL);

        computationsDisplay.setBackground(StoreColors.TEXTFIELD_COLOR);
        computationsDisplay.setOpaque(true);

        this.add(productDisplay);
        this.add(computationsDisplay);
        this.add(logDisplay);
        this.add(checkoutDisplay);

        itemNameInput.getTextField().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    boolean added = CashieringFunctions.addToCart(itemNameInput, quantityInput, cartTable, totalLabel);
                    if (added) clear(itemNameDL, priceDL, stocksDL);
                }
            }
        });

        quantityInput.getTextField().addKeyListener(new KeyListener() {
            @Override
            public void keyTyped(KeyEvent e) {

            }

            @Override
            public void keyPressed(KeyEvent e) {

            }

            @Override
            public void keyReleased(KeyEvent e) {
                if (e.getKeyChar() == KeyEvent.VK_ENTER) {
                    boolean added = CashieringFunctions.addToCart(itemNameInput, quantityInput, cartTable, totalLabel);
                    if (added) clear(itemNameDL, priceDL, stocksDL);
                }
            }
        });

        checkoutButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                try {
                    CashieringFunctions.checkout(cashInput, cartTable, totalDueDL, totalItemsDL, cashDL, changeDL);
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }

                clear(itemNameDL, priceDL, stocksDL);

                try {
                    inventoryPanel.updateInventoryTable();
                    statisticsPanel.updateStats();
                } catch (SQLException ex) {
                    throw new RuntimeException(ex);
                }
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

        addToCartButton.addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {

            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {
                boolean added = CashieringFunctions.addToCart(itemNameInput, quantityInput, cartTable, totalLabel);
                if (added) clear(itemNameDL, priceDL, stocksDL);
            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });

    }

    private void clear(DescriptionLabel descriptionLabel1, DescriptionLabel descriptionLabel2, DescriptionLabel descriptionLabel3) {
        descriptionLabel1.clearText();
        descriptionLabel2.clearText();
        descriptionLabel3.clearText();
    }

    public boolean cartIsEmpty(){
        return this.cartTable.isEmpty();
    }

}
