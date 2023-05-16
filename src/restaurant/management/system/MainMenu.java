
package restaurant.management.system;

//Libraries
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import javax.swing.Timer;



public class MainMenu extends javax.swing.JFrame {

    public MainMenu() {
        initComponents();
        
        Timer timer = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateTime();
            }
        });
        timer.start();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        dtime = new javax.swing.JLabel();
        aboutbtn = new javax.swing.JButton();
        KitchenBtn = new javax.swing.JButton();
        CashierBtn = new javax.swing.JButton();
        AdminBtn = new javax.swing.JButton();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        dtime.setFont(new java.awt.Font("Helvetica Neue", 1, 40)); // NOI18N
        jPanel1.add(dtime, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 650, 170, 40));

        aboutbtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/4.png"))); // NOI18N
        aboutbtn.setMaximumSize(new java.awt.Dimension(200, 200));
        aboutbtn.setMinimumSize(new java.awt.Dimension(200, 200));
        aboutbtn.setPreferredSize(new java.awt.Dimension(200, 200));
        aboutbtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                aboutbtnActionPerformed(evt);
            }
        });
        jPanel1.add(aboutbtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 640, 160, 60));
        aboutbtn.setOpaque(false);
        aboutbtn.setContentAreaFilled(false);
        aboutbtn.setBorderPainted(false);

        KitchenBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Kitchen.png"))); // NOI18N
        KitchenBtn.setMaximumSize(new java.awt.Dimension(200, 200));
        KitchenBtn.setMinimumSize(new java.awt.Dimension(200, 200));
        KitchenBtn.setPreferredSize(new java.awt.Dimension(200, 200));
        KitchenBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                KitchenBtnActionPerformed(evt);
            }
        });
        jPanel1.add(KitchenBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 380, 220, 200));
        KitchenBtn.setOpaque(false);
        KitchenBtn.setContentAreaFilled(false);
        KitchenBtn.setBorderPainted(false);

        CashierBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Cashier.png"))); // NOI18N
        CashierBtn.setMaximumSize(new java.awt.Dimension(200, 200));
        CashierBtn.setMinimumSize(new java.awt.Dimension(200, 200));
        CashierBtn.setPreferredSize(new java.awt.Dimension(226, 189));
        CashierBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CashierBtnActionPerformed(evt);
            }
        });
        jPanel1.add(CashierBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(530, 380, 220, 200));
        CashierBtn.setOpaque(false);
        CashierBtn.setContentAreaFilled(false);
        CashierBtn.setBorderPainted(false);

        AdminBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Admin.png"))); // NOI18N
        AdminBtn.setMaximumSize(new java.awt.Dimension(188, 189));
        AdminBtn.setMinimumSize(new java.awt.Dimension(188, 189));
        AdminBtn.setPreferredSize(new java.awt.Dimension(188, 189));
        AdminBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AdminBtnActionPerformed(evt);
            }
        });
        jPanel1.add(AdminBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 380, 190, 200));
        try {
            Image img = ImageIO.read(getClass().getResource("/images/1.png"));
            AdminBtn.setIcon(new ImageIcon(img));
        } catch (Exception ex) {
            System.out.println(ex);
        }

        AdminBtn.setOpaque(false);
        AdminBtn.setContentAreaFilled(false);
        AdminBtn.setBorderPainted(false);

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Main Menu.png"))); // NOI18N
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

    private void AdminBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AdminBtnActionPerformed
       AdminLogin adlogin = new AdminLogin();
       adlogin.setVisible(true);
      
       this.dispose();
       
    }//GEN-LAST:event_AdminBtnActionPerformed

    private void CashierBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CashierBtnActionPerformed
        CashierLogin calogin = new CashierLogin();
        calogin.setVisible(true);
      
        this.dispose();
    }//GEN-LAST:event_CashierBtnActionPerformed

    private void KitchenBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_KitchenBtnActionPerformed
        KitchenLogin kilogin = new KitchenLogin();
        kilogin.setVisible(true);

        this.dispose();
    }//GEN-LAST:event_KitchenBtnActionPerformed

    private void aboutbtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_aboutbtnActionPerformed
        JOptionPane.showMessageDialog(null, """
                                            RESTAURANT MANAGEMENT SYSTEM
                                            <html>Done by <b>GROUP 06</b></html>""",
        "About Us",
        JOptionPane.PLAIN_MESSAGE);
        
    }//GEN-LAST:event_aboutbtnActionPerformed


    private void updateTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("HH:mm:ss");
        String currentTime = sdf.format(new Date());
        dtime.setText(currentTime);
    }

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
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(MainMenu.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new MainMenu().setVisible(true);
            }
        });
        
        
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton AdminBtn;
    private javax.swing.JButton CashierBtn;
    private javax.swing.JButton KitchenBtn;
    private javax.swing.JButton aboutbtn;
    private javax.swing.JLabel bg;
    private javax.swing.JLabel dtime;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
