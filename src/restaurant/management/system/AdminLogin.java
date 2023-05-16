
package restaurant.management.system;

import codes.DBconnect;
import java.sql.ResultSet;
import java.sql.Connection;

import javax.swing.JOptionPane;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminLogin extends javax.swing.JFrame {

    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    public AdminLogin() {
        initComponents();
        conn = (Connection) DBconnect.connect();
      
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        AdminPassword = new javax.swing.JPasswordField();
        AdminUsername = new javax.swing.JTextField();
        loginbtn = new javax.swing.JButton();
        mainmenubtn = new javax.swing.JButton();
        aboutbtn = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        AdminPassword.setFont(new java.awt.Font("Helvetica Neue", 0, 17)); // NOI18N
        AdminPassword.setToolTipText("");
        jPanel1.add(AdminPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 420, 480, -1));
        AdminPassword.setOpaque(false);
        AdminPassword.setBorder(null);
        AdminPassword.setBackground(null);

        AdminUsername.setFont(new java.awt.Font("Helvetica Neue", 0, 17)); // NOI18N
        jPanel1.add(AdminUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 305, 480, 30));
        AdminUsername.setOpaque(false);
        AdminUsername.setBorder(null);
        AdminUsername.setBackground(null);

        loginbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/6.png"))); // NOI18N
        loginbtn.setMaximumSize(new java.awt.Dimension(200, 200));
        loginbtn.setMinimumSize(new java.awt.Dimension(200, 200));
        loginbtn.setPreferredSize(new java.awt.Dimension(200, 200));
        loginbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                loginbtnActionPerformed(evt);
            }
        });
        jPanel1.add(loginbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 480, 100, 60));
        loginbtn.setOpaque(false);
        loginbtn.setContentAreaFilled(false);
        loginbtn.setBorderPainted(false);

        mainmenubtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/5.png"))); // NOI18N
        mainmenubtn.setMaximumSize(new java.awt.Dimension(200, 200));
        mainmenubtn.setMinimumSize(new java.awt.Dimension(200, 200));
        mainmenubtn.setPreferredSize(new java.awt.Dimension(200, 200));
        mainmenubtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mainmenubtnActionPerformed(evt);
            }
        });
        jPanel1.add(mainmenubtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 630, 195, 60));
        mainmenubtn.setOpaque(false);
        mainmenubtn.setContentAreaFilled(false);
        mainmenubtn.setBorderPainted(false);

        aboutbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/4.png"))); // NOI18N
        aboutbtn.setMaximumSize(new java.awt.Dimension(200, 200));
        aboutbtn.setMinimumSize(new java.awt.Dimension(200, 200));
        aboutbtn.setPreferredSize(new java.awt.Dimension(200, 200));
        aboutbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutbtnActionPerformed(evt);
            }
        });
        jPanel1.add(aboutbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 630, 160, 60));
        aboutbtn.setOpaque(false);
        aboutbtn.setContentAreaFilled(false);
        aboutbtn.setBorderPainted(false);

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Admin Login.png"))); // NOI18N
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

    private void aboutbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutbtnActionPerformed
        JOptionPane.showMessageDialog(null, """
                                            RESTAURANT MANAGEMENT SYSTEM
                                            <html>Done by <b>GROUP 06</b></html>""",
        "About Us",
        JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_aboutbtnActionPerformed

    private void mainmenubtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mainmenubtnActionPerformed
        
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        MainMenu main = new MainMenu();
        main.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_mainmenubtnActionPerformed

    private void loginbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_loginbtnActionPerformed

        String usrname;
        String pass;
        
        usrname = AdminUsername.getText();
        pass = AdminPassword.getText();
        
        if (usrname.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Username cannot be empty", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } else if (pass.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Password cannot be empty", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } else if (usrname.length() > 15) {
            JOptionPane.showMessageDialog(null, "Username should be less than 15 characters", "Username Error", JOptionPane.ERROR_MESSAGE);
        } else if (usrname.contains(" ")) {
            JOptionPane.showMessageDialog(null, "Username cannot contain spaces", "Username Error", JOptionPane.ERROR_MESSAGE);
        } else if (pass.length() > 15) {
            JOptionPane.showMessageDialog(null, "Password should be less than 15 characters", "Password Error", JOptionPane.ERROR_MESSAGE);
        } else if (pass.contains(" ")) {
            JOptionPane.showMessageDialog(null, "Password cannot contain spaces", "Password Error", JOptionPane.ERROR_MESSAGE);
        } else if (pass.length() < 5) {
            JOptionPane.showMessageDialog(null, "Password should be equal or more than 5 characters", "Password Error", JOptionPane.ERROR_MESSAGE);
        } else {

            try{
                String sql = "select username,password from employee where role='admin' and username='"+usrname+"'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();

                if (rs.next()) {
                        String password = rs.getString("password");
                        if(pass.equals(password)){
                            JOptionPane.showMessageDialog(null, "Login successful!");
                            AdminScreen adscreen = new AdminScreen();
                            adscreen.setVisible(true);

                            this.dispose();
                            rs.close();
                            pst.close();
                            conn.close();
                        }else{
                            JOptionPane.showMessageDialog(null, "Invalid Password");
                        }

                    } else {
                        // Login failed
                        JOptionPane.showMessageDialog(null, "Invalid Username");

                        AdminUsername.setText("");
                        AdminPassword.setText("");

                        rs.close();
                        pst.close();
                    }




            }catch(SQLException ex){
            }
        }
        
    }//GEN-LAST:event_loginbtnActionPerformed

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(AdminLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(AdminLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(AdminLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(AdminLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new AdminLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField AdminPassword;
    private javax.swing.JTextField AdminUsername;
    private javax.swing.JButton aboutbtn;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton loginbtn;
    private javax.swing.JButton mainmenubtn;
    // End of variables declaration//GEN-END:variables
}
