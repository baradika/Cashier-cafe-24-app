/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package kasir;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Dinda Khoirunnisa
 */
public class dbconnect {
        private static java.sql.Connection dbconnect;
        
        public static java.sql.Connection getdbconnect(){
            if (dbconnect == null){
                try {
                    String url = "jbdc:mysql://localhost/kasir";
                    String user = "root";
                    String Password = "";
                    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                    dbconnect = DriverManager.getConnection(url, user, Password);
                    System.out.println("Berhasil Terhubung");
                } catch (Exception e) {
                    System.out.println("gagal terhubung");
                }
            }
            return dbconnect;
        }
    public static void main(String args[]){
        getdbconnect();
    }
}
