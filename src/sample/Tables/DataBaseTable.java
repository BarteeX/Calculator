package sample.Tables;

import sample.DBController;
import sample.IdController;

import java.util.HashMap;

import static sample.ColumnNames.DEPITION;
import static sample.ColumnNames.ID;
import static sample.ColumnNames.IDN;

/**
 * Created by BarteeX on 2017-09-01.
 */
public abstract class DataBaseTable {
    private String id;
    private String idn;
    private String depition;
    protected String tableName;
    protected HashMap<String, Object> allFields;

    public DataBaseTable(String id) {
        this.id = id;
        prepareTableName();
    }

    public DataBaseTable(HashMap<String, String> obj) {
        prepareTableName();
        update(obj);
    }

    public String getTableName() {
        return tableName;
    }

    public HashMap<String, Object> getAllFields() {
        return allFields;
    }

    public String getId() {
        return id;
    }

    public String getIdn() {
        return idn;
    }

    public String getDepition() {
        return depition;
    }

    public void setId(String id) {
        if(IdController.addNewId(tableName) > 0) {
            this.id = id;
        } else {
            System.out.println("Nie udało się załadować klucza dla tabeli " + tableName);
        }
    }

    public void setIdn(String idn) {
        this.idn = idn;
    }

    public void setDepition(String depition) {
        this.depition = depition;
    }

    public void setAllFields() {
        allFields = new HashMap<>();
        allFields.put(ID, getId());
        allFields.put(IDN, getIdn());
        allFields.put(DEPITION, getDepition());
    }

    public void update(HashMap<String, String> obj) {
        setDepition(obj.get(DEPITION));
        setId(obj.get(tableName + '_' + ID + "_pk"));
        if(getId() == null) {
            setId(obj.get(ID));
        }

        setIdn(obj.get(tableName + '_' + IDN));
        if(getIdn() == null) {
            setIdn(obj.get(IDN));
        }
    }

    public String prepareTableName() {
        String tableName = "";
        if(this instanceof Client) {
            tableName = DBController.TABLE_CLIENT;
        } else if(this instanceof Contact) {
            tableName = DBController.TABLE_CONTACT;
        } else if(this instanceof Property) {
            tableName = DBController.TABLE_PROPERTY;
        } else if(this instanceof PropertyAddres) {
            tableName = DBController.TABLE_ADDRESS;
        } else if(this instanceof Room) {
            tableName = DBController.TABLE_ROOM;
        } else if(this instanceof Work) {
            tableName = DBController.TABLE_WORK;
        } else if(this instanceof WorkSet) {
            tableName = DBController.TABLE_WORK_SET;
        }
        this.tableName = tableName;
        return tableName;
    }
}
