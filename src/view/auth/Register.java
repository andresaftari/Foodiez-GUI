package view.auth;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import controller.DBConn;
import controller.Foodiez;
import model.Admin;
import model.Customer;
import model.User;

import javax.swing.*;
import java.sql.*;
import java.util.Random;

import static java.awt.EventQueue.invokeLater;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import static javax.swing.UIManager.*;

/**
 * @author andre
 */
public class Register extends JFrame {

    private static final Foodiez foodiez = new Foodiez();
    private MysqlDataSource data = new MysqlDataSource();

    public Register() {
        DBConn dbconn = new DBConn();
        dbconn.db_connection(data);

        // Check database connection
        try {
            Connection conn = data.getConnection();
            String getQuery = "SELECT * FROM m_user";

            System.out.println("Debug message: Connection successfull!\n");

            // Database connections
            Statement state = conn.createStatement();
            ResultSet rset = state.executeQuery(getQuery);

            while (rset.next()) {
                String name = rset.getString("user_name");
                String pass = rset.getString("user_pass");
                String address = rset.getString("user_address");
                int type = rset.getInt("user_type");
                String phone = rset.getString("user_phone");
                int token = rset.getInt("auth_token");

                System.out.format("%s, %s, %s, %d, %s, %d\n", name, pass, address, type, phone, token);
                if (type == 1) {
                    foodiez.addUser(
                            new Admin(
                                    name,
                                    pass,
                                    address,
                                    phone,
                                    token,
                                    0,
                                    1,
                                    0
                            ));
                } else if (type == 2) {
                    foodiez.addUser(
                            new Customer(
                                    name,
                                    pass,
                                    address,
                                    phone,
                                    0,
                                    0,
                                    2,
                                    0
                            ));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        labelTitle = new javax.swing.JLabel();
        cover = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        labelRegister = new javax.swing.JLabel();
        textAddress = new javax.swing.JTextField();
        textPhone = new javax.swing.JTextField();
        comboType = new javax.swing.JComboBox<>();
        labelJenis = new javax.swing.JLabel();
        btnRegister = new javax.swing.JButton();
        labelSignin = new javax.swing.JLabel();
        textName = new javax.swing.JTextField();
        textPass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Foodiez - Your Food Order Solution");
        setBackground(new java.awt.Color(255, 255, 255));

        jPanel1.setBackground(new java.awt.Color(102, 51, 255));
        jPanel1.setForeground(new java.awt.Color(255, 255, 255));
        jPanel1.setToolTipText("");

        labelTitle.setFont(new java.awt.Font("Hobo Std", 0, 48)); // NOI18N
        labelTitle.setForeground(new java.awt.Color(255, 255, 255));
        labelTitle.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelTitle.setText("FOODIEZ");
        labelTitle.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        labelTitle.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        cover.setIcon(new javax.swing.ImageIcon(getClass().getResource("/view/register.png"))); // NOI18N
        cover.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.gray, java.awt.Color.darkGray, java.awt.Color.gray, java.awt.Color.lightGray));

        jLabel1.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Order makanan tanpa antre, tanpa repot!");
        jLabel1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 609, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE))))
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(cover, javax.swing.GroupLayout.PREFERRED_SIZE, 607, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(17, 17, 17))
        );
        jPanel1Layout.setVerticalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(labelTitle, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(cover, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(56, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        labelRegister.setFont(new java.awt.Font("Rockwell", 0, 36)); // NOI18N
        labelRegister.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRegister.setText("CREATE YOUR OWN ACCOUNT!");
        labelRegister.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        textAddress.setText("Silakan masukkan alamat...");
        textAddress.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textAddressMouseClicked(evt);
            }
        });

        textPhone.setText("Silakan masukkan no. HP...");
        textPhone.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textPhoneMouseClicked(evt);
            }
        });

        comboType.setModel(new javax.swing.DefaultComboBoxModel<>(new String[]{"Admin", "Customer"}));

        labelJenis.setText("Pilih jenis user");

        btnRegister.setBackground(new java.awt.Color(102, 51, 255));
        btnRegister.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        btnRegister.setForeground(new java.awt.Color(255, 255, 255));
        btnRegister.setText("REGISTER NOW!");
        btnRegister.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.darkGray, java.awt.Color.gray));
        btnRegister.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegisterActionPerformed(evt);
            }
        });

        labelSignin.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        labelSignin.setForeground(new java.awt.Color(102, 51, 255));
        labelSignin.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSignin.setText("Sudah memiliki akun? Klik di sini untuk Sign In!");
        labelSignin.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSigninMouseClicked(evt);
            }
        });

        textName.setText("Silakan masukkan nama...");
        textName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textNameMouseClicked(evt);
            }
        });

        textPass.setText("Pass");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(labelRegister, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                                        .addGroup(jPanel2Layout.createSequentialGroup()
                                                                .addComponent(labelJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                                .addComponent(comboType, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE))
                                                        .addComponent(textAddress)
                                                        .addComponent(textPass)
                                                        .addComponent(textPhone)
                                                        .addComponent(textName, javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(btnRegister, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(labelSignin, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE))
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
                jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGap(15, 15, 15)
                                .addComponent(labelRegister)
                                .addGap(87, 87, 87)
                                .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(textPass, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(35, 35, 35)
                                .addComponent(textAddress, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(29, 29, 29)
                                .addComponent(textPhone, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(32, 32, 32)
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(labelJenis, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(comboType, javax.swing.GroupLayout.PREFERRED_SIZE, 30, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnRegister, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(labelSignin)
                                .addGap(15, 15, 15))
        );

        textPhone.getAccessibleContext().setAccessibleName("");
        textPhone.getAccessibleContext().setAccessibleDescription("");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addGap(22, 22, 22)
                                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addGap(40, 40, 40)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnRegisterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegisterActionPerformed
        try {
            String username = "";
            Connection conn = data.getConnection();
            String getQuery = "SELECT * FROM m_user";

            if (!foodiez.getUserList().isEmpty()) {
                // Database connections
                Statement state = conn.createStatement();
                ResultSet rset = state.executeQuery(getQuery);

                if (rset.next()) {
                    username = rset.getString("user_name");
                }

                // Check if user name is already exist or not
                if (textName.getText().equals(username)) {
                    String message = "Username sudah digunakan!";
                    validation(message);
                } else {
                    // Validate all form data
                    if (textName.getText().equals("Silakan masukkan nama...")
                            || String.valueOf(textPass.getPassword()).equals("Pass")
                            || textAddress.getText().equals("Silakan masukkan alamat...")
                            || textPhone.getText().equals("Silakan masukkan no. HP...")
                    ) {
                        String message = "Silakan isi data Anda dengan benar!";
                        validation(message);

                        // Validate the password length
                    } else if (textPass.getPassword().length < 8) {
                        String message = "Password harus memiliki minimal 8 karakter";
                        validation(message);

                        // Validate all form data
                    } else if (textName.getText().equals("")
                            || textPass.getPassword().equals("")
                            || textAddress.getText().equals("")
                            || textPhone.getText().equals("")) {

                        String message = "Silakan isi data Anda dengan benar!";
                        validation(message);

                        // If all forms are filled, POST data to database
                    } else {
                        Random random = new Random();
                        int auth_token = random.nextInt(10000000);
                        int user_type = 0;

                        // Validate the user_type as "Admin" or "Customer"
                        if (comboType.getSelectedItem().toString().equals("Admin")) {
                            user_type = 1;
                        } else if (comboType.getSelectedItem().toString().equals("Customer")) {
                            user_type = 2;
                        }

                        // POST the Admin type user data
                        if (user_type == 1) {
                            User admin = new Admin(
                                    textName.getText().toLowerCase(),
                                    String.valueOf(textPass.getPassword()),
                                    textAddress.getText(),
                                    textPhone.getText(),
                                    auth_token,
                                    0,
                                    1,
                                    0
                            );

                            foodiez.addUser(admin);

                            try {
                                String insertQuery = "INSERT INTO m_user(user_name, user_pass, user_address, user_phone, user_type, auth_token, status) VALUES(?, ?, ?, ?, ?, ?, ?)";

                                PreparedStatement pstate = conn.prepareStatement(insertQuery);
                                pstate.setString(1, textName.getText().toLowerCase());
                                pstate.setString(2, String.valueOf(textPass.getPassword()));
                                pstate.setString(3, textAddress.getText());
                                pstate.setString(4, textPhone.getText());
                                pstate.setInt(5, user_type);
                                pstate.setInt(6, auth_token);
                                pstate.setInt(7, 0);

                                int rowAffected = pstate.executeUpdate();

                                if (rowAffected > 0) {
                                    System.out.println("Insert successfull");

                                    JOptionPane.showMessageDialog(
                                            this,
                                            "Akun Anda berhasil dibuat! Silakan sign-in untuk masuk ke aplikasi menggunakan akun Anda",
                                            "Register",
                                            JOptionPane.INFORMATION_MESSAGE
                                    );
                                    this.goToLogin();
                                }
                            } catch (SQLException e) {
                                System.out.println(e.getMessage());
                            }
                            // POST the Customer type user data
                        } else if (user_type == 2) {
                            User customer = new Customer(
                                    textName.getText().toLowerCase(),
                                    String.valueOf(textPass.getPassword()),
                                    textAddress.getText(),
                                    textPhone.getText(),
                                    0,
                                    0,
                                    2,
                                    0
                            );

                            foodiez.addUser(customer);

                            try {
                                String insertQuery = "INSERT INTO " +
                                        "m_user(user_name, user_pass, user_address, user_phone, user_type, auth_token, status, user_saldo) " +
                                        "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

                                PreparedStatement pstate = conn.prepareStatement(insertQuery);
                                pstate.setString(1, textName.getText().toLowerCase());
                                pstate.setString(2, String.valueOf(textPass.getPassword()));
                                pstate.setString(3, textAddress.getText());
                                pstate.setString(4, textPhone.getText());
                                pstate.setInt(5, user_type);
                                pstate.setInt(6, 0);
                                pstate.setInt(7, 0);
                                pstate.setInt(8, 0);

                                int rowAffected = pstate.executeUpdate();

                                if (rowAffected > 0) {
                                    System.out.println("Insert successfull");

                                    JOptionPane.showMessageDialog(
                                            this,
                                            "Akun Anda berhasil dibuat! Silakan sign-in untuk masuk ke aplikasi menggunakan akun Anda",
                                            "Register",
                                            JOptionPane.INFORMATION_MESSAGE
                                    );
                                    this.goToLogin();
                                }
                            } catch (SQLException e) {
                                System.out.println(e.getMessage());
                            }
                        }
                    }
                }
            } else {
                // Validate all form data
                if (textName.getText().equals("Silakan masukkan nama...")
                        || String.valueOf(textPass.getPassword()).equals("Pass")
                        || textAddress.getText().equals("Silakan masukkan alamat...")
                        || textPhone.getText().equals("Silakan masukkan no. HP...")
                ) {
                    String message = "Silakan isi data Anda dengan benar!";
                    validation(message);

                    // Validate the password length
                } else if (textPass.getPassword().length < 8) {
                    String message = "Password harus memiliki minimal 8 karakter";
                    validation(message);

                    // Validate all form data
                } else if (textName.getText().equals("")
                        || textPass.getPassword().equals("")
                        || textAddress.getText().equals("")
                        || textPhone.getText().equals("")) {

                    String message = "Silakan isi data Anda dengan benar!";
                    validation(message);

                    // If all forms are filled, POST data to database
                } else {
                    Random random = new Random();
                    int auth_token = random.nextInt(10000000);
                    int user_type = 0;

                    // Validate the user_type as "Admin" or "Customer"
                    if (comboType.getSelectedItem().toString().equals("Admin")) {
                        user_type = 1;
                    } else if (comboType.getSelectedItem().toString().equals("Customer")) {
                        user_type = 2;
                    }

                    // POST the Admin type user data
                    if (user_type == 1) {
                        User admin = new Admin(
                                textName.getText().toLowerCase(),
                                String.valueOf(textPass.getPassword()),
                                textAddress.getText(),
                                textPhone.getText(),
                                auth_token,
                                0,
                                1,
                                0
                        );

                        foodiez.addUser(admin);

                        try {
                            String insertQuery = "INSERT INTO m_user(user_name, user_pass, user_address, user_phone, user_type, auth_token, status) VALUES(?, ?, ?, ?, ?, ?, ?)";

                            PreparedStatement pstate = conn.prepareStatement(insertQuery);
                            pstate.setString(1, textName.getText().toLowerCase());
                            pstate.setString(2, String.valueOf(textPass.getPassword()));
                            pstate.setString(3, textAddress.getText());
                            pstate.setString(4, textPhone.getText());
                            pstate.setInt(5, user_type);
                            pstate.setInt(6, auth_token);
                            pstate.setInt(7, 0);

                            int rowAffected = pstate.executeUpdate();

                            if (rowAffected > 0) {
                                System.out.println("Insert successfull");

                                JOptionPane.showMessageDialog(
                                        this,
                                        "Akun Anda berhasil dibuat! Silakan sign-in untuk masuk ke aplikasi menggunakan akun Anda",
                                        "Register",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                                this.goToLogin();
                            }
                        } catch (SQLException e) {
                            System.out.println(e.getMessage());
                        }

                        // POST the Customer type user data
                    } else if (user_type == 2) {
                        User customer = new Customer(
                                textName.getText().toLowerCase(),
                                String.valueOf(textPass.getPassword()),
                                textAddress.getText(),
                                textPhone.getText(),
                                0,
                                0,
                                2,
                                0
                        );

                        foodiez.addUser(customer);

                        try {
                            String insertQuery = "INSERT INTO " +
                                    "m_user(user_name, user_pass, user_address, user_phone, user_type, auth_token, status, user_saldo) " +
                                    "VALUES(?, ?, ?, ?, ?, ?, ?, ?)";

                            PreparedStatement pstate = conn.prepareStatement(insertQuery);
                            pstate.setString(1, textName.getText().toLowerCase());
                            pstate.setString(2, String.valueOf(textPass.getPassword()));
                            pstate.setString(3, textAddress.getText());
                            pstate.setString(4, textPhone.getText());
                            pstate.setInt(5, user_type);
                            pstate.setInt(6, 0);
                            pstate.setInt(7, 0);
                            pstate.setInt(8, 0);

                            int rowAffected = pstate.executeUpdate();

                            if (rowAffected > 0) {
                                System.out.println("Insert successfull");

                                JOptionPane.showMessageDialog(
                                        this,
                                        "Akun Anda berhasil dibuat! Silakan sign-in untuk masuk ke aplikasi menggunakan akun Anda",
                                        "Register",
                                        JOptionPane.INFORMATION_MESSAGE
                                );
                                this.goToLogin();
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
    }//GEN-LAST:event_btnRegisterActionPerformed

    private void labelSigninMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSigninMouseClicked
        this.goToLogin();
    }//GEN-LAST:event_labelSigninMouseClicked

    private void textAddressMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textAddressMouseClicked
        if (textAddress.getText().equals("Silakan masukkan alamat...")) {
            textAddress.setText("");
        }
    }//GEN-LAST:event_textAddressMouseClicked

    private void textPhoneMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textPhoneMouseClicked
        if (textPhone.getText().equals("Silakan masukkan no. HP...")) {
            textPhone.setText("");
        }
    }//GEN-LAST:event_textPhoneMouseClicked

    private void textNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textNameMouseClicked
        if (textName.getText().equals("Silakan masukkan nama...")) {
            textName.setText("");
        }
    }//GEN-LAST:event_textNameMouseClicked

    private void validation(String message) {
        System.out.println("Debug message: " + message);
        JOptionPane.showMessageDialog(this, message, "Register", JOptionPane.ERROR_MESSAGE);
    }

    private void goToLogin() {
        Login login = new Login();

        this.dispose();
        login.setSize(1920, 1080);
        login.setVisible(true);
    }

    public static void main(String[] args) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html
         */
        try {
            for (LookAndFeelInfo info : getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException
                | InstantiationException
                | IllegalAccessException
                | UnsupportedLookAndFeelException ex) {
            getLogger(Register.class.getName()).log(SEVERE, null, ex);
        }

        invokeLater(() -> {
            Register app = new Register();

            app.setTitle("Foodiez - Your Food Order Solution");
            app.setSize(1920, 1080);
            app.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRegister;
    private javax.swing.JComboBox<String> comboType;
    private javax.swing.JLabel cover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelJenis;
    private javax.swing.JLabel labelRegister;
    private javax.swing.JLabel labelSignin;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JTextField textAddress;
    private javax.swing.JTextField textName;
    private javax.swing.JPasswordField textPass;
    private javax.swing.JTextField textPhone;
    // End of variables declaration//GEN-END:variables
}
