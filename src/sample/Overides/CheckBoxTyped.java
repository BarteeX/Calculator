package sample.Overides;

import javafx.scene.control.CheckBox;

import java.time.LocalDate;

/**
 * Created by BarteeX on 2017-09-24.
 */
public class CheckBoxTyped extends CheckBox {
    private String tableName;
    private String propName;

    public CheckBoxTyped() {
        super();
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
