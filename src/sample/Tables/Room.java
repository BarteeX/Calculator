package sample.Tables;

import sample.ColumnNames;
import sample.DBController;
import sample.DBModel;

import java.util.HashMap;

import static sample.ColumnNames.*;

/**
 * Created by BarteeX on 2017-08-26.
 */
public class Room extends DataBaseTable {
    private int windowsAmount;
    private int doorsAmount;
    private String propertyId;
    private String workSetId;

    @Override
    public void setAllFields() {
        super.setAllFields();
        allFields.put(WINDOWS_AMOUNT, getWindowsAmount());
        allFields.put(DOORS_AMOUNT, getDoorsAmount());
        allFields.put(PROPERTY_ID, getPropertyId());
        allFields.put(WORK_SET_ID, getWorkSetId());
    }

    @Override
    public void update(HashMap<String, String> obj) {
        super.update(obj);
        setWindowsAmount(obj.get(WINDOWS_AMOUNT));
        setDoorsAmount(obj.get(DOORS_AMOUNT));
        setPropertyId(obj.get(PROPERTY_ID));
        setWorkSetId(obj.get(WORK_SET_ID));
        setAllFields();
    }

    public Room() {
        super();
    }

    public Room(HashMap<String, String> room) {
        super(room);
    }

    public int getWindowsAmount() {
        return windowsAmount;
    }

    public int getDoorsAmount() {
        return doorsAmount;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public String getWorkSetId() {
        return workSetId;
    }

    public void setWindowsAmount(String windowsAmount) {
        this.windowsAmount = Integer.parseInt(windowsAmount);
    }

    public void setDoorsAmount(String doorsAmount) {
        this.doorsAmount = Integer.parseInt(doorsAmount);
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }

    public void setWorkSetId(String workSetId) {
        this.workSetId = workSetId;
    }
}
