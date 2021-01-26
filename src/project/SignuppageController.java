package project;

import DataBaseConnection.Login_check;
import DataBaseConnection.UpdateData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class SignuppageController {

    @FXML
    private PasswordField password;
    
    @FXML
    private Label message;

    @FXML
    private TextField firstname;

    @FXML
    private TextField phonenumber;

    @FXML
    private Hyperlink back;

    @FXML
    private PasswordField confirmpassword;

    @FXML
    private TextField email;

    @FXML
    private TextField lastname;
    
    @FXML
    private String []data = new String[6];
    
    @FXML
    private String validEmail = "@gmail.com";
    
    @FXML
    private Button button;

    @FXML
    void backtologin(ActionEvent event) throws Exception {
        Stage stage = (Stage)back.getScene().getWindow();
        //stage.close();
        new Project().start(stage);
        data[0] = data[1] = data[1] = data[1] = data[1] = data[1] ="";
    }

    @FXML
    void setregistration(ActionEvent event) throws Exception{
        message.setText("");
        data[0] = firstname.getText();
        data[1] = lastname.getText();
        data[2] = phonenumber.getText();
        data[3] = email.getText();
        data[4] = password.getText();
        data[5] = confirmpassword.getText();
        if(data[0].isEmpty()){
            message.setText("  Please enter your first name");
        }
        else if(data[1].isEmpty()){
            message.setText("  Please enter your last name");
        }
        else if(data[2].isEmpty()){
            message.setText("  Please enter your phone number");
        }
        else if(data[3].isEmpty()){
            message.setText("  Please enter your Email");
        }
        else if(!data[3].toLowerCase().contains(validEmail.toLowerCase())){
            message.setText("Please Enter a valid email");
        }
        else if(data[4].isEmpty()){
            message.setText("  Please enter your password");
        }
        else if(data[5].isEmpty()){
            message.setText("  Please enter your confirmation password");
        }
        else if(!data[4].equals(data[5])){
            System.out.println(data[4]+" -> "+data[5]);
            message.setText("  Error: your password and confirmation password do not match");
        }
        else{
            try {
                Boolean customerAccount = new Login_check().Customer_registration(data[3]);
                if(customerAccount){
                    message.setText("This Email is used. Registration Failed");
                }
                else{
                    //String str = "insert into customer values(0,"+data[1].trim()+","+data[0].trim()+","+data[2].trim()+","+data[4].trim()+","+data[3].trim()+")";
                    //System.out.println(str);
                    new UpdateData().updatedata_customer(data);
                    //new UpdateData().updatedata(str);
                    Stage stage = (Stage) button.getScene().getWindow();
                    new Project().start(stage);
                }
            } catch (SQLException ex) {
                message.setText("Unsuccessful Registration");
            }
            data[0] = data[1] = data[1] = data[1] = data[1] = data[1] ="";
        }
    }
}
