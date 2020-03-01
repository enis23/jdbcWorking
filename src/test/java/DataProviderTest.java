import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.sql.*;

public class DataProviderTest {
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

    @DataProvider(name = "students")
    public Object[][] studentsData() throws SQLException {
        // creating query from statement
        ResultSet resultSet = statement.executeQuery( "select " +
                "* " +
                "from students" );
        // to set cursor to the last element in order for to get the number of rows
        resultSet.last();
        // get the number of rows
        int numberOfRow = resultSet.getRow();

        // create two dimensional array based on number rows and six columns
        Object[][] resultData = new Object[numberOfRow][6];

        // point the cursor to the row before first
        resultSet.beforeFirst();
        // initialize row to the first row (0 index based)
        int row = 0;
        // while we have next row
        while(resultSet.next()) { // this advances the cursor and return true or false whether there is next row or not
            // read columns from the row
            String first_name = resultSet.getString( "first_name" );
            String last_name = resultSet.getString( "last_name" );
            String email = resultSet.getString( "email" );
            String gender = resultSet.getString( "gender" );
            String country = resultSet.getString( "country" );
            Double fee = resultSet.getDouble( "fee" );
            // assign column values to fields of my two dimensional array
            resultData[row][0] = first_name;
            resultData[row][1] = last_name;
            resultData[row][2] = email;
            resultData[row][3] = gender;
            resultData[row][4] = country;
            resultData[row][5] = fee;
            // advance row
            row++;
        }

        return resultData;
    }

    @Test(dataProvider = "students")
    public void test(String c1, String c2, String c3, String c4, String c5, Double c6){
        System.out.print(c1 + c2 + c3 + c4 + c5 + c6);

    }

}
