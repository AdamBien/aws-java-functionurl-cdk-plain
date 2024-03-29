package airhacks.lambda.control;

import java.util.Map;

import software.amazon.awscdk.Duration;
import software.amazon.awscdk.services.lambda.Architecture;
import software.amazon.awscdk.services.lambda.Code;
import software.amazon.awscdk.services.lambda.IFunction;
import software.amazon.awscdk.services.lambda.Function;
import software.amazon.awscdk.services.lambda.Runtime;
import software.constructs.Construct;

public class POJOLambda extends Construct{

    static Map<String, String> configuration = Map.of(
            "message", "hello, quarkus as AWS Lambda",
            "JAVA_TOOL_OPTIONS", "-XX:+TieredCompilation -XX:TieredStopAtLevel=1");
    static String lambdaHandler = "airhacks.lambda.greetings.boundary.HttpListener::onHTTPRequest";
    static int memory = 1024; //~0.5 vCPU
    static int timeout = 10;
    IFunction function;

    public POJOLambda(Construct scope,String functionName) {
        super(scope, "POJOLambda");
        this.function = createFunction(functionName, lambdaHandler, configuration, memory, timeout);
    }

    IFunction createFunction(String functionName,String functionHandler, Map<String,String> configuration, int memory, int timeout) {
        return Function.Builder.create(this, functionName)
                .runtime(Runtime.JAVA_21)
                .architecture(Architecture.ARM_64)
                .code(Code.fromAsset("../lambda/target/function.jar"))
                .handler(functionHandler)
                .memorySize(memory)
                .functionName(functionName)
                .environment(configuration)
                .timeout(Duration.seconds(timeout))
                .build();
    }    

    public IFunction getFunction(){
        return this.function;
    }
}