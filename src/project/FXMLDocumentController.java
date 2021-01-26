package project;

import DataBaseConnection.Login_check;
import DataBaseConnection.UpdateData;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class FXMLDocumentController {
    
//    static ArrayList<String>u_email = new ArrayList<String>();
//    static ArrayList<String>u_password = new ArrayList<String>();
    
    @FXML
    private PasswordField password;

    @FXML
    private Button login;

    @FXML
    private Label message;

    @FXML
    private Hyperlink signup;

    @FXML
    private TextField email;

    @FXML
    private Hyperlink forgotpassword;
    
    @FXML
    private String validEmail;

    @FXML
    void setpassword(ActionEvent event) {

    }

    @FXML
    void setlogin(ActionEvent event) throws IOException, SQLException {
        validEmail = "@gmail.com";
        message.setStyle("-fx-background-color:red");
        String Email= email.getText().trim();
        String Password = password.getText().trim();
        
//        u_email.add(Email);
//        u_password.add(Password);
        
        if(Email.isEmpty()){
            message.setText("Please Enter your email");
        }
        else if(!Email.toLowerCase().contains(validEmail.toLowerCase())){
            message.setText("Please Enter a valid email");
        }
        else if(Password.isEmpty()){
            message.setText("Please Enter your password");
        }
        else {
            
               Boolean customerAccount = new Login_check().Customer_login(Email, Password);
               if(customerAccount){
                   //int index = u_email.size();
                   Stage stage = (Stage) login.getScene().getWindow();
                   Parent root = FXMLLoader.load(getClass().getResource("ClientPage.fxml"));
                   //new ClientPageController().setClientPageController(u_email.get(index-1),u_password.get(index-1));
                   new ClientPageController().setClientPageController(Email,Password);
                   Scene scene = new Scene(root, 800, 600);

                   stage.setScene(scene);
                   stage.show();
               }
             else {
                Boolean employee_account = new Login_check().Employee_login(Email, Password);
                if (employee_account) {
                    //int index = u_email.size();
                    Stage stage = (Stage) login.getScene().getWindow();
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("AdminPage.fxml"));
                    //Parent root = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));
                    Parent root = loader.load();
                    Scene scene = new Scene(root, 800, 600);
                    new UpdateData().set_Privileges(loader, Email);
//                    MenuItem m1 = (MenuItem)loader.getNamespace().get("view_employee");
//                    MenuItem m2 = (MenuItem)loader.getNamespace().get("employee");
//                    MenuItem m3 = (MenuItem)loader.getNamespace().get("r_employee");
//                    //mi.managedProperty().bind(mi.visibleProperty());
//                    m1.setVisible(false);
//                    m2.setVisible(false);
//                    m3.setVisible(false);
                    
                    new AdminPageController().setAdminPageController(Email,Password);
                    
                    stage.setScene(scene);
                    stage.show();
                } else {
                    message.setText("wrong username or password");
                }
            }
            email.setText("");
            password.setText("");
        }
    }

    @FXML
    void opensignuppage(ActionEvent event) throws IOException {
        message.setText("");
        Stage stage = (Stage) signup.getScene().getWindow();

        Parent root = FXMLLoader.load(getClass().getResource("Signuppage.fxml"));
        
        Scene scene = new Scene(root,800,600);
        
        stage.setScene(scene);
        stage.show();
    }
}
