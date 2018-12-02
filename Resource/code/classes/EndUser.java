package classes;

import database.Cart;
import database.CartItem;
import database.Product;
import exceptions.CartNotValidException;
import exceptions.InsufficientFundsException;
import exceptions.ProductNotFoundException;
import exceptions.StockInsufficientException;

import java.io.Serializable;

public class EndUser extends RegisteredUser implements Serializable {

    private Cart cart;
    private double funds;

    public EndUser(Credential credential, String name, int id) {
        super(credential,name, id);
        cart=new Cart();
    }

    public Cart getCart() {
        return cart;
    }

    public double getFunds() {
        return funds;
    }

    public void addItem(Product product, int quantity){
        cart.add(product,quantity);
    }

    public void checkout(Store store) throws StockInsufficientException, CartNotValidException, InsufficientFundsException, ProductNotFoundException {

        if(!sufficientFunds()){
            throw new InsufficientFundsException("Please add more funds to continue;");
        }

        if(store.validCart(cart)){

            for (CartItem cartItem:
                 cart.getCartItems()) {
                store.sale(cartItem);
            }
        }

        else{
            throw new CartNotValidException("Number of items requested more than available in stock.");
        }
    }

    private boolean sufficientFunds() {

        return cart.getValue()<=funds;
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
