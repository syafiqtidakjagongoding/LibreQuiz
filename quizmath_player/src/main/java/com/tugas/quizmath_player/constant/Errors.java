package constant;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author syafiq
 */
public class Errors {
    public String message = ""; 
    public boolean isError = true;
    
    public Errors(String message, boolean isError) {
        this.message = message;
        this.isError = isError;
    }
}
