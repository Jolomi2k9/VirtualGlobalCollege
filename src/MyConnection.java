
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MyConnection {
    private static final String USERNAME = "root";
    private static final String PASSWORD = "";
    private static final String CONN_STRING = 
            "jdbc:mysql://localhost:3306/academic_informationdb";
    
    public static Connection getConnection(){        
        Connection con = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            con = DriverManager.getConnection(CONN_STRING,USERNAME,PASSWORD);
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
        }
        return con;
    }
}
