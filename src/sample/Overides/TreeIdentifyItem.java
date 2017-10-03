package sample.Overides;

import javafx.scene.control.TreeItem;

/**
 * Created by BarteeX on 2017-09-15.
 */
public class TreeIdentifyItem<T> extends TreeItem {
    private String tableName = null;
    private String idn = null;

    public TreeIdentifyItem(String name) {
        super(name);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getIdn() {
        return idn;
    }

    public void setIdn(String idn) {
        this.idn = idn;
    }
}
