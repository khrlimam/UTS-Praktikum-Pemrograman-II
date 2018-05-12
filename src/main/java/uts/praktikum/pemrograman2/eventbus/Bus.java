package uts.praktikum.pemrograman2.eventbus;

import com.google.common.eventbus.EventBus;

public class Bus {
  private static EventBus bus;

  public static EventBus getInstance() {
    if (bus == null)
      bus = new EventBus();
    return bus;
  }

}
