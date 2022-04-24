/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tugas4;

/**
 *
 * @author IIN
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class koneksi {
    String dbUrl = "jdbc:mysql://localhost/prakpbotugas4";
    String dbUsername = "root";
    String dbPassword = "";
    static final String driver = "com.mysql.cj.jdbc.Driver"; //newer version includes cj
    Connection konek;
    Statement statement; //untuk melakukan eksekusi query
    public koneksi() {
        try{
            Class.forName(driver); //load driver
            //membuka koneksi ke database
            konek = DriverManager.getConnection(dbUrl,dbUsername,dbPassword);
            System.out.println("Koneksi Berhasil Terhubung");
        }catch(Exception ex){
            System.out.println("Koneksi gagal Terhubung");
        }
    }
}
