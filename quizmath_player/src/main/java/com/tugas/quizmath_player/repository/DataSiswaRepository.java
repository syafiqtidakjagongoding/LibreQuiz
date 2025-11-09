/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;
import database.Database;
import entity.DataSiswa;
import entity.Kelas;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import helper.Session;
/**
 *
 * @author syafiq
 */
public class DataSiswaRepository {
    
   public void loginSiswa(DataSiswa dataSiswa) {
    String sqlInsert = "INSERT INTO siswa(nama, kelas_id, nis, no_absen) VALUES (?, ?, ?, ?)";

    try (Connection conn = Database.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sqlInsert,Statement.RETURN_GENERATED_KEYS)) {

        stmt.setString(1, dataSiswa.nama);
        stmt.setInt(2, dataSiswa.kelas_id);
        stmt.setString(3, dataSiswa.nis);
        stmt.setInt(4, Integer.parseInt(dataSiswa.noAbsen));

        int rows = stmt.executeUpdate();
        if (rows > 0) {
            try (ResultSet rs = stmt.getGeneratedKeys()) {
                if (rs.next()) {
                    int siswaId = rs.getInt(1); // ambil id auto_increment
                    System.out.println("✅ Data siswa berhasil ditambahkan dengan ID: " + siswaId);

                    // langsung set session
                    Session.setSession(siswaId, dataSiswa.nama);
                }
            }
        } else {
            System.out.println("⚠️ Tidak ada data yang ditambahkan");
        }

    } catch (SQLException e) {
        System.err.println("❌ Gagal insert siswa: " + e.getMessage());
    }
}

   
  public List<Kelas> getAllKelas() {
    List<Kelas> listKelas = new ArrayList<>();
    String sql = "SELECT * FROM kelas";

     try (Connection conn = Database.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        while (rs.next()) {
            int id = rs.getInt("id");
            String kelas = rs.getString("kelas");
            String jurusan = rs.getString("jurusan");

            Kelas k = new Kelas(id, kelas, jurusan);
            listKelas.add(k);
        }

    } catch (SQLException e) {
        System.err.println("❌ Gagal ambil data kelas: " + e.getMessage());
    }

    return listKelas;
}


}
