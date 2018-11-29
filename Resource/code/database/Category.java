package database;

import java.io.Serializable;
import java.util.ArrayList;

public class Category implements Serializable {

    //TODO see if the getters are secure or not (pass the reference or pass the clone?)
    private String name;
    private ArrayList<Product> productArrayList;
    private ArrayList<Category> categoryArrayList;

    public Category(String name) {
        this.name = name;
        this.productArrayList=new ArrayList<>();
        this.categoryArrayList=new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public Product getProduct(String name) {

        for (Product p : productArrayList) {

            if (p.getName().equals(name)) {
                return p;
            }
        }

        return null;
    }

    public Category getSubCategory(String name) {

        for (Category c : categoryArrayList) {

            if (c.getName().equals(name)) {
                return c;
            }
        }

        return null;
    }

    public boolean containsSubCategory(String name) {

        for (Category c : categoryArrayList) {

            if (c.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    public boolean containsProduct(String name) {

        for (Product p : productArrayList) {

            if (p.getName().equals(name)) {
                return true;
            }
        }

        return false;
    }

    public void addSubCategory(String name) {

        categoryArrayList.add(new Category(name));
    }

    public void addProduct(Product p) {

        productArrayList.add(p);
    }

    public void deleteProduct(String name) {

        for (int i = 0; i < productArrayList.size(); i++) {

            if (productArrayList.get(i).getName().equals(name)) {
                productArrayList.remove(i);
                break;
            }
        }
    }

    public void deleteSubCategory(String name) {

        for (int i = 0; i < categoryArrayList.size(); i++) {

            if (categoryArrayList.get(i).getName().equals(name)) {
                categoryArrayList.get(i).resetSubCategoryList();
                categoryArrayList.get(i).resetproductArrayList();
            }
        }
    }

    public boolean searchProduct(String productName, PathString path, ProductReference product) {

        //TODO PathString path is probably redundant in this algorithm as Product class already has its path. Remove this thing with utmost care, if you want to remove.

        if (this.containsProduct(productName)) {

            path.appendRight(this.getName() + ">" + this.getProduct(productName).getName());
            product.setProduct(this.getProduct(productName));
            return true;
        } else {

            boolean b = false;

            for (Category c : categoryArrayList) {

                b = b || c.searchProduct(productName, path, product);
            }

            if (b) {

                if (this.getName() == "") {
                    return true;
                }

                path.appendLeft(this.getName() + ">");
                return true;
            }
        }

        return false;
    }

    public void resetSubCategoryList() {
        this.categoryArrayList = new ArrayList<>();
    }

    public void resetproductArrayList() {
        this.productArrayList = new ArrayList<>();
    }

    public ArrayList<Product> getProductArrayList() {
        return productArrayList;
    }

    public ArrayList<Category> getCategoryArrayList() {
        return categoryArrayList;
    }
}
