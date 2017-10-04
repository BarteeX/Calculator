package sample.Tables;

import sample.ColumnNames;
import sample.DBController;

import java.time.LocalDate;
import java.util.HashMap;

import static sample.ColumnNames.*;

/**
 * Created by BarteeX on 2017-08-26.
 */
public class Client extends DataBaseTable {
    private String forename;
    private String surname;
    private String NIP;
    private String PESEL;
    private LocalDate birthDate;
    private LocalDate startDate;
    private LocalDate endDate;

    @Override
    public void setAllFields() {
        super.setAllFields();
        allFields.put(FORENAME, getForename());
        allFields.put(SURNAME, getSurname());
        allFields.put(ColumnNames.NIP, getNIP());
        allFields.put(ColumnNames.PESEL, getPESEL());
        allFields.put(BIRTH_DAT, getBirthDate());
        allFields.put(START_DAT, getStartDate());
        allFields.put(END_DATE, getEndDate());
    }

    @Override
    public void update(HashMap<String, String> obj) {
        super.update(obj);
        setEndDate(obj.get(END_DATE));
        setStartDate(obj.get(START_DAT));
        setBirthDate(obj.get(BIRTH_DAT));
        setPESEL(obj.get(ColumnNames.PESEL));
        setNIP(obj.get(ColumnNames.NIP));
        setSurname(obj.get(SURNAME));
        setForename(obj.get(FORENAME));
        setAllFields();
    }

    public Client(String id) {
        super(id);
        setAllFields();
    }

    public Client(HashMap<String, String> client) {
        super(client);
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate == null ? LocalDate.MIN : LocalDate.parse(endDate);
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate == null ? LocalDate.MIN : LocalDate.parse(startDate);
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate == null ? LocalDate.MIN : LocalDate.parse(birthDate);
    }

    public void setPESEL(String PESEL) {
        this.PESEL = PESEL;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setNIP(String NIP) {
        this.NIP = NIP;
    }

    public void setForename(String forename) {
        this.forename = forename;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public String getPESEL() {
        return PESEL;
    }

    public String getNIP() {
        return NIP;
    }

    public String getSurname() {
        return surname;
    }

    public String getForename() {
        return forename;
    }
}
