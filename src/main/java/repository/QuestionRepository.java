/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import database.Database;
import entity.Answer;
import entity.Question;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author syafiq
 */
public class QuestionRepository {
   public List<Question> getAllQuestion() {
        List<Question> questions = new ArrayList<>();

        String sql = """
            SELECT q.id, q.question_text, q.answer_type, q.level, q.topic,
                   oa.id AS answer_id, oa.answer, oa.label, oa.score, oa.correct,
                   qi.image_path, oa.image_answer
            FROM question q
            INNER JOIN options_answer oa ON oa.question_id = q.id
            LEFT JOIN question_image qi ON qi.question_id = q.id
            ORDER BY q.id;
            """;

      try (Connection conn = Database.getConnection();
         PreparedStatement stmt = conn.prepareStatement(sql);
         ResultSet rs = stmt.executeQuery()) {

        Question currentQuestion = null;
        int lastQuestionId = -1;

        while (rs.next()) {
            int qid = rs.getInt("id");

            // Kalau ketemu question baru
            if (qid != lastQuestionId) {
                currentQuestion = new Question(
                        qid,
                        rs.getString("question_text"),
                        rs.getString("image_path"),
                        rs.getString("answer_type"),
                        rs.getString("level"),
                        rs.getString("topic"),
                        new ArrayList<>() // bikin list kosong untuk jawaban
                );
                questions.add(currentQuestion);
                lastQuestionId = qid;
            }

            // Tambahkan jawaban ke question yang sedang aktif
            Answer ans = new Answer(
                    rs.getInt("answer_id"),
                    rs.getString("answer"),
                    rs.getString("label"),
                    rs.getInt("score"),
                    rs.getBoolean("correct"),
                    rs.getString("image_answer")
            );
            currentQuestion.answers.add(ans);
        }

    } catch (SQLException e) {
            e.printStackTrace();
        }

        return questions;
    }
   
  public void deleteSoal(int question_id) {
    String sql = "DELETE FROM question WHERE id = ?";

    try (Connection conn = Database.getConnection();  // ganti dengan method koneksi kamu
         PreparedStatement stmt = conn.prepareStatement(sql)) {
        
        stmt.setInt(1, question_id);   // set parameter id

        int rowsDeleted = stmt.executeUpdate();
        if (rowsDeleted > 0) {
            System.out.println("Soal dengan ID " + question_id + " berhasil dihapus.");
        } else {
            System.out.println("Soal dengan ID " + question_id + " tidak ditemukan.");
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

   
   public void createSoal(Question question) {
    String insertQuestion = """
        INSERT INTO question (question_text, answer_type, level, topic) 
        VALUES (?,?,?,?)
    """;

    String insertAnswer = """
        INSERT INTO options_answer (answer, question_id, score, correct, label, image_answer) 
        VALUES (?,?,?,?,?,?)
    """;

    String insertImage = """
        INSERT INTO question_image (image_path, question_id) 
        VALUES (?,?)
    """;

    try (Connection conn = Database.getConnection()) {
        conn.setAutoCommit(false);

        // 1. Insert ke question
        int questionId;
        try (PreparedStatement ps = conn.prepareStatement(insertQuestion, Statement.RETURN_GENERATED_KEYS)) {
            ps.setString(1, question.question_text);
            ps.setString(3, question.answer_type);
            ps.setString(4, question.level);
            ps.setString(5, question.topic);
            ps.executeUpdate();

            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (rs.next()) {
                    questionId = rs.getInt(1);
                } else {
                    throw new SQLException("Gagal ambil generated key untuk question");
                }
            }
        }

        // 2. Insert semua jawaban
        try (PreparedStatement ps = conn.prepareStatement(insertAnswer)) {
            for (Answer ans : question.answers) {
                ps.setString(1, ans.answer);
                ps.setInt(2, questionId);
                ps.setInt(3, ans.score);
                ps.setBoolean(4, ans.correct);
                ps.setString(5, ans.label);
                ps.setString(6, ans.image_answer);
                ps.addBatch();
            }
            ps.executeBatch();
        }

        // 3. Insert gambar kalau ada
        if (question.question_image != null && !question.question_image.isEmpty()) {
            try (PreparedStatement ps = conn.prepareStatement(insertImage)) {
                ps.setString(1, question.question_image);
                ps.setInt(2, questionId);
                ps.executeUpdate();
            }
        }

        conn.commit();
    } catch (SQLException e) {
        e.printStackTrace();
    }
}

}
