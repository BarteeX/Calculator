package sample;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import sample.Overides.TextFieldTreeCellImpl;
import sample.Overides.TreeIdentifyItem;
import sample.Tables.DataBaseTable;

import java.util.*;

import static sample.ColumnNames.CLIENT_ID;
import static sample.ColumnNames.PROPERTY_ID;
import static sample.ColumnNames.WORK_SET_ID;
import static sample.DBController.*;


/**
 * Created by BarteeX on 2017-08-28.
 */
public class DBViewController {
    private static DBController dbController = null;
    public static void setController(DBController dbController){
        DBViewController.dbController = dbController;
    }

    @FXML private MenuItem loadButton;
    @FXML private AnchorPane anchor;
    @FXML private AnchorPane anchor4fields;
    @FXML private TreeView treeView;
    @FXML private Tab tab;
    @FXML private ProgressIndicator spinner;


    private TreeIdentifyItem<String> makeConnection(DataBaseTable table, TreeIdentifyItem<String> root) {
        TreeIdentifyItem<String> item = new TreeIdentifyItem<>(table.getIdn());
        item.setId(table.getId());
        item.setTableName(table.getTableName());
        root.getChildren().add(item);
        return item;
    }

    public void showAcordion(HashMap<String, List<DataBaseTable>> tables) {
        List<DataBaseTable> clients = tables.get(TABLE_CLIENT);
        List<DataBaseTable> contacts = tables.get(TABLE_CONTACT);
        List<DataBaseTable> properties = tables.get(TABLE_PROPERTY);
        List<DataBaseTable> addresses = tables.get(TABLE_ADDRESS);
        List<DataBaseTable> rooms = tables.get(TABLE_ROOM);
        List<DataBaseTable> workSets = tables.get(TABLE_WORK_SET);
        List<DataBaseTable> works = tables.get(TABLE_WORK);

        TreeIdentifyItem<String> clientsRoot = new TreeIdentifyItem<>("Klienci:");
        clientsRoot.setId("add_" + TABLE_CLIENT);

        clients.forEach((DataBaseTable client) -> {
            TreeIdentifyItem<String> clientItem = makeConnection(client, clientsRoot);
            TreeIdentifyItem<String> contactsRoot = new TreeIdentifyItem<>("Kontakty:");
            contactsRoot.setId("add_" + TABLE_CONTACT);
            clientItem.getChildren().add(contactsRoot);

            String clientId = client.getId();
            contacts.forEach(contact -> {
                if (contact.getAllFields().get(CLIENT_ID).equals(clientId)) {
                    TreeIdentifyItem<String> contactItem = makeConnection(contact,contactsRoot);
                }
            });
            TreeIdentifyItem<String> propertyRoot = new TreeIdentifyItem<>("Posiadłości:");
            propertyRoot.setId("add_" + TABLE_PROPERTY);
            clientItem.getChildren().add(propertyRoot);
            properties.forEach(property -> {
                if(property.getAllFields().get(CLIENT_ID).equals(clientId)) {
                    String propertyId = property.getId();
                    TreeIdentifyItem<String> propertyItem = makeConnection(property, propertyRoot);
                    TreeIdentifyItem<String> addressRoot = new TreeIdentifyItem<>("Adres:");
                    propertyItem.getChildren().add(addressRoot);
                    addressRoot.setId("add_" + TABLE_ADDRESS);
                    addresses.forEach(address -> {
                        if(address.getAllFields().get(PROPERTY_ID).equals(propertyId)) {
                            TreeIdentifyItem<String> addressItem = makeConnection(address, addressRoot);
                        }
                    });
                    TreeIdentifyItem<String> roomsRoot = new TreeIdentifyItem<>("Pokoje");
                    propertyItem.getChildren().add(roomsRoot);
                    roomsRoot.setId("add_" + TABLE_ROOM);
                    rooms.forEach(room -> {
                        if(room.getAllFields().get(PROPERTY_ID).equals(propertyId)) {
                            TreeIdentifyItem<String> roomItem =  makeConnection(room, roomsRoot);
                            workSets.forEach(workSet -> {
                                String workSetId = workSet.getId();
                                if(room.getAllFields().get(WORK_SET_ID).equals(workSetId)) {
                                    TreeIdentifyItem<String> workSetItem = makeConnection(workSet, roomItem);
                                    works.forEach(work -> {
                                        if(work.getAllFields().get(WORK_SET_ID).equals(workSetId)) {
                                            TreeIdentifyItem<String> workItem = makeConnection(work, workSetItem);
                                        }
                                    });
                                }
                            });
                        }
                    });
                }
            });
        });
        treeView.setRoot(clientsRoot);
        treeView.setCellFactory(param -> new TextFieldTreeCellImpl(dbController, anchor4fields, tab, this));

    }

    public void clearAnchor4Fields() {
        if(anchor4fields.getChildren().size() > 0) {
            anchor4fields.getChildren().clear();
        }
    }

    @FXML protected void onAction(ActionEvent event) {
        clearAnchor4Fields();
        spinner.setVisible(true);
        //dbController.reloadDb();
        showAcordion(dbController.getAllTables());
        spinner.setVisible(false);
    }

}
