import com.google.gson.Gson;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.RoutingHandler;
import io.undertow.util.Headers;

import static java.util.Collections.singletonMap;

/*
 * This Java source file was generated by the Gradle 'init' task.
 */
public class RoutingHandlerApp {
    public static void main(String[] args) {

      Gson gson = new Gson();

      HttpHandler httpHandler = exchange -> {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        exchange.getResponseSender().send(gson.toJson(singletonMap("message", "Hola!")));
      };

      RoutingHandler routingHandler = new RoutingHandler()
          .get("/**", httpHandler)
          .post("/**", httpHandler)
          ;

      Undertow.builder()
              .addHttpListener(8080, "127.0.0.1")
              .setHandler(routingHandler)
              .build()
              .start();
    }
}
