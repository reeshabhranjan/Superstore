package database;

import java.io.Serializable;

public class Product implements Serializable {

    //TODO add rating feature if time left and willing to do.

    private String name;
    private double price;
    private String categoryPath;
    private int stockCount;
    private String description;
    private double d; //fixed cost per quarter
    private double h; //carrying cost per unit per quarter
    private double k; //demand per quarter
    private int id; //uniqueCode (planned to be provided by the superstore via client-request)

    public Product(String name, double price, String categoryPath, int stockCount, double d, double h, double k, int id) {
        this.name = name;
        this.price = price;
        this.categoryPath = categoryPath;
        this.stockCount = stockCount;
        this.d = d;
        this.h = h;
        this.k = k;
        this.id = id;
        this.description=null;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getCategoryPath() {
        return categoryPath;
    }

    public int getStockCount() {
        return stockCount;
    }

    public String getDescription() {
        return description;
    }

    public double getD() {
        return d;
    }

    public double getH() {
        return h;
    }

    public double getK() {
        return k;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategoryPath(String categoryPath) {
        this.categoryPath = categoryPath;
    }

    public void setStockCount(int stockCount) {
        this.stockCount = stockCount;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setD(double d) {
        this.d = d;
    }

    public void setH(double h) {
        this.h = h;
    }

    public void setK(double k) {
        this.k = k;
    }

    public void incrementQty(){
        this.stockCount++;
    }

    public void decrementQty(){

        //TODO add check here for negative values (generate exception or something)
        this.stockCount--;
    }

    public void update(Product newProduct) {

        this.setName(newProduct.getName());
        this.setPrice(newProduct.getPrice());
        this.setDescription(newProduct.getDescription());
        this.setStockCount(newProduct.getStockCount());
        this.setD(newProduct.getD());
        this.setH(newProduct.getH());
        this.setK(newProduct.getK());
    }

    @Override
    public String toString(){
        return (this.name+":\\u20B9"+this.price);
    }

    public String getBasicDetails(){
        StringBuilder stringBuilder=new StringBuilder(name);
        stringBuilder.append("\n"+price);
        stringBuilder.append("\n"+stockCount);
        return stringBuilder.toString();
    }
}
