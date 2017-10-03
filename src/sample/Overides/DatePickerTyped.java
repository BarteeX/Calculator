package sample.Overides;

import javafx.scene.control.DatePicker;

import java.time.LocalDate;

/**
 * Created by BarteeX on 2017-09-24.
 */
public class DatePickerTyped extends DatePicker {
    private String tableName;
    private String propName;

    public DatePickerTyped() {
        super();
    }
    public DatePickerTyped(LocalDate date) {
        super(date);
    }

    public String getPropName() {
        return propName;
    }

    public void setPropName(String propName) {
        this.propName = propName;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
