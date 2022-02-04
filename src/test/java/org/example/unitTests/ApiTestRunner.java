package org.example.unitTests;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

/**
 * @author Semih Saydam
 * @since 4.02.2022
 */
@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"org.example.step"},
        features = {"src/test/java/org/example/features/ApiRequest.feature"}
)
public class ApiTestRunner {

}
