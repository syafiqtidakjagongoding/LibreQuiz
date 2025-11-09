/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.tugas.quizmath_player;

import com.formdev.flatlaf.FlatLightLaf;
import javax.swing.UIManager;
/**
 *
 * @author syafiq
 */
public class Quizmath_player {

     public static void main(String[] args) {
          try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }
        javax.swing.SwingUtilities.invokeLater(() -> {
            form.LoadingForm lf = new form.LoadingForm();
            lf.setVisible(true);
            lf.startLoading();  // jalanin progress bar
        });
        
//       new form.OpeningForm().setVisible(true);
   //     java.awt.EventQueue.invokeLater(() -> new form.AuthForm().setVisible(true));
    }
}
