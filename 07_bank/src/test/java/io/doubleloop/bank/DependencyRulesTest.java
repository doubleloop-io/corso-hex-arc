package io.doubleloop.bank;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.syntax.ArchRuleDefinition;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled
class DependencyRulesTest {

  private final JavaClasses classes = new ClassFileImporter().importPackages(DependencyRulesTest.class.getPackageName());

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
        .should().resideInAPackage(DependencyRulesTest.class.getPackageName())
        .check(classes);
  }
}
