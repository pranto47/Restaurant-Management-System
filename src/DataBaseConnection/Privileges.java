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
public class Privileges {
    private final SimpleBooleanProperty selected;
    private final SimpleStringProperty Privileges;

    public Privileges(String name) {
        this.selected = new SimpleBooleanProperty(false);
        this.Privileges = new SimpleStringProperty(name);
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

    public String getPrivileges() {
        return Privileges.get();
    }

    public SimpleStringProperty nameProperty() {
        return Privileges;
    }

    public void setPrivileges(String name) {
        this.Privileges.set(name);
    }
}
