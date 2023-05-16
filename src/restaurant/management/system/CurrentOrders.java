
package restaurant.management.system;

import codes.DBconnect;
import java.awt.event.ActionEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import net.proteanit.sql.DbUtils;

public class CurrentOrders extends javax.swing.JFrame {

    //declaring and initializing variables for database connectivity
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    private Timer timer;
    
    public CurrentOrders() {
        initComponents();
        conn = (Connection) DBconnect.connect(); //connect to the database
        
        tableload();
        
        //refresh two tables after every 10 seconds
        startTimer();
        
    }
    
    @Override
    public void dispose() {
        stopTimer();
        
        try {
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
        }

        super.dispose();
    }
    
    private void startTimer() {
    timer = new Timer(0, (ActionEvent e) -> {
        tableload();
    });
    timer.setDelay(10000); 
    timer.start();
}

    private void stopTimer() {
        if (timer != null) {
            timer.stop();
        }
    }
    
    private void tableload(){

        try{
            String sql = "SELECT oid as OrderID ,status as Status from `order` where DATE(date) = CURDATE() and oid in (SELECT distinct o.oid from `order` o INNER JOIN ITEM_ORDER ior ON o.oid=ior.oid INNER JOIN ITEM i ON ior.iid=i.iid where i.catid=2) order by oid desc";
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


    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane3 = new javax.swing.JScrollPane();
        CurrentOrdersTable = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        OrderDetailsArea = new javax.swing.JTextArea();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setAlwaysOnTop(true);
        setResizable(false);

        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

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

        jPanel1.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 290, 480));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 30)); // NOI18N
        jLabel1.setText("Order Details");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 20, -1, 40));

        OrderDetailsArea.setColumns(20);
        OrderDetailsArea.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        OrderDetailsArea.setRows(5);
        OrderDetailsArea.setMargin(new java.awt.Insets(8, 15, 2, 6));
        jScrollPane2.setViewportView(OrderDetailsArea);
        OrderDetailsArea.setEditable(false);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 70, 330, 480));

        jLabel2.setFont(new java.awt.Font("Helvetica Neue", 0, 30)); // NOI18N
        jLabel2.setText("Current Orders");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 20, 210, 40));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 741, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void CurrentOrdersTableMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CurrentOrdersTableMousePressed
        tabledata();
    }//GEN-LAST:event_CurrentOrdersTableMousePressed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable CurrentOrdersTable;
    private javax.swing.JTextArea OrderDetailsArea;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    // End of variables declaration//GEN-END:variables
}
