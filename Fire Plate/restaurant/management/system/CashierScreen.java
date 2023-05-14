
package restaurant.management.system;

import codes.DBconnect;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;

public class CashierScreen extends javax.swing.JFrame {
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    private final String loggedInUsername;
    private final String loggedInCusname;
    

    public CashierScreen(String loggedInUsername, String loggedInCusname) {
        initComponents();
        
        this.loggedInUsername = loggedInUsername;
        this.loggedInCusname = loggedInCusname;
        
        
        conn = (Connection) DBconnect.connect();
        
        loadcategories();
        loadtable();
        combolistner();
        loadorderid();
        loadcashier();
        loadcustomer();
        
    }

    private void combolistner(){
        jComboBox1.addActionListener (new ActionListener () {
            public void actionPerformed(ActionEvent e) {
                loadtable();
            }
        });
    }

    
    public final void loadcategories(){
        try{
            String sql = "SELECT name from category";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                
                String name = rs.getString("name");
                
                DefaultComboBoxModel<String> model = (DefaultComboBoxModel<String>) jComboBox1.getModel();
                model.addElement(name);
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
                try {
                    rs.close();
                    pst.close();
            } catch (SQLException e) {
            }
        }
    }
    
    public final void loadtable(){
        try{
            Object selectedItem = jComboBox1.getSelectedItem();

            if (selectedItem != null) {
                
                String selectedValue = selectedItem.toString();

                String sql = "SELECT catid from category where name='"+selectedValue+"'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                
                int cat_id=0;
                
                if(rs.next()){
                    cat_id = rs.getInt("catid");

                    rs.close();
                    pst.close();
                    
                    try{
                        String sql1 = "SELECT iid as `Item ID` ,name as Name, price as Price, qty as Quantity from item where catid='"+cat_id+"'";
                        pst = conn.prepareStatement(sql1);
                        rs = pst.executeQuery();

                        jTable1.setModel(DbUtils.resultSetToTableModel(rs));
                        
                    }catch(Exception e){
                        JOptionPane.showMessageDialog(null,e);
                    }finally{
                        try {
                            rs.close();
                            pst.close();
                    } catch (Exception e) {
                    }
                }
                
                
            } 
                
            }
            
            
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
                try {
                    rs.close();
                    pst.close();
            } catch (SQLException e) {
            }
        }
    }
    
    public void tabledata(){
        
        String text = qty.getText();
        int item_qty;
        int selectedRow = jTable1.getSelectedRow();
        
        if(!text.isEmpty()){
            item_qty = Integer.parseInt(text);
            
            if (selectedRow != -1) {
        
                int r = jTable1.getSelectedRow();
                String id = jTable1.getValueAt(r, 0).toString();

                try{
                    String sql = "SELECT iid as `Item ID` ,name as Name, price as `Unit Price` from item where iid='"+id+"'";
                    pst = conn.prepareStatement(sql);
                    rs = pst.executeQuery();

                    if (rs.next()) {

                    int column1Value = rs.getInt("Item ID");
                    String column2Value = rs.getString("Name");
                    double column3Value = rs.getDouble("Unit Price");


                    int billColumn1Value = column1Value;
                    String billColumn2Value = column2Value;
                    double billColumn3Value = column3Value;

                    double total = column3Value*item_qty;
                    
                    DecimalFormat decimalFormat = new DecimalFormat("0.00");
                    String f_billColumn3Value = decimalFormat.format(billColumn3Value);
                    String f_total = decimalFormat.format(total);


                    DefaultTableModel model = (DefaultTableModel) billtable.getModel();
                    model.addRow(new Object[] { billColumn1Value, billColumn2Value, f_billColumn3Value, item_qty, f_total });

                }


                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, e);
                }finally{
                        try {
                            rs.close();
                            pst.close();
                    } catch (SQLException e) {
                    }
                }
                qty.setText("");
                jTable1.getSelectionModel().clearSelection();
                
                totalcal();
            }else {
                JOptionPane.showMessageDialog(null, "Please select a row from the source table");
            }
        }else {
                JOptionPane.showMessageDialog(null, "Quantity is empty!");
            }
        

    }
    
    public void totalcal(){
        double totalPrice = 0.0;
        
        int priceColumnIndex = 4; 
        int row = billtable.getRowCount();
        while(row>0){
            String priceStr = (String) billtable.getValueAt(row-1, priceColumnIndex);
            double price = Double.parseDouble(priceStr);
            totalPrice += price;
            row--;
        }
        
        
        DecimalFormat decimalFormat = new DecimalFormat("0.00");
        String formattedNumber = decimalFormat.format(totalPrice);

        jLabel5.setText("Total : "+formattedNumber);
    }
    
    public final void loadorderid(){
        try{
            String sql = "SELECT max(oid) from `order`";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            if(rs.next()){
                
                int orderID = rs.getInt("max(oid)")+1;
                
                orderno.setText(orderID+"");
            }
            
        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }finally{
                try {
                    rs.close();
                    pst.close();
            } catch (SQLException e) {
            }
        }
    }
    
    public final void loadcashier(){
        cashier_name.setText(loggedInUsername);
    }
    
    public final void loadcustomer(){
        customer_name.setText(loggedInCusname);
    }
    
    public void placeorder(){
        
        LocalDateTime currentDateTime = LocalDateTime.now();
        int empid=0;
        int cusid=0;
        
        String cusname = customer_name.getText();
        String oid = orderno.getText(); 
        int orderid = Integer.parseInt(oid);
        
        DefaultTableModel model = (DefaultTableModel) billtable.getModel();
        int rowCount = model.getRowCount();

        
        try{
            //get empid from the employee table
            String sql = "SELECT empid from employee where empname='"+loggedInUsername+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            if (rs.next()) {
                empid = rs.getInt("empid");
           
                
                rs.close();
                pst.close();
            }
            
            
            if(cusname != null){
                //get customerid from the customer table
                String cussql = "SELECT cid from customer where name='"+loggedInCusname+"'";
                pst = conn.prepareStatement(cussql);
                rs = pst.executeQuery();

                if (rs.next()) {
                    cusid = rs.getInt("cid");


                    rs.close();
                    pst.close();
                }
                
                String sql2 = "INSERT INTO `order` (oid,status, date, empid, cid) VALUES ("+orderid+",'Ordered','"+currentDateTime+"',"+empid+","+cusid+")";
                pst = conn.prepareStatement(sql2);
                pst.execute();

            }else{
                String sql2 = "INSERT INTO `order` (oid,status, date, empid, cid) VALUES ("+orderid+",'Ordered','"+currentDateTime+"',"+empid+",null)";
                pst = conn.prepareStatement(sql2);
                pst.execute();

            }

            //put data to item order table
            
            for (int row = 0; row < rowCount; row++) {

                String column2Value = model.getValueAt(row, 0).toString();
                String column3Value = model.getValueAt(row, 3).toString();
                
                String checkquery = "SELECT OID,IID FROM ITEM_ORDER WHERE OID='"+orderid+"' and iid='"+column2Value+"'";
                pst = conn.prepareStatement(checkquery);
                rs = pst.executeQuery();

                if (rs.next()) {
                    String updateqty = "UPDATE ITEM_ORDER SET qty=qty+"+column3Value+" WHERE oid='"+orderid+"'and iid='"+column2Value+"'";
                    pst = conn.prepareStatement(updateqty);

                    pst.executeUpdate();

                    rs.close();
                    pst.close();
                }else{
                
                    String sql3 = "INSERT INTO item_order (oid, iid, qty) VALUES ("+orderid+","+column2Value+","+column3Value+")";
                    pst = conn.prepareStatement(sql3);

                    pst.executeUpdate();
                }
            }
            
            //update status on ready made items to done
            String updatestatus = "UPDATE `order` SET status='Done' WHERE oid not in (SELECT distinct o.oid from `order` o INNER JOIN ITEM_ORDER ior ON o.oid=ior.oid INNER JOIN ITEM i ON ior.iid=i.iid where i.catid=2)";
            pst = conn.prepareStatement(updatestatus);

            pst.executeUpdate();

            pst.close();

                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
        }catch(HeadlessException | SQLException e){
            JOptionPane.showMessageDialog(null,e);
        }finally{
            try {
                rs.close();
                pst.close();
            } catch (SQLException e) {
            }
        } 
        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        logout = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        jLabel2 = new javax.swing.JLabel();
        cashier_name = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        customer_name = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        order_btn = new javax.swing.JButton();
        orderno = new javax.swing.JLabel();
        orderno1 = new javax.swing.JLabel();
        qty = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        AddToCart = new javax.swing.JButton();
        cancel_btn = new javax.swing.JButton();
        delete_btn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        billtable = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logout.setText("log out");
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        jPanel1.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1130, 10, 110, 50));

        jComboBox1.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 130, 30));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel2.setText("Cashier :");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 210, -1, -1));

        cashier_name.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        cashier_name.setForeground(new java.awt.Color(102, 102, 102));
        jPanel1.add(cashier_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 210, -1, -1));

        jLabel4.setFont(new java.awt.Font("Helvetica Neue", 0, 16)); // NOI18N
        jLabel4.setText("Customer Name : ");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 180, -1, -1));

        customer_name.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        customer_name.setForeground(new java.awt.Color(102, 102, 102));
        jPanel1.add(customer_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 180, 80, 20));

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel5.setText("Total : 0.00");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 650, -1, -1));

        order_btn.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        order_btn.setText("Place Order");
        order_btn.setToolTipText("");
        order_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                order_btnActionPerformed(evt);
            }
        });
        jPanel1.add(order_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 640, 110, 40));

        orderno.setFont(new java.awt.Font("Helvetica Neue", 0, 30)); // NOI18N
        jPanel1.add(orderno, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 120, -1, -1));

        orderno1.setFont(new java.awt.Font("Helvetica Neue", 0, 30)); // NOI18N
        orderno1.setText("Order No :");
        jPanel1.add(orderno1, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 120, -1, -1));
        jPanel1.add(qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 650, 70, 30));

        jLabel7.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jLabel7.setText("Quantity");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 650, -1, 30));

        AddToCart.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        AddToCart.setText("Add to Cart");
        AddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddToCartActionPerformed(evt);
            }
        });
        jPanel1.add(AddToCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 650, 120, 30));

        cancel_btn.setText("Cancel Order");
        cancel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_btnActionPerformed(evt);
            }
        });
        jPanel1.add(cancel_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 590, -1, -1));

        delete_btn.setText("Delete Item");
        delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btnActionPerformed(evt);
            }
        });
        jPanel1.add(delete_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 590, -1, -1));

        billtable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Item ID", "Name", "Unit Price", "Quantity", "Total"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(billtable);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 250, 510, 330));

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Item ID", "Name", "Price", "Quantity"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.Integer.class, java.lang.Integer.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 540, 450));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 30)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/KITCHEN Screen(1).png"))); // NOI18N
        jLabel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void logoutActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logoutActionPerformed

        MainMenu main = new MainMenu();
        main.setVisible(true);

        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
        } 

        this.dispose();
    }//GEN-LAST:event_logoutActionPerformed

    private void AddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddToCartActionPerformed
        
        tabledata();

        
    }//GEN-LAST:event_AddToCartActionPerformed

    private void order_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_order_btnActionPerformed
        
        if (billtable.getRowCount() > 0) {
            
            int confirmation = JOptionPane.showConfirmDialog(this, "Confirm the order?", "Confirmation", JOptionPane.YES_NO_OPTION);
        
            if (confirmation == JOptionPane.YES_OPTION) {
                
                placeorder();
                JOptionPane.showMessageDialog(this, "Order placed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                DefaultTableModel model = (DefaultTableModel) billtable.getModel();
                model.setRowCount(0);
                totalcal();
                loadorderid();
                
                CustomerDetails cusdet = new CustomerDetails(loggedInUsername);
                cusdet.setVisible(true);

                try {
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
                } 

                this.dispose();
                
                
            } else {
                // Order canceled by the user
                // Handle the cancellation or perform any necessary actions
            }
        }else{
            JOptionPane.showMessageDialog(this, "No any items in the cart!");
        }
        
        
        
    }//GEN-LAST:event_order_btnActionPerformed

    private void cancel_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancel_btnActionPerformed
        if (billtable.getRowCount() > 0) {
            
            int confirmation = JOptionPane.showConfirmDialog(this, "Cancel the order?", "Confirmation", JOptionPane.YES_NO_OPTION);
        
            if (confirmation == JOptionPane.YES_OPTION) {
                
                DefaultTableModel model = (DefaultTableModel) billtable.getModel();

                int rowCount = model.getRowCount();
                for (int i = rowCount - 1; i >= 0; i--) {
                    model.removeRow(i);
                }

                model.fireTableDataChanged(); // Refresh the table
                totalcal();
                
            } else {
                // Order canceled by the user
                // Handle the cancellation or perform any necessary actions
            }
        }else{
            JOptionPane.showMessageDialog(this, "No any items in the cart!");
        }
    }//GEN-LAST:event_cancel_btnActionPerformed

    private void delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btnActionPerformed
        int selectedRow = billtable.getSelectedRow();
        
        if (selectedRow != -1) {
            DefaultTableModel model = (DefaultTableModel) billtable.getModel();
            model.removeRow(selectedRow);
            model.fireTableDataChanged(); // Refresh the table
        }else{
            JOptionPane.showMessageDialog(this, "Please Select an Item");
        }
        
        totalcal();
    }//GEN-LAST:event_delete_btnActionPerformed

    
//    public static void main(String args[]) {
//        /* Set the Nimbus look and feel */
//        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
//        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
//         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
//         */
//        try {
//            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
//                if ("Nimbus".equals(info.getName())) {
//                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
//                    break;
//                }
//            }
//        } catch (ClassNotFoundException ex) {
//            java.util.logging.Logger.getLogger(CashierScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CashierScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CashierScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CashierScreen.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CashierScreen().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AddToCart;
    private javax.swing.JTable billtable;
    private javax.swing.JButton cancel_btn;
    private javax.swing.JLabel cashier_name;
    private javax.swing.JLabel customer_name;
    private javax.swing.JButton delete_btn;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton logout;
    private javax.swing.JButton order_btn;
    private javax.swing.JLabel orderno;
    private javax.swing.JLabel orderno1;
    private javax.swing.JTextField qty;
    // End of variables declaration//GEN-END:variables
}
