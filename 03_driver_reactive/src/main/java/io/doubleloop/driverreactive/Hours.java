package io.doubleloop.driverreactive;

import java.util.Objects;

public class Hours {
  private final int value;

  public Hours(int value) {
    this.value = value;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    Hours hours = (Hours) o;
    return value == hours.value;
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(value);
  }

  @Override
  public String toString() {
    return "Hours{" +
        "value=" + value +
        '}';
  }
}
