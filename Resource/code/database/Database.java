package database;

public class Database {

    private CategoryTree categoryTree;
    private double revenue;

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
    }

    public Product searchProduct(String name){

        Product product=null;

        product=categoryTree.searchProduct(name,false);

        return product;
    }

    public void modifyProduct(String productName,Product newProduct){

        Product product=null;

        product=searchProduct(productName);
        product.update(newProduct);
    }
}
