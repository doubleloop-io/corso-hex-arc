package io.doubleloop.driverexplicit;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class DemoTest {

  @Test
  void tryMe() {
    assertThat("ciao").isEqualTo("ciao");
  }

}
