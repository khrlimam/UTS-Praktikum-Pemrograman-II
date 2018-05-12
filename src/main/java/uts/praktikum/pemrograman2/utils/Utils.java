package uts.praktikum.pemrograman2.utils;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;

public class Utils {
  public static Stage makeDialogStage(FXMLLoader loader, String title, Stage owner) {
    try {
      AnchorPane pane = loader.load();
      Stage stage = new Stage();
      stage.setTitle(title);
      stage.initModality(Modality.WINDOW_MODAL);
      stage.initOwner(owner);
      Scene scene = new Scene(pane);
      stage.setScene(scene);
      return stage;
    } catch (IOException e) {
      e.printStackTrace();
      return null;
    }
  }

  public static Alert setUpDialog(String title, String header, String content, Alert.AlertType type) {
    Alert alert = new Alert(type);
    alert.setTitle(title);
    alert.setHeaderText(header);
    alert.setContentText(content);
    return alert;
  }

  public static Alert deleteConfirmation() {
    return setUpDialog("Konfirmasi", "Yakin ingin menghapus data?", "Hati-hati dengan pilihan anda!", Alert.AlertType.CONFIRMATION);
  }

}
