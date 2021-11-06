/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursemanagementsystem;
import javax.swing.*;
import java.awt.event.*;
import java.awt.GraphicsEnvironment;
/**
 *
 * @author CPA
 */
public class CourseManagementSystem {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        LoginForm login=new LoginForm();
        login.setDefaultCloseOperation(LoginForm.EXIT_ON_CLOSE);
        login.setTitle("User Login!!");
        login.setLocationRelativeTo(null);
        login.setVisible(true);
    }
}
