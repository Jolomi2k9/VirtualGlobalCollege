
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;


public class Student {
    
    public void insertUpdateDeleteStudent(char operation, String id,String fname,String lname,
                                          String sex, String bdate, String phone, String address){
        
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        
        //insert data into database
        if(operation == 'i'){
            try {
                ps = con.prepareStatement("INSERT INTO student(Student_ID,first_name,last_name,DOB,Gender,Telephone,Address) VALUES(?,?,?,?,?,?,?)");
                ps.setString(1,id);
                ps.setString(2,fname);
                ps.setString(3,lname);
                ps.setString(4,bdate);
                ps.setString(5,sex);
                ps.setString(6,phone);
                ps.setString(7,address);
                
                
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null,"Student Data Changed");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //update the database
        if(operation == 'u'){
            try {
                ps = con.prepareStatement("UPDATE `student` SET `first_Name`= ?,`last_Name`=  ?,`DOB`= ? ,`Gender`= ?,`Telephone`= ?,`Address`= ? WHERE Student_ID = ?");
               
                ps.setString(1,fname);
                ps.setString(2,lname);
                ps.setString(3,bdate);
                ps.setString(4,sex);
                ps.setString(5,phone);
                ps.setString(6,address);
                ps.setString(7,id);
                
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null,"Student Data Updated");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //delete from database
        if(operation == 'd'){
            try {
                ps = con.prepareStatement("DELETE FROM `student` WHERE `Student_ID` = ?");               
                ps.setString(1,id);                
                if(ps.executeUpdate()>0){
                    JOptionPane.showMessageDialog(null,"Student Deleted");
                }
            } catch (SQLException ex) {
                Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void fillStudentJtable(JTable table, String valueToSearch){
        
        Connection con = MyConnection.getConnection();
        PreparedStatement ps;
        
        try {
            ps = con.prepareStatement("SELECT * FROM `student` WHERE CONCAT(`first_Name`, `last_Name` ,`Telephone` ,`Address`)LIKE ?");
            ps.setString(1,"%"+valueToSearch+"%");
            
            ResultSet rs = ps.executeQuery();
            DefaultTableModel model =(DefaultTableModel) table.getModel();
            
            Object[] row;
            
            while(rs.next()){
                row = new Object[8];
                row[0] = rs.getString(1);
                row[1] = rs.getString(2);
                row[2] = rs.getString(3);
                row[3] = rs.getString(4);
                row[4] = rs.getString(5);
                row[5] = rs.getString(6);
                row[6] = rs.getString(7);
                row[7] = rs.getString(8);
                
                model.addRow(row);
            
        }
            
        } catch (SQLException ex) {
            Logger.getLogger(Student.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
