
package DataBaseConnection;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class Databaseconnection {
    private String username;
    private String password;
    private final String CONN_STRING = "jdbc:oracle:thin:@DESKTOP-4GRV9TL:1521:xe";
    public Connection connection = null;
    //private static OracleDBMS instance = null;

    public Databaseconnection()
    {
        this.username = "restaurant";
        this.password = "ilmhasan47";
    }

    public Databaseconnection(String username, String password)
    {
        this.username = username;
        this.password = password;
    }

    public Connection getConnection()
    {
        if (connection == null)
        {
            try
            {
                connection = DriverManager.getConnection(CONN_STRING, username, password);
            } catch (SQLException e)
            {
                System.out.println("Connection Failed! Check it from console");
                e.printStackTrace();
            }
        }

        return connection;
    }

    public void closeConnection()
    {
        try
        {
            if (connection != null)
            {
                connection.close();
                connection = null;
            }
        } catch (Exception e)
        {
            System.out.println(e);
        }
    }

}
