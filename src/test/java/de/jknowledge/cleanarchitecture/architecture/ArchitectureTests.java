package de.jknowledge.cleanarchitecture.architecture;

import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchRule;
import org.junit.jupiter.api.Test;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

public class ArchitectureTests {

    @Test
    public void testPackageDependencies() {
        JavaClasses importedClasses = new ClassFileImporter().importPackages("de.jknowledge.cleanarchitecture");
        ArchRule rule = layeredArchitecture()
                .consideringAllDependencies()
                .layer("Controller").definedBy("..controller..")
                .layer("Domain").definedBy("..domain..")
                .layer("EventListener").definedBy("..eventlistener..")
                .layer("Repository").definedBy("..repository..")
                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("EventListener").mayNotBeAccessedByAnyLayer()
                .whereLayer("Domain").mayNotAccessAnyLayer()
                .whereLayer("Repository").mayOnlyBeAccessedByLayers("Controller", "EventListener");
        rule.check(importedClasses);
    }

}
