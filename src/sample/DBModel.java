package sample;

import sample.Tables.*;

import java.util.*;

import static sample.ColumnNames.*;
import static sample.DBController.*;

/**
 * Created by BarteeX on 2017-08-26.
 */
public class DBModel {
    private List<DataBaseTable> clientList;
    private List<DataBaseTable> contactList;
    private List<DataBaseTable> propertyList;
    private List<DataBaseTable> propertyAddressList;
    private List<DataBaseTable> roomList;
    private List<DataBaseTable> workList;
    private List<DataBaseTable> workSetList;
    private HashMap<String, List<DataBaseTable>> allDB;

    public DBModel() {
        reset();
    }

    public void reset() {
        clientList = new ArrayList<>();
        contactList = new ArrayList<>();
        propertyList = new ArrayList<>();
        propertyAddressList = new ArrayList<>();
        roomList = new ArrayList<>();
        workList = new ArrayList<>();
        workSetList = new ArrayList<>();
        allDB = new HashMap<>();
        allDB.put(TABLE_CLIENT, clientList);
        allDB.put(TABLE_CONTACT, contactList);
        allDB.put(TABLE_PROPERTY, propertyList);
        allDB.put(TABLE_ADDRESS, propertyAddressList);
        allDB.put(TABLE_ROOM, roomList);
        allDB.put(TABLE_WORK, workList);
        allDB.put(TABLE_WORK_SET, workSetList);
    }

    public HashMap<String, List<DataBaseTable>> getAllDB() {
        return allDB;
    }

    public List<DataBaseTable> getClientList() {
        return clientList;
    }

    public List<DataBaseTable> getContactList() {
        return contactList;
    }

    public List<DataBaseTable> getPropertyList() {
        return propertyList;
    }

    public List<DataBaseTable> getRoomList() {
        return roomList;
    }

    public List<DataBaseTable> getWorkList() {
        return workList;
    }

    public List<DataBaseTable> getWorkSetList() {
        return workSetList;
    }

    public List<DataBaseTable> getPropertyAddressList() {
        return propertyAddressList;
    }

    public void setClientList(List<DataBaseTable> clientList) {
        this.clientList = clientList;
    }

    public void setContactList(List<DataBaseTable> contactList) {
        this.contactList = contactList;
    }

    public void setPropertyList(List<DataBaseTable> propertyList) {
        this.propertyList = propertyList;
    }

    public void setPropertyAddressList(List<DataBaseTable> propertyAddressList) {
        this.propertyAddressList = propertyAddressList;
    }

    public void setRoomList(List<DataBaseTable> roomList) {
        this.roomList = roomList;
    }

    public void setWorkList(List<DataBaseTable> workList) {
        this.workList = workList;
    }

    public void setWorkSetList(List<DataBaseTable> workSetList) {
        this.workSetList = workSetList;
    }

    public void update(HashMap<String, String> obj) {
        String table = obj.get("table");
        String id = obj.get(ID);
        obj.remove("table");
        List<DataBaseTable> dataBaseTables = allDB.get(table);
        for(DataBaseTable dbt : dataBaseTables) {
            if(dbt.getId().equals(id)) {
                dbt.update(obj);
            }
        }
        obj.put("table", table);
    }

    public void add(HashMap<String, String> obj) {
        List<String> tempList = new ArrayList<>();
        tempList.addAll(obj.keySet());
        final String[] propertyName = new String[1];
        String offset = "_pk";
        tempList.forEach(label -> {
            System.out.println(label);
            if(label.endsWith(offset)) propertyName[0] = label;
        });
        System.out.println(propertyName[0]);
        switch (propertyName[0]) {
            case SQL_CLIENT_ID_PK :
                add(new Client(obj));
                break;
            case SQL_CONTACT_ID_PK :
                add(new Contact(obj));
                break;
            case SQL_PROPERTY_ID_PK :
                add(new Property(obj));
                break;
            case SQL_ADDRESS_ID_PK :
                add(new PropertyAddres(obj));
                break;
            case SQL_ROOM_ID_PK :
                add(new Room(obj));
                break;
            case SQL_WORK_ID_PK :
                add(new Work(obj));
                break;
            case SQL_WORK_SET_ID_PK :
                add(new WorkSet(obj));
                break;

        }
    }

    public void add(Client client) {
        if(client != null) {
            this.clientList.add(client);
        }
    }

    public void add(Contact contact) {
        if(contact != null) {
            this.contactList.add(contact);
        }
    }

    public void add(Property property) {
        if(property != null) {
            this.propertyList.add(property);
        }
    }

    public void add(PropertyAddres propertyAddres) {
        if(propertyAddres != null) {
            this.propertyAddressList.add(propertyAddres);
        }
    }

    public void add(Room room) {
        if(room != null) {
            this.roomList.add(room);
        }
    }

    public void add(Work work) {
        if(work != null) {
            this.workList.add(work);
        }
    }

    public void add(WorkSet workSet) {
        if(workSet != null) {
            this.workSetList.add(workSet);
        }
    }

}
