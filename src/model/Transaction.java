package model;

/**
 * @author andre
 */
public class Transaction {
    private String order_code;
    private String username, product_name;

    public Transaction(String order_code, String username, String product_name) {
        this.order_code = order_code;
        this.product_name = product_name;
        this.username = username;
    }

    public String getOrder_code() {
        return order_code;
    }

    public String getUsername() {
        return username;
    }

    public String getProduct_name() {
        return product_name;
    }
}
