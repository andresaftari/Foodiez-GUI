package controller;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import model.Product;
import model.Transaction;
import model.User;

import java.util.ArrayList;

/**
 * @author andre
 */
public class Foodiez {

    private final ArrayList<User> userList = new ArrayList<>();
    private final ArrayList<Product> productList = new ArrayList<>();
    private final ArrayList<Transaction> transactionList = new ArrayList<>();

    public void addUser(User user) {
        userList.add(user);
    }

    public void addProduct(Product product) {
        productList.add(product);
    }

    public void addTransaction(Transaction transaction) {
        transactionList.add(transaction);
    }

    public ArrayList<User> getUserList() {
        return userList;
    }

    public ArrayList<Transaction> getTransactionList() {
        return transactionList;
    }

    public ArrayList<Product> getProductList() {
        return productList;
    }
}
