package sample;

import com.microsoft.sqlserver.jdbc.SQLServerException;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by BarteeX on 2017-08-24.
 */
public class DBConnection {
    private static DBConnection DBConnection = new DBConnection();
    private Connection connection;
    private Statement statement = null;
    private ResultSet resultSet = null;

    private DBConnection() {}

    public static DBConnection getInstance() {
        return DBConnection;
    }

    public void createConnection(String url, String login, String password) {
        if(connection == null){
            try {
                Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
                this.connection = DriverManager.getConnection(url, login, password);
                this.statement = this.connection.createStatement();
            } catch(Exception e) {
            }
        }
    }

    public List<HashMap<String, String>> doQuery(String query) {
        List<HashMap<String, String>> list = new ArrayList<>();
        HashMap<String, String> hashMap = new HashMap<>();
        try {
            this.resultSet = this.statement.executeQuery(query);
            while(this.resultSet.next()) {
                try {
                    hashMap = new HashMap<>();
                    for(int i = 1 ;; i++) {
                        hashMap.put(resultSet.getMetaData().getColumnName(i) + (i == 1 ? "_pk" : ""), resultSet.getString(i));
                    }
                }catch(Exception e) {}
                list.add(hashMap);
            }
        } catch (SQLException e) {}
        return list;
    }

    public boolean update(String query) {
        try {
            statement.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<String> getColumnNames(String columnName) {
        List<String> columnNames = new ArrayList<>();
        String query = SQLExpression.queryForColumnNames(columnName);
        resultSet = null;
        try {
            System.out.println(query);
            resultSet = statement.executeQuery(query);
            if (resultSet != null) {
                int i = 1;
                while (resultSet.next()) {
                    columnNames.add(resultSet.getMetaData().getCatalogName(i++));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return columnNames;
    }

}
