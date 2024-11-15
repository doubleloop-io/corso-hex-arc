package io.doubleloop.driverreactive.domain;

import java.util.Objects;
import java.util.UUID;

public class UserId {

  private final UUID value;

  public UserId(UUID value) {
    this.value = value;
  }

  public UUID getValue() {
    return value;
  }

  @Override
  public boolean equals(Object o) {
    if (o == null || getClass() != o.getClass()) return false;
    UserId userId = (UserId) o;
    return Objects.equals(value, userId.value);
  }

  @Override
  public int hashCode() {
    return Objects.hashCode(value);
  }

  @Override
  public String toString() {
    return "UserId{" +
        "value=" + value +
        '}';
  }
}
