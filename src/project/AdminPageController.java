package project;

import DataBaseConnection.GetData;
import DataBaseConnection.GetTableData;
import DataBaseConnection.GetTableData_Price;
import DataBaseConnection.Item;
import DataBaseConnection.Login_check;
import DataBaseConnection.Privileges;
import DataBaseConnection.UpdateData;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollBar;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javax.swing.JOptionPane;

public class AdminPageController {
    
    private static String UsersEmail;
    private static String UsersPassword;
    public void setAdminPageController(String email,String password){
        this.UsersEmail = email;
        this.UsersPassword = password;
//        System.out.println(UsersEmail+" "+UsersPassword);
    }
    @FXML
    private Menu x_employee;
    
    @FXML
    private Menu x_menu;
    
    @FXML
    private Menu x_view_menu;
    
    @FXML
    private Menu x_add_menu;
    
    @FXML
    private Menu x_remove_menu;
    
    @FXML
    private Menu x_feedbacks;
    
    @FXML
    private Menu x_ingredients;
    
    @FXML
    private Menu x_view_ingredients;
    
    @FXML
    private Menu x_add_ingredients;
    
    @FXML
    private Menu x_remove_ingredients;
    
    @FXML
    private Menu x_orders;
    
    @FXML
    private MenuItem view_employee;
    
    @FXML
    private MenuItem r_employee;

    @FXML
    private Hyperlink addmanager;

    @FXML
    private MenuItem add_ingredient;
    
    @FXML
    private MenuItem r_ingredient;
    
    @FXML
    private MenuItem r_food_ingredient;

    @FXML
    private MenuItem add_pack;

    @FXML
    private MenuItem ingredient;
    
    @FXML
    private Hyperlink customer;
    
     @FXML
    private MenuItem pendingOffline;

    @FXML
    private MenuItem pendingOnline;
    
    @FXML
    private MenuItem add_food_ingredient;
    

    @FXML
    private MenuItem employee;
    
    @FXML
    private MenuItem food_ingredient;

    @FXML
    private MenuItem pack;

    @FXML
    private MenuItem food;

    @FXML
    private MenuItem home;

    @FXML
    private MenuItem feedback;

    @FXML
    private MenuBar menubar;

    @FXML
    private MenuItem logout;

    @FXML
    private ScrollPane scrollpane;

    @FXML
    private MenuItem offline_Order;

    @FXML
    private MenuItem category;
    
     @FXML
    private MenuItem r_category;

    @FXML
    private MenuItem r_package;

    @FXML
    private MenuItem r_food;
    
    @FXML
    private MenuItem v_category;

    @FXML
    private MenuItem account;

    @FXML
    private MenuItem online_order;
    
    private int height = 563;
    
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
    }

    
    
    private TableView tableView_Privileges = new TableView();
    ObservableList<Privileges> data_Privileges;
    
    @FXML private TableColumn<Privileges, Boolean> checkBoxCol_Privileges = new TableColumn<>("Selection");
    @FXML private TableColumn<Privileges, String> nameCol_Privileges=new TableColumn<>("Privileges");
    private boolean init_Privileges = true;
    
    
    
    private void initializeColumns_Privileges()
    {
        checkBoxCol_Privileges.setCellFactory(
                CheckBoxTableCell.forTableColumn(checkBoxCol_Privileges)
        );
        checkBoxCol_Privileges.setCellValueFactory(
                new PropertyValueFactory<>("selected")
        );
        nameCol_Privileges.setCellValueFactory(
                new PropertyValueFactory<>("Privileges")
        );
        tableView_Privileges.getColumns().addAll(checkBoxCol_Privileges,nameCol_Privileges);
    }
    
    List<List<String>> data_Privileges_Ex = new ArrayList<>();
    public void load_Privileges(String sql) throws SQLException
    {
        if (init_Privileges)
        {
            initializeColumns_Privileges();
            init_Privileges = false;
        }
        tableView_Privileges.setEditable(true);
        data_Privileges = FXCollections.observableArrayList();
        
        data_Privileges_Ex = new GetData().getdata_Privileges(sql);
        for (List<String> row : data_Privileges_Ex)
        {
            data_Privileges.add(new Privileges(row.get(0)));
        }
        tableView_Privileges.setEditable(true);
        tableView_Privileges.setItems(data_Privileges);
        data_Privileges_Ex.clear();
    }
    
    
    @FXML
    void addEmployee(ActionEvent event){
        
        GridPane gp = new GridPane();
        gp.setHgap(7);
        gp.setVgap(15);
        
        Label message = new Label("");
        gp.add(message, 2, 1, 4, 1);
        
        Label lb_1 = new Label("First Name");
        gp.add(lb_1, 2, 2);
        
        TextField tf_1 = new TextField();
        gp.add(tf_1, 3, 2, 4, 1);
        
        Label lb_2 = new Label("Last Name");
        gp.add(lb_2, 2, 3);
        
        TextField tf_2 = new TextField();
        gp.add(tf_2, 3, 3, 4, 1);
        
        HBox hb = new HBox(5);
        Label lb_3 = new Label("Hire Date");
        gp.add(lb_3, 2, 4);
        
        TextField tf_3 = new TextField();
        tf_3.setPromptText("year");
        tf_3.setMaxWidth(80);
        
        TextField tf_10 = new TextField();
        tf_10.setPromptText("month");
        tf_10.setMaxWidth(80);
        
        
        
        TextField tf_11 = new TextField();
        tf_11.setPromptText("day");
        tf_11.setMaxWidth(80);
        
        hb.getChildren().addAll(tf_3,tf_10,tf_11);
        gp.add(hb, 3, 4,4,1);
        
        Label lb_4 = new Label("Phone Number");
        gp.add(lb_4, 2, 5);
        
        TextField tf_4 = new TextField();
        gp.add(tf_4, 3, 5, 4, 1);
        
        Label lb_5 = new Label("Job Id");
        gp.add(lb_5, 2, 6);
        
        TextField tf_5 = new TextField();
        gp.add(tf_5, 3, 6, 4, 1);
        
        Button jobList = new Button("Jobs");
        gp.add(jobList, 8, 6);
        
        Label lb_6 = new Label("Salary");
        gp.add(lb_6, 2, 7);
        
        TextField tf_6 = new TextField();
        gp.add(tf_6, 3, 7, 4, 1);
        
        Label lb_7 = new Label("Email");
        gp.add(lb_7, 2, 8);
        
        TextField tf_7 = new TextField();
        gp.add(tf_7, 3, 8, 4, 1);
        
        Label lb_8 = new Label("Password");
        gp.add(lb_8, 2, 9);
        
        TextField tf_8 = new TextField();
        gp.add(tf_8, 3, 9, 4, 1);
        
        Button privilege = new Button("Grant Privileges");
        gp.add(privilege, 3, 10, 2, 1);
        
        Button but1 = new Button("   Add   ");
        gp.add(but1, 3, 11);
        
        Button but2 = new Button(" Discard ");
        gp.add(but2, 4, 11);
        
        but1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {

                Boolean employeeAccount = new Login_check().Employee_registration(tf_7.getText().trim());
                if (employeeAccount) {
                    message.setText("This Email is used.");
                } else {
                    try {
                        String date;
                        if(tf_3.getText().isEmpty()||tf_10.getText().isEmpty()||tf_11.getText().isEmpty())
                            date = "";
                        else
                            date = tf_3.getText() + "-" + tf_10.getText() + "-" + tf_11.getText();
                        new UpdateData().updatedata_employee(tf_1.getText(), tf_2.getText(), date, tf_4.getText(), tf_5.getText(), tf_6.getText(), tf_7.getText(), tf_8.getText());
                        new UpdateData().updatedata_Privileges(data_Privileges, tf_7.getText().trim());
                        message.setText("");
                        message.setStyle("-fx-background-color:white");
                        JOptionPane.showMessageDialog(null, "Successfully added");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Failed");
                        ex.printStackTrace();
                    }
                }

            }
        });
        
        but2.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                scrollpane.setContent(null);
            }
        });
        
        jobList.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                final TableView<ObservableList<StringProperty>> table = new TableView<>();
                String str = "select * from jobs";
                new GetTableData().getAllData(table, str);
                Stage cs = new Stage();
                ScrollPane sp = new ScrollPane();
                Scene sc = new Scene(sp,410,400);
                sp.setContent(table);
                sp.setFitToHeight(true);
                sp.setFitToWidth(true);
                cs.setScene(sc);
                cs.show();
            }
        });
        
        privilege.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                String sql = "select * from employees_privileges";
                try {
                    load_Privileges(sql);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                Stage cs = new Stage();
                ScrollPane sp = new ScrollPane();
                
                VBox vb = new VBox(10);
                
                HBox hb =new HBox(15);
                Button but = new Button("Grant");
                hb.getChildren().addAll(but);
                
                vb.getChildren().addAll(tableView_Privileges,hb);
                vb.setMargin(hb,new Insets(10,10,10,10));
                hb.setPadding(new Insets(10,10,10,10));
                
                but.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        cs.close();
                    }
                });
                
                Scene sc = new Scene(sp,410,400);
                sp.setContent(vb);
                sp.setFitToHeight(true);
                sp.setFitToWidth(true);
                cs.setScene(sc);
                cs.show();
            }
        });
        
        scrollpane.setContent(gp);
    }
    
    @FXML
    void setHome(ActionEvent event) throws IOException {
        Stage stage = (Stage) menubar.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("AdminPage.fxml"));

        Scene scene = new Scene(root, 800, 600);
        stage.setScene(scene);
        stage.setResizable(true);
        stage.show();
    }

    @FXML
    void viewFood(ActionEvent event) {
        final TableView<ObservableList<StringProperty>> table = new TableView<>();
        String str = "select * from food";
        new GetTableData().getAllData(table,str);
        scrollpane.setContent(table);
        table.setPrefHeight(height);
    }
    
    @FXML
    void viewCategory(ActionEvent event) {
        final TableView<ObservableList<StringProperty>> table = new TableView<>();
        String str = "select * from menu";
        new GetTableData().getAllData(table,str);
        scrollpane.setContent(table);
        table.setPrefHeight(height);
    }

    @FXML
    void viewPackage(ActionEvent event) {
        final TableView<ObservableList<StringProperty>> table = new TableView<>();
        String str = "select * from food_package";
        new GetTableData().getAllData(table,str);
        scrollpane.setContent(table);
        table.setPrefHeight(height);
    }

    @FXML
    void addCategory(ActionEvent event) {
        Label label = new Label();
        label.setText("");
        //label.set
        
        VBox vbox = new VBox();
        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox(10);
        
        Label label1 = new Label();
        label1.setText("Category Name : ");
        TextField tf1 = new TextField();
        tf1.setPrefSize(250, 15);
        hbox1.getChildren().addAll(label1,tf1);
        hbox1.setSpacing(30);
        
        Label label2 = new Label();
        label2.setText("Category Description : ");
        TextArea tf2 = new TextArea();
        hbox2.getChildren().addAll(label2,tf2);
        //hbox2.setSpacing(10);
        tf2.setPrefSize(400,250);
        
        Button but = new Button("Add");
        but.setPrefSize(100, 8);
        Button but1 = new Button("Discard");
        but1.setPrefSize(100, 8);
        HBox hbox3 = new HBox(20);
        hbox3.getChildren().addAll(but,but1);
        hbox3.setAlignment(Pos.CENTER);
        
        vbox.getChildren().addAll(label,hbox1,hbox2,hbox3);
        vbox.setMargin(hbox1,new Insets(20,5,5,5));
        vbox.setMargin(hbox2,new Insets(5,5,5,5));
        vbox.setMargin(hbox3,new Insets(10,10,10,10));
        vbox.setMargin(label,new Insets(20,10,10,10));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(50);
        
        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int ret = -1;
                String category_name = tf1.getText();
                String category_description = tf2.getText();
                if(category_name.isEmpty()){
                    label.setText("Please Enter a Category Name");
                }
                else{
                    try {
                        ret = new UpdateData().updatedata_add_category(category_name, category_description);
                    } catch (SQLException ex) {
                        ret = -1;
                    }
                    
                }
                tf1.setText("");
                tf2.setText("");
                if(ret>=1){
                    JOptionPane.showMessageDialog(null, "Successfully added");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Unsuccessful");
                }
            }
        });

        but1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                scrollpane.setContent(null);
            }
        });

        scrollpane.setContent(vbox);
    }

    @FXML
    void addFood(ActionEvent event) {
        
        VBox vbox = new VBox();
        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox(10);
        HBox hbox3 = new HBox(20);
        HBox hbox4 = new HBox(10);
        HBox hbox5 = new HBox(10);
        HBox hbox6 = new HBox(10);
        
        Label label1 = new Label();
        label1.setText("Food Name : ");
        TextField tf1 = new TextField();
        tf1.setPrefSize(250, 15);
        hbox1.getChildren().addAll(label1,tf1);
        hbox1.setSpacing(30);
        
        Label label2 = new Label();
        label2.setText("Food Price : ");
        TextField tf2 = new TextField();
        tf2.setPrefSize(100, 15);
        hbox2.getChildren().addAll(label2,tf2);
        hbox2.setSpacing(30);
        
        Label label3 = new Label();
        label3.setText("Food Description : ");
        TextArea tf3 = new TextArea();
        hbox3.getChildren().addAll(label3,tf3);
        //hbox2.setSpacing(10);
        tf3.setPrefSize(400,250);
        
        Label label4 = new Label();
        label4.setText("Category Id : ");
        TextField tf4 = new TextField();
        tf4.setPrefSize(100, 15);
        Button cl = new Button("Category List");
        hbox4.getChildren().addAll(label4,tf4,cl);
        hbox4.setSpacing(30);
        
        Label label5 = new Label();
        label5.setText("Discount : ");
        TextField tf5 = new TextField();
        tf5.setPrefSize(100, 15);
        hbox6.getChildren().addAll(label5,tf5);
        hbox6.setSpacing(30);
        
        Button but = new Button("Add");
        but.setPrefSize(100, 8);
        Button but1 = new Button("Discard");
        but1.setPrefSize(100, 8);
        
        hbox5.getChildren().addAll(but,but1);
        hbox5.setAlignment(Pos.CENTER);
        
        vbox.getChildren().addAll(hbox1,hbox2,hbox3,hbox4,hbox6,hbox5);
        vbox.setMargin(hbox1,new Insets(20,5,5,5));
        vbox.setMargin(hbox2,new Insets(5,5,5,5));
        vbox.setMargin(hbox3,new Insets(10,10,10,10));
        vbox.setMargin(hbox4,new Insets(10,10,10,10));
        vbox.setMargin(hbox5,new Insets(10,10,10,10));
        vbox.setMargin(hbox6,new Insets(10,10,10,10));
        //vbox.setMargin(label,new Insets(20,10,10,10));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(12);
        
        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int ret = -1;
                String food_name = tf1.getText();
                String food_description = tf3.getText();
                int food_price = Integer.parseInt(tf2.getText());
                int category_id = Integer.parseInt(tf4.getText());
                double discount = Double.parseDouble(tf5.getText());
                try {
                    ret = new UpdateData().updatedata_add_food(food_name,food_price,category_id,food_description,discount);
                } catch (SQLException ex) {
                    ret = -1;
                }

                tf1.setText("");
                tf2.setText("");
                if (ret >= 1) {
                    JOptionPane.showMessageDialog(null, "Successfully added");
                } else {
                    JOptionPane.showMessageDialog(null, "Unsuccessful");
                }
            }
        });

        but1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                scrollpane.setContent(null);
            }
        });
        
        cl.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                final TableView<ObservableList<StringProperty>> table = new TableView<>();
                String str = "select category_id,category_name from menu order by category_id";
                new GetTableData().getAllData(table, str);
                Stage cs = new Stage();
                ScrollPane sp = new ScrollPane();
                Scene sc = new Scene(sp,400,400);
                sp.setContent(table);
                cs.setScene(sc);
                cs.show();
            }
        });

        scrollpane.setContent(vbox);
    }

    @FXML
    void addPackage(ActionEvent event) throws SQLException {
       VBox vbox = new VBox();
//       vbox.setPrefHeight(500);
//       vbox.setPrefWidth(800);
       
       String sql = "select m.category_name category,f.food_name food,f.food_price price from food f join menu m on(f.category_id=m.category_id) order by m.category_id";
       load(sql);
       
       HBox hbox = new HBox(20);
       Label label  = new Label("Package Name : ");
       TextField tf = new TextField();
       hbox.getChildren().addAll(label,tf);
       
       HBox hbox2 = new HBox(20);
       Label label1  = new Label("Person : ");
       TextField tf1 = new TextField();
       hbox2.getChildren().addAll(label1,tf1);
       
       HBox hbox3 = new HBox(20);
       Label label2  = new Label("Discount : ");
       TextField tf2 = new TextField();
       hbox3.getChildren().addAll(label2,tf2);
       
       HBox hbox4 = new HBox(20);
       Label label3  = new Label("Description : ");
       TextArea tf3 = new TextArea();
       hbox4.getChildren().addAll(label3,tf3);
       tf3.setPrefSize(400,250);
       
       HBox hbox1 = new HBox(20);
       Button but = new Button("Add");
       Button but1 = new Button("Discard");
       hbox1.getChildren().addAll(but,but1);
       
       vbox.getChildren().addAll(tableView,hbox,hbox2,hbox3,hbox4,hbox1);
       vbox.setSpacing(5);
       vbox.setMargin(hbox,new Insets(20,20,20,20));
       vbox.setMargin(hbox1,new Insets(20,20,20,20));
       vbox.setMargin(hbox2,new Insets(20,20,20,20));
       vbox.setMargin(hbox3,new Insets(20,20,20,20));
       vbox.setMargin(hbox4,new Insets(20,20,20,20));
       
       but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int length = data.size();
                int total_price = -1;
                int ret = -1;
                try {
                    total_price = new UpdateData().get_package_price(data);
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Error at price setting");
                    total_price = -1;
                }
                String package_name = tf.getText();
                String description = tf3.getText();
                int person;
                double discount;
                if(tf2.getText().isEmpty()){
                    discount = 0;
                }
               else{
                    discount = Double.parseDouble(tf2.getText());
                }
                if(tf1.getText().isEmpty()){
                    person = 1;
                }
               else{
                    person = Integer.parseInt(tf1.getText());
                }
                try {
                    new UpdateData().updatedata_add_package(package_name, total_price, person, description, discount);
                    new UpdateData().updatedata_package_description(data, package_name);
                    JOptionPane.showMessageDialog(null, "Successfully Added");
                } catch (SQLException ex) {
//                    System.out.println("There is the problem");
//                    ex.printStackTrace();
//                    System.out.println(ex);
                    JOptionPane.showMessageDialog(null, "Unsuccessful");
                }
                
            }
        });
       
       but1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                scrollpane.setContent(null);
            }
        });
       
       scrollpane.setContent(vbox);
       //scrollpane.setFitToHeight(true);
       scrollpane.setFitToWidth(true);
    }
    @FXML
    void removeCategory(ActionEvent event) {
        Label label = new Label();
        label.setText("Choose a method: ");
//        CheckBox cb1 = new CheckBox("Category Id");
//        CheckBox cb2 = new CheckBox("Category Name");
        
        RadioButton rb1 = new RadioButton("Category Id");
        RadioButton rb2 = new RadioButton("Category Name");
        
        ToggleGroup tg = new ToggleGroup();
        
        rb1.setToggleGroup(tg);
        rb2.setToggleGroup(tg);
        
        VBox vb = new VBox(100);
        VBox vb1 = new VBox(10);
        vb1.getChildren().addAll(label, rb1, rb2);
//        vb1.setMargin(label, new Insets(5, 5, 5, 5));
//        vb1.setMargin(cb1, new Insets(5, 5, 5, 5));
//        vb1.setMargin(cb2, new Insets(5, 5, 5, 5));
        vb.getChildren().addAll(vb1);
        //vb1.setAlignment(Pos.CENTER);
        //vb1.setStyle("-fx-background-color:green");

        Label label1 = new Label();
        TextField tf1 = new TextField();
        HBox hb1 = new HBox();
        hb1.getChildren().addAll(label1, tf1);

        Button but = new Button("Remove");
        but.setPrefSize(100, 8);
        Button but1 = new Button("Discard");
        but1.setPrefSize(100, 8);
        HBox hb2 = new HBox(20);
        hb2.getChildren().addAll(but, but1);
        hb2.setAlignment(Pos.CENTER);
        
        VBox vb2 = new VBox(10);
        vb2.getChildren().addAll(hb1, hb2);
        vb2.setSpacing(20);
        
        
        rb1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                tf1.setText("");
                if(vb.getChildren().contains(vb2)){
                    vb.getChildren().remove(vb2);
                    vb.getChildren().addAll(vb2);
                }
                else{
                    vb.getChildren().addAll(vb2);
                }
                vb.setMargin(vb2, new Insets(20, 20, 20, 20));
                label1.setText("Category Id : ");

            }

        });
        
        rb2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                tf1.setText("");
                if(vb.getChildren().contains(vb2)){
                    vb.getChildren().remove(vb2);
                    vb.getChildren().addAll(vb2);
                }
                else{
                    vb.getChildren().addAll(vb2);
                }
                vb.setMargin(vb2, new Insets(20, 20, 20, 20));
                label1.setText("Category Name : ");
            }
            
        });
       
        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int ret = -1;
                if(rb1.isSelected()){
                    int id = Integer.parseInt(tf1.getText());
                    String sql = "delete from menu where category_id ="+id;
                    try {
                        ret = new UpdateData().updatedata_remove(sql);
                    } catch (SQLException ex) {
                        ret = -1;
                    }
                    tf1.setText("");
                }
                else{
                    String name = tf1.getText();
                    String sql = "delete from menu where category_name ='"+name.trim()+"'";
                    try {
                        ret = new UpdateData().updatedata_remove(sql);
                    } catch (SQLException ex) {
                        ret = -1;
                    }
                    tf1.setText("");
                }
                //System.out.println("ret = "+ret);
                if(ret>=1){
                    JOptionPane.showMessageDialog(null, "Delete Successful");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Delete Unsuccessful");
                }
            }
        });

        but1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                tf1.setText("");
                scrollpane.setContent(null);
            }
        });

        rb1.fire();
        
        vb.setMargin(vb1, new Insets(20, 20, 20, 20));
        scrollpane.setContent(vb);
    }

    @FXML
    void removeFood(ActionEvent event) {
        HBox hb1 = new HBox(20);
        Label lb1 = new Label("Food Id");
        TextField tf1 = new TextField();
        Button fl = new Button("Food List");
        hb1.getChildren().addAll(lb1,tf1,fl);
        
        HBox hb2 = new HBox(20);
        Button but = new Button("Remove");
        Button but1 = new Button("Discard");
        hb2.getChildren().addAll(but,but1);
        
        VBox vb1 = new VBox();
        vb1.getChildren().addAll(hb1,hb2);
        
        vb1.setMargin(hb1,new Insets(10,10,10,10));
        vb1.setMargin(hb2,new Insets(30,30,30,30));
        
        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int ret = -1;
                int id = Integer.parseInt(tf1.getText());
                String sql = "delete from food where food_id =" + id;
                try {
                    ret = new UpdateData().updatedata_remove(sql);
                } catch (SQLException ex) {
                    ret = -1;
                }
                tf1.setText("");
                if(ret>=1){
                    JOptionPane.showMessageDialog(null, "Delete Successful");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Delete Unsuccessful");
                }
            }
        });
        
        but1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                tf1.setText("");
                scrollpane.setContent(null);
            }
        });
        
        fl.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                final TableView<ObservableList<StringProperty>> table = new TableView<>();
                String str = "select f.food_id,f.food_name,f.category_id ,(select category_name from menu where category_id = f.category_id) category_name from food f order by f.food_id";
                new GetTableData().getAllData(table, str);
                Stage cs = new Stage();
                ScrollPane sp = new ScrollPane();
                Scene sc = new Scene(sp,800,400);
                sp.setContent(table);
                sp.setFitToHeight(true);
                sp.setFitToWidth(true);
                cs.setScene(sc);
                cs.show();
            }
        });
        scrollpane.setContent(vb1);
    }

    @FXML
    void removePackage(ActionEvent event) {
        HBox hb1 = new HBox(20);
        Label lb1 = new Label("Package Id");
        TextField tf1 = new TextField();
        Button fl = new Button("Package List");
        hb1.getChildren().addAll(lb1,tf1,fl);
        
        HBox hb2 = new HBox(20);
        Button but = new Button("Remove");
        Button but1 = new Button("Discard");
        hb2.getChildren().addAll(but,but1);
        
        VBox vb1 = new VBox();
        vb1.getChildren().addAll(hb1,hb2);
        
        vb1.setMargin(hb1,new Insets(10,10,10,10));
        vb1.setMargin(hb2,new Insets(30,30,30,30));
        
        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int ret = -1;
                int id = Integer.parseInt(tf1.getText());
                String sql = "delete from food_package where package_id =" + id;
                try {
                    ret = new UpdateData().updatedata_remove(sql);
                } catch (SQLException ex) {
                    ret = -1;
                }
                tf1.setText("");
                if(ret>=1){
                    JOptionPane.showMessageDialog(null, "Delete Successful");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Delete Unsuccessful");
                }
            }
        });
        
        but1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                tf1.setText("");
                scrollpane.setContent(null);
            }
        });
        
        fl.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                final TableView<ObservableList<StringProperty>> table = new TableView<>();
                String str = "select package_name,package_id from food_package";
                new GetTableData().getAllData(table, str);
                Stage cs = new Stage();
                ScrollPane sp = new ScrollPane();
                Scene sc = new Scene(sp,800,400);
                sp.setContent(table);
                sp.setFitToHeight(true);
                sp.setFitToWidth(true);
                cs.setScene(sc);
                cs.show();
            }
        });
        scrollpane.setContent(vb1);
    }

    
    
    @FXML
    void removeEmployee(ActionEvent event){
        
    }

    @FXML
    void viewEmployee(ActionEvent event) {
        final TableView<ObservableList<StringProperty>> table = new TableView<>();
        String str = "select employee_id,(initcap(first_name)||' '||initcap(last_name)) Name,"
                + "hire_date,phone_number,(select job_title from jobs where job_id = emp.job_id) job,"
                + "salary,email from employees emp";
        new GetTableData().getAllData(table,str);
        scrollpane.setContent(table);
    }

    @FXML
    void setFeedback(ActionEvent event) {
        final TableView<ObservableList<StringProperty>> table = new TableView<>();
        String str = "select * from reviews";
        new GetTableData().getAllData(table,str);
        scrollpane.setContent(table);
    }

    @FXML
    void viewIngredient(ActionEvent event) {
        final TableView<ObservableList<StringProperty>> table = new TableView<>();
        String str = "select * from ingredient";
        new GetTableData().getAllData(table,str);
        scrollpane.setContent(table);
    }
    
    @FXML
    void viewFoodIngredient(ActionEvent event) {
        final TableView<ObservableList<StringProperty>> table = new TableView<>();
        String str = "select * from food_ingredient";
        new GetTableData().getAllData(table,str);
        scrollpane.setContent(table);
    }

    @FXML
    void addIngredient(ActionEvent event) {
        
        VBox vbox = new VBox();
        HBox hbox1 = new HBox();
        
        Label label1 = new Label();
        label1.setText("Ingredient Name : ");
        TextField tf1 = new TextField();
        tf1.setPrefSize(250, 15);
        hbox1.getChildren().addAll(label1,tf1);
        hbox1.setSpacing(30);
        
        Button but = new Button("Add");
        but.setPrefSize(100, 8);
        Button but1 = new Button("Discard");
        but1.setPrefSize(100, 8);
        HBox hbox3 = new HBox(20);
        hbox3.getChildren().addAll(but,but1);
        hbox3.setAlignment(Pos.CENTER);
        
        vbox.getChildren().addAll(hbox1,hbox3);
        vbox.setMargin(hbox1,new Insets(20,5,5,5));
        vbox.setMargin(hbox3,new Insets(10,10,10,10));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(50);
        
        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int ret = -1;
                String ingredient_name = tf1.getText();
                if(ingredient_name.isEmpty()){
                    JOptionPane.showMessageDialog(null, "Ingredient Name missing");
                }
                else{
                    try {
                        new UpdateData().updatedata_add_ingredient(ingredient_name);
                        JOptionPane.showMessageDialog(null, "Successfully added");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Unsuccessful");
                    }
                    
                }
                tf1.setText("");
            }
        });

        but1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                scrollpane.setContent(null);
            }
        });

        scrollpane.setContent(vbox);
    }
    
    @FXML
    void addFoodIngredient(ActionEvent event) {
        VBox vbox = new VBox();
        HBox hbox1 = new HBox();
        HBox hbox2 = new HBox();
        HBox hbox4 = new HBox();
        HBox hbox5 = new HBox();
        
        Label label1 = new Label();
        label1.setText("Ingredient Id : ");
        TextField tf1 = new TextField();
        Button ilist = new Button("Ingredient List");
        tf1.setPrefSize(250, 15);
        hbox1.getChildren().addAll(label1,tf1,ilist);
        hbox1.setSpacing(30);
        
        Label label2 = new Label();
        label2.setText("Food Id : ");
        TextField tf2 = new TextField();
        Button flist = new Button("Food List");
        tf2.setPrefSize(250, 15);
        hbox2.getChildren().addAll(label2,tf2,flist);
        hbox2.setSpacing(30);
        
        Label label4 = new Label();
        label4.setText("Stock : ");
        TextField tf4 = new TextField();
        hbox4.getChildren().addAll(label4,tf4);
        hbox4.setSpacing(30);
        
        Label label5 = new Label();
        label5.setText("Availability : ");
        
        final Spinner<Integer>spinner = new Spinner<Integer>();
        final int initialValue = 1;
        int availability_value = 1;
        
        SpinnerValueFactory<Integer>valueFactory = new SpinnerValueFactory.IntegerSpinnerValueFactory(0,1,initialValue);
        spinner.setValueFactory(valueFactory);
        
        spinner.valueProperty().addListener(new ChangeListener<Integer>(){
            @Override
            public void changed(ObservableValue<? extends Integer> observable, Integer oldValue, Integer newValue) {
                int availability_value = newValue;
                //System.out.println("value = "+newValue);
            }
            
        });
        
        hbox5.getChildren().addAll(label5,spinner);
        hbox5.setSpacing(30);
        
        
        
        Button but = new Button("Add");
        but.setPrefSize(100, 8);
        Button but1 = new Button("Discard");
        but1.setPrefSize(100, 8);
        HBox hbox3 = new HBox(20);
        hbox3.getChildren().addAll(but,but1);
        hbox3.setAlignment(Pos.CENTER);
        
        vbox.getChildren().addAll(hbox2,hbox1,hbox4,hbox5,hbox3);
        vbox.setMargin(hbox1,new Insets(20,5,5,5));
        vbox.setMargin(hbox3,new Insets(10,10,10,10));
        vbox.setMargin(hbox2,new Insets(10,10,10,10));
        vbox.setMargin(hbox4,new Insets(10,10,10,10));
        vbox.setMargin(hbox5,new Insets(10,10,10,10));
        vbox.setAlignment(Pos.CENTER);
        vbox.setSpacing(50);
        
        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int ingredient_id;// = Integer.parseInt(tf1.getText());
                int food_id;// = Integer.parseInt(tf2.getText());
                double stock = 0;
                if(tf1.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Ingredient Id missing");
                }
                else if(tf2.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null, "Food Id missing");
                } 
                else{
                    if(!tf4.getText().isEmpty()){
                        stock = Double.parseDouble(tf4.getText());
                    }
                    ingredient_id = Integer.parseInt(tf1.getText());
                    food_id = Integer.parseInt(tf2.getText());
                    try {
                        new UpdateData().updatedata_add_food_ingredient(food_id,ingredient_id,availability_value,stock);
                        JOptionPane.showMessageDialog(null, "Successfully added");
                    } catch (SQLException ex) {
                        JOptionPane.showMessageDialog(null, "Unsuccessful");
                    }
                }
                tf1.setText("");
                tf2.setText("");
            }
        });

        but1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                scrollpane.setContent(null);
            }
        });
        
        ilist.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                final TableView<ObservableList<StringProperty>> table = new TableView<>();
                String str = "select ingredient_id,ingredient_name from ingredient";
                new GetTableData().getAllData(table, str);
                Stage cs = new Stage();
                ScrollPane sp = new ScrollPane();
                Scene sc = new Scene(sp,600,400);
                sp.setContent(table);
                sp.setFitToHeight(true);
                sp.setFitToWidth(true);
                cs.setScene(sc);
                cs.show();
            }
        });
        
        flist.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                final TableView<ObservableList<StringProperty>> table = new TableView<>();
                String str = "select food_id,food_name from food";
                new GetTableData().getAllData(table, str);
                Stage cs = new Stage();
                ScrollPane sp = new ScrollPane();
                Scene sc = new Scene(sp,600,400);
                sp.setContent(table);
                sp.setFitToHeight(true);
                sp.setFitToWidth(true);
                cs.setScene(sc);
                cs.show();
            }
        });

        scrollpane.setContent(vbox);
    }
    
    @FXML
    void removeIngredient(ActionEvent event) {
        HBox hb1 = new HBox(20);
        Label lb1 = new Label("Ingredient Id : ");
        TextField tf1 = new TextField();
        Button fl = new Button("Ingredient List");
        hb1.getChildren().addAll(lb1,tf1,fl);
        
        HBox hb2 = new HBox(20);
        Button but = new Button("Remove");
        Button but1 = new Button("Discard");
        hb2.getChildren().addAll(but,but1);
        
        VBox vb1 = new VBox();
        vb1.getChildren().addAll(hb1,hb2);
        
        vb1.setMargin(hb1,new Insets(10,10,10,10));
        vb1.setMargin(hb2,new Insets(30,30,30,30));
        
        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int ret = -1;
                int id = Integer.parseInt(tf1.getText());
                String sql = "delete from Ingredient where ingredient_id =" + id;
                try {
                    ret = new UpdateData().updatedata_remove(sql);
                } catch (SQLException ex) {
                    ret = -1;
                }
                tf1.setText("");
                if(ret>=1){
                    JOptionPane.showMessageDialog(null, "Delete Successful");
                }
                else{
                    JOptionPane.showMessageDialog(null, "Delete Unsuccessful");
                }
            }
        });
        
        but1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                tf1.setText("");
                scrollpane.setContent(null);
            }
        });
        
        fl.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                final TableView<ObservableList<StringProperty>> table = new TableView<>();
                String str = "select ingredient_id,ingredient_name from ingredient";
                new GetTableData().getAllData(table, str);
                Stage cs = new Stage();
                ScrollPane sp = new ScrollPane();
                Scene sc = new Scene(sp,800,400);
                sp.setContent(table);
                sp.setFitToHeight(true);
                sp.setFitToWidth(true);
                cs.setScene(sc);
                cs.show();
            }
        });
        scrollpane.setContent(vb1);
    }
    
    @FXML
    void removeFoodIngredient(ActionEvent event) {
        HBox hb1 = new HBox(20);
        Label lb1 = new Label("Food Id : ");
        TextField tf1 = new TextField();
        Button fl = new Button("Food Ingredient List");
        hb1.getChildren().addAll(lb1,tf1,fl);
        
        HBox hb5 = new HBox(20);
        Label lb5 = new Label("Ingredient Id : ");
        TextField tf5 = new TextField();
        hb5.getChildren().addAll(lb5,tf5);
        
        HBox hb2 = new HBox(20);
        Button but = new Button("Remove");
        Button but1 = new Button("Discard");
        hb2.getChildren().addAll(but,but1);
        
        VBox vb1 = new VBox();
        vb1.getChildren().addAll(hb1,hb5,hb2);
        
        vb1.setMargin(hb1,new Insets(10,10,10,10));
        vb1.setMargin(hb2,new Insets(30,30,30,30));
        vb1.setMargin(hb5,new Insets(10,10,10,10));
        
        but.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                int ret = -1;
                int ingredient_id = Integer.parseInt(tf5.getText());
                int food_id = Integer.parseInt(tf1.getText());
                String sql = "delete from food_ingredient where ingredient_id =" + ingredient_id+" and food_id = "+food_id;
                try {
                    new UpdateData().updatedata_remove(sql);
                    JOptionPane.showMessageDialog(null, "Delete Successful");
                } catch (SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Delete Unsuccessful");
                }
                tf1.setText("");
                tf5.setText("");
            }
        });
        
        but1.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                tf1.setText("");
                scrollpane.setContent(null);
            }
        });
        
        fl.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent e) {
                final TableView<ObservableList<StringProperty>> table = new TableView<>();
                String str = "select food_id,(select food_name from food where food_id = fi.food_id) food_name,ingredient_id,(select ingredient_name from ingredient where ingredient_id = fi.ingredient_id) ingredient_name from food_ingredient fi";
                new GetTableData().getAllData(table, str);
                Stage cs = new Stage();
                ScrollPane sp = new ScrollPane();
                Scene sc = new Scene(sp,800,400);
                sp.setContent(table);
                sp.setFitToHeight(true);
                sp.setFitToWidth(true);
                cs.setScene(sc);
                cs.show();
            }
        });
        scrollpane.setContent(vb1);
    }

    @FXML
    void viewOnlineOrders(ActionEvent event) {
        final TableView<ObservableList<StringProperty>> table = new TableView<>();
        String str ="select oo.order_id,cus.customer_id,"
                + "(initcap(cus.first_name) || ' '||initcap(cus.last_name)) Name,cus.email,"
                + "oo.DELIVERY_ADDRESS,(select area_name from area where post_code = oo.post_code) area_name "
                + "from online_order oo join customer cus on(oo.customer_id = cus.customer_id)";
        new GetTableData_Price().getAllData(table,str);
        scrollpane.setContent(table);
    }

    @FXML
    void viewOfflineOrder(ActionEvent event) {
        final TableView<ObservableList<StringProperty>> table = new TableView<>();
        String str ="SELECT ORDER_ID,EMPLOYEE_ID,(SELECT (INITCAP(FIRST_NAME)||' '||INITCAP(LAST_NAME)) NAME "
                + "FROM EMPLOYEES WHERE EMPLOYEE_ID = OO.EMPLOYEE_ID) EMPLOYEE_NAME,TABLE_NO,ORDER_DATE "
                + "FROM OFFLINE_ORDER OO";
        new GetTableData_Price().getAllData_Offline(table,str);
        scrollpane.setContent(table);
    }
    
    @FXML
    void viewPendingOfflineOrder(ActionEvent event) {
        final TableView<ObservableList<StringProperty>> table = new TableView<>();
        String str ="SELECT ORDER_ID,EMPLOYEE_ID,(SELECT (INITCAP(FIRST_NAME)||' '||INITCAP(LAST_NAME)) NAME "
                + "FROM EMPLOYEES WHERE EMPLOYEE_ID = OO.EMPLOYEE_ID) EMPLOYEE_NAME,TABLE_NO,ORDER_DATE "
                + "FROM OFFLINE_ORDER OO where lower(OO.status) like 'pending'";
        new GetTableData_Price().getAllData_Offline(table,str);
        scrollpane.setContent(table);
    }

    @FXML
    void viewPendingOnlineOrders(ActionEvent event) {
        final TableView<ObservableList<StringProperty>> table = new TableView<>();
        String str ="select oo.order_id,cus.customer_id,"
                + "(initcap(cus.first_name) || ' '||initcap(cus.last_name)) Name,cus.email,"
                + "oo.DELIVERY_ADDRESS,(select area_name from area where post_code = oo.post_code) area_name "
                + "from online_order oo join customer cus on(oo.customer_id = cus.customer_id)"
                + " where lower(oo.status) like 'pending'";
        new GetTableData_Price().getAllData(table,str);
        scrollpane.setContent(table);
    }
    
     @FXML
    void viewCustomers(ActionEvent event) {
        final TableView<ObservableList<StringProperty>> table = new TableView<>();
        String str = "select * from customer";
        new GetTableData().getAllData(table,str);
        scrollpane.setContent(table);
    }

    @FXML
    void setLogout(ActionEvent event) throws Exception {
        UsersEmail = "";
        UsersPassword ="";
        Stage stage= (Stage)menubar.getScene().getWindow();
        new Project().start(stage);
    }

    @FXML
    void setAccount(ActionEvent event) {
        Stage st = new Stage();
        Boolean stage = false;
        
        VBox vb = new VBox(20);
        
        RadioButton cb1 = new RadioButton("Change Name");
        RadioButton cb2 = new RadioButton("Change Contact Number");
        RadioButton cb3 = new RadioButton("Change Password");
        
        ToggleGroup tg = new ToggleGroup();
        cb1.setToggleGroup(tg);
        cb2.setToggleGroup(tg);
        cb3.setToggleGroup(tg);
        
        TextField tf1 = new TextField();
        TextField tf2 = new TextField();
        
        cb1.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                tf1.setText("");
                tf2.setText("");
                GridPane gp = new GridPane();
                gp.setHgap(7);
                gp.setVgap(15);
                gp.setPadding(new Insets(50,50,0,50));
                
                Label lb1 = new Label("First Name : ");
                gp.add(lb1, 2, 2);
                
                
                gp.add(tf1, 3, 2);
                
                Label lb2 = new Label("Last Name : ");
                gp.add(lb2, 2, 3);
                
                
                gp.add(tf2, 3, 3);
                
                Button but = new Button("Change");
                gp.add(but, 2, 4);
                
                Button but1 = new Button("Discard");
                gp.add(but1, 3, 4);
                
                but.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        String firstName = tf1.getText();
                        String lastName = tf2.getText();
                        if(!tf1.getText().isEmpty()){
                            firstName = firstName.trim();
                        }
                        if(!tf2.getText().isEmpty()){
                            lastName = lastName.trim();
                        }
                        //System.out.println("email = "+UsersEmail+"  password = "+UsersPassword);
                        String sql = "update employees set last_name = '"+lastName+"' "
                                + ", first_name = '"+firstName+"' where email = '"+UsersEmail.trim()+"'";
                        
                        try {
                            new UpdateData().updatedata_remove(sql);
                            JOptionPane.showMessageDialog(null, "Successfully Updated");
                            tf1.setText("");
                            tf2.setText("");
                            cb1.setSelected(false);
                            st.close();
                            
                        } catch (SQLException ex) {
                            JOptionPane.showMessageDialog(null, "Update Failed");
                        }
                    }
                });
                
                but1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        tf1.setText("");
                        tf2.setText("");
                        cb1.setSelected(false);
                        st.close();
                    }
                });

                Scene sc = new Scene(gp,400,400);
                gp.setStyle("-fx-background-color:CADETBLUE");
                //gp.setStyle("-fx-background-color:CORNFLOWERBLUE");
                st.setScene(sc);
                //sc.setFill(Color.CORNFLOWERBLUE);
                
                //st.initStyle(StageStyle.TRANSPARENT);
                //st.setOpacity(0.8);

                st.show();
            }
        });
        
        cb2.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                tf1.setText("");
                tf2.setText("");
                
                GridPane gp = new GridPane();
                gp.setHgap(7);
                gp.setVgap(15);
                gp.setPadding(new Insets(80,50,0,50));
                
                Label message = new Label("");
                gp.add(message, 1, 1, 3, 1);
                
                Label lb1 = new Label("Phone");
                Label lb2 = new Label("Number");
                gp.add(lb1, 1, 3);
                gp.add(lb2, 2, 3);
                gp.add(tf1, 3, 3);
                
                Button but = new Button("Change");
                gp.add(but, 2, 4);
                
                Button but1 = new Button("Discard");
                gp.add(but1, 3, 4);
                
                but.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        String phoneNumber = tf1.getText();
                        if (!tf1.getText().isEmpty()) {
                            phoneNumber = phoneNumber.trim();
                        }
                        if (tf1.getText().isEmpty()) {
                            message.setText("Enter a Phone Number");
                            message.setStyle("-fx-background-color:red");
                            message.setTextFill(Color.WHITE);
                        } else {
                            try {
                                String sql = "update employees set phone_number = '" + phoneNumber
                                        + "' where email = '" + UsersEmail.trim() + "'";
                                new UpdateData().updatedata_remove(sql);
                                JOptionPane.showMessageDialog(null, "Successfully Updated");
                                tf1.setText("");
                                cb2.setSelected(false);
                                st.close();

                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "Update Failed");
                            }
                            message.setText("");
                            message.setStyle("-fx-background-color:cadetblue");
                        }
                    }
                });
                
                but1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        tf1.setText("");
                        tf2.setText("");
                        cb2.setSelected(false);
                        st.close();
                    }
                });

                Scene sc = new Scene(gp,400,400);
                gp.setStyle("-fx-background-color:CADETBLUE");
                //gp.setStyle("-fx-background-color:CORNFLOWERBLUE");
                st.setScene(sc);
                //sc.setFill(Color.CORNFLOWERBLUE);
                
                //st.initStyle(StageStyle.TRANSPARENT);
                //st.setOpacity(0.8);

                st.show();
            }
        });
        
        
        cb3.setOnAction(new EventHandler<ActionEvent>(){
            @Override
            public void handle(ActionEvent event) {
                
                tf1.setText("");
                tf2.setText("");
                GridPane gp = new GridPane();
                gp.setHgap(7);
                gp.setVgap(15);
                gp.setPadding(new Insets(50,50,0,50));
                
                Label message = new Label("");
                gp.add(message, 2, 1, 3, 1);
                
                Label lb1 = new Label("Password : ");
                gp.add(lb1, 2, 2);
                
                
                gp.add(tf1, 3, 2);
                
                Label lb2 = new Label("Confirm Password : ");
                gp.add(lb2, 2, 3);
                
                
                gp.add(tf2, 3, 3);
                
                Button but = new Button("Change");
                gp.add(but, 2, 4);
                
                Button but1 = new Button("Discard");
                gp.add(but1, 3, 4);
                
                but.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        String password = tf1.getText();
                        String confirmPassword = tf2.getText();
                        if(tf1.getText().isEmpty()){
                            message.setText("Password Field Empty");
                            message.setStyle("-fx-background-color:red");
                            message.setTextFill(Color.WHITE);
                        }
                        else if(tf2.getText().isEmpty()){
                            message.setText("Confirm Password Field Empty");
                            message.setStyle("-fx-background-color:red");
                            message.setTextFill(Color.WHITE);
                        } else {
                            try {
                                String sql = "update employees set login_password = '"+confirmPassword+"' "
                                        + "where email = '"+UsersEmail.trim()+"'";
                                new UpdateData().updatedata_remove(sql);
                                JOptionPane.showMessageDialog(null, "Successfully Updated");
                                tf1.setText("");
                                tf2.setText("");
                                message.setText("");
                                message.setStyle("-fx-background-color:cadetblue");
                                message.setTextFill(Color.BLACK);
                                st.close();

                            } catch (SQLException ex) {
                                JOptionPane.showMessageDialog(null, "Update Failed");
                            }
                        }
                    }
                });
                
                but1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent e) {
                        tf1.setText("");
                        tf2.setText("");
                        st.close();
                    }
                });

                Scene sc = new Scene(gp,400,400);
                gp.setStyle("-fx-background-color:CADETBLUE");
                //gp.setStyle("-fx-background-color:CORNFLOWERBLUE");
                st.setScene(sc);
                //sc.setFill(Color.CORNFLOWERBLUE);
                
                //st.initStyle(StageStyle.TRANSPARENT);
                //st.setOpacity(0.8);

                st.show();
            }
        });
        
        
        
        vb.getChildren().addAll(cb1,cb2,cb3);
        vb.setPadding(new Insets(150,100,100,200));
        
        scrollpane.setContent(vb);
    }


    @FXML
    void Addmanager(ActionEvent event) {

    }

}
