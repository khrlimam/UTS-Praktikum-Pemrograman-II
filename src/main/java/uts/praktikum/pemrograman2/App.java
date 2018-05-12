package uts.praktikum.pemrograman2;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import uts.praktikum.pemrograman2.utils.DBMigrations;

public class App extends Application {

  public static Stage MAIN_STAGE;

  @Override
  public void start(Stage primaryStage) throws Exception {
    MAIN_STAGE = primaryStage;
    DBMigrations.migrateUp();
    Parent root = FXMLLoader.load(getClass().getResource("/uis/main.fxml"));
    primaryStage.setTitle(Constants.APP_TITLE);
    primaryStage.setScene(new Scene(root, 600, 600));
    primaryStage.setMaxWidth(600d);
    primaryStage.setMinWidth(650d);
    primaryStage.setMaxHeight(600d);
    primaryStage.setMinHeight(650d);
    primaryStage.show();
  }

  public static void main(String[] args) {
    launch(args);
  }
}
