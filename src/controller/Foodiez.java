package controller;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import model.*;
import view.admin.AdminMain;
import view.auth.Login;
import view.customer.CustomerMain;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.Random;

/**
 * @author andre
 */
public class Foodiez {
    private static final MysqlDataSource data = new MysqlDataSource();
    private final ArrayList<User> userList = new ArrayList<>();
    private final ArrayList<Product> productList = new ArrayList<>();
    private final ArrayList<Transaction> transactionList = new ArrayList<>();

    public Foodiez() {
        DBConn dbconn = new DBConn();
        dbconn.db_connection(data);
    }

    // Check the database connection
    public void databaseConnectionCheck() {
        try {
            Connection conn = data.getConnection();
            String getQuery = "SELECT * FROM m_user";

            System.out.println("Connection successfull!\n");

            // Database connections
            Statement state = conn.createStatement();
            ResultSet rset = state.executeQuery(getQuery);

            while (rset.next()) {
                String name = rset.getString("user_name");
                String address = rset.getString("user_address");
                int type = rset.getInt("user_type");
                String phone = rset.getString("user_phone");
                int token = rset.getInt("auth_token");

                System.out.format("%s, %s, %d, %s, %d\n", name, address, type, phone, token);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // User registration
    public void userRegistration(
            JFrame jFrame,
            Component parentComponent,
            String textName,
            String textPass,
            String textAddress,
            String phone,
            String comboType
    ) {
        try {
            String username = "";
            Connection conn = data.getConnection();
            String getQuery = "SELECT * FROM m_user";

            boolean data = textName.equals("Silakan masukkan nama...")
                    || textPass.equals("Pass")
                    || textAddress.equals("Silakan masukkan alamat...")
                    || phone.equals("Silakan masukkan no. HP...");
            boolean validate = textName.equals("")
                    || textPass.equals("")
                    || textAddress.equals("")
                    || phone.equals("");
            if (!userList.isEmpty()) {
                // Database connections
                Statement state = conn.createStatement();
                ResultSet rset = state.executeQuery(getQuery);

                if (rset.next()) {
                    username = rset.getString("user_name");
                }

                // Check if user name is already exist or not
                if (textName.equals(username)) {
                    String message = "Username sudah digunakan!";
                    validation(parentComponent, message);
                } else {
                    // Validate all form data
                    if (data) {
                        String message = "Silakan isi data Anda dengan benar!";
                        validation(parentComponent, message);

                        // Validate the password length
                    } else if (textPass.length() < 8) {
                        String message = "Password harus memiliki minimal 8 karakter";
                        validation(parentComponent, message);

                        // Validate all form data
                    } else if (validate) {
                        String message = "Silakan isi data Anda dengan benar!";
                        validation(parentComponent, message);

                        // If all forms are filled, POST data to database
                    } else {
                        Random random = new Random();
                        int auth_token = random.nextInt(10000000);
                        int user_type = 0;

                        // Validate the user_type as "Admin" or "Customer"
                        if (comboType.equals("Admin")) {
                            user_type = 1;
                        } else if (comboType.equals("Customer")) {
                            user_type = 2;
                        }

                        // POST the Admin type user data
                        if (user_type == 1) {
                            User admin = new Admin(
                                    textName.toLowerCase(),
                                    textPass,
                                    textAddress,
                                    phone,
                                    auth_token,
                                    0,
                                    1,
                                    0
                            );

                            addUser(admin);

                            try {
                                String insertQuery = "INSERT INTO m_user(user_name, user_pass, user_address, user_phone, user_type, auth_token, status) VALUES(?, ?, ?, ?, ?, ?, ?)";

                                PreparedStatement pstate = conn.prepareStatement(insertQuery);
                                pstate.setString(1, textName.toLowerCase());
                                pstate.setString(2, textPass);
                                pstate.setString(3, textAddress);
                                pstate.setString(4, phone);
                                pstate.setInt(5, user_type);
                                pstate.setInt(6, auth_token);
                                pstate.setInt(7, 0);

                                int rowAffected = pstate.executeUpdate();

                                if (rowAffected > 0) {
                                    System.out.println("Insert successfull");

                                    JOptionPane.showMessageDialog(
                                            parentComponent,
                                            "Akun Anda berhasil dibuat! Silakan sign-in untuk masuk ke aplikasi menggunakan akun Anda",
                                            "Register",
                                            JOptionPane.INFORMATION_MESSAGE
                                    );
                                    this.goToLogin(jFrame);
                                }
                            } catch (SQLException e) {
                                System.out.println(e.getMessage());
                            }
                            // POST the Customer type user data
                        } else if (user_type == 2) {
                            User customer = new Customer(
                                    textName.toLowerCase(),
                                    textPass,
                                    textAddress,
                                    phone,
                                    0,
                                    0,
                                    2,
                                    0
                            );
                            addUser(customer);

                            try {
                                String insertQuery = "INSERT INTO " +
                                        "m_user(user_name, user_pass, user_address, user_phone, user_type, auth_token, status, user_saldo) " +
                                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

                                PreparedStatement pstate = conn.prepareStatement(insertQuery);
                                pstate.setString(1, textName.toLowerCase());
                                pstate.setString(2, textPass);
                                pstate.setString(3, textAddress);
                                pstate.setString(4, phone);
                                pstate.setInt(5, user_type);
                                pstate.setInt(6, 0);
                                pstate.setInt(7, 0);
                                pstate.setInt(8, 0);

                                int rowAffected = pstate.executeUpdate();

                                if (rowAffected > 0) {
                                    System.out.println("Insert successfull");

                                    JOptionPane.showMessageDialog(
                                            parentComponent,
                                            "Akun Anda berhasil dibuat! Silakan sign-in untuk masuk ke aplikasi menggunakan akun Anda",
                                            "Register",
                                            JOptionPane.INFORMATION_MESSAGE
                                    );
                                    this.goToLogin(jFrame);
                                }
                            } catch (SQLException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                }
            } else {
                // Validate all form data
                if (data) {
                    String message = "Silakan isi data Anda dengan benar!";
                    validation(parentComponent, message);

                    // Validate the password length
                } else if (textPass.length() < 8) {
                    String message = "Password harus memiliki minimal 8 karakter";
                    validation(parentComponent, message);

                    // Validate all form data
                } else if (validate) {
                    String message = "Silakan isi data Anda dengan benar!";
                    validation(parentComponent, message);

                    // If all forms are filled, POST data to database
                } else {
                    Random random = new Random();
                    int auth_token = random.nextInt(10000000);
                    int user_type = 0;

                    // Validate the user_type as "Admin" or "Customer"
                    if (comboType.equals("Admin")) user_type = 1;
                    else if (comboType.equals("Customer")) user_type = 2;

                    // POST the Admin type user data
                    if (user_type == 1) {
                        User admin = new Admin(
                                textName.toLowerCase(),
                                textPass,
                                textAddress,
                                phone,
                                auth_token,
                                0,
                                1,
                                0
                        );
                        addUser(admin);

                        try {
                            String insertQuery = "INSERT INTO m_user(user_name, user_pass, user_address, user_phone, user_type, auth_token, status) VALUES(?, ?, ?, ?, ?, ?, ?)";

                            PreparedStatement pstate = conn.prepareStatement(insertQuery);
                            pstate.setString(1, textName.toLowerCase());
                            pstate.setString(2, textPass);
                            pstate.setString(3, textAddress);
                            pstate.setString(4, phone);
                            pstate.setInt(5, user_type);
                            pstate.setInt(6, auth_token);
                            pstate.setInt(7, 0);

                            int rowAffected = pstate.executeUpdate();

                            if (rowAffected > 0) {
                                System.out.println("Insert successfull");

                                JOptionPane.showMessageDialog(
                                        parentComponent,
                                        "Akun Anda berhasil dibuat! Silakan sign-in untuk masuk ke aplikasi menggunakan akun Anda",
                                        "Register",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                                this.goToLogin(jFrame);
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }

                        // POST the Customer type user data
                    } else if (user_type == 2) {
                        User customer = new Customer(
                                textName.toLowerCase(),
                                textPass,
                                textAddress,
                                phone,
                                0,
                                0,
                                2,
                                0
                        );
                        addUser(customer);

                        try {
                            String insertQuery = "INSERT INTO " +
                                    "m_user(user_name, user_pass, user_address, user_phone, user_type, auth_token, status, user_saldo) " +
                                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

                            PreparedStatement pstate = conn.prepareStatement(insertQuery);
                            pstate.setString(1, textName.toLowerCase());
                            pstate.setString(2, textPass);
                            pstate.setString(3, textAddress);
                            pstate.setString(4, phone);
                            pstate.setInt(5, user_type);
                            pstate.setInt(6, 0);
                            pstate.setInt(7, 0);
                            pstate.setInt(8, 0);

                            int rowAffected = pstate.executeUpdate();

                            if (rowAffected > 0) {
                                System.out.println("Insert successfull");

                                JOptionPane.showMessageDialog(
                                        parentComponent,
                                        "Akun Anda berhasil dibuat! Silakan sign-in untuk masuk ke aplikasi menggunakan akun Anda",
                                        "Register",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                                this.goToLogin(jFrame);
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }
                    }
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // User login
    public void userLogin(
            JFrame jFrame,
            Component parentComponent,
            String textName,
            String textPass
    ) {
        try {
            Connection conn = data.getConnection();
            String getQuery = "SELECT * FROM m_user WHERE user_name=?";
            String username = "", pass = "";
            int userType = 0, userState = 0;

            PreparedStatement pstate = conn.prepareStatement(getQuery);
            pstate.setString(1, textName);

            // Database connections
            ResultSet rset = pstate.executeQuery();

            while (rset.next()) {
                username = rset.getString("user_name");
                pass = rset.getString("user_pass");
                userType = rset.getInt("user_type");
                userState = rset.getInt("status");
            }

            System.out.format("%s, %s\n", username, pass);

            if (userState == 2) {
                JOptionPane.showMessageDialog(
                        parentComponent,
                        "Akses ditolak karena akun Anda sedang di-suspend!",
                        "Authorization Check",
                        JOptionPane.WARNING_MESSAGE
                );
            } else if (!textName.equals(username) || !textPass.equals(pass)) {
                String message = "Pastikan username dan password yang Anda masukan benar!";
                validation(parentComponent, message);

            } else if (textName.equals("")
                    || textName.equals("Silakan masukkan nama...")
                    || textPass.equals("")
                    || textPass.equals("Pass")
            ) {
                String message = "Silakan isi data Anda dengan benar!";
                validation(parentComponent, message);
            } else {
                String query = "UPDATE m_user SET status = 1 WHERE user_name=?";

                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, username);
                ps.executeUpdate();

                JOptionPane.showMessageDialog(
                        parentComponent,
                        "Hello, " + username, "Login",
                        JOptionPane.INFORMATION_MESSAGE
                );

                if (userType == 1) {
                    AdminMain page = new AdminMain(username);

                    jFrame.dispose();
                    page.setSize(1920, 1080);
                    page.setVisible(true);
                } else if (userType == 2) {
                    CustomerMain page = new CustomerMain(username);

                    jFrame.dispose();
                    page.setSize(1920, 1080);
                    page.setVisible(true);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Load Customer saldo for table
    public void loadSaldoUser(
            String current_user,
            JLabel labelSaldo
    ) {
        try {
            Connection conn = data.getConnection();
            String getQuery = "SELECT user_saldo FROM m_user WHERE user_name=?";

            PreparedStatement ps = conn.prepareStatement(getQuery);
            ps.setString(1, current_user);

            ResultSet rset = ps.executeQuery();

            int saldo = 0;
            while (rset.next()) {
                saldo = rset.getInt("user_saldo");
            }

            labelSaldo.setText("Saldo Anda: Rp" + saldo);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Load transaction data for table
    public void loadTransactionData(DefaultTableModel tableModel) {
        try {
            ArrayList<TemporaryModel> tempList = new ArrayList<>();

            String order_code;
            int id_m_user, id_m_product;

            Connection conn = data.getConnection();
            String getQuery = "SELECT * FROM m_transaction";

            Statement state = conn.createStatement();
            ResultSet rset = state.executeQuery(getQuery);

            while (rset.next()) {
                order_code = rset.getString("order_code");
                id_m_user = rset.getInt("id_m_user");
                id_m_product = rset.getInt("id_m_product");

                tempList.add(new TemporaryModel(order_code, id_m_user, id_m_product));
            }

            tempList.forEach(it -> {
                try {
                    String username, productname;
                    String getData = "SELECT m_user.user_name, m_product.product_name " +
                            "FROM m_user, m_product " +
                            "WHERE m_user.id=? AND m_product.id=?";

                    PreparedStatement ps = conn.prepareStatement(getData);
                    ps.setInt(1, it.getId_m_user());
                    ps.setInt(2, it.getId_m_product());

                    ResultSet dataset = ps.executeQuery();

                    while (dataset.next()) {
                        username = dataset.getString("user_name");
                        productname = dataset.getString("product_name");

                        addTransaction(new Transaction(it.getOrder_code(), username, productname));

                        int index = transactionList.size() - 1;

                        tableModel.addRow(new Object[]{
                                transactionList.get(index).getOrder_code(),
                                transactionList.get(index).getUsername(),
                                transactionList.get(index).getProduct_name()
                        });
                    }
                } catch (SQLException e) {
                    System.out.println(e.getMessage());
                }
            });
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Load user data for table
    public void loadUserData(DefaultTableModel tableModel) {
        try {
            Connection conn = data.getConnection();
            String getQuery = "SELECT * FROM m_user";

            Statement state = conn.createStatement();
            ResultSet rset = state.executeQuery(getQuery);

            while (rset.next()) {
                String username = rset.getString("user_name");
                String pass = rset.getString("user_pass");
                String address = rset.getString("user_address");
                String phone = rset.getString("user_phone");
                int type = rset.getInt("user_type");
                int auth = rset.getInt("auth_token");
                int saldo = rset.getInt("user_saldo");
                int status = rset.getInt("status");

                if (type == 1) addUser(new Admin(
                        username,
                        pass,
                        address,
                        phone,
                        auth,
                        saldo,
                        status,
                        type
                ));
                else if (type == 2) addUser(new Customer(
                        username,
                        pass,
                        address,
                        phone,
                        auth,
                        saldo,
                        status,
                        type
                ));
                int index = userList.size() - 1;

                tableModel.addRow(new Object[]{
                        userList.get(index).getUser_name(),
                        userList.get(index).getUser_address(),
                        userList.get(index).getUser_phone(),
                        userList.get(index).getUserType(),
                        userList.get(index).getUser_token(),
                        userList.get(index).getSaldo(),
                        userList.get(index).getLoginStatus()
                });
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Load product data for table
    public void loadProductData(DefaultTableModel tableModel) {
        try {
            Connection conn = data.getConnection();
            String query = "SELECT * FROM m_product";

            Statement state = conn.createStatement();
            ResultSet rset = state.executeQuery(query);

            while (rset.next()) {
                String productName = rset.getString("product_name");
                String productDesc = rset.getString("product_desc");
                int productQty = rset.getInt("product_qty");
                int productPrice = rset.getInt("product_price");
                int productStatus = rset.getInt("is_available");

                addProduct(new Product(
                        productName,
                        productDesc,
                        productQty,
                        productStatus,
                        productPrice
                ));
                int index = productList.size() - 1;

                tableModel.addRow(new Object[]{
                        productList.get(index).getProduct_name(),
                        productList.get(index).getProduct_price(),
                        productList.get(index).getProduct_desc(),
                        productList.get(index).getProduct_qty(),
                        productList.get(index).getAvailableStatus()
                });
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // User login authorization check
    public void authorizationCheck(
            JFrame jFrame,
            Component parentComponent,
            String current_user
    ) {
        try {
            Connection conn = data.getConnection();
            String getQuery = "SELECT * FROM m_user WHERE user_name=?";

            PreparedStatement ps = conn.prepareStatement(getQuery);
            ps.setString(1, current_user);

            ResultSet rs = ps.executeQuery();
            int user_state = 0;

            while (rs.next()) {
                user_state = rs.getInt("status");
            }

            if (current_user == null || current_user.equals("") || current_user.equals("null") || user_state == 0) {
                JOptionPane.showMessageDialog(
                        parentComponent,
                        "Akses ditolak karena Anda belum melakukan login sebagai Admin!",
                        "Authorization Check",
                        JOptionPane.WARNING_MESSAGE
                );
                this.goToLogin(jFrame);
            } else if (user_state == 2) {
                JOptionPane.showMessageDialog(
                        parentComponent,
                        "Akses ditolak karena akun Anda sedang di-suspend!",
                        "Authorization Check",
                        JOptionPane.WARNING_MESSAGE
                );
                this.goToLogin(jFrame);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Insert product data into database
    public void insertProductData(
            Component parentComponent,
            String textName,
            String textDesc,
            String comboStatus,
            String textQuantity,
            String textPrice,
            DefaultTableModel tableModel
    ) {
        try {
            String name = "", desc = "";
            Connection conn = data.getConnection();

            if (!productList.isEmpty()) {
                String checkQuery = "SELECT * FROM m_product";

                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(checkQuery);

                if (rs.next()) {
                    name = rs.getString("product_name");
                    desc = rs.getString("product_desc");
                }

                if (textName.equals(name) && textDesc.equals(desc)) {
                    JOptionPane.showMessageDialog(
                            parentComponent,
                            "Data produk sudah ada, silakan di-update!",
                            "Add Product",
                            JOptionPane.WARNING_MESSAGE
                    );
                } else {
                    String insertQuery = "INSERT INTO m_product(product_name, product_desc, product_qty, product_price, is_available) " +
                            "VALUES (?, ?, ?, ?, ?)";

                    int status = 0;
                    switch (comboStatus) {
                        case "Ada":
                            status = 1;
                            break;
                        case "Pre Order":
                            status = 2;
                            break;
                        case "Habis":
                            status = 0;
                            break;
                    }

                    PreparedStatement ps = conn.prepareStatement(insertQuery);
                    ps.setString(1, textName);
                    ps.setString(2, textDesc);
                    ps.setInt(3, Integer.parseInt(textQuantity));
                    ps.setInt(4, Integer.parseInt(textPrice));
                    ps.setInt(5, status);
                    ps.executeUpdate();

                    addProduct(new Product(
                            textName,
                            textDesc,
                            Integer.parseInt(textQuantity),
                            status,
                            Integer.parseInt(textPrice)
                    ));
                    int index = getProductList().size() - 1;

                    tableModel.addRow(new Object[]{
                            getProductList().get(index).getProduct_name(),
                            getProductList().get(index).getProduct_price(),
                            getProductList().get(index).getProduct_desc(),
                            getProductList().get(index).getProduct_qty(),
                            getProductList().get(index).getAvailableStatus()
                    });

                    JOptionPane.showMessageDialog(
                            parentComponent,
                            "Produk berhasil ditambahkan!",
                            "Admin Page",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            } else {
                String insertQuery = "INSERT INTO m_product(product_name, product_desc, product_qty, product_price, is_available) " +
                        "VALUES (?, ?, ?, ?, ?)";

                int status = 0;
                switch (comboStatus) {
                    case "Ada":
                        status = 1;
                        break;
                    case "Pre Order":
                        status = 2;
                        break;
                    case "Habis":
                        status = 0;
                        break;
                }

                PreparedStatement ps = conn.prepareStatement(insertQuery);
                ps.setString(1, textName);
                ps.setString(2, textDesc);
                ps.setInt(3, Integer.parseInt(textQuantity));
                ps.setInt(4, Integer.parseInt(textPrice));
                ps.setInt(5, status);
                ps.executeUpdate();

                addProduct(new Product(
                        textName,
                        textDesc,
                        Integer.parseInt(textQuantity),
                        status,
                        Integer.parseInt(textPrice)
                ));
                int index = getProductList().size() - 1;

                tableModel.addRow(new Object[]{
                        getProductList().get(index).getProduct_name(),
                        getProductList().get(index).getProduct_price(),
                        getProductList().get(index).getProduct_desc(),
                        getProductList().get(index).getProduct_qty(),
                        getProductList().get(index).getAvailableStatus()
                });

                JOptionPane.showMessageDialog(
                        parentComponent,
                        "Produk berhasil ditambahkan!",
                        "Admin Page",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Customer transaction
    public void executeTransaction(
            Component parentComponent,
            String current_user,
            String productName,
            int price,
            int buyQuantity,
            int stokBarang,
            int sisaStok,
            int tableRow,
            JLabel labelSaldo,
            DefaultTableModel tableModel
    ) {
        try {
            int saldo = 0;
            Connection conn = data.getConnection();
            String getQuery = "SELECT user_saldo FROM m_user WHERE user_name=?";

            PreparedStatement ps = conn.prepareStatement(getQuery);
            ps.setString(1, current_user);

            ResultSet rset = ps.executeQuery();

            while (rset.next()) {
                saldo = rset.getInt("user_saldo");
            }

            if (sisaStok < 0) {
                JOptionPane.showMessageDialog(
                        parentComponent,
                        "Stok produk yang Anda beli berlebih, produk hanya tersedia " + stokBarang + " buah",
                        "Customer App",
                        JOptionPane.WARNING_MESSAGE
                );
            } else if (saldo < price * buyQuantity) {
                JOptionPane.showMessageDialog(
                        parentComponent,
                        "Saldo Anda tidak mencukupi!",
                        "Customer App",
                        JOptionPane.WARNING_MESSAGE
                );
            } else {
                Random random = new Random();
                String order_code = "ORDER" + random.nextInt(10000);

                // Insert transaction table
                String insertQuery = "INSERT INTO m_transaction(id_m_user, id_m_product, order_code) " +
                        "VALUES (" +
                        "(SELECT id FROM m_user WHERE user_name=?), " +
                        "(SELECT id FROM m_product WHERE product_name=?)," +
                        "?);";

                String foodName = productList.get(tableRow).getProduct_name();

                PreparedStatement trans = conn.prepareStatement(insertQuery);
                trans.setString(1, current_user);
                trans.setString(2, foodName);
                trans.setString(3, order_code);
                trans.executeUpdate();

                // Update product quantity
                String findFood = "SELECT product_qty FROM m_product WHERE product_name=?";

                PreparedStatement findQty = conn.prepareStatement(findFood);
                findQty.setString(1, productName);
                ResultSet qtySet = findQty.executeQuery();

                int qty = 0;
                while (qtySet.next()) {
                    qty = qtySet.getInt("product_qty");
                }

                int stok = qty - buyQuantity;
                System.out.println(stok);
                String updateQty = "UPDATE m_product SET product_qty=? WHERE product_name=?";

                PreparedStatement pQty = conn.prepareStatement(updateQty);
                pQty.setInt(1, stok);
                pQty.setString(2, productName);
                pQty.executeUpdate();

                // Update user saldo
                int total = price * buyQuantity;
                int sisaSaldo = saldo - total;

                System.out.println(sisaSaldo);
                String updateSaldo = "UPDATE m_user SET user_saldo=? WHERE user_name=?";

                PreparedStatement pSaldo = conn.prepareStatement(updateSaldo);
                pSaldo.setInt(1, sisaSaldo);
                pSaldo.setString(2, current_user);
                pSaldo.executeUpdate();

                addTransaction(new Transaction(order_code, current_user, productName));
                getProductList().get(tableRow).setProduct_qty(stok);
                tableModel.setRowCount(0);

                userList.forEach(it -> {
                    if (it.getUser_name().equals(current_user)) {
                        it.setSaldo(it.getSaldo() - price * buyQuantity);

                        System.out.println(it.getSaldo());
                    }
                });

                if (stok == 0) {
                    String setStatus = "UPDATE m_product SET is_available = 0 WHERE product_name=?";

                    PreparedStatement pStatus = conn.prepareStatement(setStatus);
                    pStatus.setString(1, productName);
                    pStatus.executeUpdate();

                    productList.get(tableRow).setIs_available(0);
                }

                JOptionPane.showMessageDialog(
                        parentComponent,
                        "Transaksi Berhasil!",
                        "Customer App",
                        1
                );
                labelSaldo.setText("Saldo Anda: Rp...");
                this.loadSaldoUser(current_user, labelSaldo);

                productList.clear();
                loadProductData(tableModel);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Update user account state
    public void updateUserState(
            Component parentComponent,
            String username,
            int login_state
    ) {
        try {
            Connection conn = data.getConnection();
            String getQuery = "UPDATE m_user SET status=? WHERE user_name=?";

            PreparedStatement ps = conn.prepareStatement(getQuery);
            ps.setInt(1, login_state);
            ps.setString(2, username);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                    parentComponent,
                    "Status akun berhasil diubah!",
                    "Admin Page",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Update saldo user
    public void updateSaldoUser(
            Component parentComponent,
            String current_user,
            int setSaldo,
            JLabel labelSaldo
    ) {
        int total = 0;
        try {
            Connection conn = data.getConnection();
            String getQuery = "SELECT user_saldo FROM m_user WHERE user_name=?";

            PreparedStatement pSaldo = conn.prepareStatement(getQuery);
            pSaldo.setString(1, current_user);
            ResultSet rset = pSaldo.executeQuery();

            while (rset.next()) {
                total = rset.getInt("user_saldo") + setSaldo;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        try {
            Connection conn = data.getConnection();
            String updateQuery = "UPDATE m_user SET user_saldo=? WHERE user_name=?";

            PreparedStatement pUpdate = conn.prepareStatement(updateQuery);
            pUpdate.setInt(1, total);
            pUpdate.setString(2, current_user);
            pUpdate.executeUpdate();

            JOptionPane.showMessageDialog(
                    parentComponent,
                    "Isi saldo berhasil!"
            );

            labelSaldo.setText("");
            this.loadSaldoUser(current_user, labelSaldo);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Update product data into database
    public void updateProductData(
            Component parentComponent,
            String getEditName,
            String getEditDesc,
            int getIs_available,
            int getEditQuantity,
            int getEditPrice,
            DefaultTableModel tableModel,
            String oldProductName
    ) {
        try {
            Connection conn = data.getConnection();
            String setQuery = "UPDATE m_product "
                    + "SET product_name=?, product_desc=?, product_qty=?, product_price=?, is_available=? "
                    + "WHERE product_name=?";

            PreparedStatement psUpdate = conn.prepareStatement(setQuery);

            psUpdate.setString(1, getEditName);
            psUpdate.setString(2, getEditDesc);
            psUpdate.setInt(3, getEditQuantity);
            psUpdate.setInt(4, getEditPrice);
            psUpdate.setInt(5, getIs_available);
            psUpdate.setString(6, oldProductName);
            int affected = psUpdate.executeUpdate();

            if (affected > 0) {
                if (getEditQuantity == 0) {
                    String setStatus = "UPDATE m_product SET is_available = 0 WHERE product_name=?";

                    PreparedStatement pStatus = conn.prepareStatement(setStatus);
                    pStatus.setString(1, getEditName);
                    pStatus.executeUpdate();
                }

                JOptionPane.showMessageDialog(
                        parentComponent,
                        "Pengubahan data produk berhasil!",
                        "Admin Page",
                        1
                );
            }
            getProductList().clear();

            tableModel.setRowCount(0);
            tableModel.fireTableRowsDeleted(0, productList.size());
            loadProductData(tableModel);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Delete product data into database
    public void deleteProductData(
            Component parentComponent,
            String productName,
            DefaultTableModel tableModel
    ) {
        try {
            Connection conn = data.getConnection();
            String removeForeignKeyCheck = "SET foreign_key_checks = 0";

            Statement st = conn.createStatement();
            st.executeQuery(removeForeignKeyCheck);

            String removeQuery = "DELETE FROM m_product WHERE product_name=?";

            PreparedStatement psDel = conn.prepareStatement(removeQuery);
            psDel.setString(1, productName);
            int affected = psDel.executeUpdate();

            if (affected > 0) {
                JOptionPane.showMessageDialog(
                        parentComponent,
                        "Penghapusan data produk berhasil!",
                        "Admin Page",
                        1
                );
            }
            productList.clear();

            tableModel.setRowCount(0);
            tableModel.fireTableRowsDeleted(0, productList.size());
            loadProductData(tableModel);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Logout account
    public void logoutAccount(
            JFrame jFrame,
            Component parentComponent,
            String current_user
    ) {
        try {
            Connection conn = data.getConnection();
            String getQuery = "SELECT * FROM m_user WHERE user_name=?";

            PreparedStatement ps = conn.prepareStatement(getQuery);
            ps.setString(1, current_user);

            // Database connections
            ps.executeQuery();
            String updateQuery = "UPDATE m_user SET status = 0 WHERE user_name=?";

            PreparedStatement pstate = conn.prepareStatement(updateQuery);
            pstate.setString(1, current_user);
            pstate.executeUpdate();

            JOptionPane.showMessageDialog(
                    parentComponent,
                    current_user + " berhasil logout!",
                    "Admin Page",
                    1
            );
            this.goToLogin(jFrame);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Go to login
    private void goToLogin(JFrame jFrame) {
        Login login = new Login();

        jFrame.dispose();
        login.setSize(1920, 1080);
        login.setVisible(true);
    }

    // Validation
    private void validation(Component parentComponent, String message) {
        System.out.println("Debug message: " + message);
        JOptionPane.showMessageDialog(
                parentComponent,
                message,
                "Login",
                JOptionPane.ERROR_MESSAGE
        );
    }

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
