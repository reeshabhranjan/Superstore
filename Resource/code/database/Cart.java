package database;

import java.io.Serializable;
import java.util.ArrayList;

public class Cart implements Serializable {

    private int value; // total value of the cart (sum of prices of the items)
    private ArrayList<CartItem> cartItems;

    public Cart() {
        cartItems=new ArrayList<>();
        value=0;
    }

    public void add(Product product, int quantity){

        CartItem cartItem=new CartItem(product,quantity);
        cartItems.add(cartItem);
        value+=cartItem.getQuantity()*cartItem.getProduct().getPrice();
    }

    public ArrayList<CartItem> getCartItems() {
        return cartItems;
    }

    public int getValue() {
        return value;
    }
}
