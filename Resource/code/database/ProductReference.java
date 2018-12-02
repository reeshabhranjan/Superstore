package database;

import java.io.Serializable;

public final class ProductReference implements Serializable {

    private Product product;

    public ProductReference() {
        this.product = null;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}

