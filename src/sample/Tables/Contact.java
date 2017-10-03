package sample.Tables;

import sample.DBController;

import javax.xml.crypto.Data;
import java.util.HashMap;

import static sample.ColumnNames.*;
import static sample.ColumnNames.IDN;

/**
 * Created by BarteeX on 2017-08-26.
 */
public class Contact extends DataBaseTable {
    private String phone1;
    private String phone2;
    private String homePhone;
    private String gg;
    private String skype;
    private String clientId;

    @Override
    public void setAllFields() {
        super.setAllFields();
        allFields.put(PHONE1, getPhone1());
        allFields.put(PHONE2, getPhone2());
        allFields.put(HOME_PHON, getHome_phone());
        allFields.put(GG, getGg());
        allFields.put(SKYPE, getSkype());
        allFields.put(CLIENT_ID, getClient_id());
    }
    @Override
    public void update(HashMap<String, String> obj) {
        super.update(obj);
        setPhone1(obj.get(PHONE1));
        setPhone2(obj.get(PHONE2));
        setHome_phone(obj.get(HOME_PHON));
        setGg(obj.get(GG));
        setSkype(obj.get(SKYPE));
        setClient_id(obj.get(CLIENT_ID));
        setAllFields();
    }

    public Contact(HashMap<String, String> contact) {
        super(contact);
    }

    public String getPhone1() {
        return phone1;
    }

    public String getPhone2() {
        return phone2;
    }

    public String getHome_phone() {
        return homePhone;
    }

    public String getGg() {
        return gg;
    }

    public String getSkype() {
        return skype;
    }

    public String getClient_id() {
        return clientId;
    }

    public void setPhone1(String phone1) {
        this.phone1 = phone1;
    }

    public void setPhone2(String phone2) {
        this.phone2 = phone2;
    }

    public void setHome_phone(String homePhone) {
        this.homePhone = homePhone;
    }

    public void setGg(String gg) {
        this.gg = gg;
    }

    public void setSkype(String skype) {
        this.skype = skype;
    }

    public void setClient_id(String clientId) {
        this.clientId = clientId;
    }

}
