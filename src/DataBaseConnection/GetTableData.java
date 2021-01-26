
package DataBaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class GetTableData {
    public void getAllData(final TableView<ObservableList<StringProperty>> table,String sql)
    {
        try{
            Connection con = new Databaseconnection().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            for(int column=0;column<rsmd.getColumnCount();column++){
                table.getColumns().add(createColumn(column,rsmd.getColumnLabel(column+1)));
            }
            while (rs.next())
            {
                ObservableList<StringProperty> data = FXCollections.observableArrayList();
                for(int i = 0;i<rsmd.getColumnCount();i++){
                    data.add(new SimpleStringProperty(rs.getString(i+1)));
                } 
                table.getItems().add(data);
            }
            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            
        }
    }
    
    public TableColumn<ObservableList<StringProperty>, String> createColumn(int columnIndex, String columnTitle) {
        TableColumn<ObservableList<StringProperty>, String> column = new TableColumn<>();
        column.setMinWidth(200);
        String title;
        if (columnTitle == null || columnTitle.trim().length() == 0) {
            title = "Column " + (columnIndex + 1);
        } else {
            title = columnTitle;
        }
        column.setText(title);
        column.setCellValueFactory(new Callback<TableColumn.CellDataFeatures<ObservableList<StringProperty>, String>, ObservableValue<String>>() {
            @Override
            public ObservableValue<String> call(
                    CellDataFeatures<ObservableList<StringProperty>, String> cellDataFeatures) {
                ObservableList<StringProperty> values = cellDataFeatures.getValue();
                return cellDataFeatures.getValue().get(columnIndex);
            }
        });
        return column;
    }

}
