
package DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import project.ClientPageController;

public class GetData {
    List<List<String>> resultList = new ArrayList<>();
    public List<List<String>>  getdata(String sql) throws SQLException{
        Connection con = new Databaseconnection().getConnection();
        PreparedStatement pst = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = pst.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        String name = " ";
        while (rs.next()) {
            List<String> row = new ArrayList<>();
            if(name.equals(rs.getString(1))){
                row.add("");
            }
            else{
                row.add(rs.getString(1));
                name = rs.getString(1);
            }
            row.add(rs.getString(2));
            row.add(rs.getString(3));
            resultList.add(row);
        }
        pst.close();
        con.close();
        return resultList;
    }
    List<List<String>> resultList_Privileges = new ArrayList<>();
    public List<List<String>>  getdata_Privileges(String sql) throws SQLException{
        Connection con = new Databaseconnection().getConnection();
        PreparedStatement pst = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = pst.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        while (rs.next()) {
            List<String> row = new ArrayList<>();
            row.add(rs.getString(1));
            row.add(rs.getString(2));
            row.add(rs.getString(3));
            resultList_Privileges.add(row);
        }
        pst.close();
        con.close();
        return resultList_Privileges;
    }
    
    List<List<String>> food_data = new ArrayList();
    List<List<String>> package_data = new ArrayList();
       
    
    public void  getdata_food(String sql) throws SQLException{
        food_data.clear();
        Connection con = new Databaseconnection().getConnection();
        PreparedStatement pst = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = pst.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        while (rs.next()) {
            List<String> row = new ArrayList<>();
            row.add(rs.getString(1));
            row.add(rs.getString(2));
            row.add(rs.getString(3));
            row.add(rs.getString(4));
            row.add(rs.getString(5));
            food_data.add(row);
        }
        pst.close();
        con.close();
    }
    
    public void  getdata_package(String sql) throws SQLException{
        package_data.clear();
        Connection con = new Databaseconnection().getConnection();
        PreparedStatement pst = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = pst.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        while (rs.next()) {
            List<String> row = new ArrayList<>();
            row.add(rs.getString(1));
            row.add(rs.getString(2));
            row.add(rs.getString(3));
            row.add(rs.getString(4));
            package_data.add(row);
        }
        pst.close();
        con.close();
    }
    
    public int  getdata_menu_length() throws SQLException{
        String sql = "select count(category_id) length from menu";
        int length= 0;
        Connection con = new Databaseconnection().getConnection();
        PreparedStatement pst = con.prepareStatement(sql,ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
        ResultSet rs = pst.executeQuery();
        ResultSetMetaData rsmd = rs.getMetaData();
        while (rs.next()) {
            length = rs.getInt(1);
        }
        pst.close();
        con.close();
        return length;
    }
    
    public int  getdata_package_desc(int package_id,String package_name) throws SQLException{
        String sql="select fo.food_name name,fo.food_price price from food_package fp join food fo on(fp.food_id = fo.food_id) where package_id = ?";
        Connection con = new Databaseconnection().getConnection();
        PreparedStatement pst = null;

        pst = con.prepareStatement(sql);
        pst.setInt(1, package_id);
        ResultSet rs = pst.executeQuery();
        
        String message = package_name+"\n\nContents:\n";
        int i =1;
        while (rs.next()) {
            message = message+1+". "+rs.;
        }

        pst.close();
        con.close();
    }
    
    public VBox get_Order(String sql_food,String sql_package) throws SQLException{
        getdata_food(sql_food);
        getdata_package(sql_package);
        
        VBox vbox = new VBox(5);
        
        int menu_length = getdata_menu_length();

        int f_count = food_data.size();
        int p_count = package_data.size();
        
        Label category_name = new Label("Category Name");
        Label food_name = new Label("Food Name");
        Label price = new Label("Price");
        Label food_quantity = new Label("Quantity");
        category_name.setFont(new Font("Arial",15));
        food_name.setFont(new Font("Arial",15));
        price.setFont(new Font("Arial",15));
        food_quantity.setFont(new Font("Arial",15));
        HBox hb_header = new HBox(5);
        hb_header.getChildren().addAll(category_name,food_name,price,food_quantity);
        category_name.setPrefWidth(150);
        food_name.setPrefWidth(150);
        price.setPrefWidth(50);
        food_quantity.setPrefWidth(100);
        
        Hyperlink [] menu_hyperlink_desc = new Hyperlink[f_count];
        CheckBox [] food_checkbox_choice = new CheckBox[f_count];
        TextField [] food_textfield_input = new TextField[f_count];
        Hyperlink [] food_hyperlink_desc = new Hyperlink[f_count];
        Label [] food_label_price = new Label[f_count];
        HBox []food_hbox = new HBox[f_count];
        
        CheckBox [] package_checkbox_choice = new CheckBox[p_count];
        TextField [] package_textfield_input = new TextField[p_count];
        Hyperlink [] package_hyperlink_desc = new Hyperlink[p_count];
        Label [] package_label_person = new Label[p_count];
        Label [] package_label_price = new Label[p_count];
        HBox []package_hbox = new HBox[p_count];
        
        Label header = new Label("   Food   ");
        Label space = new Label("");
        header.setFont(new Font("Arial",30));
        vbox.getChildren().add(header);
        vbox.getChildren().add(hb_header);
        vbox.setMargin(header,new Insets(10,10,10,100));
        vbox.setMargin(hb_header,new Insets(10,10,10,40));
        
        for(int i = 0;i<f_count;i++){
            //System.out.println("size = "+i);
            CheckBox cb_food = food_checkbox_choice[i] = new CheckBox();
            Hyperlink hpl_category = menu_hyperlink_desc[i] = new Hyperlink(food_data.get(i).get(2));
            Hyperlink hpl_food = food_hyperlink_desc[i] = new Hyperlink(food_data.get(i).get(3));
            Label lb_food = food_label_price[i] = new Label(food_data.get(i).get(4));
            TextField tf_food = food_textfield_input[i] = new TextField("1");
            HBox hb_food = food_hbox[i] = new HBox(5);
            
            cb_food.setPrefWidth(10);
            hpl_category.setPrefWidth(150);
            hpl_food.setPrefWidth(150);
            lb_food.setPrefWidth(50);
            
            vbox.setMargin(food_hbox[i],new Insets(10,10,10,10));
            
            food_hbox[i].getChildren().addAll(food_checkbox_choice[i],menu_hyperlink_desc[i],food_hyperlink_desc[i],food_label_price[i],food_textfield_input[i]);
            vbox.getChildren().add(food_hbox[i]);
        }
        vbox.getChildren().add(space);
        
        
        Label package_name = new Label("Package Name");
        Label person = new Label("Person");
        Label package_price = new Label("Price");
        Label package_quantity = new Label("Quantity");
        package_name.setFont(new Font("Arial",15));
        person.setFont(new Font("Arial",15));
        package_price.setFont(new Font("Arial",15));
        package_quantity.setFont(new Font("Arial",15));
        HBox hb_header_package = new HBox(5);
        hb_header_package.getChildren().addAll(package_name,person,package_price,package_quantity);
        package_name.setPrefWidth(250);
        person.setPrefWidth(50);
        package_price.setPrefWidth(50);
        package_quantity.setPrefWidth(100);
        
        vbox.getChildren().add(hb_header_package);
        vbox.setMargin(hb_header_package,new Insets(10,10,10,40));
        
        for(int i=0;i<p_count;i++){
            CheckBox cb_package = package_checkbox_choice[i] = new CheckBox();
            Hyperlink hpl_package = package_hyperlink_desc[i] = new Hyperlink(package_data.get(i).get(1));
            Label lb_person = package_label_person[i] = new Label(package_data.get(i).get(2));
            Label lb_price_package = package_label_price[i] = new Label(package_data.get(i).get(3));
            TextField tf_package_choice = package_textfield_input[i] = new TextField("1");
            HBox hb_package = package_hbox[i] = new HBox(5);
            
            cb_package.setPrefWidth(10);
            hpl_package.setPrefWidth(250);
            lb_person.setPrefWidth(50);
            lb_price_package.setPrefWidth(50);
            
            hpl_package.setOnAction(new EventHandler<ActionEvent>(){
                @Override
                public void handle(ActionEvent event) {
                    
                }
                
            });
            
            vbox.setMargin(package_hbox[i],new Insets(10,10,10,10));
            hb_package.getChildren().addAll(cb_package,hpl_package,lb_person,lb_price_package,tf_package_choice);
            vbox.getChildren().add(hb_package);
        }
        
        return vbox;
    }
    
}
