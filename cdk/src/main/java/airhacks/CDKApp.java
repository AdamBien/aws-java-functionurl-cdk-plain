package airhacks;

import java.util.Map;

import airhacks.functionurl.boundary.FunctionURLStack;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Tags;

public class CDKApp {

    public static void main(final String[] args) {

        var app = new App();

        Tags.of(app).add("project", "POJO Function URL");
        Tags.of(app).add("environment", "development");
        Tags.of(app).add("application", "functionurl-lambda");

        new FunctionURLStack.Builder(app, "airhacks")
                .configuration(Map.of("message", "hello, boilerplate"))
                .build();
        app.synth();
    }
}
