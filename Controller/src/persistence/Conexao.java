/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package persistence;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


/**
 *
 * @author Andreia
 */
public class Conexao {
 private static Conexao instance;
    public static Conexao getInstance(){
        if(instance == null)
            instance = new Conexao();
        return instance;
    }
    private Conexao(){
    }
    public Connection getConnection() throws SQLException, ClassNotFoundException
    {
        //Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");
        //Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        //Connection conn = java.sql.DriverManager.getConnection("jdbc:sqlserver://localhost:1433;" +
	//		"databaseName=AdventureWorks;integratedSecurity=true;");
                //java.sql.DriverManager.getConnection("jdbc:sqlserver://localhost:1433;Database=BusinessDatabase;User=sa; Password=10906509");                
                //DriverManager.getConnection("jdbc:odbc:System","system","Contact");
               // DriverManager.getConnection("jdbc:microsoft:sqlserver://localhost:1433","sa","10906509");
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
String connectionUrl = "jdbc:sqlserver://QUEIROZ-NOTE\\SQLEXPRESS:1433;database=MVC;user=sa;password=10906509";
Connection conn = DriverManager.getConnection(connectionUrl);
        return conn;
    }
}

