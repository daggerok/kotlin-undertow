import com.google.gson.Gson;
import io.undertow.Undertow;
import io.undertow.server.HttpHandler;
import io.undertow.util.Headers;

import static java.util.Collections.singletonMap;

public class HttpHandlerApp {
    public static void main(String[] args) {

      Gson gson = new Gson();

      HttpHandler httpHandler = exchange -> {
        exchange.getResponseHeaders().put(Headers.CONTENT_TYPE, "application/json");
        exchange.getResponseSender().send(gson.toJson(singletonMap("message", "Hello handler!")));
      };

      Undertow.builder()
              .addHttpListener(8080, "127.0.0.1")
              .setHandler(httpHandler)
              .build()
              .start();
    }
}
