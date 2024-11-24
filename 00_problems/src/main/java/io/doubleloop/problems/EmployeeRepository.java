package io.doubleloop.problems;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class EmployeeRepository {
  private final String filePath;

  public EmployeeRepository(@Value("${app.employee.file}") String filePath) {
    this.filePath = filePath;
  }

  public List<Employee> readEmployees() throws IOException {
    return Files.readAllLines(Paths.get(filePath))
        .stream()
        .skip(1) // skip header
        .map(EmployeeRepository::parseLine)
        .toList();
  }

  private static Employee parseLine(String str) {
    final var parts = str.split(", ");
    return new Employee(parts[1], parts[0], parts[2], parts[3]);
  }
}
