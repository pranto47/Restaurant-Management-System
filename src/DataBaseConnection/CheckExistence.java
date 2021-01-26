/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Imran
 */
public class CheckExistence {
    Boolean success;
    public boolean checkexistence(String sql) throws SQLException{
        success = false;
        Connection con = new Databaseconnection().getConnection();
        PreparedStatement pst = con.prepareStatement(sql);
        ResultSet rs = pst.executeQuery();
        if(rs.next()){
            success = true;
        }
        pst.close();
        con.close();
        return success;
    }
}
