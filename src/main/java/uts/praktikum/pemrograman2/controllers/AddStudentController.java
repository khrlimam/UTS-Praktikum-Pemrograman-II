package uts.praktikum.pemrograman2.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import lombok.Data;
import uts.praktikum.pemrograman2.customui.NumberTextField;
import uts.praktikum.pemrograman2.db.Connection;
import uts.praktikum.pemrograman2.eventbus.Bus;
import uts.praktikum.pemrograman2.eventbus.events.StudentEvent;
import uts.praktikum.pemrograman2.models.Student;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

@Data
public class AddStudentController implements Initializable {

  @FXML
  private NumberTextField tfNim;

  @FXML
  private TextField tfNama;

  @Override
  public void initialize(URL location, ResourceBundle resources) {

  }

  @FXML
  public void saveNewStudent() {
    if (tfNim.getText().trim().length() <= 0 || tfNama.getText().trim().length() <= 0) return;

    Student student = new Student();
    student.setNama(tfNama.getText());
    student.setNim(tfNim.getText());
    try {
      Student.Dao().create(student);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    Bus.getInstance().post(new StudentEvent(student));
    tfNim.clear();
    tfNama.clear();
    try {
      Connection.getInstance().close();
    } catch (IOException e) {
      e.printStackTrace();
    }
  }
}
