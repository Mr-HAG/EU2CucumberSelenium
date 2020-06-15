package com.vytrack.runners;


import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(

        plugin = {"json:target/cucumber.json",
                "html:target/default-html-reports",
        "rerun:target/rerun.txt"},
        features = "src/test/resources/features/",
        glue = "com/vytrack/step_definitions",
        dryRun = false,

        // "@driver and @VYT-123" => old syntax -> tags = {"@driver","@VYT-123"} - run if they together
        // "@driver or @store_manager"  -> run each of them
        // "@login and not @wip" --> all login but not wip
        tags = "@smoke"
)
public class CukesRunner {
}
