package database;

import java.io.Serializable;
import java.util.Arrays;

public final class CategoryTree implements Serializable {

    private final Category root;

    public CategoryTree() {

        root = new Category("","");
    }

    public Category getRoot() {
        return root;
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

        for(int i=0; i<categoryPathValues.length;i++)
        {
            String s = categoryPathValues[i];
            if (!c.containsSubCategory(s)) {
                String[] categoryPathArray = Arrays.copyOfRange(categoryPathValues, 0, i);
                String categoryPathString = String.join(">",categoryPathArray);
                c.addSubCategory(s,categoryPathString);
            }

            c = c.getSubCategory(s);
        }
//        for (String s : categoryPathValues) {
//
//            if (!c.containsSubCategory(s)) {
//                c.addSubCategory(s);
//            }
//
//            c = c.getSubCategory(s);
//        }
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

