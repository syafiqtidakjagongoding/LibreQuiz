/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import entity.AuthKey;
/**
 *
 * @author syafiq
 */
public class AuthKeyRepository  {
    
   public boolean validate(String inputKey) {
    String sql = "SELECT auth_key FROM auth_key LIMIT 1";

    try (Connection conn = Database.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        if (rs.next()) {
            String keyFromDb = rs.getString("auth_key");
            return inputKey.equals(keyFromDb);
        }

    } catch (SQLException e) {
        e.printStackTrace(); 
        // biar repository tidak tergantung UI (JOptionPane)
    }

    return false; // default kalau tidak ada data atau error
}

   
}
