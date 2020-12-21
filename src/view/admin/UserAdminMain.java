package view.admin;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import controller.DBConn;
import controller.Foodiez;
import model.Admin;
import model.Customer;
import model.User;
import view.auth.Login;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import java.sql.*;

import static java.awt.EventQueue.invokeLater;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import static javax.swing.SwingConstants.CENTER;
import static javax.swing.UIManager.*;

/**
 * @author andre
 */
public class UserAdminMain extends JFrame {
    private final Foodiez foodiez = new Foodiez();
    private static String current_user = "";
    private final DefaultTableModel tableModel;

    private static final MysqlDataSource data = new MysqlDataSource();
    private String[][] rowData;

    public UserAdminMain(String current_user) {
        DBConn dbconn = new DBConn();
        dbconn.db_connection(data);

        UserAdminMain.current_user = current_user;
        initComponents();

        String[] header = {
                "Username",
                "Alamat",
                "No. HP",
                "Level",
                "Admin Token",
                "Saldo",
                "Status Login"
        };

        tableModel = new DefaultTableModel(rowData, header);
        tableUser.setModel(tableModel);

        TableColumnModel columnModel = tableUser.getColumnModel();
        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer();

        columnModel.getColumn(0).setMinWidth(100);
        columnModel.getColumn(0).setMaxWidth(120);

        columnModel.getColumn(1).setMinWidth(320);
        columnModel.getColumn(2).setMinWidth(100);
        columnModel.getColumn(3).setMinWidth(80);
        columnModel.getColumn(4).setMinWidth(100);
        columnModel.getColumn(5).setMinWidth(100);
        columnModel.getColumn(6).setMinWidth(100);

        cellRenderer.setHorizontalAlignment(CENTER);
        columnModel.getColumn(2).setCellRenderer(cellRenderer);
        columnModel.getColumn(3).setCellRenderer(cellRenderer);
        columnModel.getColumn(4).setCellRenderer(cellRenderer);
        columnModel.getColumn(5).setCellRenderer(cellRenderer);
        columnModel.getColumn(6).setCellRenderer(cellRenderer);

        this.loadData();
    }

    private void loadData() {
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

                if (type == 1)
                    foodiez.addUser(new Admin(username, pass, address, phone, auth, saldo, status, type));
                else if (type == 2)
                    foodiez.addUser(new Customer(username, pass, address, phone, auth, saldo, status, type));

                int index = foodiez.getUserList().size() - 1;

                tableModel.addRow(new Object[]{
                        foodiez.getUserList().get(index).getUser_name(),
                        foodiez.getUserList().get(index).getUser_address(),
                        foodiez.getUserList().get(index).getUser_phone(),
                        foodiez.getUserList().get(index).getUserType(),
                        foodiez.getUserList().get(index).getUser_token(),
                        foodiez.getUserList().get(index).getSaldo(),
                        foodiez.getUserList().get(index).getLoginStatus()
                });
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel5 = new javax.swing.JPanel();
        btnEditUserState = new javax.swing.JButton();
        labelStatus2 = new javax.swing.JLabel();
        comboUserState = new javax.swing.JComboBox<>();
        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnToTransaction = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnLogout = new javax.swing.JButton();
        btnProductManagement = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableUser = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        btnEditUserState.setText("EDIT USER");
        btnEditUserState.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditUserStateActionPerformed(evt);
            }
        });

        labelStatus2.setText("Ubah Status");

        comboUserState.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Online", "Offline", "Suspended" }));

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(labelStatus2)
                .addGap(18, 18, 18)
                .addComponent(comboUserState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(37, Short.MAX_VALUE))
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEditUserState, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelStatus2, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(comboUserState, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditUserState, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("User Management");
        jLabel3.setToolTipText("");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnToTransaction.setBackground(new java.awt.Color(0, 0, 153));
        btnToTransaction.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        btnToTransaction.setForeground(new java.awt.Color(255, 255, 255));
        btnToTransaction.setText("TO TRANSACTION");
        btnToTransaction.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnToTransactionActionPerformed(evt);
            }
        });

        jSeparator2.setBackground(new java.awt.Color(0, 0, 0));

        btnLogout.setBackground(new java.awt.Color(153, 51, 0));
        btnLogout.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("LOGOUT");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnProductManagement.setBackground(new java.awt.Color(0, 0, 153));
        btnProductManagement.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        btnProductManagement.setForeground(new java.awt.Color(255, 255, 255));
        btnProductManagement.setText("TO PRODUCT MANAGEMENT");
        btnProductManagement.setToolTipText("");
        btnProductManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnProductManagementActionPerformed(evt);
            }
        });

        jSeparator3.setBackground(new java.awt.Color(0, 0, 0));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(12, 12, 12)
                        .addComponent(jSeparator2))
                    .addComponent(btnToTransaction, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btnProductManagement, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jSeparator3)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnProductManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnToTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 313, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("WELCOME TO FOODIEZ ADMIN");

        jLabel5.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Manage your products and users are now easier than you think!");

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jLabel5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 958, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableUser.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null},
                {null, null, null, null, null, null, null}
            },
            new String [] {
                "Username", "Alamat", "No. HP", "Level", "Admin Token", "Saldo", "Login Status"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableUser.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableUser.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableUser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableUserMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableUser);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jPanel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void goToLogin() {
        Login login = new Login();

        this.dispose();
        login.setSize(1920, 1080);
        login.setVisible(true);
    }

    private void authorizationCheck() {
        try {
            Connection conn = data.getConnection();
            String getQuery = "SELECT * FROM m_user WHERE user_name=?";

            PreparedStatement ps = conn.prepareStatement(getQuery);
            ps.setString(1, current_user);

            // Database connections
            ResultSet rs = ps.executeQuery();
            int user_state = 0;

            while (rs.next()) {
                user_state = rs.getInt("status");
            }

            if (current_user == null || current_user.equals("") || current_user.equals("null") || user_state == 0) {
                JOptionPane.showMessageDialog(
                        this,
                        "Akses ditolak karena Anda belum melakukan login sebagai Admin!",
                        "Authorization Check",
                        JOptionPane.WARNING_MESSAGE
                );
                this.goToLogin();
            } else if (user_state == 2) {
                JOptionPane.showMessageDialog(
                        this,
                        "Akses ditolak karena akun Anda sedang di-suspend!",
                        "Authorization Check",
                        JOptionPane.WARNING_MESSAGE
                );
                this.goToLogin();
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
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

            JOptionPane.showMessageDialog(this, current_user + " berhasil logout!", "Admin Page", 1);
            this.goToLogin();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnToTransactionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnToTransactionActionPerformed
        TransAdminMain app = new TransAdminMain(current_user);

        this.dispose();
        app.setSize(1920, 1080);
        app.setVisible(true);
    }//GEN-LAST:event_btnToTransactionActionPerformed

    private void btnProductManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnProductManagementActionPerformed
        AdminMain app = new AdminMain(current_user);

        this.dispose();
        app.setSize(1920, 1080);
        app.setVisible(true);
    }//GEN-LAST:event_btnProductManagementActionPerformed

    private void btnEditUserStateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditUserStateActionPerformed
        int row = tableUser.getSelectedRow();

        String status = comboUserState.getSelectedItem().toString();
        int login = 0;

        switch (status) {
            case "Online":
                login = 1;
                break;

            case "Offline":
                login = 0;
                break;

            case "Suspended":
                login = 2;
                break;
        }

        foodiez.getUserList().get(row).setStatus(login);
        tableModel.setRowCount(0);

        for (int i = 0; i < foodiez.getUserList().size(); i++) {
            tableModel.addRow(new Object[]{
                    foodiez.getUserList().get(i).getUser_name(),
                    foodiez.getUserList().get(i).getUser_address(),
                    foodiez.getUserList().get(i).getUser_phone(),
                    foodiez.getUserList().get(i).getUserType(),
                    foodiez.getUserList().get(i).getUser_token(),
                    foodiez.getUserList().get(i).getSaldo(),
                    foodiez.getUserList().get(i).getLoginStatus()
            });
        }

        String name = foodiez.getUserList().get(row).getUser_name();

        try {
            Connection conn = data.getConnection();
            String getQuery = "UPDATE m_user SET status=? WHERE user_name=?";

            PreparedStatement ps = conn.prepareStatement(getQuery);
            ps.setInt(1, login);
            ps.setString(2, name);
            ps.executeUpdate();

            JOptionPane.showMessageDialog(
                    this,
                    "Status akun berhasil diubah!",
                    "Admin Page",
                    JOptionPane.INFORMATION_MESSAGE
            );
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }//GEN-LAST:event_btnEditUserStateActionPerformed

    private void tableUserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableUserMouseClicked
        int row = tableUser.getSelectedRow();

        String state = foodiez.getUserList().get(row).getLoginStatus();

        for (int i = 0; i < comboUserState.getItemCount(); i++) {
            if (comboUserState.getItemAt(i).equals(String.valueOf(state))) {
                comboUserState.setSelectedIndex(i);
            }
        }
    }//GEN-LAST:event_tableUserMouseClicked

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
        } catch (ClassNotFoundException | UnsupportedLookAndFeelException | IllegalAccessException | InstantiationException ex) {
            getLogger(UserAdminMain.class.getName()).log(SEVERE, null, ex);
        }
        //</editor-fold>

        invokeLater(() -> {
            UserAdminMain app = new UserAdminMain(current_user);

            app.setTitle("Foodiez - Admin app");
            app.setSize(1920, 1080);
            app.setVisible(true);
            app.authorizationCheck();
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditUserState;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnProductManagement;
    private javax.swing.JButton btnToTransaction;
    private javax.swing.JComboBox<String> comboUserState;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel labelStatus2;
    private javax.swing.JTable tableUser;
    // End of variables declaration//GEN-END:variables
}
