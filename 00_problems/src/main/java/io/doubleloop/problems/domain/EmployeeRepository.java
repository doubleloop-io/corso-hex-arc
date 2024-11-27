package io.doubleloop.problems.domain;

import java.io.IOException;
import java.util.List;

public interface EmployeeRepository {
  List<Employee> readEmployees() throws IOException;
}
