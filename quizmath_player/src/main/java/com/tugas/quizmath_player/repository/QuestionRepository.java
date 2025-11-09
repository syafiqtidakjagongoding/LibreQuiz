/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package repository;

import entity.QuestionId;
import entity.Question;
import entity.OptionAnswer;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import database.Database;
/**
 *
 * @author syafiq
 */
public class QuestionRepository {
     public QuestionId getAllId() {
        String sql = "SELECT id FROM question";
        List<Integer> ids = new ArrayList<>();

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ids.add(rs.getInt("id"));
            }

            return new QuestionId(ids);

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return new QuestionId(new ArrayList<>()); // kalau error / kosong
    }
     
    public Question getQuestionById(int questionId) {
        String sql = "SELECT q.id, q.question_text, q.level, q.answer_type, " +
                     "q.topic, o.id AS id_answer, o.answer, o.correct, o.score, qi.image_path, o.image_answer " +
                     "FROM question q " +
                     "LEFT JOIN options_answer o ON o.question_id = q.id " +
                     "LEFT JOIN question_image qi ON qi.question_id = q.id " +
                     "WHERE q.id = ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, questionId);

            try (ResultSet rs = stmt.executeQuery()) {
                Question question = null;

                while (rs.next()) {
                    if (question == null) {
                        question = new Question(
                            rs.getInt("id"),
                            rs.getString("question_text"),
                            rs.getString("answer_type"), // answer_type belum ada di query
                            rs.getString("level"),
                            rs.getString("topic"),
                            rs.getString("image_path")
                        );
                    }

                    // Tambah opsi jawaban
                    int idAnswer = rs.getInt("id_answer");
                    String answer = rs.getString("answer");
                     int score = rs.getInt("score");
                     String image_answer = rs.getString("image_answer");
                    boolean correct = rs.getBoolean("correct");
                    question.addAnswer(new OptionAnswer(idAnswer, answer, score,correct, image_answer));
                }

                return question;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null; // kalau tidak ketemu
    }
}
