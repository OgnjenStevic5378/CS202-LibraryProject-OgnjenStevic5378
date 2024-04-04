package Database;

import DataClass.Client;
import DataClass.Employee;
import DataClass.StatisticData;
import Scenes.AlertManager;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * This class contains all logic related to database.
 */

public class DataBase {
    private static java.sql.Connection connection = null;
    public static String url = "jdbc:mysql://localhost:3306/cdproject";
    public static String username = "root";
    private static String password = "";


    // The method returns a List of all Employees
    public static List<Employee> getAllEmployee() {
        ArrayList<Employee> userList = new ArrayList<Employee>();

        try {
            connection = DriverManager.getConnection(url, username, password);
            String query = "SELECT * FROM employee";
            Statement st = (Statement) connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt("id_employee");
                String username = rs.getString("username");
                String password = rs.getString("password");
                userList.add(new Employee(id, username, password));
            }
            st.close();
        } catch (SQLException e) {
            System.err.println(e);
        }
        return userList;
    }

    // The method used to add a new Client to the Database
    public static void addNewClient(int idClient, String name, String lastName, Date birthDate) { // The SQL Date, not regular one
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO client (id_client, name, last_name, birth_date) "
                    + "VALUES (?,?,?,?)");

            preparedStatement.setInt(1, idClient);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, lastName);
            preparedStatement.setDate(4, birthDate);

            preparedStatement.execute();
            connection.close();

            // Alert to show that everything has been completed well
            AlertManager.showSuccessfullyAlert();
        } catch (SQLException e) {
            // Alert to show that we have an error
            System.err.println(e);
        }
    }

    // The method used to add a new CD in the database
    public static void addNewCD(int idClient, String name, String genre, Integer publisherYear) { // publisherYear is int
        try {
            connection = DriverManager.getConnection(url, username, password);
            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cd (id_cd, name, genre, publisher_year) "
                    + "VALUES (?,?,?,?)");

            preparedStatement.setInt(1, idClient);
            preparedStatement.setString(2, name);
            preparedStatement.setString(3, genre);

            // Adding NULL to database if Integer is NULl
            if (publisherYear == null) {
                preparedStatement.setNull(4, java.sql.Types.INTEGER);
            } else {
                preparedStatement.setInt(4, publisherYear);
            }

            preparedStatement.execute();
            connection.close();

            // Alert to show that everything has been completed well
            AlertManager.showSuccessfullyAlert();
        } catch (SQLException e) {
            // Alert to show that we have an error
            AlertManager.showDuplicatedIDAlert();
            System.err.println(e);
        }
    }

    // The method returns data about returned and not returned CDs
    public static ArrayList<StatisticData> getStatisticData() {

        ArrayList<StatisticData> statisticDataArrayList = new ArrayList<StatisticData>();
        try {
            connection = DriverManager.getConnection(url, username, password);
            String query =
                    "SELECT client.id_client, CONCAT(client.name, ' ', client.last_name) AS full_name, cd.id_cd, cd.name AS cd_name, cd_and_client.begin_date, cd_and_client.end_date FROM cd_and_client\n" +
                            "INNER JOIN client ON cd_and_client.fk_client = client.id_client\n" +
                            "INNER JOIN cd ON cd_and_client.fk_cd = cd.id_cd";
            Statement st = (Statement) connection.createStatement();
            ResultSet rs = st.executeQuery(query);
            while (rs.next()) {
                int idClient = rs.getInt("id_client");
                String nameClient = rs.getString("full_name");
                int idCD = rs.getInt("id_cd");
                String nameCD = rs.getString("cd_name");
                Date cdAndClientBeginDate = rs.getDate("begin_date");
                Date cdAndClientEndDate = rs.getDate("end_date");

                statisticDataArrayList.add(new StatisticData(idClient, nameClient, idCD, nameCD, cdAndClientBeginDate, cdAndClientEndDate));
            }
            st.close();
        } catch (SQLException e) {
            System.err.println(e);
        }

        return statisticDataArrayList;
    }

    public static void issueCD(int issueID, int idCD, int idClient, Date currentDate) {
        try {
            connection = DriverManager.getConnection(url, username, password);

            PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO cd_and_client (id_cd_and_client, fk_cd, fk_client, begin_date, end_date) "
                    + "VALUES (?, ?, ?, ?, NULL)");
            preparedStatement.setInt(1, issueID);
            preparedStatement.setInt(2, idCD);
            preparedStatement.setInt(3, idClient);
            preparedStatement.setDate(4, currentDate);

            preparedStatement.execute();
            connection.close();

            // Alert to show that everything has been completed well
            AlertManager.showSuccessfullyAlert();
        } catch (SQLException e) {
            // Alert to show that we have an error
            if(e.toString().contains("Duplicate entry")) // The exception used for duplicated ID
                AlertManager.showDuplicatedIDAlert();
            else if (e.toString().contains("FOREIGN KEY (`fk_client`) REFERENCES `client` (`id_client`)")) // The exception used for invalid Client ID
                AlertManager.showInvalidClientIDAlert();
            else if (e.toString().contains("FOREIGN KEY (`fk_cd`) REFERENCES `cd` (`id_cd`)")) // The exception used for invalid CD ID
                AlertManager.showInvalidCDIDAlert();
            System.err.println(e);
        }
    }

    public static void returnCD(int issueID, Date endDate) {
        try {
            connection = DriverManager.getConnection(url, username, password);

            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE cd_and_client SET end_date = ? WHERE cd_and_client.id_cd_and_client = ?");
            preparedStatement.setDate(1, endDate);
            preparedStatement.setInt(2, issueID);

            preparedStatement.execute();
            connection.close();

            // Alert to show that everything has been completed well
            AlertManager.showSuccessfullyAlert();
        } catch (SQLException e) {
            System.err.println(e);
        }
    }
}
