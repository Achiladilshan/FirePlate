
package codes;

import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.*;

public class DBconnect {
    public static Connection connect(){
    
        Connection conn = null;
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3306/Restuarant_management","root","");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
        
        return conn;
    
    }
}
