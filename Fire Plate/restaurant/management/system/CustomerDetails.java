
package restaurant.management.system;

import codes.DBconnect;
import java.awt.Cursor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class CustomerDetails extends javax.swing.JFrame {

    private final String loggedInUsername;
    private String loggedInCusname;
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public CustomerDetails(String loggedInUsername) {
        initComponents();
        conn = (Connection) DBconnect.connect();
        this.loggedInUsername = loggedInUsername;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        cusname = new javax.swing.JTextField();
        tel = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        registerBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        search_tel = new javax.swing.JTextField();
        searchBtn = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setText("New Customer");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 110, -1, -1));

        jLabel2.setText("Existing Customer");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 110, -1, -1));

        jLabel3.setText("Customer Name :");
        jPanel1.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 180, -1, -1));

        jLabel4.setText("Tel No               :");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 220, -1, -1));

        jLabel5.setText("Email                :");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 260, -1, -1));
        jPanel1.add(cusname, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 180, 160, -1));
        jPanel1.add(tel, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 220, 160, -1));
        jPanel1.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(310, 260, 160, -1));

        registerBtn.setText("Register");
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnActionPerformed(evt);
            }
        });
        jPanel1.add(registerBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 320, -1, -1));

        jLabel6.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(0, 51, 255));
        jLabel6.setText("Proceed to cashier without customer details >>");
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel6MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                jLabel6MouseExited(evt);
            }
        });
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 400, 410, 20));

        jLabel7.setText("Tel No :");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(720, 180, -1, -1));
        jPanel1.add(search_tel, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 180, 140, -1));

        searchBtn.setText("search");
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        jPanel1.add(searchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 180, -1, -1));

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

    private void registerBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_registerBtnActionPerformed
        String cname = cusname.getText();
        String cmail = email.getText();
        String ctel = tel.getText();
        
        if(!cname.isEmpty() && !ctel.isEmpty()){
            if(cmail.isEmpty()){
                try{
                    String sql = "INSERT INTO customer (name, telno, email) VALUES ('"+cname+"','"+ctel+"',"+null+")";
                    pst = conn.prepareStatement(sql);
                    pst.execute();

                    JOptionPane.showMessageDialog(this, "Customer Registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, e);
                }finally{
                        try {
                            //rs.close();
                            pst.close();
                    } catch (SQLException e) {
                    }
                }
            }else{
                try{
                    String sql = "INSERT INTO customer (name, telno, email) VALUES ('"+cname+"','"+ctel+"','"+cmail+"')";
                    pst = conn.prepareStatement(sql);
                    pst.execute();

                    JOptionPane.showMessageDialog(this, "Customer Registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                }catch(SQLException e){
                    JOptionPane.showMessageDialog(null, e);
                }finally{
                        try {
                            //rs.close();
                            pst.close();
                    } catch (SQLException e) {
                    }
                }
            }
            
            loggedInCusname = cname;
            
            CashierScreen cashscreen = new CashierScreen(loggedInUsername,loggedInCusname);
            cashscreen.setVisible(true);
            
            try {
            conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
            }

            this.dispose();
            
        }else{
            JOptionPane.showMessageDialog(null, "Please Add Name and Tel No!");
        }
        
        
        
        
    }//GEN-LAST:event_registerBtnActionPerformed

    private void jLabel6MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseEntered
        jLabel6.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }//GEN-LAST:event_jLabel6MouseEntered

    private void jLabel6MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseExited
        jLabel6.setCursor(Cursor.getDefaultCursor());
    }//GEN-LAST:event_jLabel6MouseExited

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        CashierScreen cashscreen = new CashierScreen(loggedInUsername, loggedInCusname);
        cashscreen.setVisible(true);
        
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.dispose();
    }//GEN-LAST:event_jLabel6MouseClicked

    private void searchBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_searchBtnActionPerformed
        String stel = search_tel.getText();
        
        try{
            String sql = "SELECT NAME FROM CUSTOMER WHERE TELNO='"+stel+"'";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            if(rs.next()){
                
                String name = rs.getString("name");
                
                loggedInCusname = name;
                JOptionPane.showMessageDialog(this, "Customer logged successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                
                CashierScreen cashscreen = new CashierScreen(loggedInUsername,loggedInCusname);
                cashscreen.setVisible(true);

                try {
                    rs.close();
                    pst.close();
                    conn.close();
                } catch (SQLException ex) {
                    Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
                }

                this.dispose();
                
            }else{
                JOptionPane.showMessageDialog(null, "There is no Customer with this telephone number!");
            }

        }catch(SQLException e){
            JOptionPane.showMessageDialog(null, e);
        }
        
        
    }//GEN-LAST:event_searchBtnActionPerformed

 
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
//            java.util.logging.Logger.getLogger(CustomerDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (InstantiationException ex) {
//            java.util.logging.Logger.getLogger(CustomerDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (IllegalAccessException ex) {
//            java.util.logging.Logger.getLogger(CustomerDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
//            java.util.logging.Logger.getLogger(CustomerDetails.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
//        }
//        //</editor-fold>
//
//        /* Create and display the form */
//        java.awt.EventQueue.invokeLater(new Runnable() {
//            public void run() {
//                new CustomerDetails().setVisible(true);
//            }
//        });
//    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cusname;
    private javax.swing.JTextField email;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton registerBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField search_tel;
    private javax.swing.JTextField tel;
    // End of variables declaration//GEN-END:variables
}
