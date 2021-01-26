/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBaseConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;

public class UpdateData {
    public void updatedata_customer(String []str) throws SQLException{
        String sql="insert into customer values(?,?,?,?,?,?)";
        Connection con = new Databaseconnection().getConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
        } catch (SQLException ex) {
            System.out.println("Connection Problem");
        }
        pst.setInt(1, 0);
        pst.setString(2, str[1]);
        pst.setString(3, str[0]);
        pst.setString(4, str[2]);
        pst.setString(5, str[4]);
        pst.setString(6, str[3]);
        try {
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Connection Problem");
        }
        pst.close();
        con.close();
    }
    
    public void updatedata_employee(String fN,String lN,String hd,String pn,String ji,String s,String e,String p) throws SQLException  {
        
        Connection con = new Databaseconnection().getConnection();
        PreparedStatement pst = null;
        if (!hd.isEmpty()) {
            String sql = "insert into employees values(?,?,?,?,?,?,?,?,?)";
            try {
                pst = con.prepareStatement(sql);
            } catch (SQLException ex) {
                System.out.println("Connection Problem");
            }
            try {
                pst.setInt(1, 0);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            try {
                pst.setString(2, lN);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            pst.setString(3, fN);
            pst.setDate(4, java.sql.Date.valueOf(hd));
            pst.setString(5, pn);
            pst.setInt(6, Integer.parseInt(ji));
            pst.setString(7, p);
            pst.setInt(8, Integer.parseInt(s));
            pst.setString(9, e);
            try {
                pst.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        else{
            String sql = "insert into employees(employee_id,last_name,first_name,phone_number,job_id,login_password,salary,email) values(?,?,?,?,?,?,?,?)";
            try {
                pst = con.prepareStatement(sql);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            pst.setInt(1, 0);
            pst.setString(2, lN);
            pst.setString(3, fN);
            pst.setString(4, pn);
            pst.setInt(5, Integer.parseInt(ji));
            pst.setString(6, p);
            pst.setInt(7, Integer.parseInt(s));
            pst.setString(8, e);
            try {
                pst.executeUpdate();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        pst.close();
        con.close();
    }
    
    public int updatedata_add_category(String c_name,String c_description) throws SQLException{
        int ret = -1;
        String sql="insert into menu values(?,?,?)";
        Connection con = new Databaseconnection().getConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
        } catch (SQLException ex) {
            System.out.println("Connection Problem");
        }
        pst.setInt(1, 0);
        pst.setString(2, c_name);
        pst.setString(3, c_description);
        try {
            ret = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Connection Problem");
        }
        pst.close();
        con.close();
        return ret;
    }
    
    public int updatedata_add_food(String food_name,int food_price,int category_id,String food_description,double discount) throws SQLException{
        int ret = -1;
        String sql="insert into food values(?,?,?,?,?,?)";
        Connection con = new Databaseconnection().getConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
        } catch (SQLException ex) {
            System.out.println("Connection Problem");
        }
        pst.setInt(1, 0);
        pst.setString(2, food_name);
        pst.setInt(3, food_price);
        pst.setInt(4, category_id);
        pst.setString(5, food_description);
        pst.setDouble(6, discount);
        try {
            ret = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Connection Problem");
        }
        pst.close();
        con.close();
        return ret;
    }
    
    
    public int updatedata_add_package(String package_name,int package_price,int person,String package_description,double discount) throws SQLException{
        int ret = -1;
        String sql="insert into food_package values(?,?,?,?,?,?)";
        Connection con = new Databaseconnection().getConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
        } catch (SQLException ex) {
            System.out.println("Connection Problem");
        }
        pst.setInt(1, 0);
        pst.setInt(2, person);
        pst.setString(3, package_description);
        pst.setDouble(4, discount);
        pst.setInt(5, package_price);
        pst.setString(6, package_name);
        try {
            ret = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Connection Problem");
        }
        pst.close();
        con.close();
        return ret;
    }
    
    
    public int updatedata_add_ingredient(String ingredient_name) throws SQLException{
        int ret = -1;
        String sql="insert into ingredient values(?,?)";
        Connection con = new Databaseconnection().getConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
        } catch (SQLException ex) {
            System.out.println("Connection Problem");
        }
        pst.setInt(1, 0);
        pst.setString(2, ingredient_name);
        try {
            ret = pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Connection Problem");
        }
        pst.close();
        con.close();
        return ret;
    }
    
    public void updatedata_add_food_ingredient(int food_id,int ingredient_id,int availability,double stock) throws SQLException{
        String sql="insert into food_ingredient values(?,?,?,?)";
        Connection con = new Databaseconnection().getConnection();
        PreparedStatement pst = null;
        try {
            pst = con.prepareStatement(sql);
        } catch (SQLException ex) {
            System.out.println("Connection Problem");
        }
        pst.setInt(1, food_id);
        pst.setInt(2, ingredient_id);
        pst.setInt(3, availability);
        pst.setDouble(4, stock);
        try {
            pst.executeUpdate();
        } catch (SQLException ex) {
            System.out.println("Connection Problem");
        }
        pst.close();
        con.close();
    }
    
    public int updatedata_package_description(ObservableList<Item> data,String package_name) throws SQLException{
        String sql="insert into package_description values(?,?,?)";
        Connection con = new Databaseconnection().getConnection();
        int ret = -1;
        int foodId = 0;
        int packageId = 0;
        PreparedStatement pst = null;
        for(int i=0;i<data.size();i++){
            if(data.get(i).isSelected()){
                String sql1="select food_id from food where food_name = '"+data.get(i).getName().trim()+"'";
                PreparedStatement pst1 = con.prepareStatement(sql1);
                ResultSet rs = pst1.executeQuery();
                while(rs.next()){
                    foodId = rs.getInt(1);
                }
                pst1.close();
                
                sql1="select package_id from food_package where package_name = '"+package_name.trim()+"'";
                PreparedStatement pst2 = con.prepareStatement(sql1);
                ResultSet rs1 = pst2.executeQuery();
                while(rs1.next()){
                    packageId = rs1.getInt(1);
                }
                pst2.close();
                
                pst = con.prepareStatement(sql);
                pst.setInt(1, packageId);
                pst.setInt(2, foodId);
                pst.setInt(3, Integer.parseInt(data.get(i).getQuantity().getText()));
                
                ret = pst.executeUpdate();

                pst.close();
            }
        }
        con.close();
        return ret;
    }
    
    public int updatedata_remove(String sql) throws SQLException{
        int ret = -1;
        Connection con = new Databaseconnection().getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        ret = pst.executeUpdate();
        pst.close();
        con.close();
        return ret;
    }
    
    public int get_package_price(ObservableList<Item> data) throws SQLException{
        int ret = -1;
        double total_price = 0;
        int quantity = 0;
        Connection con = new Databaseconnection().getConnection();
        for (int i=0;i<data.size();i++) {
            if (data.get(i).isSelected()) {
                CallableStatement stmt = con.prepareCall("{? = call get_food_price(?,?)}");
                stmt.registerOutParameter(1, Types.DOUBLE);

                stmt.setString(2, data.get(i).getName());
                quantity = Integer.parseInt(data.get(i).getQuantity().getText());
                stmt.setInt(3, quantity);
                stmt.execute();
                double get = stmt.getDouble(1);
                if(get==-1){
                    total_price = -1;
                    break;
                }
                total_price += get;
            }
        }
        con.close();
        return (int)total_price;
    }
    
    
    public void updatedata_Privileges(ObservableList<Privileges> data_Privileges,String email) throws SQLException{
        
        String str = "select employee_id from employees where email = '"+email+"'";
        Connection con1 = new Databaseconnection().getConnection();
        PreparedStatement pst1 = con1.prepareStatement(str);
        ResultSet rs = pst1.executeQuery();
        int id = -1;
        while(rs.next()){
            id = rs.getInt("employee_id");
        }
        if(id==-1)
            return;
        pst1.close();
        con1.close();
        
        Connection con = new Databaseconnection().getConnection();
        for(int i=0;i<data_Privileges.size();i++){
            if (!data_Privileges.get(i).isSelected()) {
                String sql = "insert into employees_privilege_set values(?,?)";
                PreparedStatement pst = null;
                try {
                    pst = con.prepareStatement(sql);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                pst.setInt(1, id);
                pst.setString(2, data_Privileges.get(i).getPrivileges());
                try {
                    pst.executeUpdate();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                pst.close();
            }
        }
        con.close();
    }
    
    
    public void set_Privileges(FXMLLoader loader,String email) throws SQLException{
        
        String str = "select employee_id from employees where email = '"+email+"'";
        Connection con1 = new Databaseconnection().getConnection();
        PreparedStatement pst1 = con1.prepareStatement(str);
        ResultSet rs = pst1.executeQuery();
        int id = -1;
        while(rs.next()){
            id = rs.getInt("employee_id");
        }
        if(id==-1)
            return;
        pst1.close();
        con1.close();
        
        String str1 = "select privilege_name from employees_privilege_set where employee_id = "+id;
        Connection con2 = new Databaseconnection().getConnection();
        PreparedStatement pst2 = con2.prepareStatement(str1);
        ResultSet rs1 = pst2.executeQuery();
        
        Connection con = new Databaseconnection().getConnection();
        String fxid;
        String type;
        while (rs1.next()) {
            fxid = "";
            type = "";
            String sql = "select node_fx_id,node_type from employees_privileges where privilege_name = '" + rs1.getString("privilege_name") + "'";
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs2 = pst.executeQuery();
            while(rs2.next()){
                fxid = rs2.getString("node_fx_id");
                type = rs2.getString("node_type");
            }
            if(fxid.isEmpty()||type.isEmpty())
                return;
            
            if(type.equalsIgnoreCase("menu")){
                Menu m1 = (Menu)loader.getNamespace().get(fxid);
                m1.setVisible(false);
            }
            else if(type.equalsIgnoreCase("menuitem")){
                MenuItem m1 = (MenuItem)loader.getNamespace().get(fxid);
                m1.setVisible(false);
            }
            pst.close();
        }
        pst2.close();
        con2.close();
        con.close();
    }
    
    
}
