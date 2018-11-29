package classes;

import database.Cart;
import database.CartItem;
import database.Product;

import java.io.Serializable;

public class EndUser extends RegisteredUser implements Serializable {

    Cart cart;

    public EndUser(Credential credential, String name, int id) {
        super(credential,name, id);
        cart=new Cart();
    }

    public void addItem(Product product, int quantity){
        cart.add(product,quantity);
    }

    public void checkout(Store store){

        if(store.validCart(cart)){

            for (CartItem cartItem:
                 cart.getCartItems()) {
                store.sale(cartItem);
            }
        }
    }

    @Override
    public void update(RegisteredUser registeredUser) {

    }

    @Override
    public void runSession() {
        //TODO complete definition
    }

    @Override
    public void showMenu() {
        //TODO complete definition
    }
}
