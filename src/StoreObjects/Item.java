package StoreObjects;

public class Item {

    private final String itemName;
    private final int stocks;
    private final double price;
    private final double costToBuy;
    private final int itemsSold;

    public Item(String itemName, int stocks, double price, double costToBuy, int itemsSold){
        this.itemName = itemName;
        this.stocks = stocks;
        this.price = price;
        this.costToBuy = costToBuy;
        this.itemsSold = itemsSold;
    }

    public String getItemName() {
        return itemName;
    }

    public int getStocks() {
        return stocks;
    }

    public double getPrice() {
        return price;
    }

    public double getCostToBuy(){
        return costToBuy;
    }

    public int getItemsSold(){
        return itemsSold;
    }
}
