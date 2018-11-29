package classes;

import database.Cart;
import database.CartItem;
import database.Database;
import database.Product;

import java.io.Serializable;

public class Store implements Serializable {
    private final int id;
    private StoreAdmin admin;
    private String name;
    private Warehouse warehouse;
    Database database;

    public Store(int id, String name, StoreAdmin admin) {
        this.id=id;
        this.name=name;
        this.admin=admin;
    }

    public void setWarehouse(Warehouse warehouse) {
        this.warehouse=warehouse;
    }

    public boolean validCart(Cart cart) {

        boolean valid=true;

        for (CartItem cartItem :
                cart.getCartItems()) {

            Product product=cartItem.getProduct();
            int quantity=cartItem.getQuantity();

            if(quantity>product.getStockCount()){
                valid=false;
                break;
            }
        }

        return valid;
    }

    public void sale(CartItem cartItem) {
        Product product=cartItem.getProduct();
        int quantity=cartItem.getQuantity();

        product.setStockCount(product.getStockCount()-quantity);

        if(product.getStockCount()==0){
            //TODO request warehouse for more products.
        }
    }
}
