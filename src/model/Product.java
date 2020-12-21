package model;

/**
 *
 * @author andre
 */
public class Product {
    private String product_name, product_desc;
    private int product_qty, is_available, product_price;

    public Product(String product_name, String product_desc, int product_qty, int is_available, int product_price) {
        this.product_name = product_name;
        this.product_desc = product_desc;
        this.product_qty = product_qty;
        this.is_available = is_available;
        this.product_price = product_price;
    }

    public Product(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_name() {
        return product_name;
    }

    public void setProduct_name(String product_name) {
        this.product_name = product_name;
    }

    public String getProduct_desc() {
        return product_desc;
    }

    public void setProduct_desc(String product_desc) {
        this.product_desc = product_desc;
    }

    public int getProduct_qty() {
        return product_qty;
    }

    public void setProduct_qty(int product_qty) {
        this.product_qty = product_qty;
    }
    
    public String getAvailableStatus() {
        String result = "";

        switch (getIs_available()) {
            case 0:
                result = "Habis";
                break;
            case 1:
                result = "Ada";
                break;
            case 2:
                result = "Pre Order";
                break;
        }
        return result;
    }

    public int getIs_available() {
        return is_available;
    }

    public void setIs_available(int is_available) {
        this.is_available = is_available;
    }

    public int getProduct_price() {
        return product_price;
    }

    public void setProduct_price(int product_price) {
        this.product_price = product_price;
    }
}
