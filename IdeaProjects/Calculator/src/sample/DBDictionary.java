package sample;

import java.util.HashMap;

import static sample.ColumnNames.*;
import static sample.DBController.*;

/**
 * Created by BarteeX on 2017-09-16.
 */
public class DBDictionary {
    private static final HashMap<String,String> translateMap = initMap();
    static HashMap<String, String> initMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put(ID, "Index");
        map.put(IDN, "Identyfikator");
        map.put(DEPITION, "Opis");
        map.put(AMOUNT, "Ilość");
        map.put(PRICE, "Cena");
        map.put(WINDOWS_AMOUNT, "Okna");
        map.put(DOORS_AMOUNT, "Drzwi");
        map.put(STREET, "Ulica");
        map.put(HOUSE_NUMBER, "Nr. domu");
        map.put(FLAT_NUMBER, "Nr. mieszkania");
        map.put(POSTAL_CODE, "Kod pocztowy");
        map.put(CITY, "Miasto");
        map.put(POSTAL, "Poczta");
        map.put(FLAT, "Mieszkanie");
        map.put(HOUSE, "Dom");
        map.put(SEMI_DETACHED, "Bliźniak");
        map.put(FLATS_AMOUNT, "Ilość pięter");
        map.put(ROOMS_AMOUNT, "Ilośc pokoi");
        map.put(PHONE1, "Telefon1");
        map.put(PHONE2, "Telefon2");
        map.put(HOME_PHON, "Domowy");
        map.put(GG, "GG");
        map.put(SKYPE, "Skype");
        map.put(FORENAME, "Imię");
        map.put(SURNAME, "Nazwisko");
        map.put(NIP, "NIP");
        map.put(PESEL, "PESEL");
        map.put(BIRTH_DAT, "Data urodzenia");
        map.put(START_DAT, "Data rozpoczęcia");
        map.put(END_DATE, "Data zakończenia");
        map.put(CLIENT_ID, "Powiązany client");
        map.put(SQL_CONTACT_ID_PK, "Powiązany kontakt");
        map.put(PROPERTY_ID, "Powiązana posiadłość");
        map.put(SQL_ADDRESS_ID_PK, "Powiązany adres");
        map.put(SQL_ROOM_ID_PK, "Powiązany pokój");
        map.put(SQL_WORK_ID_PK, "Powiązana usługa");
        map.put(WORK_SET_ID, "Powiązany zestaw prac");
        map.put(TABLE_CLIENT, "Klient");
        map.put(TABLE_CONTACT, "Kontakt");
        map.put(TABLE_PROPERTY, "Posiadłość");
        map.put(TABLE_ADDRESS, "Adress");
        map.put(TABLE_ROOM, "Pokój");
        map.put(TABLE_WORK, "Usługa");
        map.put(TABLE_WORK_SET, "Zestaw prac");
        return map;
    }
    public static String getTranslate(String text) {
        return text.isEmpty() ? "" : translateMap.get(text);
    }
}
