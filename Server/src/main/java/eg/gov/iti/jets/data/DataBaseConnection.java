package eg.gov.iti.jets.data;

import com.mysql.cj.jdbc.MysqlDataSource;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public class DataBaseConnection {
    private Connection connection =null;
    private InputStream inputStream;
    private Properties properties=new Properties();
    private static DataBaseConnection dataBaseConnection;

    private DataBaseConnection(){
        try {
            inputStream=new FileInputStream(getClass().getResource("/db.properties").getPath());
            //System.out.println(getClass().getResource("/db.properties").getPath());
            MysqlDataSource mysqlDataSource = new MysqlDataSource();
            properties.load(inputStream);
            mysqlDataSource.setURL(properties.getProperty("MYSQL_DB_URL"));
            mysqlDataSource.setUser(properties.getProperty("MYSQL_DB_USERNAME"));
            mysqlDataSource.setPassword(properties.getProperty("MYSQL_DB_PASSWORD"));
            connection =mysqlDataSource.getConnection();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public synchronized static DataBaseConnection getInstance(){
        if(dataBaseConnection==null){
            dataBaseConnection = new DataBaseConnection();
        }
        return  dataBaseConnection;
    }
    public Connection getConnection(){
        return connection;
    }
    public void closeConnection(){
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
