package model;

public class TemporaryModel {
    private String order_code;
    private int id_m_user;
    private int id_m_product;

    public TemporaryModel(String order_code, int id_m_user, int id_m_product) {
        this.order_code = order_code;
        this.id_m_user = id_m_user;
        this.id_m_product = id_m_product;
    }

    public String getOrder_code() {
        return order_code;
    }

    public int getId_m_user() {
        return id_m_user;
    }

    public int getId_m_product() {
        return id_m_product;
    }
}