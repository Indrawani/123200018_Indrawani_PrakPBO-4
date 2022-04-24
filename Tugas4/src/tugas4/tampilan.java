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
import javax.swing.*;
import java.awt.event.*;
import java.sql.ResultSet;
import java.sql.SQLException;

public class tampilan extends JFrame {
    
     // Instansiasi Objek Komponen GUI
    
    JLabel ltitle = new JLabel("*** LOGIN ***",SwingConstants.CENTER);
    JLabel luser = new JLabel("Username   : ");
    JLabel lpass = new JLabel("Password    : ");
    JLabel ldaftar = new JLabel("*** DAFTAR ***",SwingConstants.CENTER);
    JLabel lnuser = new JLabel("Username  : ");
    JLabel lnpass = new JLabel("Password   : ");

    JTextField tfUser = new JTextField();
    JTextField tfPass = new JTextField();
    JTextField tfnuser = new JTextField();
    JTextField tfnpass = new JTextField();

    JButton btnLogin = new JButton("LOGIN");
    JButton btnRegis = new JButton("DAFTAR");
    String dbtabel = "tugasjdbc";
    String query;
    String[][] data = new String[2][2];

    koneksi konek = new koneksi();
    
    public tampilan(){
        setTitle("Login dan pendaftaran");
        setLayout(null);
        setSize(390 , 480);

            add(ltitle);
            add(luser);
            add(lpass);
            add(lnuser);
            add(lnpass);
            add(ldaftar);
            add(tfPass);
            add(tfUser);
            add(tfnuser);
            add(tfnpass);
            add(btnLogin);
            add(btnRegis);

        ltitle.setBounds(0,10,480,20);
        luser.setBounds(20,50,100,25);
        tfUser.setBounds(160,50,180,25);
        lpass.setBounds(20,90,100,25);
        tfPass.setBounds(160,90,180,25);
        btnLogin.setBounds(50,160,130,30);
        ldaftar.setBounds(0,220,480,20);
        lnuser.setBounds(20,260,100,25);
        tfnuser.setBounds(160,260,180,25);
        lnpass.setBounds(20,300,100,25);
        tfnpass.setBounds(160,300,180,25);
        btnRegis.setBounds(50,360,130,30);

        btnLogin.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {

                try {
                    konek.statement = konek.konek.createStatement();
                    query = "select 'username' from " + dbtabel + " where username = '" + getUser() + "';";
                    ResultSet result_user = konek.statement.executeQuery(query);
                    
                    while (result_user.next())
                        data[0][0] = result_user.getString("username");
                    query = "select 'password' from " + dbtabel + " where password = '" + getPass() + "';";
                    ResultSet result_pass = konek.statement.executeQuery(query);
                    
                    while (result_pass.next())
                        data[0][1] =result_pass.getString("password");
                    
                    if (getUser().isEmpty()|| getPass().isEmpty()) {
                        JOptionPane.showMessageDialog(null, "Isi semua Data");
                        
                    }else if (data[0][0] == null){
                        JOptionPane.showMessageDialog(null,"Username tidak ditemukan !");
                        
                    } else if (data[0][1] == null){
                        JOptionPane.showMessageDialog(null,"Password Anda Salah !");
                        
                    } else {
                        JOptionPane.showMessageDialog(null,"Login berhasil dilakukan !");
                        
                    }
                    data[0][0]= null; data[0][1]= null;

                } catch (SQLException e1){
                    System.out.println(e1.getMessage());
                }
            }
        });
        btnRegis.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    konek.statement = konek.konek.createStatement();
                    query = "select 'username' from " + dbtabel + " where username = '" + getnuser() + "';";
                    ResultSet result_user = konek.statement.executeQuery(query);
                        while (result_user.next())
                            data[0][0] = result_user.getString("username");
                        
                        if (getnuser().isEmpty()|| getnpass().isEmpty()) {
                            JOptionPane.showMessageDialog(null, "Isi semua data");
                            
                        }else if(data[0][0] != null){
                            JOptionPane.showMessageDialog(null,"Username telah digunakan");
                            
                        } else {
                            query = "Insert  into " + dbtabel + " (username, password) Values ('" + getnuser() + "','" + getnpass() + "')";
                            konek.statement.executeUpdate(query);
                            JOptionPane.showMessageDialog(null,"Pendaftaran berhasil !");
                        }
                        data[0][0] = null;
         
                }catch (SQLException e2){
                    System.out.println(e2.getMessage());
                }
            }
        });
        setVisible(true);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

    public String getUser(){
        return tfUser.getText();
    }
    public String getPass(){
        return tfPass.getText();
    }
        public String getnuser(){return tfnuser.getText();}

        public String getnpass(){return tfnpass.getText();}
}