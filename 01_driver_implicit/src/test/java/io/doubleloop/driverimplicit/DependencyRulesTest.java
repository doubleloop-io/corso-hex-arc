package io.doubleloop.driverimplicit;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class DependencyRulesTest {

  private final JavaClasses classes = new ClassFileImporter().importPackages(DependencyRulesTest.class.getPackageName());

  @Disabled("Run after moving classes into various packages")
  @Test
  void domainShouldNotDependOnAdapter() {

    ArchRuleDefinition.noClasses()
        .that().resideInAPackage("..domain..")
        .should().dependOnClassesThat().resideInAPackage("..adapter..")
        .check(classes);
  }

  @Disabled("Run after moving classes into various packages")
  @Test
  void appStayInTheRootPackage() {
    ArchRuleDefinition
        .theClass(Application.class)
        .should().resideInAPackage(DependencyRulesTest.class.getPackageName())
        .check(classes);
  }
}
