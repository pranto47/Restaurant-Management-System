
package DataBaseConnection;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Types;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellDataFeatures;
import javafx.scene.control.TableView;
import javafx.util.Callback;

public class GetTableData_Price {
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
            table.getColumns().add(createColumn(rsmd.getColumnCount(),"Bill"));
            while (rs.next())
            {
                CallableStatement stmt = con.prepareCall("{call ONLINE_ORDER_PRICE(?,?)}");
                stmt.registerOutParameter(2, Types.DOUBLE);

                stmt.setInt(1,Integer.parseInt(rs.getString(1)));
                stmt.execute();
                double get = stmt.getDouble(2);
                String price = String.valueOf(get);
                ObservableList<StringProperty> data = FXCollections.observableArrayList();
                for(int i = 0;i<rsmd.getColumnCount();i++){
                    data.add(new SimpleStringProperty(rs.getString(i+1)));
                } 
                data.add(new SimpleStringProperty(price));
                table.getItems().add(data);
            }
            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println("Error_..");
        }
    }
    
    public void getAllData_Offline(final TableView<ObservableList<StringProperty>> table,String sql)
    {
        try{
            Connection con = new Databaseconnection().getConnection();
            PreparedStatement pst = con.prepareStatement(sql);
            ResultSet rs = pst.executeQuery();
            ResultSetMetaData rsmd = rs.getMetaData();
            for(int column=0;column<rsmd.getColumnCount();column++){
                table.getColumns().add(createColumn(column,rsmd.getColumnLabel(column+1)));
            }
            table.getColumns().add(createColumn(rsmd.getColumnCount(),"Bill"));
            while (rs.next())
            {
                CallableStatement stmt = con.prepareCall("{call OFFLINE_ORDER_PRICE(?,?)}");
                stmt.registerOutParameter(2, Types.DOUBLE);

                stmt.setInt(1,Integer.parseInt(rs.getString(1)));
                stmt.execute();
                double get = stmt.getDouble(2);
                String price = String.valueOf(get);
                ObservableList<StringProperty> data = FXCollections.observableArrayList();
                for(int i = 0;i<rsmd.getColumnCount();i++){
                    data.add(new SimpleStringProperty(rs.getString(i+1)));
                } 
                data.add(new SimpleStringProperty(price));
                table.getItems().add(data);
            }
            pst.close();
            con.close();
        }
        catch(Exception e)
        {
            System.out.println("Error_..");
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
