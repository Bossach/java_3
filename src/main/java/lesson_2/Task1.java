package lesson_2;

import java.sql.*;

public class Task1 {
    private static Connection connection;
    private static Statement statement;

    public static void main(String[] args) {

        try {
            connect();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

//        create("asd", "field1 INT PRIMARY_KEY", "field2 VARCHAR(100)");
        create("name", "str VARCHAR(100)", "str2 VARCHAR(100)");
        insert("name", "\"value1\"", "\"val2\"");
        insert("name", "\"value3\"", "\"val4\"");
        insert("name", "\"value1\"", "\"val6\"");

        update("name", "str2", "\"updated\"", "str = \"value1\"");

        select("name", "str = \"value1\"");

        disconnect();
    }

    public static void connect() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
        connection = DriverManager.getConnection("jdbc:sqlite:test.db");
        statement = connection.createStatement();
    }

    public static void disconnect() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void create(String tableName, String... colString) {

        String command = "CREATE TABLE IF NOT EXISTS " + tableName + " (" +
                getStringFromArray(colString) + ");";

        try {
            statement.execute(command);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void insert(String table, String... values) {

        try {
            statement.execute("INSERT INTO " + table + " VALUES (" + getStringFromArray(values) + ");");
        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public static void delete(String tableName, String conditions) {
        try {
            statement.execute("DELETE FROM " + tableName + " WHERE " + conditions + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static ResultSet select(String tableName, String conditions) {

        ResultSet rs = null;

        try {
            rs = statement.executeQuery("SELECT * FROM " + tableName + " WHERE " + conditions + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return rs;
    }

    public static void update(String tableName, String field, String newValue, String condition) {
        try {
            statement.execute("UPDATE " + tableName + " SET " + field + "=" + newValue + " WHERE " + condition + ";");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public static String getStringFromArray(String[] arr) {

        StringBuilder line = new StringBuilder();
        for (int i = 0; i < arr.length; i++) {
            if (i != 0) line.append(",");
            line.append(arr[i]);
        }

        return line.toString();
    }
}
