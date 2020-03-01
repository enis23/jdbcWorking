import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.*;

public class ZippoProviderTest {
    private Statement statement;
    private Connection connection;
    private String tableName;

    @BeforeClass
    public void connect() throws SQLException {
        tableName = "zippo_table";
        String url = "jdbc:mysql://database-techno.c771qxmldhez.us-east-2.rds.amazonaws.com:3306/daulet2030_zippo_database";
        String user = "daulet2030";
        String password = "daulet2030@gmail.com";
        connection = DriverManager.getConnection( url, user, password );
        statement = connection.createStatement();
    }

    @AfterClass
    public void disconnect() throws SQLException {
        connection.close();
    }

    @DataProvider(name = "zippo")
    public Object[][] zippoData() throws SQLException {
        // creating query from statement
        ResultSet resultSet = statement.executeQuery( "select " +
                "* " +
                "from " + tableName );
        // to set cursor to the last element in order for to get the number of rows
        resultSet.last();
        // get the number of rows
        int numberOfRow = resultSet.getRow();

        // create two dimensional array based on number rows and 4 columns
        Object[][] resultData = new Object[numberOfRow][4];

        // point the cursor to the row before first
        resultSet.beforeFirst();
        // initialize row to the first row (0 index based)
        int row = 0;
        // while we have next row
        while(resultSet.next()) { // this advances the cursor and return true or false whether there is next row or not
            // read columns from the row
            String country_code = resultSet.getString( "country_code" );
            String zip_code = resultSet.getString( "zip_code" );
            String place_name = resultSet.getString( "place_name" );
            String place_count = resultSet.getString( "place_count" );
           // assign column values to fields of my two dimensional array
            resultData[row][0] = country_code;
            resultData[row][1] = zip_code;
            resultData[row][2] = place_name;
            resultData[row][3] = place_count;
            // advance row
            row++;
        }

        return resultData;
    }

    @Test(dataProvider = "zippo")
    public void test(String c1, String c2, String c3, String c4){
        System.out.print(c1 + c2 + c3 + c4);
    }

}
