/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class Login_check {
    Boolean success;
    Connection con;
    public Boolean Customer_login(String email,String password){
        try {
            success = false;
            con = new Databaseconnection().getConnection();
            String sql = "select * from customer where email = ? and login_password = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                success = true;
            }
            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println("Coonection Failed");
        }
        return success;
    }
    
    public Boolean Employee_login(String email,String password){
        try {
            success = false;
            con = new Databaseconnection().getConnection();
            String sql = "select * from employees where email = ? and login_password = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, email);
            pst.setString(2, password);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                success = true;
            }
            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println("Coonection Failed");
        }
        return success;
    }
    
    public Boolean Customer_registration(String email){
        try {
            success = false;
            con = new Databaseconnection().getConnection();
            String sql = "select * from customer where email = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                success = true;
            }
            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println("Coonection Failed");
        }
        return success;
    }
    
    public Boolean Employee_registration(String email){
        try {
            success = false;
            con = new Databaseconnection().getConnection();
            String sql = "select * from employees where email = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                success = true;
            }
            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println("Coonection Failed");
        }
        return success;
    }
    
    public Boolean Employees_registration(String email){
        try {
            success = false;
            con = new Databaseconnection().getConnection();
            String sql = "select * from employees where email = ?";
            PreparedStatement pst = con.prepareStatement(sql);
            pst.setString(1, email);
            ResultSet rs = pst.executeQuery();
            if (rs.next()) {
                success = true;
            }
            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println("Coonection Failed");
        }
        return success;
    }
}
