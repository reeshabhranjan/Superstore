package database;

import exceptions.ProductNotFoundException;
import javafx.scene.control.TreeItem;
import javafx.scene.control.TreeView;

import java.io.Serializable;
import java.util.ArrayList;

public class Database implements Serializable {

    private CategoryTree categoryTree;
    private double revenue;
    private TreeView<String> treeView;

    public TreeView<String> generateTreeView(){
        TreeItem<String> treeRoot = new TreeItem<String>();
        treeRoot.setExpanded(true);

        Category categoryRoot = categoryTree.getRoot();

        addNode(treeRoot,categoryRoot);

        treeView = new TreeView<>(treeRoot);
        treeView.setShowRoot(false);

        return treeView;
    }

    private void addNode(TreeItem<String> treeNode, Category categoryNode) {
        ArrayList<Category> subcategories = categoryNode.getCategoryArrayList();

        for (Category category : subcategories) {
            TreeItem<String> node = new TreeItem<String>(category.getName());
            node.setExpanded(true);

            treeNode.getChildren().add(node);

            addNode(node,category);
        }
    }

    public Database() {
        this.categoryTree = new CategoryTree();
        this.revenue = 0;
    }

    public void reset(){

        this.categoryTree=new CategoryTree();
        this.revenue=0;
    }

    public double getRevenue() {
        return revenue;
    }

    public void addProduct(String categoryPath, Product product){

        categoryTree.addCategory(categoryPath);
        Category category = categoryTree.getCategory(categoryPath);
        category.addProduct(product);
        //TODO create addCategory method (should assign categoryPath to the created category)
    }

    public void delete(String path){

        String categoryPath;
        String productName;

        if (path.contains(">")) {
            int splitIndex = path.lastIndexOf('>');
            categoryPath = path.substring(0, splitIndex);
            productName = path.substring(splitIndex + 1);
        } else {
            categoryPath = "";
            productName = path;
        }

        Category category=categoryTree.getCategory(categoryPath);

        //TODO check for null values for category

        if (category.containsProduct(productName)) {

            category.deleteProduct(productName);
        } else if (category.containsSubCategory(productName)) {

            category.deleteSubCategory(productName);
        }

        //TODO create deleteCategory method
    }

    public Product searchProduct(String name) throws ProductNotFoundException {

        Product product=null;

        product=categoryTree.searchProduct(name,false);

        if(product==null){
            throw new ProductNotFoundException("Product: "+name+" not found in the database.");
        }

        return product;
    }

    public void modifyProduct(String productName,Product newProduct) throws ProductNotFoundException {

        Product product=null;

        product=searchProduct(productName);
        product.update(newProduct);
    }

    public Category getCategory(String path){

        Category category=null;

        category=categoryTree.getCategory(path);
        return category;
    }
}
