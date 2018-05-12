package uts.praktikum.pemrograman2.controllers;

import com.google.common.eventbus.Subscribe;
import com.j256.ormlite.dao.Dao;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.scene.input.KeyEvent;
import lombok.Data;
import uts.praktikum.pemrograman2.eventbus.Bus;
import uts.praktikum.pemrograman2.eventbus.events.StudentEvent;
import uts.praktikum.pemrograman2.models.Student;
import uts.praktikum.pemrograman2.utils.Utils;

import java.net.URL;
import java.sql.SQLException;
import java.util.Optional;
import java.util.ResourceBundle;

@Data
public class TableStudentController implements Initializable {

  private Dao<Student, Integer> studentDao = Student.Dao();
  private ObservableList<Student> tblStudentsDataSource = FXCollections.observableArrayList(studentDao.queryForAll());

  public TableStudentController() throws SQLException {
  }

  @FXML
  private TableView<Student> tblStudents;

  @FXML
  private TableColumn<Student, String> nimColumn, namaColumn;

  @Override
  public void initialize(URL location, ResourceBundle resources) {
    Bus.getInstance().register(this);
    tblStudents.setItems(tblStudentsDataSource);
    nimColumn.setCellValueFactory(new PropertyValueFactory<>("nim"));
    namaColumn.setCellValueFactory(new PropertyValueFactory<>("nama"));

    nimColumn.setCellFactory(TextFieldTableCell.forTableColumn());
    namaColumn.setCellFactory(TextFieldTableCell.forTableColumn());

    nimColumn.setOnEditCommit(event -> {
      Student selectedStudent = tblStudents.getSelectionModel().getSelectedItem();
      selectedStudent.setNim(event.getNewValue());
      try {
        studentDao.update(selectedStudent);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    });

    namaColumn.setOnEditCommit(event -> {
      Student selectedStudent = tblStudents.getSelectionModel().getSelectedItem();
      selectedStudent.setNama(event.getNewValue());
      try {
        studentDao.update(selectedStudent);
      } catch (SQLException e) {
        e.printStackTrace();
      }
    });
  }

  @Subscribe
  public void onNewStudent(StudentEvent event) {
    Student newStudent = event.getStudent();
    tblStudentsDataSource.add(newStudent);
  }

  @FXML
  private void deleteData(KeyEvent keyEvent) {
    switch (keyEvent.getCode()) {
      case DELETE:
        Optional<ButtonType> deleteConfirmation = Utils.deleteConfirmation().showAndWait();
        boolean yes = deleteConfirmation.get().getButtonData().equals(ButtonBar.ButtonData.OK_DONE);
        if (yes) {
          Student selectedStudent = tblStudents.getSelectionModel().getSelectedItem();
          try {
            tblStudentsDataSource.remove(selectedStudent);
            studentDao.delete(selectedStudent);
          } catch (SQLException e) {
            e.printStackTrace();
          }
        }
        break;
    }
  }

}