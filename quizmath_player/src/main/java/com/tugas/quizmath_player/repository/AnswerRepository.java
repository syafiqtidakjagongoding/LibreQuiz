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
/**
 *
 * @author syafiq
 */
public class AnswerRepository {
   public void upsertAnswer(int questionAnswerId, int siswaId) {
    String sql = "INSERT INTO siswa_answer (question_answer_id, siswa_id) " +
                 "VALUES (?, ?) " +
                 "ON DUPLICATE KEY UPDATE question_answer_id = VALUES(question_answer_id)";

    try (Connection conn = Database.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, questionAnswerId);
        stmt.setInt(2, siswaId);

        stmt.executeUpdate();

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
   public void deleteAnswerByQuestion(int questionId, int siswaId) {
    String sql = "DELETE FROM siswa_answer " +
                 "WHERE siswa_id = ? AND question_answer_id IN (" +
                 "   SELECT id FROM options_answer WHERE question_id = ?" +
                 ")";
    try (Connection conn = Database.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        stmt.setInt(1, siswaId);
        stmt.setInt(2, questionId);
        stmt.executeUpdate();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


}
