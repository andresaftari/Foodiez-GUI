package model;

/**
 * @author andre
 */
public abstract class User {
    protected String user_name;
    protected String user_pass;
    protected String user_address;
    protected String user_phone;
    protected int user_token, saldo, status;
    protected int user_type;

    public User(String user_name) {
        this.user_name = user_name;
    }

    public User(
            String user_name,
            String user_pass,
            String user_address,
            String user_phone,
            int user_token,
            int saldo,
            int status,
            int user_type
    ) {
        this.user_name = user_name;
        this.user_pass = user_pass;
        this.user_address = user_address;
        this.user_phone = user_phone;
        this.user_token = user_token;
        this.saldo = saldo;
        this.status = status;
        this.user_type = user_type;
    }

    public int getUser_token() {
        return user_token;
    }

    public int getSaldo() {
        return saldo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getLoginStatus() {
        String result = "";

        switch (getStatus()) {
            case 0:
                result = "Offline";
                break;

            case 1:
                result = "Online";
                break;

            case 2:
                result = "Suspended";
                break;
        }
        return result;
    }

    public int getUser_type() {
        return user_type;
    }

    public void setSaldo(int saldo) {
        this.saldo = saldo;
    }

    public String getUserType() {
        String result = "";

        switch (getUser_type()) {
            case 1:
                result = "Admin";
                break;

            case 2:
                result = "Customer";
                break;
        }
        return result;
    }

    public String getUser_name() {
        return user_name;
    }

    public String getUser_address() {
        return user_address;
    }

    public String getUser_phone() {
        return user_phone;
    }
}
