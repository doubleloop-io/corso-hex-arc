package io.doubleloop.problems.adapter;

import io.doubleloop.problems.domain.Employee;
import io.doubleloop.problems.domain.EmployeeRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

@Component
public class CsvFileEmployeeRepository implements EmployeeRepository {
  private final String filePath;

  public CsvFileEmployeeRepository(@Value("${app.employee.file}") String filePath) {
    this.filePath = filePath;
  }

  @Override
  public List<Employee> readEmployees() throws IOException {
    return Files.readAllLines(Paths.get(filePath))
        .stream()
        .skip(1) // skip header
        .map(CsvFileEmployeeRepository::parseLine)
        .toList();
  }

  private static Employee parseLine(String str) {
    final var parts = str.split(", ");
    return new Employee(parts[1], parts[0], parts[2], parts[3]);
  }
}
