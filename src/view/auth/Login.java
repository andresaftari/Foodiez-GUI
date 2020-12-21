package view.auth;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import controller.DBConn;
import static java.awt.EventQueue.invokeLater;
import static java.util.logging.Logger.getLogger;
import static javax.swing.UIManager.*;
import static java.util.logging.Level.SEVERE;
import javax.swing.*;
import java.sql.*;
import java.util.Arrays;

import view.admin.AdminMain;
import view.customer.CustomerMain;

/**
 *
 * @author andre
 */
public class Login extends JFrame {
    private final MysqlDataSource data = new MysqlDataSource();

    public Login() {
        DBConn dbconn = new DBConn();
        dbconn.db_connection(data);

        // Check database connection
        try {
            Connection conn = data.getConnection();
            String getQuery = "SELECT * FROM m_user";

            System.out.println("Connection successfull!\n");

            // Database connections
            Statement state = conn.createStatement();
            ResultSet rset = state.executeQuery(getQuery);

            int saldo = 0;
            while (rset.next()) {
                String name = rset.getString("user_name");
                String address = rset.getString("user_address");
                int type = rset.getInt("user_type");
                String phone = rset.getString("user_phone");
                int token = rset.getInt("auth_token");

                if (rset.getInt("user_saldo") > 0) {
                    saldo = rset.getInt("user_saldo");
                }

                System.out.format("%s, %s, %d, %s, %d, %d\n", name, address, type, phone, token, saldo);
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
        btnLogin = new javax.swing.JButton();
        textName = new javax.swing.JTextField();
        labelSignup = new javax.swing.JLabel();
        textPass = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

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
                .addContainerGap(51, Short.MAX_VALUE))
        );

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setPreferredSize(new java.awt.Dimension(622, 779));

        labelRegister.setFont(new java.awt.Font("Rockwell", 0, 36)); // NOI18N
        labelRegister.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelRegister.setText("SIGN IN WITH YOUR ACCOUNT!");
        labelRegister.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnLogin.setBackground(new java.awt.Color(102, 51, 255));
        btnLogin.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        btnLogin.setForeground(new java.awt.Color(255, 255, 255));
        btnLogin.setText("SIGN IN!");
        btnLogin.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.darkGray, java.awt.Color.gray));
        btnLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLoginActionPerformed(evt);
            }
        });

        textName.setText("Silakan masukkan nama...");
        textName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textNameMouseClicked(evt);
            }
        });

        labelSignup.setFont(new java.awt.Font("Rockwell", 0, 14)); // NOI18N
        labelSignup.setForeground(new java.awt.Color(102, 51, 255));
        labelSignup.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        labelSignup.setText("Belum memiliki akun? Silakan daftar disini!");
        labelSignup.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                labelSignupMouseClicked(evt);
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
                    .addComponent(labelRegister, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 610, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textName)
                            .addComponent(btnLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(labelSignup, javax.swing.GroupLayout.DEFAULT_SIZE, 427, Short.MAX_VALUE)
                            .addComponent(textPass))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(labelRegister)
                .addGap(53, 53, 53)
                .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addComponent(textPass, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogin, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelSignup)
                .addGap(12, 12, 12))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 743, Short.MAX_VALUE))
                .addGap(39, 39, 39))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void labelSignupMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_labelSignupMouseClicked
        Register app = new Register();

        this.dispose();
        app.setSize(1920, 1080);
        app.setVisible(true);
    }//GEN-LAST:event_labelSignupMouseClicked

    private void btnLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLoginActionPerformed
        try {
            Connection conn = data.getConnection();
            String getQuery = "SELECT * FROM m_user WHERE user_name=?";
            String username = "", pass = "";
            int userType = 0, userState = 0;
            
            PreparedStatement pstate = conn.prepareStatement(getQuery);
            pstate.setString(1, textName.getText());

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
                        this,
                        "Akses ditolak karena akun Anda sedang di-suspend!",
                        "Authorization Check",
                        JOptionPane.WARNING_MESSAGE
                );
            }

            if (!textName.getText().equals(username) || !String.valueOf(textPass.getPassword()).equals(pass)) {
                String message = "Pastikan username dan password yang Anda masukan benar!";
                validation(message);

            } else if (textName.getText().equals("")
                    || textName.getText().equals("Silakan masukkan nama...")
                    || String.valueOf(textPass.getPassword()).equals("")
                    || String.valueOf(textPass.getPassword()).equals("Pass")) {

                String message = "Silakan isi data Anda dengan benar!";
                validation(message);
            } else {
                String query = "UPDATE m_user SET status = 1 WHERE user_name=?";

                PreparedStatement ps = conn.prepareStatement(query);
                ps.setString(1, username);
                ps.executeUpdate();
                                
                JOptionPane.showMessageDialog(
                        this,
                        "Hello, " + username, "Login",
                        JOptionPane.INFORMATION_MESSAGE
                );
                
                if (userType == 1) {
                    AdminMain page = new AdminMain(username);

                    this.dispose();
                    page.setSize(1920, 1080);
                    page.setVisible(true);
                } else if (userType == 2) {
                    CustomerMain page = new CustomerMain(username);

                    this.dispose();
                    page.setSize(1920, 1080);
                    page.setVisible(true);
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnLoginActionPerformed

    private void textNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textNameMouseClicked
        if (textName.getText().equals("Silakan masukkan nama...")) {
            textName.setText("");
        }
    }//GEN-LAST:event_textNameMouseClicked

    private void validation(String message) {
        System.out.println("Debug message: " + message);
        JOptionPane.showMessageDialog(this, message, "Login", JOptionPane.ERROR_MESSAGE);
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
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | UnsupportedLookAndFeelException ex) {
            getLogger(Login.class.getName()).log(SEVERE, null, ex);
        }

        invokeLater(() -> {
            Login app = new Login();

            app.setTitle("Foodiez - Your Food Order Solution");
            app.setSize(1920, 1080);
            app.setVisible(true);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnLogin;
    private javax.swing.JLabel cover;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel labelRegister;
    private javax.swing.JLabel labelSignup;
    private javax.swing.JLabel labelTitle;
    private javax.swing.JTextField textName;
    private javax.swing.JPasswordField textPass;
    // End of variables declaration//GEN-END:variables
}
