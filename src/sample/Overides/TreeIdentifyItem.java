package sample.Overides;

import javafx.scene.control.TreeItem;

/**
 * Created by BarteeX on 2017-09-15.
 */
public class TreeIdentifyItem<T> extends TreeItem {
    private String tableName = null;
    private String id = null;

    public TreeIdentifyItem(String name) {
        super(name);
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
