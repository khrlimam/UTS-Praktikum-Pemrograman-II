package uts.praktikum.pemrograman2.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import uts.praktikum.pemrograman2.App;
import uts.praktikum.pemrograman2.Constants;
import uts.praktikum.pemrograman2.customui.NumberTextField;
import uts.praktikum.pemrograman2.utils.Utils;

import java.net.URL;
import java.util.ResourceBundle;

public class MainController implements Initializable {

  private Stage addStudentDialog;
  private AddStudentController addStudentController;

  @FXML
  private Label lblAppTitle;

  @FXML
  private NumberTextField tfNim;

  @FXML
  private TextField tfNama;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    lblAppTitle.setText(Constants.APP_TITLE);
    FXMLLoader studentLoader = new FXMLLoader(getClass().getResource("/uis/add_student.fxml"));
    addStudentDialog = Utils.makeDialogStage(studentLoader, "Add new student", App.MAIN_STAGE);
    addStudentController = studentLoader.getController();

    addStudentController = new AddStudentController();
    addStudentController.setTfNim(tfNim);
    addStudentController.setTfNama(tfNama);
  }

  @FXML
  private void showFormAddStudent() {
    addStudentDialog.showAndWait();
  }

  @FXML
  private void saveStudent() {
    addStudentController.saveNewStudent();
  }

  @FXML
  private void deleteSelectedRow() {
    System.out.println("deleting selected row");
  }

}
