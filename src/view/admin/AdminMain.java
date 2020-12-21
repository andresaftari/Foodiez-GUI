package view.admin;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import controller.DBConn;
import controller.Foodiez;
import model.Product;
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
public class AdminMain extends JFrame {

    private final Foodiez foodiez = new Foodiez();
    private static String current_user = "";
    private final DefaultTableModel tableModel;

    private static final MysqlDataSource data = new MysqlDataSource();
    private String[][] rowData;

    public AdminMain(String current_user) {
        DBConn dbconn = new DBConn();
        dbconn.db_connection(data);

        AdminMain.current_user = current_user;
        initComponents();

        String[] header = {
                "Nama Produk",
                "Harga Produk",
                "Detail Produk",
                "Stok Produk",
                "Status Ketersediaan"
        };

        tableModel = new DefaultTableModel(rowData, header);
        tableProduct.setModel(tableModel);

        TableColumnModel columnModel = tableProduct.getColumnModel();
        DefaultTableCellRenderer cellRender = new DefaultTableCellRenderer();

        columnModel.getColumn(0).setMinWidth(200);
        columnModel.getColumn(0).setMaxWidth(120);
        columnModel.getColumn(1).setMaxWidth(140);
        columnModel.getColumn(1).setMinWidth(140);
        columnModel.getColumn(2).setMinWidth(200);
        columnModel.getColumn(3).setMaxWidth(140);
        columnModel.getColumn(3).setMinWidth(140);
        columnModel.getColumn(4).setMaxWidth(140);
        columnModel.getColumn(4).setMinWidth(140);

        cellRender.setHorizontalAlignment(CENTER);
        columnModel.getColumn(1).setCellRenderer(cellRender);
        columnModel.getColumn(3).setCellRenderer(cellRender);
        columnModel.getColumn(4).setCellRenderer(cellRender);

        this.loadData();
    }

    private void loadData() {
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

                foodiez.addProduct(new Product(
                        productName,
                        productDesc,
                        productQty,
                        productStatus,
                        productPrice
                ));

                int index = foodiez.getProductList().size() - 1;

                tableModel.addRow(new Object[]{
                        foodiez.getProductList().get(index).getProduct_name(),
                        foodiez.getProductList().get(index).getProduct_price(),
                        foodiez.getProductList().get(index).getProduct_desc(),
                        foodiez.getProductList().get(index).getProduct_qty(),
                        foodiez.getProductList().get(index).getAvailableStatus()
                });
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnToTransaction = new javax.swing.JButton();
        jSeparator2 = new javax.swing.JSeparator();
        btnLogout = new javax.swing.JButton();
        btnUserManagement = new javax.swing.JButton();
        jSeparator3 = new javax.swing.JSeparator();
        jPanel3 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProduct = new javax.swing.JTable();
        jPanel2 = new javax.swing.JPanel();
        textPrice = new javax.swing.JTextField();
        textName = new javax.swing.JTextField();
        textQuantity = new javax.swing.JTextField();
        textDesc = new javax.swing.JTextField();
        btnEditProduct = new javax.swing.JButton();
        btnAddProduct = new javax.swing.JButton();
        btnDeleteProduct = new javax.swing.JButton();
        labelStatus = new javax.swing.JLabel();
        comboStatus = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Product Management");
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

        btnUserManagement.setBackground(new java.awt.Color(0, 0, 153));
        btnUserManagement.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        btnUserManagement.setForeground(new java.awt.Color(255, 255, 255));
        btnUserManagement.setText("TO USER MANAGEMENT");
        btnUserManagement.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUserManagementActionPerformed(evt);
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
                    .addComponent(btnUserManagement, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
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
                .addComponent(btnUserManagement, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnToTransaction, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 12, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jLabel1.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("WELCOME TO FOODIEZ ADMIN");

        jLabel2.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Manage your products and users are now easier than you think!");

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 756, Short.MAX_VALUE)
                .addGap(208, 208, 208))
            .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel2)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        tableProduct.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "Nama Produk", "Harga Produk", "Detail Produk", "Stok Produk", "Status Ketersediaan"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tableProduct.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableProduct.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        tableProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableProduct);

        textPrice.setText("Harga Produk");
        textPrice.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textPriceMouseClicked(evt);
            }
        });

        textName.setText("Nama Produk");
        textName.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textNameMouseClicked(evt);
            }
        });

        textQuantity.setText("Stok Produk");
        textQuantity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textQuantityMouseClicked(evt);
            }
        });

        textDesc.setText("Deskripsi Produk");
        textDesc.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textDescMouseClicked(evt);
            }
        });

        btnEditProduct.setText("EDIT PRODUCT");
        btnEditProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditProductActionPerformed(evt);
            }
        });

        btnAddProduct.setText("ADD PRODUCT");
        btnAddProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddProductActionPerformed(evt);
            }
        });

        btnDeleteProduct.setText("DELETE PRODUCT");
        btnDeleteProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteProductActionPerformed(evt);
            }
        });

        labelStatus.setText("Status Ketersediaan");

        comboStatus.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Ada", "Pre Order", "Habis" }));

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(textQuantity)
                    .addComponent(textPrice)
                    .addComponent(textDesc, javax.swing.GroupLayout.DEFAULT_SIZE, 473, Short.MAX_VALUE)
                    .addComponent(textName)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(labelStatus)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEditProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDeleteProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 473, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnAddProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btnEditProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(textName)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(textPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(12, 12, 12)
                        .addComponent(textDesc, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(textQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(labelStatus, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboStatus, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnDeleteProduct, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(41, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jScrollPane1)
                    .addComponent(jPanel2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnAddProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddProductActionPerformed
        try {
            String name = "", desc = "";
            Connection conn = data.getConnection();

            if (!foodiez.getProductList().isEmpty()) {
                String checkQuery = "SELECT * FROM m_product";

                Statement st = conn.createStatement();
                ResultSet rs = st.executeQuery(checkQuery);

                if (rs.next()) {
                    name = rs.getString("product_name");
                    desc = rs.getString("product_desc");
                }

                if (textName.getText().equals(name) && textDesc.getText().equals(desc)) {
                    JOptionPane.showMessageDialog(
                            this,
                            "Data produk sudah ada, silakan di-update!",
                            "Add Product",
                            JOptionPane.WARNING_MESSAGE
                    );
                } else {
                    String insertQuery = "INSERT INTO m_product(product_name, product_desc, product_qty, product_price, is_available) VALUES (?, ?, ?, ?, ?)";

                    int status = 0;
                    switch (comboStatus.getSelectedItem().toString()) {
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
                    ps.setString(1, textName.getText());
                    ps.setString(2, textDesc.getText());
                    ps.setInt(3, Integer.parseInt(textQuantity.getText()));
                    ps.setInt(4, Integer.parseInt(textPrice.getText()));
                    ps.setInt(5, status);
                    ps.executeUpdate();

                    foodiez.addProduct(new Product(
                            textName.getText(),
                            textDesc.getText(),
                            Integer.parseInt(textQuantity.getText()),
                            status,
                            Integer.parseInt(textPrice.getText())
                    ));
                    int index = foodiez.getProductList().size() - 1;

                    tableModel.addRow(new Object[]{
                            foodiez.getProductList().get(index).getProduct_name(),
                            foodiez.getProductList().get(index).getProduct_price(),
                            foodiez.getProductList().get(index).getProduct_desc(),
                            foodiez.getProductList().get(index).getProduct_qty(),
                            foodiez.getProductList().get(index).getAvailableStatus()
                    });

                    JOptionPane.showMessageDialog(
                            this,
                            "Produk berhasil ditambahkan!",
                            "Admin Page",
                            JOptionPane.INFORMATION_MESSAGE
                    );
                }
            } else {
                String insertQuery = "INSERT INTO m_product(product_name, product_desc, product_qty, product_price, is_available) VALUES (?, ?, ?, ?, ?)";

                int status = 0;
                switch (comboStatus.getSelectedItem().toString()) {
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
                ps.setString(1, textName.getText());
                ps.setString(2, textDesc.getText());
                ps.setInt(3, Integer.parseInt(textQuantity.getText()));
                ps.setInt(4, Integer.parseInt(textPrice.getText()));
                ps.setInt(5, status);
                ps.executeUpdate();

                foodiez.addProduct(new Product(
                        textName.getText(),
                        textDesc.getText(),
                        Integer.parseInt(textQuantity.getText()),
                        status,
                        Integer.parseInt(textPrice.getText())
                ));
                int index = foodiez.getProductList().size() - 1;

                tableModel.addRow(new Object[]{
                        foodiez.getProductList().get(index).getProduct_name(),
                        foodiez.getProductList().get(index).getProduct_price(),
                        foodiez.getProductList().get(index).getProduct_desc(),
                        foodiez.getProductList().get(index).getProduct_qty(),
                        foodiez.getProductList().get(index).getAvailableStatus()
                });

                JOptionPane.showMessageDialog(
                        this,
                        "Produk berhasil ditambahkan!",
                        "Admin Page",
                        JOptionPane.INFORMATION_MESSAGE
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnAddProductActionPerformed

    private void tableProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductMouseClicked
        int row = tableProduct.getSelectedRow();

        String name = foodiez.getProductList().get(row).getProduct_name();
        String desc = foodiez.getProductList().get(row).getProduct_desc();
        String status = foodiez.getProductList().get(row).getAvailableStatus();
        int harga = foodiez.getProductList().get(row).getProduct_price();
        int stok = foodiez.getProductList().get(row).getProduct_qty();

        textName.setText(name);
        textDesc.setText(desc);
        textPrice.setText(String.valueOf(harga));
        textQuantity.setText(String.valueOf(stok));

        int is_available = 0;
        switch (status) {
            case "Ada":
                is_available = 0;
                break;
            case "Pre Order":
                is_available = 1;
                break;
            case "Habis":
                is_available = 2;
                break;
        }
        comboStatus.setSelectedIndex(is_available);
    }//GEN-LAST:event_tableProductMouseClicked

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

            JOptionPane.showMessageDialog(
                    this,
                    current_user + " berhasil logout!",
                    "Admin Page",
                    1
            );
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

    private void btnUserManagementActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUserManagementActionPerformed
        UserAdminMain app = new UserAdminMain(current_user);

        this.dispose();
        app.setSize(1920, 1080);
        app.setVisible(true);
    }//GEN-LAST:event_btnUserManagementActionPerformed

    private void btnEditProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditProductActionPerformed
        int row = tableProduct.getSelectedRow();

        if (!textName.getText().equals("")
                && !textDesc.getText().equals("")
                && !textPrice.getText().equals("")
                && !textQuantity.getText().equals("")) {
            String getEditName = textName.getText();
            String getEditDesc = textDesc.getText();
            int getEditPrice = Integer.parseInt(textPrice.getText());
            int getEditQuantity = Integer.parseInt(textQuantity.getText());

            String availability = comboStatus.getSelectedItem().toString();
            int getIs_available = 0;

            String productName = foodiez.getProductList().get(row).getProduct_name();

            switch (availability) {
                case "Ada":
                    getIs_available = 1;
                    break;

                case "Pre Order":
                    getIs_available = 2;
                    break;

                case "Habis":
                    getIs_available = 0;
                    break;
            }

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
                psUpdate.setString(6, productName);
                int affected = psUpdate.executeUpdate();

                if (affected > 0) {
                    if (getEditQuantity == 0) {
                        String setStatus = "UPDATE m_product SET is_available = 0 WHERE product_name=?";

                        PreparedStatement pStatus = conn.prepareStatement(setStatus);
                        pStatus.setString(1, getEditName);
                        pStatus.executeUpdate();
                    }

                    JOptionPane.showMessageDialog(
                            this,
                            "Pengubahan data produk berhasil!",
                            "Admin Page",
                            1
                    );
                }
                foodiez.getProductList().clear();

                tableModel.setRowCount(0);
                tableModel.fireTableRowsDeleted(0, foodiez.getProductList().size());
                this.loadData();
            } catch (SQLException e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_btnEditProductActionPerformed

    private void btnDeleteProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteProductActionPerformed
        int row = tableProduct.getSelectedRow();
        String productName = foodiez.getProductList().get(row).getProduct_name();

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
                        this,
                        "Penghapusan data produk berhasil!",
                        "Admin Page",
                        1
                );
            }
            foodiez.getProductList().clear();

            tableModel.setRowCount(0);
            tableModel.fireTableRowsDeleted(0, foodiez.getProductList().size());
            this.loadData();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnDeleteProductActionPerformed

    private void textNameMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textNameMouseClicked
        if (textName.getText().equals("Nama Produk")) {
            textName.setText("");
        }
    }//GEN-LAST:event_textNameMouseClicked

    private void textPriceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textPriceMouseClicked
        if (textPrice.getText().equals("Harga Produk")) {
            textPrice.setText("");
        }
    }//GEN-LAST:event_textPriceMouseClicked

    private void textDescMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textDescMouseClicked
        if (textDesc.getText().equals("Deskripsi Produk")) {
            textDesc.setText("");
        }
    }//GEN-LAST:event_textDescMouseClicked

    private void textQuantityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textQuantityMouseClicked
        if (textQuantity.getText().equals("Stok Produk")) {
            textQuantity.setText("");
        }
    }//GEN-LAST:event_textQuantityMouseClicked

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
            getLogger(AdminMain.class.getName()).log(SEVERE, null, ex);
        }
        //</editor-fold>

        invokeLater(() -> {
            AdminMain app = new AdminMain(current_user);

            app.setTitle("Foodiez - Admin app");
            app.setSize(1920, 1080);
            app.setVisible(true);
            app.authorizationCheck();
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddProduct;
    private javax.swing.JButton btnDeleteProduct;
    private javax.swing.JButton btnEditProduct;
    private javax.swing.JButton btnLogout;
    private javax.swing.JButton btnToTransaction;
    private javax.swing.JButton btnUserManagement;
    private javax.swing.JComboBox<String> comboStatus;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel labelStatus;
    private javax.swing.JTable tableProduct;
    private javax.swing.JTextField textDesc;
    private javax.swing.JTextField textName;
    private javax.swing.JTextField textPrice;
    private javax.swing.JTextField textQuantity;
    // End of variables declaration//GEN-END:variables
}
