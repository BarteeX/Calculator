package sample.Tables;

import sample.DBController;

import java.util.HashMap;

import static sample.ColumnNames.*;

/**
 * Created by BarteeX on 2017-08-26.
 */
public class WorkSet extends DataBaseTable {
    @Override
    public void setAllFields() {
        super.setAllFields();
    }

    @Override
    public void update(HashMap<String, String> obj) {
        super.update(obj);
        setAllFields();
    }

    public WorkSet(HashMap<String, String> workSet) {
        super(workSet);
    }
}
