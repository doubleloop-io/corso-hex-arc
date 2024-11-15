package io.doubleloop.driverreactive;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Test;

class DependencyRulesTest {

  private final String ROOT_PACKAGE = "io.doubleloop.driverreactive";

  private final JavaClasses classes = new ClassFileImporter().importPackages(ROOT_PACKAGE);

  @Test
  void domainShouldNotDependOnAdapter() {
    ArchRuleDefinition.noClasses()
        .that().resideInAPackage("..domain..")
        .should().dependOnClassesThat().resideInAPackage("..adapter..")
        .check(classes);
  }

  @Test
  void appStayInTheRootPackage() {
    ArchRuleDefinition
        .theClass(Application.class)
        .should().resideInAPackage(ROOT_PACKAGE)
        .check(classes);
  }
}
