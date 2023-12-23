package GuiComponents;

import GuiComponents.CustomComponents.LabelButton;
import GuiComponents.CustomComponents.UIPanel;
import GuiComponents.MainWindowPanels.CashieringPanel;
import GuiComponents.MainWindowPanels.InventoryPanel;
import GuiComponents.MainWindowPanels.StatisticsPanel;
import GuiComponents.Resources.Images;
import GuiComponents.Resources.StoreColors;

import javax.swing.*;
import java.awt.*;
import java.sql.SQLException;
import java.util.HashMap;

public class MainWindow extends JFrame {

    //region Static Variables
    final private static int WINDOW_WIDTH = 1024;
    final private static int WINDOW_HEIGHT = 576;

    final private static int SECTION_SELECTOR_PANEL_WIDTH = 200;
    final private static int SECTION_SELECTOR_PANEL_HEIGHT = 550;
    final private static int SECTION_SELECTOR_PANEL_X = 0;
    final private static int SECTION_SELECTOR_PANEL_Y = 0;

    final private static int SECTION_PANEL_WIDTH = 820;
    final private static int SECTION_PANEL_HEIGHT = 550;
    final private static int SECTION_PANEL_X = 200;
    final private static int SECTION_PANEL_Y = 0;

    final private static ImageIcon ICON = Images.APP_ICON;

    final private static int SECTION_BUTTON_WIDTH = 200;
    final private static int SECTION_BUTTON_HEIGHT = 60;

    final private static Point CASHIER_SECTION_BUTTON_LOCATION = new Point(0, 120);
    final private static ImageIcon CASHIER_SECTION_BUTTON_IMAGE = Images.CASHIERING;
    final private static ImageIcon CASHIER_SECTION_BUTTON_HOVER_IMAGE = Images.CASHIERING_HOVER;
    final private static ImageIcon CASHIER_SECTION_BUTTON_SELECTED_IMAGE = Images.CASHIERING_SELECTED;

    final private static Point INVENTORY_SECTION_BUTTON_LOCATION = new Point(0, 185);
    final private static ImageIcon INVENTORY_SECTION_BUTTON_IMAGE = Images.INVENTORY;
    final private static ImageIcon INVENTORY_SECTION_BUTTON_HOVER_IMAGE = Images.INVENTORY_HOVER;
    final private static ImageIcon INVENTORY_SECTION_BUTTON_SELECTED_IMAGE = Images.INVENTORY_SELECTED;

    final private static Point STATISTICS_SECTION_BUTTON_LOCATION = new Point(0, 250);
    final private static ImageIcon STATISTICS_SECTION_BUTTON_IMAGE = Images.STATISTICS;
    final private static ImageIcon STATISTICS_SECTION_BUTTON_HOVER_IMAGE = Images.STATISTICS_HOVER;
    final private static ImageIcon STATISTICS_SECTION_BUTTON_SELECTED_IMAGE = Images.STATISTICS_SELECTED;

    final private static Dimension SECTIONS_HEADER_SIZE = new Dimension(200, 100);
    final private static ImageIcon SECTIONS_HEADER_LOGO = Images.SECTIONS_HEADER_LOGO;
    final private static Point SECTIONS_LOCATION = new Point(0, 10);

    final private static HashMap<LabelButton, JPanel> associateButtonAndPanels = new HashMap<>();
    //endregion

    public MainWindow(boolean isVisible) throws SQLException {

        UIPanel sectionSelectorPanel = new UIPanel(
                SECTION_SELECTOR_PANEL_WIDTH,
                SECTION_SELECTOR_PANEL_HEIGHT,
                SECTION_SELECTOR_PANEL_X,
                SECTION_SELECTOR_PANEL_Y,
                StoreColors.BACKGROUND_COLOR
        );

        StatisticsPanel statisticsPanel = new StatisticsPanel(
                SECTION_PANEL_WIDTH,
                SECTION_PANEL_HEIGHT,
                SECTION_PANEL_X,
                SECTION_PANEL_Y,
                StoreColors.TEXTFIELD_COLOR
        );
        InventoryPanel inventoryPanel = new InventoryPanel(
                SECTION_PANEL_WIDTH,
                SECTION_PANEL_HEIGHT,
                SECTION_PANEL_X,
                SECTION_PANEL_Y,
                StoreColors.BACKGROUND_COLOR,
                statisticsPanel
        );
        CashieringPanel cashieringPanel = new CashieringPanel(
                SECTION_PANEL_WIDTH,
                SECTION_PANEL_HEIGHT,
                SECTION_PANEL_X,
                SECTION_PANEL_Y,
                StoreColors.BACKGROUND_COLOR,
                inventoryPanel,
                statisticsPanel
        );

        LabelButton cashierSectionButton = new LabelButton(
                SECTION_BUTTON_WIDTH,
                SECTION_BUTTON_HEIGHT,
                CASHIER_SECTION_BUTTON_IMAGE,
                CASHIER_SECTION_BUTTON_HOVER_IMAGE,
                CASHIER_SECTION_BUTTON_SELECTED_IMAGE,
                associateButtonAndPanels
        );
        LabelButton inventorySectionButton = new LabelButton(
                SECTION_BUTTON_WIDTH, SECTION_BUTTON_HEIGHT,
                INVENTORY_SECTION_BUTTON_IMAGE,
                INVENTORY_SECTION_BUTTON_HOVER_IMAGE,
                INVENTORY_SECTION_BUTTON_SELECTED_IMAGE,
                associateButtonAndPanels
        );
        LabelButton statisticsSectionButton = new LabelButton(
                SECTION_BUTTON_WIDTH,
                SECTION_BUTTON_HEIGHT,
                STATISTICS_SECTION_BUTTON_IMAGE,
                STATISTICS_SECTION_BUTTON_HOVER_IMAGE,
                STATISTICS_SECTION_BUTTON_SELECTED_IMAGE,
                associateButtonAndPanels
        );

        JLabel sectionsHeader = new JLabel();

        cashierSectionButton.select();
        cashieringPanel.setVisible(true);

        inventoryPanel.setCashieringPanel(cashieringPanel);

        associateButtonAndPanels.put(cashierSectionButton, cashieringPanel);
        associateButtonAndPanels.put(inventorySectionButton, inventoryPanel);
        associateButtonAndPanels.put(statisticsSectionButton, statisticsPanel);

        sectionsHeader.setSize(SECTIONS_HEADER_SIZE);
        sectionsHeader.setLocation(SECTIONS_LOCATION);
        sectionsHeader.setIcon(SECTIONS_HEADER_LOGO);

        this.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setLayout(null);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setTitle("Tindahan");
        this.setIconImage(ICON.getImage());
        this.setResizable(false);

        cashierSectionButton.setLocation(CASHIER_SECTION_BUTTON_LOCATION);
        inventorySectionButton.setLocation(INVENTORY_SECTION_BUTTON_LOCATION);
        statisticsSectionButton.setLocation(STATISTICS_SECTION_BUTTON_LOCATION);

        sectionSelectorPanel.setBackground(StoreColors.BACKGROUND_COLOR);
        sectionSelectorPanel.setOpaque(true);

        this.add(sectionSelectorPanel);
        this.add(cashieringPanel);
        this.add(inventoryPanel);
        this.add(statisticsPanel);

        sectionSelectorPanel.add(sectionsHeader);
        sectionSelectorPanel.add(cashierSectionButton);
        sectionSelectorPanel.add(inventorySectionButton);
        sectionSelectorPanel.add(statisticsSectionButton);

        this.setVisible(isVisible);
    }

}
