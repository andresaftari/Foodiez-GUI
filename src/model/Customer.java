package model;

/**
 * @author andre
 */
public class Customer extends User {
    private int saldo;

    public Customer(String user_name) {
        super(user_name);
        this.user_name = user_name;
    }

    public Customer(
            String user_name,
            String user_pass,
            String user_address,
            String user_phone,
            int auth_token,
            int saldo,
            int login,
            int user_type
    ) {
        super(user_name, user_pass, user_address, user_phone, auth_token, saldo, login, user_type);
        this.user_name = user_name;
        this.user_pass = user_pass;
        this.user_address = user_address;
        this.user_phone = user_phone;
        this.saldo = saldo;
        this.user_type = user_type;
        this.status = login;
    }

    public int getSaldo() {
        return saldo;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }
}
