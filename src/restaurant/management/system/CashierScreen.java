
package restaurant.management.system;

import codes.DBconnect;
import java.awt.Desktop;
import java.awt.Dialog;
import java.awt.HeadlessException;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import static java.nio.file.Files.lines;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import net.proteanit.sql.DbUtils;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDPageContentStream;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.pdmodel.font.PDType1Font;
import org.apache.pdfbox.pdmodel.font.PDType0Font;

public class CashierScreen extends javax.swing.JFrame {
    
    Connection conn = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    
    private final String loggedInUsername;
    private final String loggedInCusname;
    private double totalPrice = 0.0;
    

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
        
        if (text.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Quantity cannot be empty", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } else if (text.length() > 4) {
            JOptionPane.showMessageDialog(null, "Quantity cannot exceed 4 digits", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } else if (!text.matches("\\d+")) {
            JOptionPane.showMessageDialog(null, "Quantity must contain only numeric digits", "Validation Error", JOptionPane.ERROR_MESSAGE);
        } else {

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
                    
                    String checkqty = "SELECT qty from item where iid='"+column1Value+"' and catid!= 2";
                    pst = conn.prepareStatement(checkqty);
                    rs = pst.executeQuery();
                    int itemqty;
                    
                    if (rs.next()) {

                        itemqty = rs.getInt("qty");
                        
                        if(itemqty>=item_qty){
                            
                            //update item Quantities
                            String reduceqty = "UPDATE ITEM SET qty=qty-"+item_qty+" WHERE iid='"+column1Value+"' and catid!= 2";
                            pst = conn.prepareStatement(reduceqty);
                            pst.executeUpdate();

                            loadtable();
                            
                            double total = column3Value*item_qty;
                    
                            DecimalFormat decimalFormat = new DecimalFormat("0.00");
                            String f_billColumn3Value = decimalFormat.format(billColumn3Value);
                            String f_total = decimalFormat.format(total);


                            DefaultTableModel model = (DefaultTableModel) billtable.getModel();
                            model.addRow(new Object[] { billColumn1Value, billColumn2Value, f_billColumn3Value, item_qty, f_total });
                    
                        }else{
                            JOptionPane.showMessageDialog(null, "Not Enough Stocks on This Item!");
                        }
                    }else{
                            double total = column3Value*item_qty;
                    
                            DecimalFormat decimalFormat = new DecimalFormat("0.00");
                            String f_billColumn3Value = decimalFormat.format(billColumn3Value);
                            String f_total = decimalFormat.format(total);


                            DefaultTableModel model = (DefaultTableModel) billtable.getModel();
                            model.addRow(new Object[] { billColumn1Value, billColumn2Value, f_billColumn3Value, item_qty, f_total });
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
                qty.setText("");
                jTable1.getSelectionModel().clearSelection();
                
                totalcal();
            }else {
                JOptionPane.showMessageDialog(null, "Please select a row from the source table");
            }
        }
        

    }
    
    
    public void totalcal(){
        
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

        jLabel5.setText("Total :  "+formattedNumber);
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
    
    public void bill(){
        LocalDateTime currentDateTime = LocalDateTime.now();
        int balance;
        
        String oid = orderno.getText(); 
        int orderid = Integer.parseInt(oid);
        String paid = PaidText.getText();
        int paidamount = Integer.parseInt(paid);
        
        balance = (int) (paidamount-totalPrice);
        
        
        
        try{
            String sql = "INSERT INTO bill (date,total, paid, bal, oid) VALUES ('"+currentDateTime+"',"+(int)totalPrice+","+paidamount+","+balance+","+orderid+")";
            pst = conn.prepareStatement(sql);
            pst.execute();
            
            DecimalFormat decimalFormat = new DecimalFormat("0.00");
            String formattedNumber = decimalFormat.format(balance);
            
            JOptionPane.showMessageDialog(null, "Balance is : "+formattedNumber);
            
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
    
    public void generatePDFBill() {
        int bill_id = 0;
        // Create a new document
        PDDocument document = new PDDocument();
    
        try {
            // Load Arial-Bold font
            PDType0Font timesBoldFont = PDType0Font.load(document, new File("/Users/achiladilshan/Arial-MT-Bold.ttf"));
            
            // Create a new page
            PDPage page = new PDPage(PDRectangle.A5);
            document.addPage(page);

            // Create a new content stream for the page
            PDPageContentStream contentStream = new PDPageContentStream(document, page);

            // Set font and font size for the content stream
            contentStream.setFont(timesBoldFont, 12);

            // Define the starting y-coordinate for the content
            float y = page.getMediaBox().getHeight() - 50;

            // Write the additional text
            
            String billText = "FIRE PLATE Restaurant\n" +
            "Kelaniya, Sri Lanka\n" +
            "-----------------------------------\n" +
            "Bill Details:\n" +
            "Date: May 20, 2023\n" +
            "Time: 18:30\n" +
            "Table: 5\n" +
            "Server: John Doe\n" +
            "\n" +
            "-----------------------------------\n" +
            "Item                 Quantity     Price\n" +
            "-----------------------------------\n";
                    
            String[] lines = billText.split("\n");
            for (String line : lines) {        
                contentStream.beginText();
                contentStream.newLineAtOffset(50, y);
                contentStream.showText(line);
                contentStream.endText();
            }

            // Move the y-coordinate up
            y -= 20;

            // Write the table data from JTable
            for (int row = 0; row < billtable.getRowCount(); row++) {
                for (int col = 0; col < billtable.getColumnCount(); col++) {
                    String cellValue = billtable.getValueAt(row, col).toString();
                    contentStream.beginText();
                    contentStream.newLineAtOffset(50 + col * 100, y);
                    contentStream.showText(cellValue);
                    contentStream.endText();
                }

                // Move the y-coordinate up
                y -= 20;
            }

            // Close the content stream
            contentStream.close();
            
            //get bill id
            try{
                String billid = "SELECT max(BID) FROM BILL";
                pst = conn.prepareStatement(billid);
                rs = pst.executeQuery();

                if (rs.next()) {
                    bill_id = rs.getInt("max(BID)");


                    rs.close();
                    pst.close();
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

            // Save the document to a PDF file
            File file = new File("/Users/achiladilshan/NetBeansProjects/restaurant management system/bills/"+bill_id+".pdf");
            document.save(file);

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                // Close the document
                document.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        logout = new javax.swing.JButton();
        jComboBox1 = new javax.swing.JComboBox<>();
        cashier_name = new javax.swing.JLabel();
        customer_name = new javax.swing.JLabel();
        PaidText = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        order_btn = new javax.swing.JButton();
        orderno = new javax.swing.JLabel();
        qty = new javax.swing.JTextField();
        AddToCart = new javax.swing.JButton();
        cancel_btn = new javax.swing.JButton();
        delete_btn = new javax.swing.JButton();
        CurrentOrders = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        billtable = new javax.swing.JTable();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setResizable(false);

        jPanel1.setPreferredSize(new java.awt.Dimension(1280, 720));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        logout.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Log Out.png"))); // NOI18N
        logout.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                logoutActionPerformed(evt);
            }
        });
        jPanel1.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 18, 170, 50));
        logout.setOpaque(false);
        logout.setContentAreaFilled(false);
        logout.setBorderPainted(false);

        jComboBox1.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jPanel1.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 120, 170, 40));

        cashier_name.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        cashier_name.setForeground(new java.awt.Color(102, 102, 102));
        jPanel1.add(cashier_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 198, 120, 20));

        customer_name.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        customer_name.setForeground(new java.awt.Color(102, 102, 102));
        jPanel1.add(customer_name, new org.netbeans.lib.awtextra.AbsoluteConstraints(850, 167, 80, 20));

        PaidText.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jPanel1.add(PaidText, new org.netbeans.lib.awtextra.AbsoluteConstraints(768, 660, 150, 30));
        PaidText.setOpaque(false);
        PaidText.setBorder(null);
        PaidText.setBackground(null);

        jLabel5.setFont(new java.awt.Font("Helvetica Neue", 0, 24)); // NOI18N
        jLabel5.setText("Total :  0.00");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 600, -1, -1));

        order_btn.setFont(new java.awt.Font("Helvetica Neue", 0, 15)); // NOI18N
        order_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Place Order.png"))); // NOI18N
        order_btn.setToolTipText("");
        order_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                order_btnActionPerformed(evt);
            }
        });
        jPanel1.add(order_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 650, 150, 50));
        order_btn.setOpaque(false);
        order_btn.setContentAreaFilled(false);
        order_btn.setBorderPainted(false);

        orderno.setFont(new java.awt.Font("Helvetica Neue", 0, 30)); // NOI18N
        jPanel1.add(orderno, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 106, 90, 40));

        qty.setFont(new java.awt.Font("Helvetica Neue", 0, 18)); // NOI18N
        jPanel1.add(qty, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 650, 90, 30));
        qty.setOpaque(false);
        qty.setBorder(null);
        qty.setBackground(null);

        AddToCart.setFont(new java.awt.Font("Helvetica Neue", 0, 14)); // NOI18N
        AddToCart.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Add to Cart.png"))); // NOI18N
        AddToCart.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AddToCartActionPerformed(evt);
            }
        });
        jPanel1.add(AddToCart, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 639, 150, 50));
        AddToCart.setOpaque(false);
        AddToCart.setContentAreaFilled(false);
        AddToCart.setBorderPainted(false);

        cancel_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Cancel Order.png"))); // NOI18N
        cancel_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancel_btnActionPerformed(evt);
            }
        });
        jPanel1.add(cancel_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 584, 150, -1));
        cancel_btn.setOpaque(false);
        cancel_btn.setContentAreaFilled(false);
        cancel_btn.setBorderPainted(false);

        delete_btn.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Delete Item.png"))); // NOI18N
        delete_btn.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                delete_btnActionPerformed(evt);
            }
        });
        jPanel1.add(delete_btn, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 584, -1, -1));
        delete_btn.setOpaque(false);
        delete_btn.setContentAreaFilled(false);
        delete_btn.setBorderPainted(false);

        CurrentOrders.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/Current Order.png"))); // NOI18N
        CurrentOrders.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CurrentOrdersActionPerformed(evt);
            }
        });
        jPanel1.add(CurrentOrders, new org.netbeans.lib.awtextra.AbsoluteConstraints(417, 110, 170, 50));
        CurrentOrders.setOpaque(false);
        CurrentOrders.setContentAreaFilled(false);
        CurrentOrders.setBorderPainted(false);

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
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(billtable);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(696, 240, 510, 330));

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
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 170, 540, 450));

        jLabel1.setFont(new java.awt.Font("Helvetica Neue", 0, 30)); // NOI18N
        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/cashier screen.png"))); // NOI18N
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

        DefaultTableModel model = (DefaultTableModel) billtable.getModel();
        int rowCount = model.getRowCount();
        
        if(rowCount != 0){
            JOptionPane.showMessageDialog(null,"Please Clear the Order First!");
        }else{
                
            MainMenu main = new MainMenu();
            main.setVisible(true);

            try {
                conn.close();
            } catch (SQLException ex) {
                Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
            } 

            this.dispose();
        }
    }//GEN-LAST:event_logoutActionPerformed

    private void AddToCartActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AddToCartActionPerformed
        
        tabledata();

        
    }//GEN-LAST:event_AddToCartActionPerformed

    private void order_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_order_btnActionPerformed
        
        String paidamount = PaidText.getText();

        if (billtable.getRowCount() > 0) {
            if(paidamount.isEmpty()){
                JOptionPane.showMessageDialog(null, "Enter the Paid Amount!", "Validation Error", JOptionPane.ERROR_MESSAGE);
            } else if (paidamount.length() > 11) {
                JOptionPane.showMessageDialog(null, "Quantity cannot exceed 11 digits", "Validation Error", JOptionPane.ERROR_MESSAGE);
            } else if (!paidamount.matches("\\d+")) {
                JOptionPane.showMessageDialog(null, "Quantity must contain only numeric digits", "Validation Error", JOptionPane.ERROR_MESSAGE);
            } else {
                int paidAmountInt = Integer.parseInt(paidamount);
                
                if(paidAmountInt<totalPrice){
                    JOptionPane.showMessageDialog(null, "Total Bill is Greater than Paid Amount!", "Validation Error", JOptionPane.ERROR_MESSAGE);
                }else{
        
                    int confirmation = JOptionPane.showConfirmDialog(this, "Confirm the order?", "Confirmation", JOptionPane.YES_NO_OPTION);

                    if (confirmation == JOptionPane.YES_OPTION) {

                        placeorder();
                        bill();
                        JOptionPane.showMessageDialog(this, "Order placed successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                        DefaultTableModel model = (DefaultTableModel) billtable.getModel();
                        model.setRowCount(0);
                        totalcal();
                        loadorderid();
                        generatePDFBill();

                        CustomerDetails cusdet = new CustomerDetails(loggedInUsername);
                        cusdet.setVisible(true);

                        try {
                            conn.close();
                        } catch (SQLException ex) {
                            Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
                        } 

                        this.dispose();
                    }

                } 
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
                    
                    String id = billtable.getValueAt(i, 0).toString();
                    int item_qty = (int) billtable.getValueAt(i, 3);
                    
                    model.removeRow(i);
                    
                    try{
                        //update item Quantities
                        String recoverqty = "UPDATE ITEM SET qty=qty+"+item_qty+" WHERE iid='"+id+"' and catid!= 2";
                        pst = conn.prepareStatement(recoverqty);
                        pst.executeUpdate();

                        loadtable();

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

                model.fireTableDataChanged(); // Refresh the table
                totalcal();
                
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
    }//GEN-LAST:event_cancel_btnActionPerformed

    private void delete_btnActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_delete_btnActionPerformed
        int selectedRow = billtable.getSelectedRow();
        
        if (selectedRow != -1) {
            int r = billtable.getSelectedRow();
            
            String id = billtable.getValueAt(r, 0).toString();
            int item_qty = (int) billtable.getValueAt(r, 3);
            
            DefaultTableModel model = (DefaultTableModel) billtable.getModel();
            model.removeRow(selectedRow);
            model.fireTableDataChanged(); // Refresh the table
            
            try{
                //update item Quantities
                String recoverqty = "UPDATE ITEM SET qty=qty+"+item_qty+" WHERE iid='"+id+"' and catid!= 2";
                pst = conn.prepareStatement(recoverqty);
                pst.executeUpdate();

                loadtable();
            
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
            JOptionPane.showMessageDialog(this, "Please Select an Item");
        }
        
        totalcal();
        
        
    }//GEN-LAST:event_delete_btnActionPerformed

    private void CurrentOrdersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CurrentOrdersActionPerformed
        CurrentOrders co = new CurrentOrders();
        co.setVisible(true);
                
    }//GEN-LAST:event_CurrentOrdersActionPerformed

    
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
    private javax.swing.JButton CurrentOrders;
    private javax.swing.JTextField PaidText;
    private javax.swing.JTable billtable;
    private javax.swing.JButton cancel_btn;
    private javax.swing.JLabel cashier_name;
    private javax.swing.JLabel customer_name;
    private javax.swing.JButton delete_btn;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    private javax.swing.JButton logout;
    private javax.swing.JButton order_btn;
    private javax.swing.JLabel orderno;
    private javax.swing.JTextField qty;
    // End of variables declaration//GEN-END:variables
}
