package library.helper;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import java.beans.PropertyVetoException;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {
    public static final String URL = "jdbc:mariadb://localhost/oracleproject";
    public static final String LOGIN = "root";
    public static final String PASSWORD = "123456";
    public static final String DRIVER_CLASS = "org.mariadb.jdbc.Driver";
    private static ComboPooledDataSource cpds;

    private ConnectionFactory(){}

    private static void createConnection(){
        cpds = new ComboPooledDataSource();
        try {
            cpds.setDriverClass(DRIVER_CLASS); //loads the jdbc driver
        } catch (PropertyVetoException e) {
            System.out.println("Unable to set jdbc driver:");
            e.printStackTrace();
        }
        cpds.setJdbcUrl(URL);
        cpds.setUser(LOGIN);
        cpds.setPassword(PASSWORD);

        cpds.setMinPoolSize(3);
        cpds.setAcquireIncrement(5);
        cpds.setMaxPoolSize(23);
    }

    public static Connection getConnection() throws SQLException {
        if(cpds == null)
            createConnection();
        return cpds.getConnection();
    }
}
