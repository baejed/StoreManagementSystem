package StoreFunctions;

import Database.Database;
import GuiComponents.CustomComponents.DescriptionLabel;
import GuiComponents.CustomComponents.LabeledInputField;
import GuiComponents.Table.CartTable;
import GuiComponents.Resources.Alert;
import StoreObjects.Item;

import javax.swing.*;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.HashMap;

public class CashieringFunctions {

    public static void updateDatabase(CartTable cartTable) throws SQLException {
        Database.checkoutCart(cartTable.getCartContents());
    }

    public static void checkout(LabeledInputField cashInput, CartTable cartTable, DescriptionLabel totalDueDL, DescriptionLabel totalItemsDL, DescriptionLabel cashDL, DescriptionLabel changeDL) throws SQLException {
        String cashString = cashInput.getInput();

        if(cartTable.getTotalQuantity() == 0){
            Alert.alert("Cart is empty");
            return;
        }

        if(cashString.isEmpty()){
            Alert.alert("Enter cash");
            return;
        }

        double totalDue = cartTable.getTotal();
        double cash = Double.parseDouble(cashString);
        double change = cash - totalDue;

        if (change < 0) {
            Alert.alert("Insufficient cash");
            return;
        }

        totalDueDL.setText("₱" + CashieringFunctions.addCommas(totalDue));
        totalItemsDL.setText(cartTable.getTotalQuantity() + " Item(s)");
        cashDL.setText("₱" + CashieringFunctions.addCommas(cash));
        changeDL.setText("₱" + CashieringFunctions.addCommas(change));
        updateDatabase(cartTable);
        cartTable.clear();
        cashInput.clearInput();
    }

    public static boolean addToCart(LabeledInputField itemNameInput, LabeledInputField quantityInput, CartTable cartTable, JLabel totalDisplay) {
        String itemName = itemNameInput.getInput();
        String quantity = quantityInput.getInput();
        double total;
        boolean added;

        added = CashieringFunctions.addToCartTable(itemName, quantity, cartTable);

        if(!added) return false;

        total = cartTable.getTotal();

        totalDisplay.setText("₱" + CashieringFunctions.addCommas(total));
        itemNameInput.getTextField().grabFocus();
        itemNameInput.clearInput();
        quantityInput.clearInput();
        cartTable.incrementHeight();

        return true;
    }

    public static boolean addToCartTable(String itemNameInput, String quantityInput, CartTable cartTable){
        Item item;
        int quantity;

        try {
            item = Database.getItemDetails(itemNameInput);
            if (item == null) {
                Alert.alert("Item not found");
                return false;
            }
        } catch (SQLException ex) {
            throw new RuntimeException(ex);
        }

        if (quantityInput.isEmpty()){
            Alert.alert("Please enter the quantity");
            return false;
        }

        try{
            quantity = Integer.parseInt(quantityInput);
            cartTable.addData(item, quantity);
        }catch (Exception e){

        }

        return true;

    }

    public static String addCommas(double num) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        // Format the double value with commas
        return decimalFormat.format(num);
    }

    public static String addCommas(int num) {
        DecimalFormat decimalFormat = new DecimalFormat("#,##0");
        // Format the double value with commas
        return decimalFormat.format(num);
    }

}
