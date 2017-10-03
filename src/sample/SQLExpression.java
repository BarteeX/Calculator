package sample;

import sample.ColumnNames;
import sample.DBController;
import sample.Tables.DataBaseTable;

import java.time.LocalDate;
import java.util.*;

import static sample.ColumnNames.ID;
import static sample.ColumnNames.IDN;
import static sample.DBController.*;

/**
 * Created by BarteeX on 2017-08-27.
 */
public class SQLExpression {
    public static final String ALL_CLIENTS = "SELECT * FROM Client";
    public static final String ALL_CONTACT = "SELECT * FROM Contact";
    public static final String ALL_PROPERTY= "SELECT * FROM Property";
    public static final String ALL_PROPERTY_ADDRESS = "SELECT * FROM Property_address";
    public static final String ALL_ROOM = "SELECT * FROM Room";
    public static final String ALL_WORK = "SELECT * FROM Work";
    public static final String ALL_WORK_SET = "SELECT * FROM Work_set";

    public static final String[] ALL_DB_TAB = new String[] {ALL_CLIENTS, ALL_CONTACT,ALL_PROPERTY,ALL_PROPERTY_ADDRESS,ALL_ROOM, ALL_WORK,ALL_WORK_SET};

    public static final List<String> ALL_DB = Arrays.asList(ALL_DB_TAB);

    public static final String[] PRIMARY_KEYS_TAB = new String[]{
        "Client_id_pk", "Contact_id_pk", " Property_id_pk", "Address_id_pk",
        "Room_id_pk", "Work_id_pk", "Work_set_id_pk"
    };

    public static final List<String> PRIMARY_KEYS = Arrays.asList(PRIMARY_KEYS_TAB);

    public static String update(DataBaseTable table) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(table.getTableName());
        sb.append(" SET ");
        HashMap<String, ?> allFields = table.getAllFields();
        for(Map.Entry entry : allFields.entrySet()) {
            String columnName = entry.getKey().toString();
            Object value = entry.getValue();
            if(value != null) {
                if(columnName.endsWith(ID)) continue;
                if(columnName.equals(IDN)) {
                    columnName = table.getTableName() + "_" + columnName;
                }
                sb.append(columnName);
                sb.append("=");
                if(value instanceof String || value instanceof LocalDate) {
                    value = "\'" + value +"\'";
                }
                sb.append(value.toString());
                sb.append(",");
            }
        }
        sb.deleteCharAt(sb.lastIndexOf(","));
        sb.append(" WHERE ");
        sb.append(table.getTableName());
        sb.append(".");
        sb.append(table.getTableName());
        sb.append("_id=");
        sb.append(table.getId());
        String query = sb.toString();
        return query;
    }

    public static String getAllFrom(String tableName) {
        switch (tableName) {
            case TABLE_CLIENT:
                return ALL_CLIENTS;
            case TABLE_PROPERTY:
                return ALL_PROPERTY;
            case TABLE_ADDRESS:
                return ALL_PROPERTY_ADDRESS;
            case TABLE_ROOM:
                return ALL_ROOM;
            case TABLE_CONTACT:
                return ALL_CONTACT;
            case TABLE_WORK:
                return ALL_WORK;
            case TABLE_WORK_SET:
                return ALL_WORK_SET;
            default:
                return "";
        }
    }

    public static String queryForColumnNames(String table) {
        return "SELECT *\n" +
                "FROM Renovation.INFORMATION_SCHEMA.COLUMNS\n" +
                "WHERE TABLE_NAME = N'" + table + "'";
    }
}
