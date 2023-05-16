
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
        cusname = new javax.swing.JTextField();
        tel = new javax.swing.JTextField();
        email = new javax.swing.JTextField();
        registerBtn = new javax.swing.JButton();
        jLabel6 = new javax.swing.JLabel();
        search_tel = new javax.swing.JTextField();
        loginBtn = new javax.swing.JButton();
        searchBtn = new javax.swing.JButton();
        updateBtn = new javax.swing.JButton();
        cn = new javax.swing.JTextField();
        tn = new javax.swing.JTextField();
        em = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(1280, 720));
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cusname.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jPanel1.add(cusname, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 290, 280, -1));
        cusname.setOpaque(false);
        cusname.setBorder(null);
        cusname.setBackground(null);

        tel.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jPanel1.add(tel, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 350, 280, -1));
        tel.setOpaque(false);
        tel.setBorder(null);
        tel.setBackground(null);

        email.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jPanel1.add(email, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 406, 280, -1));
        email.setOpaque(false);
        email.setBorder(null);
        email.setBackground(null);

        registerBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/3.png"))); // NOI18N
        registerBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                registerBtnActionPerformed(evt);
            }
        });
        jPanel1.add(registerBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 470, -1, -1));
        registerBtn.setOpaque(false);
        registerBtn.setContentAreaFilled(false);
        registerBtn.setBorderPainted(false);

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
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 600, 410, 20));

        search_tel.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        jPanel1.add(search_tel, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 291, 270, -1));
        search_tel.setOpaque(false);
        search_tel.setBorder(null);
        search_tel.setBackground(null);

        loginBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/2.png"))); // NOI18N
        loginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginBtnActionPerformed(evt);
            }
        });
        jPanel1.add(loginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 470, -1, -1));
        loginBtn.setOpaque(false);
        loginBtn.setContentAreaFilled(false);
        loginBtn.setBorderPainted(false);

        searchBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/4 copy.png"))); // NOI18N
        searchBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                searchBtnActionPerformed(evt);
            }
        });
        jPanel1.add(searchBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1147, 286, -1, -1));
        searchBtn.setOpaque(false);
        searchBtn.setContentAreaFilled(false);
        searchBtn.setBorderPainted(false);

        updateBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/update.png"))); // NOI18N
        updateBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateBtnActionPerformed(evt);
            }
        });
        jPanel1.add(updateBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(875, 470, -1, -1));
        updateBtn.setOpaque(false);
        updateBtn.setContentAreaFilled(false);
        updateBtn.setBorderPainted(false);

        cn.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jPanel1.add(cn, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 350, 240, -1));
        cn.setOpaque(false);
        cn.setBorder(null);
        cn.setBackground(null);

        tn.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jPanel1.add(tn, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 392, 240, -1));
        tn.setOpaque(false);
        tn.setBorder(null);
        tn.setBackground(null);

        em.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        jPanel1.add(em, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 437, 240, -1));
        em.setOpaque(false);
        em.setBorder(null);
        em.setBackground(null);

        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/customer.png"))); // NOI18N
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1280, 720));

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
        
        if (cname.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty", "Username Error", JOptionPane.ERROR_MESSAGE);
        } else if(cname.matches("^[0-9]+$")) {
            JOptionPane.showMessageDialog(null, "Username cannot contain only numbers.", "Error", JOptionPane.ERROR_MESSAGE);       
        } else if (cname.length() > 30) {
            JOptionPane.showMessageDialog(null, "Username should be less than 30 characters", "Username Error", JOptionPane.ERROR_MESSAGE);
        } else if (cname.contains(" ")) {
            JOptionPane.showMessageDialog(null, "Username cannot contain spaces", "Username Error", JOptionPane.ERROR_MESSAGE);
        } else if (!cname.matches("^[a-zA-Z0-9]+$")){
            JOptionPane.showMessageDialog(null, "Username cannot contain special Charcaters", "Username Error", JOptionPane.ERROR_MESSAGE);
        }else if (ctel.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Telephone Number cannot be empty", "Validation Error", JOptionPane.ERROR_MESSAGE);
        }else if (ctel.length() != 10) {
            JOptionPane.showMessageDialog(null, "Telephone Number must be 10 numbers", "Validation Error", JOptionPane.ERROR_MESSAGE);
        }else if (!ctel.matches("07\\d{8}")) {
            JOptionPane.showMessageDialog(null, "Invalid Telephone Number", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } else if (!cmail.isEmpty() && !cmail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")){
            JOptionPane.showMessageDialog(null, "Invalid Email", "Validation Error", JOptionPane.ERROR_MESSAGE);
        }else{
            try{
                String sql = "select telno from customer where telno='"+ctel+"'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();
                
                if (rs.next()) {
                    JOptionPane.showMessageDialog(null, "This Telephone Number Already Exists!", "Register Error", JOptionPane.ERROR_MESSAGE);
                }else{
                    String sql1 = "select email from customer where email='"+cmail+"'";
                    pst = conn.prepareStatement(sql1);
                    rs = pst.executeQuery();
                    
                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "This Email Address Already Exists!", "Register Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        if(cmail.isEmpty()){
                            try{
                                String sql2 = "INSERT INTO customer (name, telno, email) VALUES ('"+cname+"','"+ctel+"',"+null+")";
                                pst = conn.prepareStatement(sql2);
                                pst.execute();

                                JOptionPane.showMessageDialog(this, "Customer Registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                            }catch(SQLException e){
                                JOptionPane.showMessageDialog(null, e);
                            }finally{
                                    try {
                                        rs.close();
                                        pst.close();
                                } catch (SQLException e) {
                                }
                            }
                        }else{
                            try{
                                String sql3 = "INSERT INTO customer (name, telno, email) VALUES ('"+cname+"','"+ctel+"','"+cmail+"')";
                                pst = conn.prepareStatement(sql3);
                                pst.execute();

                                JOptionPane.showMessageDialog(this, "Customer Registered successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);

                            }catch(SQLException e){
                                JOptionPane.showMessageDialog(null, e);
                            }finally{
                                    try {
                                        
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
        
        if (stel.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Telephone Number cannot be empty", "Validation Error", JOptionPane.ERROR_MESSAGE);
        }else if (stel.length() != 10) {
            JOptionPane.showMessageDialog(null, "Telephone Number must be 10 numbers", "Validation Error", JOptionPane.ERROR_MESSAGE);
        }else if (!stel.matches("07\\d{8}")) {
            JOptionPane.showMessageDialog(null, "Invalid Telephone Number", "Validation Error", JOptionPane.ERROR_MESSAGE);
        }else{
        
            try{
                String sql = "SELECT NAME,TELNO,EMAIL FROM CUSTOMER WHERE TELNO='"+stel+"'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();

                if(rs.next()){

                    String name = rs.getString("name");
                    String tel = rs.getString("telno");
                    String email = rs.getString("email");

                    cn.setText(name);
                    cn.setEditable(false);
                    tn.setText(tel);
                    tn.setEditable(false);
                    em.setText(email);


                }else{
                    JOptionPane.showMessageDialog(null, "There is no Customer with this telephone number!");
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
        
        
    }//GEN-LAST:event_searchBtnActionPerformed

    private void loginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginBtnActionPerformed
        String stel = tn.getText();
        
        if(stel.isEmpty()){
            JOptionPane.showMessageDialog(null, "Search for Customer First", "Login Error", JOptionPane.ERROR_MESSAGE);
        }else{
        
            try{
                String sql = "SELECT NAME,email FROM CUSTOMER WHERE TELNO='"+stel+"'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();

                if(rs.next()){

                    String name = rs.getString("name");
                    String mail = rs.getString("email");

                    if(mail.equals(em.getText())){

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
                          JOptionPane.showMessageDialog(null, "Email changed! Check again or update the new email", "Email Error", JOptionPane.ERROR_MESSAGE);
                         }

                }

            }catch(SQLException e){
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }//GEN-LAST:event_loginBtnActionPerformed

    private void updateBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateBtnActionPerformed
        String mail = em.getText();
        String tel = tn.getText();
        
        if(tel.isEmpty()){
            JOptionPane.showMessageDialog(null, "Search for Customer First", "Update Error", JOptionPane.ERROR_MESSAGE);
        }else{
        
            if (!mail.matches("^[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}$")){
                JOptionPane.showMessageDialog(null, "Invalid Email", "Validation Error", JOptionPane.ERROR_MESSAGE);
            }else{
                
                try{
                    String sql = "select email from customer where email='"+mail+"'";
                    pst = conn.prepareStatement(sql);
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        JOptionPane.showMessageDialog(null, "This Email Address Already Exists!", "Register Error", JOptionPane.ERROR_MESSAGE);
                    }else{
                        int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to change the email?", "Confirmation", JOptionPane.YES_NO_OPTION);
                        if (choice == JOptionPane.YES_OPTION) {
                            try {
                                String updatestatus = "UPDATE CUSTOMER SET EMAIL='"+mail+"' WHERE TELNO='"+tel+"'";
                                pst = conn.prepareStatement(updatestatus);
                                pst.executeUpdate();
                                pst.close();

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }  
                            JOptionPane.showMessageDialog(this, "Email Changed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                            search_tel.setText("");
                            cn.setText("");
                            tn.setText("");
                            em.setText("");
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
        }
    }//GEN-LAST:event_updateBtnActionPerformed

 
    
    
    
    
    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField cn;
    private javax.swing.JTextField cusname;
    private javax.swing.JTextField em;
    private javax.swing.JTextField email;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loginBtn;
    private javax.swing.JButton registerBtn;
    private javax.swing.JButton searchBtn;
    private javax.swing.JTextField search_tel;
    private javax.swing.JTextField tel;
    private javax.swing.JTextField tn;
    private javax.swing.JButton updateBtn;
    // End of variables declaration//GEN-END:variables
}
