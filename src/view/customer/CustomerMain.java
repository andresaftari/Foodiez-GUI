package view.customer;

import com.mysql.jdbc.jdbc2.optional.MysqlDataSource;
import controller.DBConn;
import controller.Foodiez;

import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;

import static java.awt.EventQueue.invokeLater;
import static java.util.logging.Level.SEVERE;
import static java.util.logging.Logger.getLogger;
import static javax.swing.SwingConstants.CENTER;
import static javax.swing.UIManager.*;

/**
 * @author andre
 */
public class CustomerMain extends JFrame {

    private final Foodiez foodiez = new Foodiez();
    private static String current_user = "";
    private final DefaultTableModel tableModel;

    private String[][] rowData;

    public CustomerMain(String current_user) {
        MysqlDataSource data = new MysqlDataSource();
        DBConn conns = new DBConn();
        conns.db_connection(data);

        CustomerMain.current_user = current_user;
        initComponents();

        String[] header = {
                "Nama Produk",
                "Harga Produk",
                "Detail Produk",
                "Stok Produk",
                "Status Ketersediaan"
        };

        textName.setEditable(false);
        textPrice.setEditable(false);

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

        labelTotal.setText("Total Harga: Rp.0");
        foodiez.loadProductData(tableModel);
        foodiez.loadSaldoUser(current_user, labelSaldo);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        btnLogout = new javax.swing.JButton();
        btnAddBalances = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tableProduct = new javax.swing.JTable();
        textName = new javax.swing.JTextField();
        textPrice = new javax.swing.JTextField();
        textQuantity = new javax.swing.JTextField();
        btnBuy = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        labelSaldo = new javax.swing.JLabel();
        labelTotal = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel1.setBackground(new java.awt.Color(153, 153, 255));

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Rockwell", 1, 24)); // NOI18N
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Customer App");
        jLabel3.setToolTipText("");

        jSeparator1.setBackground(new java.awt.Color(0, 0, 0));
        jSeparator1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));

        btnLogout.setBackground(new java.awt.Color(153, 51, 0));
        btnLogout.setFont(new java.awt.Font("Rockwell", 1, 14)); // NOI18N
        btnLogout.setForeground(new java.awt.Color(255, 255, 255));
        btnLogout.setText("LOGOUT");
        btnLogout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLogoutActionPerformed(evt);
            }
        });

        btnAddBalances.setBackground(new java.awt.Color(0, 0, 153));
        btnAddBalances.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        btnAddBalances.setForeground(new java.awt.Color(255, 255, 255));
        btnAddBalances.setText("Tambah Saldo");
        btnAddBalances.setToolTipText("");
        btnAddBalances.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddBalancesActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
                jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btnAddBalances, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                                        .addGroup(jPanel1Layout.createSequentialGroup()
                                                .addContainerGap()
                                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jSeparator1)
                                                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                        .addComponent(btnLogout, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                                .addComponent(btnAddBalances, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(btnLogout, javax.swing.GroupLayout.PREFERRED_SIZE, 51, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );

        jLabel4.setFont(new java.awt.Font("Rockwell", 0, 24)); // NOI18N
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("WELCOME TO FOODIEZ");

        jLabel5.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("Are you hungry? Just one-tap the food here and order it!");

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

        tableProduct.setModel(new javax.swing.table.DefaultTableModel(
                new Object[][]{
                        {null, null, null, null}
                },
                new String[]{
                        "Nama Produk", "Deskripsi Produk", "Harga Produk", "Status Ketersediaan"
                }
        ) {
            Class[] types = new Class[]{
                    java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean[]{
                    false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types[columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit[columnIndex];
            }
        });
        tableProduct.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tableProductMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tableProduct);

        textName.setText("Nama Produk");

        textPrice.setText("Harga Produk");

        textQuantity.setText("Jumlah Beli");
        textQuantity.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                textQuantityMouseClicked(evt);
            }
        });

        btnBuy.setBackground(new java.awt.Color(51, 204, 0));
        btnBuy.setFont(new java.awt.Font("Rockwell", 1, 18)); // NOI18N
        btnBuy.setForeground(new java.awt.Color(255, 255, 255));
        btnBuy.setText("BELI PRODUK!");
        btnBuy.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        btnBuy.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBuyActionPerformed(evt);
            }
        });

        jLabel1.setText("Nama Produk");

        jLabel2.setText("Harga Produk");

        jLabel6.setText("Jumlah Beli");

        labelSaldo.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        labelSaldo.setText("Sisa saldo kamu");

        labelTotal.setFont(new java.awt.Font("Rockwell", 0, 18)); // NOI18N
        labelTotal.setText("Total Harga");

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
                                        .addComponent(textName)
                                        .addComponent(textPrice)
                                        .addComponent(textQuantity)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel2, javax.swing.GroupLayout.Alignment.LEADING)
                                                        .addComponent(jLabel6, javax.swing.GroupLayout.Alignment.LEADING))
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 654, Short.MAX_VALUE)
                                                .addComponent(btnBuy, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addGap(0, 0, Short.MAX_VALUE)
                                                .addComponent(labelTotal, javax.swing.GroupLayout.PREFERRED_SIZE, 231, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(labelSaldo, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 338, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textName, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel2)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textPrice, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(jLabel6)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(textQuantity, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(labelSaldo)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 40, Short.MAX_VALUE)
                                                .addComponent(labelTotal)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btnBuy, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(7, 7, 7))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnLogoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLogoutActionPerformed
        try {
            foodiez.logoutAccount(
                    this,
                    this,
                    current_user
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnLogoutActionPerformed

    private void btnAddBalancesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddBalancesActionPerformed
        String getSaldo = JOptionPane.showInputDialog(this, "Besar saldo yang akan diisi: ");
        int setSaldo = Integer.parseInt(getSaldo);

        try {
            foodiez.updateSaldoUser(
                    this,
                    current_user,
                    setSaldo,
                    labelSaldo
            );
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }//GEN-LAST:event_btnAddBalancesActionPerformed

    private void btnBuyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBuyActionPerformed
        int row = tableProduct.getSelectedRow();

        String name = textName.getText();
        String priceCheck = textPrice.getText();
        String buyQtyCheck = textQuantity.getText();

        if (name.equals("Nama Produk") || priceCheck.equals("Harga Produk") || buyQtyCheck.equals("Jumlah Beli")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Periksa kembali menu yang akan dibeli!",
                    "Customer App",
                    JOptionPane.WARNING_MESSAGE
            );
        } else if (buyQtyCheck.equals("") || buyQtyCheck.equals("0")) {
            JOptionPane.showMessageDialog(
                    this,
                    "Minimal pembelian produk adalah 1 produk",
                    "Customer App",
                    JOptionPane.WARNING_MESSAGE
            );
        } else {
            int price = Integer.parseInt(textPrice.getText());
            int buyQty = Integer.parseInt(textQuantity.getText());

            int stockBarang = foodiez.getProductList().get(row).getProduct_qty();
            int sisaStock = foodiez.getProductList().get(row).getProduct_qty() - buyQty;

            try {
                foodiez.executeTransaction(
                        this,
                        current_user,
                        name,
                        price,
                        buyQty,
                        stockBarang,
                        sisaStock,
                        row,
                        labelSaldo,
                        tableModel
                );
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }//GEN-LAST:event_btnBuyActionPerformed

    private void tableProductMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tableProductMouseClicked
        int row = tableProduct.getSelectedRow();

        String name = foodiez.getProductList().get(row).getProduct_name();
        int price = foodiez.getProductList().get(row).getProduct_price();

        textName.setText(name);
        textPrice.setText(String.valueOf(price));
        labelTotal.setText("Total Harga: Rp.0");

        if (!textQuantity.getText().equals("Jumlah Beli")) {
            labelTotal.setText("Total Harga: Rp" + price * Integer.parseInt(textQuantity.getText()));
        }
    }//GEN-LAST:event_tableProductMouseClicked

    private void textQuantityMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_textQuantityMouseClicked
        if (textQuantity.getText().equals("Jumlah Beli")) textQuantity.setText("");
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
            getLogger(CustomerMain.class.getName()).log(SEVERE, null, ex);
        }
        //</editor-fold>

        invokeLater(() -> {
            CustomerMain app = new CustomerMain(current_user);

            app.setTitle("Foodiez - Your Food Order Solution");
            app.setSize(1920, 1080);
            app.setVisible(true);
            app.foodiez.authorizationCheck(app, app, current_user);
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAddBalances;
    private javax.swing.JButton btnBuy;
    private javax.swing.JButton btnLogout;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JLabel labelSaldo;
    private javax.swing.JLabel labelTotal;
    private javax.swing.JTable tableProduct;
    private javax.swing.JTextField textName;
    private javax.swing.JTextField textPrice;
    private javax.swing.JTextField textQuantity;
    // End of variables declaration//GEN-END:variables
}
