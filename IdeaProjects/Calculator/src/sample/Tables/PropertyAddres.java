package sample.Tables;

import sample.ColumnNames;
import sample.DBController;

import java.util.HashMap;

import static sample.ColumnNames.*;

/**
 * Created by BarteeX on 2017-08-26.
 */
public class PropertyAddres extends DataBaseTable{
    private String street;
    private String houseNumber;
    private String flatNumber;
    private String postalCode;
    private String city;
    private String postal;
    private String propertyId;

    @Override
    public void setAllFields() {
        super.setAllFields();
        super.tableName = DBController.TABLE_ADDRESS;
        allFields.put(STREET, getStreet());
        allFields.put(HOUSE_NUMBER, getHouseNumber());
        allFields.put(FLAT_NUMBER, getFlatNumber());
        allFields.put(POSTAL_CODE, getPostalCode());
        allFields.put(CITY, getCity());
        allFields.put(POSTAL, getPostal());
        allFields.put(PROPERTY_ID, getPropertyId());
    }

    @Override
    public void update(HashMap<String, String> obj) {
        super.update(obj);
        setStreet(obj.get(STREET));
        setHouseNumber(obj.get(HOUSE_NUMBER));
        setFlatNumber(obj.get(FLAT_NUMBER));
        setPostalCode(obj.get(POSTAL_CODE));
        setCity(obj.get(CITY));
        setPostal(obj.get(POSTAL));
        setPropertyId(obj.get(PROPERTY_ID));
        setAllFields();
    }

    public PropertyAddres(HashMap<String, String> propertyAddres) {
        super(propertyAddres);
    }

    public String getStreet() {
        return street;
    }

    public String getHouseNumber() {
        return houseNumber;
    }

    public String getFlatNumber() {
        return flatNumber;
    }

    public String getPostalCode() {
        return postalCode;
    }

    public String getCity() {
        return city;
    }

    public String getPostal() {
        return postal;
    }

    public String getPropertyId() {
        return propertyId;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public void setHouseNumber(String houseNumber) {
        this.houseNumber = houseNumber;
    }

    public void setFlatNumber(String flatNumber) {
        this.flatNumber = flatNumber;
    }

    public void setPostalCode(String postalCode) {
        this.postalCode = postalCode;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public void setPostal(String postal) {
        this.postal = postal;
    }

    public void setPropertyId(String propertyId) {
        this.propertyId = propertyId;
    }
}
