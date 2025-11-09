/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import database.Database;
import java.awt.Component;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

/**
 *
 * @author syafiq
 */
public class FinalScoreRepository {
  public void calculateScore(Component parentComponent, int siswaId) {
    String sqlBenar = """
        SELECT COUNT(*) AS total_benar
        FROM siswa_answer sa
        INNER JOIN options_answer oa ON sa.question_answer_id = oa.id
        WHERE oa.correct = 1 AND sa.siswa_id = ?
    """;

    String sqlTotalSoal = "SELECT COUNT(*) AS total_soal FROM question";

    try (Connection conn = Database.getConnection();
         PreparedStatement stmtBenar = conn.prepareStatement(sqlBenar);
         PreparedStatement stmtTotal = conn.prepareStatement(sqlTotalSoal)) {

        // set siswaId untuk query jawaban benar
        stmtBenar.setInt(1, siswaId);

        int totalBenar = 0;
        try (ResultSet rsBenar = stmtBenar.executeQuery()) {
            if (rsBenar.next()) {
                totalBenar = rsBenar.getInt("total_benar");
            }
        }

        int totalSoal = 0;
        try (ResultSet rsTotal = stmtTotal.executeQuery()) {
            if (rsTotal.next()) {
                totalSoal = rsTotal.getInt("total_soal");
            }
        }

        if (totalSoal == 0) {
            JOptionPane.showMessageDialog(
                parentComponent,
                "Total soal masih 0, tidak bisa hitung skor.",
                "Peringatan",
                JOptionPane.WARNING_MESSAGE
            );
            return;
        }

        int totalSalah = totalSoal - totalBenar;
        double nilaiAkhir = ((double) totalBenar / totalSoal) * 100.0;

        String sqlInsertFinal = """
            INSERT INTO final_score (siswa_id, correct_answer, wrong_answer, total_question, final_score)
            VALUES (?, ?, ?, ?, ?)
            ON DUPLICATE KEY UPDATE 
                correct_answer = VALUES(correct_answer),
                wrong_answer = VALUES(wrong_answer),
                total_question = VALUES(total_question),
                final_score = VALUES(final_score)
        """;

        try (PreparedStatement stmtInsert = conn.prepareStatement(sqlInsertFinal)) {
            stmtInsert.setInt(1, siswaId);
            stmtInsert.setInt(2, totalBenar);
            stmtInsert.setInt(3, totalSalah);
            stmtInsert.setInt(4, totalSoal);
            stmtInsert.setDouble(5, nilaiAkhir);
            stmtInsert.executeUpdate();
        }

        // tampilkan hasilnya ke user
        String hasil = String.format(
            "Siswa ID: %d\nBenar: %d\nSalah: %d\nTotal Soal: %d\nNilai Akhir: %.2f",
            siswaId, totalBenar, totalSalah, totalSoal, nilaiAkhir
        );

        JOptionPane.showMessageDialog(
            parentComponent,
            hasil,
            "Hasil Perhitungan Skor",
            JOptionPane.INFORMATION_MESSAGE
        );

    } catch (SQLException e) {
        JOptionPane.showMessageDialog(
            parentComponent,
            "Terjadi kesalahan saat menghitung skor:\n" + e.getMessage(),
            "Error",
            JOptionPane.ERROR_MESSAGE
        );
    }
}

}
