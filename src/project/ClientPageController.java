/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package project;


import DataBaseConnection.GetData;
import DataBaseConnection.Item;
import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;


public class ClientPageController implements Initializable {

    
    static String UsersEmail;
    static String UsersPassword;
    public void setClientPageController(String email,String password){
        UsersEmail = email;
        UsersPassword = password;
        System.out.println(UsersEmail+" "+UsersPassword);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
     @FXML
    private MenuItem feedback;

    @FXML
    private MenuItem settings;

    @FXML
    private MenuItem logout;
    
    @FXML
    private MenuBar menubar;
    
    @FXML
    private ScrollPane scrollpane;

    @FXML
    private MenuItem orders;

    @FXML
    private MenuItem menuItem;

    @FXML
    private AnchorPane anchorParent;

    @FXML
    private MenuItem home;
    
    @FXML
    private int length;
    
    
    private TableView tableView = new TableView();

    ObservableList<Item> data;
    
    @FXML private TableColumn<Item, Boolean> checkBoxCol = new TableColumn<>("Selection");
    @FXML private TableColumn<Item, String> nameCol=new TableColumn<>("Name");
    @FXML private TableColumn<Item, String> category_nameCol=new TableColumn<>("category_name");
    @FXML private TableColumn<Item, String> priceCol=new TableColumn<>("price");
    @FXML private TableColumn<Item, TextField> quantityCol=new TableColumn<>("Quantity");


    private boolean init = true;
    
    private void initializeColumns()
    {
        checkBoxCol.setCellFactory(
                CheckBoxTableCell.forTableColumn(checkBoxCol)
        );
        checkBoxCol.setCellValueFactory(
                new PropertyValueFactory<>("selected")
        );
        nameCol.setCellValueFactory(
                new PropertyValueFactory<>("name")
        );
        category_nameCol.setCellValueFactory(
                new PropertyValueFactory<>("category_name")
        );
        priceCol.setCellValueFactory(
                new PropertyValueFactory<>("price")
        );
        quantityCol.setCellValueFactory(new PropertyValueFactory<Item, TextField>("Quantity"));
        tableView.getColumns().addAll(checkBoxCol,category_nameCol,nameCol,priceCol,quantityCol);
        //tableView.getItems().clear();
    }
    
    public void load(String sql) throws SQLException
    {
        if (init)
        {
            initializeColumns();
            init = false;
        }
        tableView.setEditable(true);
        data = FXCollections.observableArrayList();
        
        List<List<String>> userDataList = new GetData().getdata(sql);
        for (List<String> row : userDataList)
        {
            data.add(new Item(row.get(0),row.get(1),row.get(2)));
        }
        tableView.setEditable(true);
        tableView.setItems(data);
        Item ob = data.get(0);
        Boolean b = ob.isSelected();
        System.out.println("selected = "+b);
    }

    

    @FXML
    void setHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) menubar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("ClientPage.fxml"));

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

//    @FXML
//    void viewMenuItem(ActionEvent event) throws SQLException {
//       VBox vbox = new VBox();
//       vbox.setPrefHeight(500);
//       vbox.setPrefWidth(800);
//       
//       
//       HBox hbox = new HBox();
//       hbox.setPrefHeight(100);
//       hbox.setPrefWidth(800);
//       Label label=new Label("hello world1");
//       Label label2=new Label("hello world");
//       
//       
//       String sql = "select m.category_name category,f.food_name food,f.food_price price from food f join menu m on(f.category_id=m.category_id) order by m.category_id";
//       load(sql);
//       
//       
//       vbox.getChildren().add(tableView);
//       //hbox.getChildren().add(label2);
//       hbox.setStyle("-fx-backgroun-color:yellow");
//       anchorParent.getChildren().addAll(vbox,hbox);
//       AnchorPane.setTopAnchor(vbox, 0.0);
//       AnchorPane.setBottomAnchor(hbox, 0.0);
//    }
    
    
    @FXML
    void viewMenuItem(ActionEvent event) throws SQLException {
       VBox vbox = new VBox();
       vbox.setPrefHeight(500);
       vbox.setPrefWidth(800);
       
       String sql_food = "select category_id,food_id,(select category_name from menu where category_id = fo.category_id) "
               + "category_name,food_name,food_price from food fo";
       String sql_package = "select package_id,package_name,person,price from food_package";
       
//       List<List<String>> food_data = new GetData().getdata_Privileges(sql_food);
//       List<List<String>> package_data = new GetData().getdata_Privileges(sql_package);
       
        ScrollPane sp = new ScrollPane();
        sp.setPrefSize(798, 450);
       vbox = new GetData().get_Order(sql_food,sql_package);
       sp.setContent(vbox);
       anchorParent.getChildren().addAll(sp);
       AnchorPane.setTopAnchor(vbox, 0.0);
    }

    @FXML
    void viewOrders(ActionEvent event) {
//        for(Item data1 :data){
//            System.out.println(data1.isSelected()+" "+data1.getcategory_name()+" "+data1.getName()+" "+data1.getprice()+" "+data1.getQuantity().getText());
//        }
    }

    @FXML
    void giveFeedback(ActionEvent event) {

    }

    @FXML
    void setSettings(ActionEvent event) {

    }

    @FXML
    void setLogout(ActionEvent event) throws Exception {
        Stage stage= (Stage) menubar.getScene().getWindow();
        new Project().start(stage);
    }
    
}
