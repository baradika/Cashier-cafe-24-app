/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package koneksi;

/**
 *
 * @author Dinda Khoirunnisa
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class koneksi {
    private static Connection koneksi = null;
    public static Connection getKoneksi(){
        if (koneksi != null){
            return koneksi;
        }
        else {
            String dbUrl = "jdbc:mysql://localhost:3306/dbcafe24?user=root&password=";
            
            try {
                Class.forName("com.mysql.jdbc.Driver");
                koneksi = DriverManager.getConnection(dbUrl);
                System.out.println("koneksi sukses");
            } catch (ClassNotFoundException | SQLException e){
                System.out.println("koneksi gagal: "+e);
            }
            return koneksi;
        }
               
    }
    
    public static void main(String args []){
        getKoneksi();
    }

    public static Connection getConnection() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}