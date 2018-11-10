package database;

import java.io.Serializable;

public final class CategoryTree implements Serializable {

    private final Category root;

    public CategoryTree() {

        root = new Category("");
    }

    public Category getCategory(String categoryPath) {

        Category c = root;

        if (categoryPath.equals("")) {
            return c;
        }

        String[] categoryPathValues = categoryPath.split(">");

        for (String s : categoryPathValues) {

            c = c.getSubCategory(s);

            if (c == null) {
                return null;
            }
        }

        return c;
    }

    public void addCategory(String categoryPath) {

        Category c = root;
        String[] categoryPathValues = categoryPath.split(">");

        for (String s : categoryPathValues) {

            if (!c.containsSubCategory(s)) {
                c.addSubCategory(s);
            }

            c = c.getSubCategory(s);
        }
    }

    public Product searchProduct(String productName, boolean showPath) {

        PathString path = new PathString();
        ProductReference p = new ProductReference();

        root.searchProduct(productName, path, p);

        if (showPath) {
            System.out.println(path);
        }

        return p.getProduct();
    }
}

