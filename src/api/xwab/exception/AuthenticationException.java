/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package api.xwab.exception;

/**
 *
 * @author manyrus
 */
public class AuthenticationException extends Exception {
    public static int WRONG_PASSWORD = 1;
    public static int WRONG_ID = 2;
    private int code;
    private String description;
    
    public AuthenticationException(String description, int code) {
        this.code = code;
        this.description = description;
    }
    
    public int getCode() {
        return this.code;
    }
    
    public String getDescription() {
        return this.description;
    }
    
}
