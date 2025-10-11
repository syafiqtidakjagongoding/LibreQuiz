/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import database.Database;
import entity.Kelas;
import entity.Siswa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author syafiq
 */
public class KelasRepository {
    public List<Kelas> getAllKelas() {
        String sql = """
        SELECT id, kelas, jurusan FROM kelas
        """;
        List<Kelas> kelass = new ArrayList<>();
         try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String namaKelas = rs.getString("kelas");
                String jurusan = rs.getString("jurusan");
                
               Kelas kelas = new Kelas(id,namaKelas,jurusan);
               kelass.add(kelas);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return kelass;
    }
}
