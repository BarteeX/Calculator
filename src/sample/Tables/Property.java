package sample.Tables;

import sample.ColumnNames;
import sample.DBController;

import java.util.HashMap;

import static sample.ColumnNames.*;

/**
 * Created by BarteeX on 2017-08-26.
 */
public class Property extends DataBaseTable {
    private Boolean flat;
    private Boolean house;
    private Boolean semiDetached;
    private int flatsAmount;
    private int roomsAmount;
    private String clientId;

    @Override
    public void setAllFields() {
        super.setAllFields();
        allFields.put(FLAT, getFlat());
        allFields.put(HOUSE, getHouse());
        allFields.put(SEMI_DETACHED, getSemiDetached());
        allFields.put(FLATS_AMOUNT, getFlatsAmount());
        allFields.put(ROOMS_AMOUNT, getRoomsAmount());
        allFields.put(CLIENT_ID, getClientId());
    }

    @Override
    public void update(HashMap<String, String> obj) {
        super.update(obj);
        setFlat(obj.get(FLAT));
        setHouse(obj.get(HOUSE));
        setSemiDetached(obj.get(SEMI_DETACHED));
        setFlatsAmount(obj.get(FLATS_AMOUNT));
        setRoomsAmount(obj.get(ROOMS_AMOUNT));
        setClientId(obj.get(CLIENT_ID));
        setAllFields();
    }

    public Property(HashMap<String,String> property) {
        super(property);
    }

    public Boolean getFlat() {
        return flat;
    }

    public Boolean getHouse() {
        return house;
    }

    public Boolean getSemiDetached() {
        return semiDetached;
    }

    public int getFlatsAmount() {
        return flatsAmount;
    }

    public int getRoomsAmount() {
        return roomsAmount;
    }

    public String getClientId() {
        return clientId;
    }

    public void setFlat(String flat) {
        if(flat != null)
            this.flat = flat.equals("true");//Boolean.parseBoolean(flat);
        else
            this.flat = false;
    }

    public void setHouse(String house) {
        if(house != null)
            this.house = house.equals("true");//Boolean.parseBoolean(house);
        else
            this.house = false;
    }

    public void setSemiDetached(String semiDetached) {
        if(semiDetached != null)
            this.semiDetached = semiDetached.equals("true");//Boolean.parseBoolean(semiDetached);
        else
            this.semiDetached = false;
    }

    public void setFlatsAmount(String flatsAmount) {
        this.flatsAmount = flatsAmount == null ? 0 : Integer.parseInt(flatsAmount);
    }

    public void setRoomsAmount(String roomsAmount) {
        this.roomsAmount = roomsAmount == null ? 0 : Integer.parseInt(roomsAmount);
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }
}
