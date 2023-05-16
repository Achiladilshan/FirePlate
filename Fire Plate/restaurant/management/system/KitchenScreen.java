package restaurant.management.system;


//Libraries
import codes.DBconnect;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import net.proteanit.sql.DbUtils;


public class KitchenScreen extends javax.swing.JFrame {
    
    
    //declaring and initializing variables for database connectivity
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    private Timer timer;

    public KitchenScreen() {
        initComponents();
        conn = (Connection) DBconnect.connect(); //connect to the database
        
        tableload();
        table2load();
        
        //refresh two tables after every 20 seconds
        startTimer();

    }
    
    private void startTimer() {
    timer = new Timer(0, (ActionEvent e) -> {
        tableload();
        table2load();
    });
    timer.setDelay(20000); 
    timer.start();
}

    private void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }
    
    
    private void tableload(){

        try{
            String sql = "SELECT oid as OrderID ,status as Status from `order` where status in ('Ordered','Preparing') and DATE(date) = CURDATE() and oid in (SELECT distinct o.oid from `order` o INNER JOIN ITEM_ORDER ior ON o.oid=ior.oid INNER JOIN ITEM i ON ior.iid=i.iid where i.catid=2) order by oid desc";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            CurrentOrdersTable.setModel(DbUtils.resultSetToTableModel(rs));
            
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
    
    private void table2load(){
        try{
            String sql = "SELECT oid as OrderID ,status as Status from `order` where status in ('Done','Canceled')  and DATE(date) = CURDATE() and oid in (SELECT distinct o.oid from `order` o INNER JOIN ITEM_ORDER ior ON o.oid=ior.oid INNER JOIN ITEM i ON ior.iid=i.iid where i.catid=2) order by oid desc";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            PreparedOrdersTable.setModel(DbUtils.resultSetToTableModel(rs));
            
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
    
    
    private void tabledata(){
        
        OrderDetailsArea.setText("");
        
        //check the selected row id and get the order id from it
        int r = CurrentOrdersTable.getSelectedRow();
        String id = CurrentOrdersTable.getValueAt(r, 0).toString();
        
        
        try{
            String sql = "SELECT o.oid ,o.status, o.date, i.name, ior.qty from `order` o INNER JOIN ITEM_ORDER ior ON o.oid=ior.oid INNER JOIN ITEM i ON ior.iid=i.iid where o.oid='"+id+"' and i.catid=2";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                int oid = rs.getInt("o.oid");
                String status = rs.getString("o.status");
                String date = rs.getString("o.date");
                
                
                OrderDetailsArea.append("OrderID: " + oid + "\n"+ "Date & Time: "+date+ "\n\nStatus: " + status + "\n\n- Ordered Items -\n-------------------\n\n");
                
                do {
                    String name = rs.getString("i.name");
                    int quantity = rs.getInt("ior.qty");

                    OrderDetailsArea.append(name+"\t"+quantity+"\n");
                } while (rs.next() && rs.getInt("o.oid") == oid);
                
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
    
    
    private void table2data(){
        
        OrderDetailsArea.setText("");
        
        //check the selected row id and get the order id from it
        int r = PreparedOrdersTable.getSelectedRow();
        String id = PreparedOrdersTable.getValueAt(r, 0).toString();
       
        
        try{
            String sql = "SELECT o.oid ,o.status, o.date, i.name, ior.qty from `order` o INNER JOIN ITEM_ORDER ior ON o.oid=ior.oid INNER JOIN ITEM i ON ior.iid=i.iid where o.oid='"+id+"'and i.catid=2";
            pst = conn.prepareStatement(sql);
            rs = pst.executeQuery();
            
            while(rs.next()){
                int oid = rs.getInt("o.oid");
                String status = rs.getString("o.status");
                String date = rs.getString("o.date");
                
                
                OrderDetailsArea.append("OrderID: " + oid + "\n"+ "Date & Time: "+date+ "\n\nStatus: " + status + "\n\n- Ordered Items -\n-------------------\n\n");
                
                do {
                    String name = rs.getString("i.name");
                    int quantity = rs.getInt("ior.qty");
                    
                    OrderDetailsArea.append(name+"\t"+quantity+"\n");
                } while (rs.next() && rs.getInt("o.oid") == oid);
                
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        logout_btn = new javax.swing.JButton();
        preparing_btn = new javax.swing.JButton();
        done_btn = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        OrderDetailsArea = new javax.swing.JTextArea();
        jScrollPane4 = new javax.swing.JScrollPane();
        PreparedOrdersTable = new javax.swing.JTable();
        jScrollPane3 = new javax.swing.JScrollPane();
        CurrentOrdersTable = new javax.swing.JTable();
        cancelBtn = new javax.swing.JButton();
        bg = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logout_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Log Out.png"))); // NOI18N
        logout_btn.setPreferredSize(new java.awt.Dimension(150, 67));
        logout_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logout_btnActionPerformed(evt);
            }
        });
        jPanel1.add(logout_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1100, 10, -1, -1));
        logout_btn.setOpaque(false);
        logout_btn.setContentAreaFilled(false);
        logout_btn.setBorderPainted(false);

        preparing_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Preparing.png"))); // NOI18N
        preparing_btn.setPreferredSize(new java.awt.Dimension(140, 45));
        preparing_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                preparing_btnActionPerformed(evt);
            }
        });
        jPanel1.add(preparing_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 590, 160, 50));
        preparing_btn.setOpaque(false);
        preparing_btn.setContentAreaFilled(false);
        preparing_btn.setBorderPainted(false);

        done_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Done.png"))); // NOI18N
        done_btn.setPreferredSize(new java.awt.Dimension(140, 45));
        done_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                done_btnActionPerformed(evt);
            }
        });
        jPanel1.add(done_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1070, 590, 160, 50));
        done_btn.setOpaque(false);
        done_btn.setContentAreaFilled(false);
        done_btn.setBorderPainted(false);

        OrderDetailsArea.setColumns(20);
        OrderDetailsArea.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        OrderDetailsArea.setRows(5);
        OrderDetailsArea.setMargin(new java.awt.Insets(8, 15, 2, 6));
        jScrollPane2.setViewportView(OrderDetailsArea);
        OrderDetailsArea.setEditable(false);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 160, 330, 410));

        PreparedOrdersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Order ID", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        PreparedOrdersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                PreparedOrdersTableMousePressed(evt);
            }
        });
        jScrollPane4.setViewportView(PreparedOrdersTable);

        jPanel1.add(jScrollPane4, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 160, 290, 480));

        CurrentOrdersTable.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null},
                {null, null},
                {null, null},
                {null, null}
            },
            new String [] {
                "Order ID", "Status"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        CurrentOrdersTable.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                CurrentOrdersTableMousePressed(evt);
            }
        });
        jScrollPane3.setViewportView(CurrentOrdersTable);

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 160, 290, 480));

        cancelBtn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Cancel Order.png"))); // NOI18N
        cancelBtn.setToolTipText("");
        cancelBtn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelBtnActionPerformed(evt);
            }
        });
        jPanel1.add(cancelBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 650, 160, 50));
        cancelBtn.setOpaque(false);
        cancelBtn.setContentAreaFilled(false);
        cancelBtn.setBorderPainted(false);

        bg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Without Button.png"))); // NOI18N
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

    private void logout_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_logout_btnActionPerformed
        
        MainMenu main = new MainMenu();
        main.setVisible(true);
        
        stopTimer();
        
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        this.dispose();

    }//GEN-LAST:event_logout_btnActionPerformed

    private void preparing_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_preparing_btnActionPerformed
        
        int r = CurrentOrdersTable.getSelectedRow();
        
        if (r != -1) {
            String id = CurrentOrdersTable.getValueAt(r, 0).toString();

            try{
                String sql = "SELECT oid,status from `order` where oid='"+id+"'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();

                if (rs.next()) {
                    int oid = rs.getInt("oid");
                    String status = rs.getString("status");

                    rs.close();
                    pst.close();

                    switch (status) {
                        case "Ordered":
                            try{
                                String sql1 = "UPDATE `order` SET status='Preparing' WHERE oid='"+oid+"'";
                                pst = conn.prepareStatement(sql1);
                                pst.execute();

                                JOptionPane.showMessageDialog(null,"Preparing Started!");
                            }catch(HeadlessException | SQLException e){
                                JOptionPane.showMessageDialog(null,e);
                            }finally{
                                try {
                                    rs.close();
                                    pst.close();
                                } catch (SQLException e) {
                                }
                            }       break;
                        case "Preparing":
                            JOptionPane.showMessageDialog(null,"It's Preparing!");
                            break;
                        default:
                            break;
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
            tableload();
            OrderDetailsArea.setText("");
        }else{
            JOptionPane.showMessageDialog(null, "Please Select a Current Order!");
        }
    }//GEN-LAST:event_preparing_btnActionPerformed

    private void done_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_done_btnActionPerformed
        int r = CurrentOrdersTable.getSelectedRow();
        
        if (r != -1) {
            String id = CurrentOrdersTable.getValueAt(r, 0).toString();

            try{
                String sql = "SELECT oid,status from `order` where oid='"+id+"'";
                pst = conn.prepareStatement(sql);
                rs = pst.executeQuery();

                if (rs.next()) {
                    int oid = rs.getInt("oid");
                    String status = rs.getString("status");

                    switch (status) {
                        case "Preparing":
                            try{
                                String sql1 = "UPDATE `order` SET status='Done' WHERE oid='"+oid+"'";
                                pst = conn.prepareStatement(sql1);
                                pst.execute();
                                
                                JOptionPane.showMessageDialog(null,"Order Done!");
                            }catch(Exception e){
                                JOptionPane.showMessageDialog(null,e);
                            }finally{
                                try {
                                    rs.close();
                                    pst.close();
                                } catch (Exception e) {
                                }
                            }       break;
                        case "Ordered":
                            JOptionPane.showMessageDialog(null,"Prepare it first!");
                            break;
                        default:
                            break;
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

            tableload();
            table2load();
            OrderDetailsArea.setText("");
        }else{
            JOptionPane.showMessageDialog(null, "Please Select a Current Order!");
        }
    }//GEN-LAST:event_done_btnActionPerformed

    private void CurrentOrdersTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CurrentOrdersTableMousePressed
        tabledata();
        PreparedOrdersTable.getSelectionModel().clearSelection();
    }//GEN-LAST:event_CurrentOrdersTableMousePressed

    private void PreparedOrdersTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_PreparedOrdersTableMousePressed
        table2data();
        CurrentOrdersTable.getSelectionModel().clearSelection();
    }//GEN-LAST:event_PreparedOrdersTableMousePressed

    private void cancelBtnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelBtnActionPerformed
        int r = CurrentOrdersTable.getSelectedRow();
        
        if (r != -1) {
            int choice = JOptionPane.showConfirmDialog(null, "Are you sure you want to cancel the order?", "Confirmation", JOptionPane.YES_NO_OPTION);
            if (choice == JOptionPane.YES_OPTION) {
                String id = CurrentOrdersTable.getValueAt(r, 0).toString();

                try{
                    String sql = "SELECT oid,status from `order` where oid='"+id+"'";
                    pst = conn.prepareStatement(sql);
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        int oid = rs.getInt("oid");
                        String status = rs.getString("status");

                        switch (status) {
                            case "Preparing":
                                JOptionPane.showMessageDialog(null,"Order is Preparing. Can't Cancel It!");
                                break;
                            case "Ordered":
                                
                                try{
                                    String sql1 = "UPDATE `order` SET status='Canceled' WHERE oid='"+oid+"'";
                                    pst = conn.prepareStatement(sql1);
                                    pst.execute();

                                }catch(Exception e){
                                    JOptionPane.showMessageDialog(null,e);
                                }finally{
                                    try {
                                        rs.close();
                                        pst.close();
                                    } catch (Exception e) {
                                    }
                                }
                                break;
                            default:
                                break;
                        }

                    }
                    
                    String sql2 = "SELECT oid from bill where oid='"+id+"'";
                    pst = conn.prepareStatement(sql2);
                    rs = pst.executeQuery();

                    if (rs.next()) {
                        int oid = rs.getInt("oid");

                        String sql3 = "UPDATE bill SET total='0',bal='0',paid='0' WHERE oid='"+oid+"'";
                        pst = conn.prepareStatement(sql3);
                        pst.execute();
                    }
                    
                    JOptionPane.showMessageDialog(null,"Order Canceled Successfully!");

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
            tableload();
            table2load();
            OrderDetailsArea.setText("");
        }else{
            JOptionPane.showMessageDialog(null, "Please Select a Current Order!");
        }
    }//GEN-LAST:event_cancelBtnActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new KitchenScreen().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CurrentOrdersTable;
    private javax.swing.JTextArea OrderDetailsArea;
    private javax.swing.JTable PreparedOrdersTable;
    private javax.swing.JLabel bg;
    private javax.swing.JButton cancelBtn;
    private javax.swing.JButton done_btn;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JButton logout_btn;
    private javax.swing.JButton preparing_btn;
    // End of variables declaration//GEN-END:variables
}
