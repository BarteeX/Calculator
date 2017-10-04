package sample;

import sample.Tables.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import static sample.ColumnNames.ID;

/**
 * Created by BarteeX on 2017-08-26.
 */
public class DBController {
    public static final String TABLE_CLIENT = "Client";
    public static final String TABLE_PROPERTY = "Property";
    public static final String TABLE_ADDRESS = "Property_address";
    public static final String TABLE_ROOM = "Room";
    public static final String TABLE_CONTACT = "Contact";
    public static final String TABLE_WORK = "Work";
    public static final String TABLE_WORK_SET = "Work_set";

    private DBConnection connection;
    private DBModel dbModel;

    private String url;
    private String login;
    private String password;

    public DBController(DBModel dbModel) {
        this.dbModel = dbModel;
        this.connection = DBConnection.getInstance();
    }

    public boolean reloadDb() {
        if(url != null && login != null && password != null) {
            dbModel.reset();
            loadDB(url, login, password);
            return true;
        } else {
            return false;
        }
    }

    public void loadDB(String url, String login, String password) {
        this.url = url;
        this.login = login;
        this.password = password;
        connection.createConnection(url, login, password);
        SQLExpression.ALL_DB.forEach(query -> {
            List<HashMap<String, String>> result = connection.doQuery(query);
            result.forEach(record -> {
                dbModel.add(record);
            });
        });

    }

    public List<DataBaseTable> get(String tableName) {
        switch (tableName) {
            case TABLE_CLIENT:
                return dbModel.getClientList();
            case TABLE_PROPERTY:
                return dbModel.getPropertyList();
            case TABLE_ADDRESS:
                return dbModel.getPropertyAddressList();
            case TABLE_ROOM:
                return dbModel.getRoomList();
            case TABLE_CONTACT:
                return dbModel.getContactList();
            case TABLE_WORK:
                return dbModel.getWorkList();
            case TABLE_WORK_SET:
                return dbModel.getWorkSetList();
            default:
                return new ArrayList<>();
        }
    }

    public DataBaseTable getEmpty(String tableName, String id) {
        switch (tableName) {
            case TABLE_CLIENT:
                return new Client(id);
            case TABLE_PROPERTY:
                return new Property(id);
            case TABLE_ADDRESS:
                return new PropertyAddres(id);
            case TABLE_ROOM:
                return new Room(id);
            case TABLE_CONTACT:
                return new Contact(id);
            case TABLE_WORK:
                return new Work(id);
            case TABLE_WORK_SET:
                return new WorkSet(id);
            default:
                return null;
        }
    }

    public DataBaseTable getById(String tableName, String id) {
        List<DataBaseTable> toReturn = get(tableName);
        for(DataBaseTable table : toReturn) {
            if (table.getId().equals(id)) {
                return table;
            }
        }
        return null;
    }

    private void updateRecordInDataBase(HashMap<String, String> obj) {
        String tableName = obj.get("table");
        List<DataBaseTable> dataBaseTables = dbModel.getAllDB().get(tableName);
        dataBaseTables.forEach(table -> {
            if(table.getId().equals(obj.get(ID))) {
                String query = SQLExpression.update(table);
                System.out.println(query);
                connection.update(query);
            }
        });
    }

    public void update(HashMap<String, String> obj) {
        dbModel.update(obj);
        updateRecordInDataBase(obj);
    }

    public void add(HashMap<String, String> obj) {
        String tableName = obj.get("table");
        List<DataBaseTable> dataBaseTables = dbModel.getAllDB().get(tableName);
        dataBaseTables.forEach(table -> {
            if (table.getId().equals(obj.get(ID))) {
                String query = SQLExpression.add(table);
                System.out.println(query);
                connection.update(query);
            }
        });

    }

    public List<String> getColumnNamesFor(String columnName) {
        return connection.getColumnNames(columnName);
    }

    public HashMap<String, List<DataBaseTable>> getAllTables(){
        return dbModel.getAllDB();
    }
}
