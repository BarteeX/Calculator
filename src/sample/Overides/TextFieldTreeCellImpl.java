package sample.Overides;

import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import sample.*;
import sample.Tables.DataBaseTable;
import sun.reflect.generics.tree.Tree;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.*;

import static sample.ColumnNames.NEW_OBJECT;

/**
 * Created by BarteeX on 2017-09-17.
 */
public final class TextFieldTreeCellImpl extends TreeCell<String> {
    private Label textField;
    private ContextMenu menu;
    private DBController dbController;
    private AnchorPane anchor4fields;
    private Tab tab;
    private MenuItem showItem;
    private MenuItem editItem;
    private MenuItem addItem;
    private Button saveButton;
    private DBViewController dbViewController;

    public TextFieldTreeCellImpl(DBController dbController, AnchorPane anchor4fields, Tab tab, DBViewController dbViewController) {
        this.dbViewController = dbViewController;
        this.tab = tab;
        this.anchor4fields = anchor4fields;
        this.dbController = dbController;

        menu = new ContextMenu();

        showItem = new MenuItem("Prezentuj...");
        showItem.setOnAction(event -> {
            showItem();
        });

        editItem = new MenuItem("Edytuj...");
        editItem.setOnAction(event -> {
            editValues();
        });

        addItem = new MenuItem("Dodaj...");
        addItem.setOnAction(event -> {
            addNewRecord();
        });
        menu.getItems().add(showItem);
        menu.getItems().add(editItem);
        menu.getItems().add(addItem);
    }

    @Override
    public void startEdit() {
        super.startEdit();

        if (textField == null) {
            createTextField();
        }
        setText(null);
        setGraphic(textField);
        //textField.selectAll();
    }

    @Override
    public void cancelEdit() {
        super.cancelEdit();
        setText(getItem());
        setGraphic(getTreeItem().getGraphic());
    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);

        if (empty) {
            setText(null);
            setGraphic(null);
        } else {
            if(((TreeIdentifyItem)getTreeItem()).getIdn() != null && ((TreeIdentifyItem)getTreeItem()).getIdn().contains("add_")){
                showItem.setDisable(true);
                editItem.setDisable(true);
                addItem.setDisable(false);
            } else {
                showItem.setDisable(false);
                editItem.setDisable(false);
                addItem.setDisable(true);
            }
            if (isEditing()) {
                if (textField != null) {
                    textField.setText(getString());
                }
                setText(null);
                setGraphic(textField);
            } else {
                setText(getString());
                setGraphic(getTreeItem().getGraphic());
                setContextMenu(menu);
            }
        }

    }

    public void putMask(ObservableList<Node> children, boolean value) {
        for(Node field : children) {
            if(field instanceof TextField) {
                ((TextField) field).setEditable(!value);
            } else if (field instanceof DatePicker) {
                ((DatePicker) field).setEditable(!value);
            }
            field.setDisable(value);
        }
    }

    public void editValues() {
        showItem();

        ObservableList<Node> anchorChildren = anchor4fields.getChildren();
        if(anchorChildren.size() > 0) {
            GridPane gridPane = (GridPane) anchorChildren.get(0);
            ObservableList<Node> children = gridPane.getChildren();
            putMask(children, false);
            if(!children.contains(saveButton)) {
                initButton();
                Integer rows = 20;
                try {
                    Method method = gridPane.getClass().getDeclaredMethod("getNumberOfRows");
                    method.setAccessible(true);
                    rows = (Integer) method.invoke(gridPane);
                } catch (IllegalAccessException | NoSuchMethodException | InvocationTargetException e) {
                    e.printStackTrace();
                }
                gridPane.add(saveButton, 0 , rows);
            }
        } else {
            System.err.println("Element wasn't abe to edit.");
            System.err.println("IDN = " + ((TreeIdentifyItem)getTreeItem()).getIdn());
            System.err.println("TABLE NAME = " + ((TreeIdentifyItem)getTreeItem()).getTableName());
        }
    }

    public void saveObject(HashMap obj) {
        dbController.update(obj);
    }

    public void stopEditing() {
        ObservableList<Node> anchorChildren = anchor4fields.getChildren();
        GridPane gridPane = (GridPane) anchorChildren.get(0);
        ObservableList<Node> children = gridPane.getChildren();
        Node toDelte = null;
        for(Node node : children) {
            if(node.equals(saveButton)) {
                toDelte = node;
            }
        }
        children.remove(toDelte);
        putMask(children, true);
        dbViewController.showAcordion(dbController.getAllTables());
        for(int i = 0; i < anchor4fields.getChildren().size(); i++) {
            anchor4fields.getChildren().remove(i);
        }
    }

    private void initButton() {
        saveButton = new Button();
        saveButton.setText("Zapisz");
        saveButton.setOnAction(e -> {
            GridPane grid = (GridPane) anchor4fields.getChildren().get(0);
            HashMap obj = new HashMap();
            obj.put("table", ((TreeIdentifyItem)getTreeItem()).getTableName());
            grid.getChildren().forEach(child -> {
                if(child instanceof TextFieldTyped) {
                    obj.put(((TextFieldTyped) child).getPropName(), ((TextFieldTyped) child).getText());
                } else if(child instanceof DatePickerTyped) {
                    obj.put(((DatePickerTyped) child).getPropName(), ((DatePickerTyped) child).getValue().toString());
                } else if(child instanceof CheckBoxTyped) {
                    obj.put(((CheckBoxTyped) child).getPropName(), String.valueOf(((CheckBoxTyped)child).isSelected()));
                }
            });
            saveObject(obj);
            stopEditing();
        });
    }

    private DatePickerTyped initDatePicker(String key, Object value) {
        TreeIdentifyItem treeItem = (TreeIdentifyItem) getTreeItem();
        DatePickerTyped datePicker = new DatePickerTyped();
        datePicker.setValue(LocalDate.parse(value == null ? (String)value : value.toString()));
        datePicker.setPropName(key);
        datePicker.setTableName(treeItem.getTableName());
        datePicker.setTooltip(new Tooltip("Klucz = " + key + "\nWartość = " + value + "\nTabela = " + treeItem.getTableName()));
        datePicker.setStyle("-fx-opacity: 1");
        datePicker.getEditor().setStyle("-fx-opacity: 1");
        return datePicker;
    }

    private TextFieldTyped initTextField(String key, Object value) {
        TreeIdentifyItem treeItem = (TreeIdentifyItem) getTreeItem();
        TextFieldTyped textField = new TextFieldTyped(value == null ? (String)value : value.toString());
        textField.setPropName(key);
        textField.setTableName(treeItem.getTableName());
        textField.setTooltip(new Tooltip("Klucz = " + key + "\nWartość = " + value + "\nTabela = " + treeItem.getTableName()));
        textField.setStyle("-fx-opacity: 1");
        return textField;
    }

    private CheckBoxTyped initCheckBox(String key, Object value) {
        TreeIdentifyItem treeItem = (TreeIdentifyItem) getTreeItem();
        CheckBoxTyped checkBox = new CheckBoxTyped();
        checkBox.setSelected((Boolean)value);
        checkBox.setPropName(key);
        checkBox.setTableName(treeItem.getTableName());
        checkBox.setTooltip(new Tooltip("Klucz = " + key + "\nWartość = " + value + "\nTabela = " + treeItem.getTableName()));
        checkBox.setStyle("-fx-opacity: 1");
        return checkBox;
    }

    private GridPane initGridPane() {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(15,15,15,15));
        grid.setHgap(10);
        grid.setVgap(10);
        return grid;
    }

    private List<String> prepareKeySet(HashMap<String, ?> elementMap) {
        List<String> keysList = new ArrayList<>();
        elementMap.forEach((key, value) -> {
            keysList.add(key);
        });
        Collections.sort(keysList);
        return keysList;
    }

    private void showItem() {
        TreeIdentifyItem treeItem = (TreeIdentifyItem) getTreeItem();
        String idn = treeItem.getIdn();
        String tableName = treeItem.getTableName();

        if(idn != null && tableName != null && !idn.isEmpty() && !tableName.isEmpty()) {
            DataBaseTable elementToPrint = dbController.getById(tableName, idn);
            HashMap<String, ?> elementMap = elementToPrint.getAllFields();
            List<String> keysList = prepareKeySet(elementMap);

            GridPane grid = initGridPane();
            int rowsAmount = (int) (Math.floor(elementMap.size()) + 1);
            int iter = rowsAmount;
            for(String key : keysList) {
                Object value = elementMap.get(key);

                Label label = new Label(DBDictionary.getTranslate(key));
                grid.add(label, 0, rowsAmount - iter);

                if (value != null && value.toString().matches("[\\d]{4}-[\\d]{2}-[\\d]{2}")) {
                    DatePickerTyped datePicker = initDatePicker(key, value);
                    grid.add(datePicker, 1, rowsAmount - iter--);
                } else if (value instanceof Boolean) {
                    CheckBoxTyped checkBox = initCheckBox(key, value);
                    grid.add(checkBox, 1, rowsAmount - iter--);
                } else {
                    TextFieldTyped textField = initTextField(key, value);
                    grid.add(textField, 1 , rowsAmount - iter--);
                }
            }
            anchor4fields.getChildren().clear();
            anchor4fields.getChildren().add(grid);
            tab.setText(DBDictionary.getTranslate(treeItem.getTableName()));
            putMask(grid.getChildren(), true);
        }
    }

    private void appendNewObjectToTree() {
        TreeIdentifyItem item = (TreeIdentifyItem) getTreeItem();
        
    }

    private void addNewRecord() {
        //TODO : dorobić dodawanie nowych rekordów!
        TreeIdentifyItem<String> treeItem = (TreeIdentifyItem<String>) getTreeItem();
        String idn = treeItem.getIdn();
        if(idn.contains("add_")) {
            String tableName = idn.replaceFirst("add_", "");
            TreeIdentifyItem<String> itemToAdd = new TreeIdentifyItem<String>(DBDictionary.getTranslate(tableName));
            itemToAdd.setTableName(tableName);
            itemToAdd.setIdn(NEW_OBJECT);
            int newId = IdController.addNewId(tableName);

            treeItem.getChildren().add(itemToAdd);
        } else  if (!treeItem.isLeaf()){

        }
    }

    private void createTextField() {
        textField = new Label(getString());
    }

    private String getString() {
        return getItem() == null ? "" : getItem().toString();
    }
}
