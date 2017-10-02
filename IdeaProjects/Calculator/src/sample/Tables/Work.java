package sample.Tables;

import sample.DBController;

import java.util.HashMap;

import static sample.ColumnNames.*;
import static sample.ColumnNames.ID;
import static sample.ColumnNames.IDN;

/**
 * Created by BarteeX on 2017-08-26.
 */
public class Work extends DataBaseTable {
    private Float amount;
    private Float price;
    private String workSetId;

    @Override
    public void setAllFields() {
        super.setAllFields();
        allFields.put(AMOUNT, getAmount());
        allFields.put(PRICE, getPrice());
        allFields.put(WORK_SET_ID, getWorkSetId());
    }

    @Override
    public void update(HashMap<String, String> obj) {
        super.update(obj);
        setAmount(obj.get(AMOUNT));
        setPrice(obj.get(PRICE));
        setWorkSetId(obj.get(WORK_SET_ID));
        setAllFields();
    }

    public Work(HashMap<String, String> work) {
        super(work);
    }

    public float getAmount() {
        return amount;
    }

    public float getPrice() {
        return price;
    }

    public String getWorkSetId() {
        return workSetId;
    }

    public void setAmount(String amount) {
        this.amount = Float.parseFloat(amount);
    }

    public void setPrice(String price) {
        this.price = Float.parseFloat(price);
    }

    public void setWorkSetId(String workSetId) {
        this.workSetId = workSetId;
    }
}
