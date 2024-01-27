package de.neonique.architecture;

import com.tngtech.archunit.core.importer.ImportOption;
import com.tngtech.archunit.junit.AnalyzeClasses;
import com.tngtech.archunit.junit.ArchTest;
import com.tngtech.archunit.lang.ArchRule;

import static com.tngtech.archunit.library.Architectures.layeredArchitecture;
@AnalyzeClasses(packages = "de.neonique", importOptions = {
        ImportOption.DoNotIncludeTests.class})
public class TestArch {

    @ArchTest
    ArchRule layerTest = layeredArchitecture()
            .consideringAllDependencies()
            //.layer("UI").definedBy("..ui..")
            .layer("Service").definedBy("..service..")
            .layer("Persistence").definedBy("..persistence..")

            //.whereLayer("UI").mayNotBeAccessedByAnyLayer()
            //.whereLayer("Service").mayOnlyBeAccessedByLayers("UI")
            .whereLayer("Persistence").mayOnlyBeAccessedByLayers("Service");

}
