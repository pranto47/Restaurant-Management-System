/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DataBaseConnection;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.scene.control.TextField;

/**
 *
 * @author Imran
 */
public class Item {
    private final SimpleBooleanProperty selected;
    private final SimpleStringProperty name;
    private final SimpleStringProperty category_name;
    private final SimpleStringProperty price;
    private  TextField Quantity;

    public Item(String category_name,String name,String price) {
        this.selected = new SimpleBooleanProperty(false);
        this.name = new SimpleStringProperty(name);
        this.category_name = new SimpleStringProperty(category_name);
        this.price = new SimpleStringProperty(price);
        this.Quantity = new TextField("1");
    }

    public boolean isSelected() {
        return selected.get();
    }

    public SimpleBooleanProperty selectedProperty() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected.set(selected);
    }

    public String getName() {
        return name.get();
    }

    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }
    
    public String getcategory_name() {
        return category_name.get();
    }

    public SimpleStringProperty category_nameProperty() {
        return category_name;
    }

    public void setcategory_name(String category_name) {
        this.category_name.set(category_name);
    }
    
    public String getprice() {
        return price.get();
    }

    public SimpleStringProperty priceProperty() {
        return price;
    }

    public void setprice(String price) {
        this.price.set(price);
    }
    
    public TextField getQuantity() {
        return Quantity;
    }

//    public TextField QuantityProperty() {
//        return Quantity;
//    }

    public void setQuantity(TextField quantity) {
        this.Quantity = quantity;
    }
    
    
}
