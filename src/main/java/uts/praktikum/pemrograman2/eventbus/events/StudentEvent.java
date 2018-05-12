package uts.praktikum.pemrograman2.eventbus.events;

import lombok.Data;
import uts.praktikum.pemrograman2.models.Student;

@Data
public class StudentEvent {
  private Student student;

  public StudentEvent(Student student) {
    this.student = student;
  }
}
