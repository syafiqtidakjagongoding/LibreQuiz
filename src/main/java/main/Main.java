/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package main;

import form.LoginForm;
import form.SoalForm;
import javax.swing.UIManager;
import com.formdev.flatlaf.FlatLightLaf;
import form.DaftarSiswaForm;
import form.LeaderboardForm;
/**
 *
 * @author syafiq
 */
public class Main {

    public static void main(String[] args) {
       try {
            UIManager.setLookAndFeel(new FlatLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        new LeaderboardForm().setVisible(true);
    }
}
