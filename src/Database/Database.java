package Database;

import StoreFunctions.CashieringFunctions;
import StoreObjects.Item;

import java.sql.*;
import java.util.HashMap;

public class Database {
    final private static String DRIVER_NAME = "org.mariadb.jdbc.Driver";
    final private static String DATABASE_URL = "jdbc:mariadb://localhost:3306/store_manager?user=root";
    final private static Connection CONNECTION;
    private static Statement STATEMENT;
    final private static double INTEREST_RATE = 0.12;

    static {
        try {
            Class.forName(DRIVER_NAME);
            CONNECTION = DriverManager.getConnection(DATABASE_URL);
            STATEMENT = CONNECTION.createStatement();
            System.out.println("Connection established");
        } catch (SQLException | ClassNotFoundException e) {
            System.out.println("Connection error");
            throw new RuntimeException(e);
        }
    }

    public static boolean itemExists(String itemName) throws SQLException {
        String sqlQuery = "SELECT item_name FROM inventory where item_name = '" + itemName + "'";
        ResultSet resultSet = STATEMENT.executeQuery(sqlQuery);

        return resultSet.next();
    }

    public static Item getItemDetails(String itemName) throws SQLException {
        String sqlQuery = "SELECT * FROM inventory WHERE item_name = '" + itemName + "'";
        ResultSet resultSet = STATEMENT.executeQuery(sqlQuery);
        Item item = null;

        if (resultSet.next()) {
            String name = resultSet.getString("item_name");
            int stocks = resultSet.getInt("stocks");
            double price = resultSet.getDouble("price");
            double costToBuy = resultSet.getDouble("cost_to_buy");
            int itemsSold = resultSet.getInt("items_sold");

            item = new Item(name, stocks, price, costToBuy, itemsSold);
        }

        return item;
    }

    public static boolean verifyLogin(String username, String passwordHash) throws SQLException {
        String sqlQuery = "SELECT password FROM admin_login WHERE username = '" + username + "'";
        ResultSet resultSet = STATEMENT.executeQuery(sqlQuery);
        String dbPassHash = "";

        if (resultSet.next()) dbPassHash = resultSet.getString("password");
        return dbPassHash.equals(passwordHash);
    }

    public static void checkoutCart(HashMap<String, Integer> cartContents) throws SQLException {
        for (String key : cartContents.keySet()) {
            Item item = getItemDetails(key);
            int stockSPurchased = cartContents.get(key);
            int stocksLeft = item.getStocks() - stockSPurchased;
            int itemsSold = item.getItemsSold();
            int updatedItemsSold = itemsSold + stockSPurchased;
            String sqlQuery = "UPDATE inventory SET stocks = " + stocksLeft + ", items_sold = '" + updatedItemsSold + "' WHERE item_name = '" + key + "'";
            STATEMENT.executeQuery(sqlQuery);
        }
    }

    public static ResultSet getInventoryData() throws SQLException {
        String sqlQuery = "SELECT * FROM inventory";
        return STATEMENT.executeQuery(sqlQuery);
    }

    public static void addItem(String itemName, double itemPrice, double costPerItem) throws SQLException {
        String sqlQuery = "INSERT INTO inventory VALUES ('" + itemName + "'," + itemPrice + ", 0, " + costPerItem + ", 0)";
        STATEMENT.executeQuery(sqlQuery);
    }

    public static void deleteItem(String itemName) throws SQLException {
        String sqlQuery = "DELETE FROM inventory WHERE item_name = '" + itemName + "'";
        STATEMENT.executeQuery(sqlQuery);
    }

    public static void disposeItem(String itemName, int amountToDispose) throws SQLException {
        Item item = Database.getItemDetails(itemName);
        int currentStocks = item.getStocks();
        int newStocks = currentStocks - amountToDispose;
        newStocks = Math.max(newStocks, 0);

        String sqlQuery = "UPDATE inventory SET stocks = " + newStocks + " WHERE item_name = '" + itemName + "'";
        STATEMENT.executeQuery(sqlQuery);
    }

    public static void restockItem(String itemName, int amountToRestock) throws SQLException {
        Item item = Database.getItemDetails(itemName);
        int currentStocks = item.getStocks();
        int newStocks = currentStocks + amountToRestock;

        String sqlQuery = "UPDATE inventory SET stocks = " + newStocks + " WHERE item_name = '" + itemName + "'";
        STATEMENT.executeQuery(sqlQuery);
    }

    public static void updateItem(String itemName, String newItemName, double newPrice, double newCostPerItem) throws SQLException {
        String sqlQuery = "UPDATE inventory SET item_name = '" + newItemName + "', price = " + newPrice + ", cost_to_buy = " + newCostPerItem + " WHERE item_name = '" + itemName + "'";
        STATEMENT.executeQuery(sqlQuery);
    }

    public static double getRevenue() throws SQLException {
        String sqlQuery = "SELECT price, items_sold FROM inventory";
        ResultSet resultSet = STATEMENT.executeQuery(sqlQuery);
        double revenue = 0;

        while (resultSet.next()) {
            double itemPrice = resultSet.getDouble("price");
            double itemsSold = resultSet.getDouble("items_sold");

            revenue += (itemPrice * itemsSold);
        }

        return revenue;
    }

    public static double getCostOfGoodsSold() throws SQLException {
        String sqlQuery = "SELECT cost_to_buy, items_sold FROM inventory";
        ResultSet resultSet = STATEMENT.executeQuery(sqlQuery);
        double costOfGoodsSold = 0;

        while (resultSet.next()) {
            double itemCostToBuy = resultSet.getDouble("cost_to_buy");
            double itemsSold = resultSet.getDouble("items_sold");

            costOfGoodsSold += itemCostToBuy * itemsSold;
        }

        return costOfGoodsSold;
    }

    public static double getGrossIncome() throws SQLException {
        double revenue = getRevenue();
        double costOfGoodsSold = getCostOfGoodsSold();

        return revenue - costOfGoodsSold;
    }

    public static double getTaxDeduction() throws SQLException {
        double taxDeduction = getGrossIncome() * INTEREST_RATE;

        if(taxDeduction < 0) return 0;

        return taxDeduction;
    }

    public static double getNetIncome() throws SQLException {
        return getGrossIncome() - getTaxDeduction();
    }

    public static int getTotalNumOfItemsSold() throws SQLException {
        String sqlQuery = "SELECT items_sold FROM inventory";
        ResultSet resultSet = STATEMENT.executeQuery(sqlQuery);
        int totalNumOfItemsSold = 0;

        while (resultSet.next()) {
            totalNumOfItemsSold += resultSet.getInt("items_sold");
        }

        return totalNumOfItemsSold;
    }

    public static String getMostSoldItem() throws SQLException {
        String sqlQuery = "SELECT item_name, items_sold FROM inventory";
        ResultSet resultSet = STATEMENT.executeQuery(sqlQuery);
        StringBuilder output = new StringBuilder();
        int maxItemsSold = 0;

        while (resultSet.next()) {
            String currentItem = resultSet.getString("item_name");
            int itemsSold = resultSet.getInt("items_sold");

            if (itemsSold > maxItemsSold) {
                maxItemsSold = itemsSold;
                output.delete(0, output.length());
                output.append(currentItem);
            } else if (itemsSold == maxItemsSold && itemsSold != 0) {
                output.append(", " + currentItem);
            }
        }

        return output + ". (" + CashieringFunctions.addCommas(maxItemsSold) + " items)";
    }

    public static String getMostExpensiveItem() throws SQLException {
        String sqlQuery = "SELECT item_name, price FROM inventory";
        ResultSet resultSet = STATEMENT.executeQuery(sqlQuery);
        StringBuilder mostExpensiveItem = new StringBuilder();
        double highestPrice = 0;

        while (resultSet.next()) {
            String currentItem = resultSet.getString("item_name");
            double currentPrice = resultSet.getDouble("price");

            if (currentPrice > highestPrice) {
                mostExpensiveItem.delete(0, mostExpensiveItem.length());
                mostExpensiveItem.append(currentItem);
                highestPrice = currentPrice;
            } else if (currentPrice == highestPrice) {
                mostExpensiveItem.append(", " + currentItem);
            }
        }

        return mostExpensiveItem + ". (₱" + CashieringFunctions.addCommas(highestPrice) + ")";
    }

    public static String getCheapestItem() throws SQLException {
        String sqlQuery = "SELECT item_name, price FROM inventory";
        ResultSet resultSet = STATEMENT.executeQuery(sqlQuery);
        StringBuilder cheapestItem = new StringBuilder();
        double lowestPrice = Integer.MAX_VALUE;

        while (resultSet.next()) {
            String currentItem = resultSet.getString("item_name");
            double currentPrice = resultSet.getDouble("price");

            if (currentPrice < lowestPrice) {
                cheapestItem.delete(0, cheapestItem.length());
                cheapestItem.append(currentItem);
                lowestPrice = currentPrice;
            } else if (currentPrice == lowestPrice) {
                cheapestItem.append(", " + currentItem);
            }
        }

        return cheapestItem + ". (₱" + CashieringFunctions.addCommas(lowestPrice) + ")";
    }

}
