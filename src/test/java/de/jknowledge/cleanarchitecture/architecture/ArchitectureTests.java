package de.jknowledge.cleanarchitecture.architecture;

import com.tngtech.archunit.core.domain.JavaClass;
import com.tngtech.archunit.core.domain.JavaClasses;
import com.tngtech.archunit.core.importer.ClassFileImporter;
import com.tngtech.archunit.lang.ArchCondition;
import com.tngtech.archunit.lang.ArchRule;
import com.tngtech.archunit.lang.ConditionEvents;
import com.tngtech.archunit.lang.SimpleConditionEvent;
import com.tngtech.archunit.library.dependencies.SlicesRuleDefinition;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
import static java.util.stream.Collectors.toSet;

public class ArchitectureTests {

    private final JavaClasses classedOfRootPackage = new ClassFileImporter().importPackages("de.jknowledge.cleanarchitecture");

    private final JavaClasses eventListenerClasses = new ClassFileImporter().importPackages("de.jknowledge.cleanarchitecture.application.eventlistener");

    private final ArchCondition<JavaClass> notHaveMoreThanOneRepositoryDependency =
            new ArchCondition<>("check if classes depend on more than one repository") {
                @Override
                public void check(JavaClass item, ConditionEvents events) {
                    Set<String> referencesRepositories = item
                            .getDirectDependenciesFromSelf()
                            .stream()
                            .filter(c -> c.getTargetClass().getPackageName().contains("repository"))
                            .map(c -> c.getTargetClass().getName())
                            .collect(toSet());
                    if(referencesRepositories.size() > 1) {
                        events.add(SimpleConditionEvent.violated(item, item.getDescription() + "has dependencies to more than one repositories."));
                    }
                }
            };

    @Test
    public void testLayers() {
        ArchRule rule = layeredArchitecture()
                .consideringOnlyDependenciesInLayers()
                .layer("Controller").definedBy("..controller..")
                .layer("Domain").definedBy("..domain..")
                .layer("EventListener").definedBy("..eventlistener..")
                .layer("Repository").definedBy("..repository..")
                .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
                .whereLayer("EventListener").mayNotBeAccessedByAnyLayer()
                .whereLayer("Domain").mayNotAccessAnyLayer();
        rule.check(classedOfRootPackage);
    }

    @Test
    public void testDomainModuleNoDependencieBetweenAggregates() {
        SlicesRuleDefinition
                .slices()
                .matching("(de.jknowledge.cleanarchitecture.domain.aggregate.*)")
                .should()
                .notDependOnEachOther()
                .check(classedOfRootPackage);
    }


    @Test
    public void testRepositoryClassesShouldEndWithRepository() {
        classes()
                .that()
                .resideInAPackage("..repository..")
                .should()
                .haveSimpleNameEndingWith("Repository")
                .check(classedOfRootPackage);
    }

    @Test
    public void testEventListenerDependyOnlyOnOneRepository() {
        classes().should(notHaveMoreThanOneRepositoryDependency).check(eventListenerClasses);
    }



}
