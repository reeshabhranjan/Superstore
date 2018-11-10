package database;

public final class ProductReference {

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

