package GuiComponents.MainWindowPanels;

import Database.Database;
import GuiComponents.CustomComponents.DescriptionLabel;
import GuiComponents.Resources.StoreColors;
import StoreFunctions.CashieringFunctions;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.sql.SQLException;

public class StatisticsPanel extends JPanel {

    final private static Border BORDER = BorderFactory.createMatteBorder(0, 3, 0, 0, StoreColors.DARK_MAROON);

    final private static Point REVENUE_DL_LOCATION = new Point(10, 10);
    final private static Point COST_OF_GOODS_SOLD_DL_LOCATION = new Point(10, 50);
    final private static Point GROSS_INCOME_DL_LOCATION = new Point(10, 90);
    final private static Point TAXES_DL_LOCATION = new Point(10, 130);
    final private static Point NET_INCOME_DL_LOCATION = new Point(10, 170);

    final private static Point NUM_OF_ITEMS_SOLD_DL_LOCATION = new Point(10, 250);
    final private static Point MOST_SOLD_ITEM_DL_LOCATION = new Point(10, 290);
    final private static Point MOST_EXPENSIVE_ITEM_DL_LOCATION = new Point(10, 330);
    final private static Point CHEAPEST_ITEM_DL_LOCATION = new Point(10, 370);

    final private static int STATS_DL_LABEL_WIDTH = 500;
    final private static int STATS_DL_LABEL_X = 250; //x location of the label
    final private static int STATS_DL_PANEL_WIDTH = 780;
    final private static int STATS_DL_LABEL_NAME_WIDTH = 240;

    final private DescriptionLabel revenueDL;
    final private DescriptionLabel costOfGoodsSoldDL;
    final private DescriptionLabel grossIncomeDL;
    final private DescriptionLabel taxesDL;
    final private DescriptionLabel netIncomeDL;
    final private DescriptionLabel numOfItemsSoldDL;
    final private DescriptionLabel mostSoldItemDL;
    final private DescriptionLabel mostExpensiveItemDL;
    final private DescriptionLabel cheapestItemDL;


    public StatisticsPanel(int width, int height, int XLocation, int YLocation, Color color) throws SQLException {

        revenueDL = new DescriptionLabel("Revenue:",
                REVENUE_DL_LOCATION,
                STATS_DL_LABEL_X,
                STATS_DL_LABEL_WIDTH,
                STATS_DL_PANEL_WIDTH,
                STATS_DL_LABEL_NAME_WIDTH,
                false
        );

        costOfGoodsSoldDL = new DescriptionLabel(
                "Cost of Goods Sold:",
                COST_OF_GOODS_SOLD_DL_LOCATION,
                STATS_DL_LABEL_X,
                STATS_DL_LABEL_WIDTH,
                STATS_DL_PANEL_WIDTH,
                STATS_DL_LABEL_NAME_WIDTH,
                false
        );

        grossIncomeDL = new DescriptionLabel(
                "Gross Income:",
                GROSS_INCOME_DL_LOCATION,
                STATS_DL_LABEL_X,
                STATS_DL_LABEL_WIDTH,
                STATS_DL_PANEL_WIDTH,
                STATS_DL_LABEL_NAME_WIDTH,
                false
        );

        taxesDL = new DescriptionLabel(
                "Taxes (12%)",
                TAXES_DL_LOCATION,
                STATS_DL_LABEL_X,
                STATS_DL_LABEL_WIDTH,
                STATS_DL_PANEL_WIDTH,
                STATS_DL_LABEL_NAME_WIDTH,
                false
        );

        netIncomeDL = new DescriptionLabel(
                "Net Income:",
                NET_INCOME_DL_LOCATION,
                STATS_DL_LABEL_X,
                STATS_DL_LABEL_WIDTH,
                STATS_DL_PANEL_WIDTH,
                STATS_DL_LABEL_NAME_WIDTH,
                false
        );

        numOfItemsSoldDL = new DescriptionLabel(
                "Number Of Items Sold:",
                NUM_OF_ITEMS_SOLD_DL_LOCATION,
                STATS_DL_LABEL_X,
                STATS_DL_LABEL_WIDTH,
                STATS_DL_PANEL_WIDTH,
                STATS_DL_LABEL_NAME_WIDTH,
                false
        );

        mostSoldItemDL = new DescriptionLabel(
                "Most Sold Item:",
                MOST_SOLD_ITEM_DL_LOCATION,
                STATS_DL_LABEL_X,
                STATS_DL_LABEL_WIDTH,
                STATS_DL_PANEL_WIDTH,
                STATS_DL_LABEL_NAME_WIDTH,
                false
        );

        mostExpensiveItemDL = new DescriptionLabel(
                "Most Expensive Item:",
                MOST_EXPENSIVE_ITEM_DL_LOCATION,
                STATS_DL_LABEL_X,
                STATS_DL_LABEL_WIDTH,
                STATS_DL_PANEL_WIDTH,
                STATS_DL_LABEL_NAME_WIDTH,
                false
        );

        cheapestItemDL = new DescriptionLabel(
                "Cheapest Item:",
                CHEAPEST_ITEM_DL_LOCATION,
                STATS_DL_LABEL_X,
                STATS_DL_LABEL_WIDTH,
                STATS_DL_PANEL_WIDTH,
                STATS_DL_LABEL_NAME_WIDTH,
                false
        );

        updateStats();

        this.setVisible(false);
        this.setBackground(color);
        this.setOpaque(true);
        this.setSize(width, height);
        this.setLocation(XLocation, YLocation);
        this.setLayout(null);
        this.setBorder(BORDER);

        this.add(revenueDL);
        this.add(costOfGoodsSoldDL);
        this.add(grossIncomeDL);
        this.add(taxesDL);
        this.add(netIncomeDL);
        this.add(numOfItemsSoldDL);
        this.add(mostExpensiveItemDL);
        this.add(mostSoldItemDL);
        this.add(cheapestItemDL);

    }

    public void updateStats() throws SQLException {
        revenueDL.setText("₱" + CashieringFunctions.addCommas(Database.getRevenue()));
        costOfGoodsSoldDL.setText("₱" + CashieringFunctions.addCommas(Database.getCostOfGoodsSold()));
        grossIncomeDL.setText("₱" + CashieringFunctions.addCommas(Database.getGrossIncome()));
        taxesDL.setText("₱" + CashieringFunctions.addCommas(Database.getTaxDeduction()));
        netIncomeDL.setText("₱" + CashieringFunctions.addCommas(Database.getNetIncome()));

        numOfItemsSoldDL.setText(CashieringFunctions.addCommas(Database.getTotalNumOfItemsSold()));
        mostSoldItemDL.setText(Database.getMostSoldItem());
        mostExpensiveItemDL.setText(Database.getMostExpensiveItem());
        cheapestItemDL.setText(Database.getCheapestItem());
    }

}
