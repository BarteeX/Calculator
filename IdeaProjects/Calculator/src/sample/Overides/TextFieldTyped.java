package sample.Overides;

import javafx.scene.control.*;


/**
 * Created by BarteeX on 2017-09-24.
 */
public class TextFieldTyped extends TextField {
    private String tableName;
    private String propName;

    public TextFieldTyped() {
        super();
    }
    public TextFieldTyped(String text) {
        super(text);
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
