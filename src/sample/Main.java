package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.jetbrains.annotations.NotNull;

public class Main extends Application {

    @Override
    public void start(@NotNull Stage primaryStage) throws Exception {
        String url = "jdbc:sqlserver://localhost:49865;databaseName=Renovation";
        String login = "sa";
        String password = "123";

        DBModel dbModel = new DBModel();
        DBController dbController = new DBController(dbModel);
        dbController.loadDB(url, login, password);
        System.out.println("DB loaded or not. ");
        DBViewController.setController(dbController);
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        Scene scene = new Scene(root, 600,700);
        primaryStage.setTitle("System bazodanowy dla wykończeniówki");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    public static void main(String[] args) {
        launch(args);
    }
}
