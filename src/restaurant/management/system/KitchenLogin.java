
package restaurant.management.system;

import codes.DBconnect;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;


public class KitchenLogin extends javax.swing.JFrame {
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    public KitchenLogin() {
        initComponents();
        conn = (Connection) DBconnect.connect();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        KitchenPassword = new javax.swing.JPasswordField();
        KitchenUsername = new javax.swing.JTextField();
        LoginBtn = new javax.swing.JButton();
        MenuBtn = new javax.swing.JButton();
        aboutbtn = new javax.swing.JButton();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        KitchenPassword.setFont(new java.awt.Font("Helvetica Neue", 0, 17)); // NOI18N
        KitchenPassword.setToolTipText("");
        jPanel1.add(KitchenPassword, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 420, 480, -1));
        KitchenPassword.setOpaque(false);
        KitchenPassword.setBorder(null);
        KitchenPassword.setBackground(null);

        KitchenUsername.setFont(new java.awt.Font("Helvetica Neue", 0, 17)); // NOI18N
        jPanel1.add(KitchenUsername, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 305, 480, 30));
        KitchenUsername.setOpaque(false);
        KitchenUsername.setBorder(null);
        KitchenUsername.setBackground(null);

        LoginBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/6.png"))); // NOI18N
        LoginBtn.setMaximumSize(new java.awt.Dimension(200, 200));
        LoginBtn.setMinimumSize(new java.awt.Dimension(200, 200));
        LoginBtn.setPreferredSize(new java.awt.Dimension(200, 200));
        LoginBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                LoginBtnActionPerformed(evt);
            }
        });
        jPanel1.add(LoginBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 480, 100, 60));
        LoginBtn.setOpaque(false);
        LoginBtn.setContentAreaFilled(false);
        LoginBtn.setBorderPainted(false);

        MenuBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/5.png"))); // NOI18N
        MenuBtn.setMaximumSize(new java.awt.Dimension(200, 200));
        MenuBtn.setMinimumSize(new java.awt.Dimension(200, 200));
        MenuBtn.setPreferredSize(new java.awt.Dimension(200, 200));
        MenuBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                MenuBtnActionPerformed(evt);
            }
        });
        jPanel1.add(MenuBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 630, 195, 60));
        MenuBtn.setOpaque(false);
        MenuBtn.setContentAreaFilled(false);
        MenuBtn.setBorderPainted(false);

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

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Kitchen Login.png"))); // NOI18N
        jPanel1.add(bg, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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

    private void LoginBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_LoginBtnActionPerformed
        String usrname;
        String pass;
        
        usrname = KitchenUsername.getText();
        pass = KitchenPassword.getText();
        
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
                String sql = "select username,password from employee where role='kitchen' and username='"+usrname+"'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();

                if (rs.next()) {
                        String password = rs.getString("password");
                        if(pass.equals(password)){
                            JOptionPane.showMessageDialog(null, "Login successful!");
                            KitchenScreen kitscreen = new KitchenScreen();
                            kitscreen.setVisible(true);

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

                        KitchenUsername.setText("");
                        KitchenPassword.setText("");

                        rs.close();
                        pst.close();
                    }




            }catch(SQLException ex){
            }
        }
    }//GEN-LAST:event_LoginBtnActionPerformed

    private void MenuBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_MenuBtnActionPerformed
        
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        MainMenu main = new MainMenu();
        main.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_MenuBtnActionPerformed

    private void aboutbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutbtnActionPerformed
        JOptionPane.showMessageDialog(null, """
                                            RESTAURANT MANAGEMENT SYSTEM
                                            <html>Done by <b>GROUP 06</b></html>""",
        "About Us",
        JOptionPane.PLAIN_MESSAGE);
    }//GEN-LAST:event_aboutbtnActionPerformed


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
            java.util.logging.Logger.getLogger(KitchenLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(KitchenLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(KitchenLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(KitchenLogin.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KitchenLogin().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPasswordField KitchenPassword;
    private javax.swing.JTextField KitchenUsername;
    private javax.swing.JButton LoginBtn;
    private javax.swing.JButton MenuBtn;
    private javax.swing.JButton aboutbtn;
    private javax.swing.JLabel bg;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
