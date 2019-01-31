import com.google.gson.Gson;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.server.RoutingHandler;
import io.undertow.server.handlers.NameVirtualHostHandler;
import io.undertow.util.Headers;

import static java.util.Collections.singletonMap;

public class NameVirtualHostRootHandlerApp {
    public static void main(String[] args) {

      Gson gson = new Gson();

      HttpHandler ruHandler = exchange -> {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        exchange.getResponseSender().send(gson.toJson(singletonMap("message", "Превед!")));
      };

      RoutingHandler ruRouter = new RoutingHandler()
          .get("/**", ruHandler);

      HttpHandler enHandler = exchange -> {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        exchange.getResponseSender().send(gson.toJson(singletonMap("message", "Yo!")));
      };

      RoutingHandler enRouter = new RoutingHandler()
          .get("/**", enHandler);

      NameVirtualHostHandler rootHandler = new NameVirtualHostHandler()
          .addHost("ru.my-app.com", ruRouter)
          .addHost("en.my-app.com", enRouter)
          .addHost("my-app.com", enRouter);

      // http :8080 host:my-app.com
      // http :8080 host:ru.my-app.com

      Undertow.builder()
              .addHttpListener(8080, "127.0.0.1", rootHandler)
              .build()
              .start();
    }
}
