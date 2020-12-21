package model;

/**
 *
 * @author andre
 */
public class Admin extends User {
    private int auth_token;

    public Admin(
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
        this.user_type = user_type;
        this.auth_token = auth_token;
        this.status = login;
    }

    public int getAuth_token() {
        return auth_token;
    }
    public void setAuth_token(int auth_token) {
        this.auth_token = auth_token;
    }
}
