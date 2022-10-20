package airhacks.lambda.greetings.boundary;

import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPEvent;
import com.amazonaws.services.lambda.runtime.events.APIGatewayV2HTTPResponse;

public class HttpListener {

    static String message = System.getenv("message");

    public HttpListener() {
        System.out.println("initialized with configuration: " + message);
    }

    public APIGatewayV2HTTPResponse onHTTPRequest(APIGatewayV2HTTPEvent event) {
        System.out.println(event.getHeaders());
        System.out.println(event.getRawPath());
        System.out.println(event.getHeaders());
        System.out.println(event.getBody());

        return APIGatewayV2HTTPResponse.builder()
                .withStatusCode(200)
                .withBody("hello, from POJO " + System.currentTimeMillis())
                .build();
    }

}
