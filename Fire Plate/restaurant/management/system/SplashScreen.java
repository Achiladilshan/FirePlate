
package restaurant.management.system;

public class SplashScreen extends javax.swing.JFrame {


    public SplashScreen() {
        initComponents();
    }


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        back_img = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        back_img.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Splash screen.png"))); // NOI18N
        jPanel1.add(back_img, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

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

    public void loading(int x){
        try {
            // Wait for x seconds
            Thread.sleep(3*1000);
        } catch (InterruptedException ex) {
            // Handle the exception
        }
    }
    
    
    public static void main(String args[]) {
        
        // Create and show the splash screen
        SplashScreen splash = new SplashScreen();
        splash.setVisible(true);
            
            
        splash.loading(3); //loading time in seconds
            
        // Create and show the main screen
        MainMenu Main = new MainMenu();
        Main.setVisible(true);

        splash.dispose();


    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel back_img;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
