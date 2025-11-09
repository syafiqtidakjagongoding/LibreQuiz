/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entity;
import constant.Errors;
/**
 *
 * @author syafiq
 */
public class DataSiswa {
    public String noAbsen;
    public String nama;
    public int kelas_id;
    public String nis;
    
   // Constructor dengan parameter
    public DataSiswa(String nama, int kelas_id, String nis,String noAbsen) {
        this.noAbsen = noAbsen;
        this.nama = nama;
        this.kelas_id = kelas_id;
        this.nis = nis;
    }
    
    public Errors validate() {
       if (nama == null || nama.trim().isEmpty()) {
        return new Errors("❌ Nama tidak boleh kosong", true);
    }
     
    if (nis == null || !nis.matches("\\d+")) {
        return new Errors("❌ NIS harus berupa angka", true);
    }
    
   if (noAbsen == null || noAbsen.trim().isEmpty()) {
    return new Errors("❌ No Absen tidak boleh kosong", true);
}

if (!noAbsen.trim().matches("\\d+")) {
    return new Errors("❌ No Absen harus berupa angka", true);
}

if (Integer.parseInt(noAbsen.trim()) <= 0) {
    return new Errors("❌ No Absen harus lebih dari 0", true);
}
   
    if (kelas_id <= 0) {
        return new Errors("❌ Kelas tidak valid", true);
    }
    
    return new Errors("✅ Data valid", false);
    }
}
