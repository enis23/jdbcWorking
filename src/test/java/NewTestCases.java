import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.sql.*;

public class NewTestCases {
    private Statement statement;
    private Connection connection;

    @BeforeClass
    public void connect() throws SQLException {
        String url = "jdbc:mysql://database-techno.c771qxmldhez.us-east-2.rds.amazonaws.com:3306/daulet2030_studens_database";
        String user = "daulet2030";
        String password = "daulet2030@gmail.com";
        connection = DriverManager.getConnection( url, user, password );
        statement = connection.createStatement();
    }

    @AfterClass
    public void disconnect() throws SQLException {
        connection.close();
    }

    @Test
    public void test() throws SQLException {
        ResultSet resultSet = statement.executeQuery( "select " +
                "concat(first_name, ' ', last_name), country, city, postal_code " +
                "from students" );
        while(resultSet.next()) {
            String name = resultSet.getString( 1 );
            String country = resultSet.getString( 2 );
            String city = resultSet.getString( 3 );
            String postalCode = resultSet.getString( 4 );
            System.out.println( name + ", " + country + ", " + city + ", " + postalCode );
        }
    }

    @Test
    public void testNew() throws SQLException {
        ResultSet resultSet = statement.executeQuery( "select" +
                " * " +
                "from students order by fee DESC limit 20" );
        while(resultSet.next()) {
            String email = resultSet.getString( "email" );
            Double fee = resultSet.getDouble( "fee" );
            String currency = resultSet.getString( "currency" );
            System.out.println( "email: " + email + ", fee:" + fee + " " + currency );
        }
    }
}
