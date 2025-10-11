/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;

/**
 *
 * @author syafiq
 */
public class Kelas {
    public int id;
    public String kelas;
    public String jurusan;
    
    // Constructor kosong (default)
    public Kelas() {
    }

    // Constructor dengan semua field
    public Kelas(int id, String kelas, String jurusan) {
        this.id = id;
        this.kelas = kelas;
        this.jurusan = jurusan;
    }
    
     @Override
    public String toString() {
        // Teks yang tampil di combo box
        return kelas;
    }
}
