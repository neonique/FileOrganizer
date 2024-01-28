package de.neonique.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;
import de.neonique.stereotypes.Root;

import static com.tngtech.archunit.lang.syntax.ArchRuleDefinition.classes;
import static com.tngtech.archunit.library.Architectures.layeredArchitecture;

//Will work better with Spring or likewise framework
//Maybe rework to use onionArchitecture()
@AnalyzeClasses(packages = "de.neonique", importOptions = {
        ImportOption.DoNotIncludeTests.class})
public class TestArch {
    @ArchTest
    ArchRule layerTest = layeredArchitecture()
            .consideringAllDependencies()
            .layer("Controller").definedBy("..controller..")
            .layer("Service").definedBy("..service..")
            .layer("Persistence").definedBy("..persistence..")

            .whereLayer("Controller").mayNotBeAccessedByAnyLayer()
            .whereLayer("Service").mayOnlyBeAccessedByLayers("Controller", "Persistence");
            //.whereLayer("Persistence").mayNotBeAccessedByAnyLayer();
    @ArchTest
    ArchRule root = classes().that().areAnnotatedWith(Root.class)
            .should().onlyBeAccessed().byClassesThat().resideInAPackage("..controller..");
}
