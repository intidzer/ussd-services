package rs.ac.singidunum.apigw.security;

import java.util.List;
import lombok.AllArgsConstructor;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.cloud.gateway.route.Route;
import org.springframework.cloud.gateway.support.ServerWebExchangeUtils;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
@AllArgsConstructor
public class ApiKeyAuthorisationFilter implements GlobalFilter {

    private final FakeApiAuthorizationChecker authorizationChecker;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        System.out.println("ApiKeyAuthorisationFilter... checking the key");

        Route attribute = exchange.getAttribute(ServerWebExchangeUtils.GATEWAY_ROUTE_ATTR);
        String application = attribute.getId();

        List<String> apiKey = exchange.getRequest().getHeaders().get("ApiKey");

        if (application == null || apiKey == null || apiKey.isEmpty() || !authorizationChecker.isAuthorized(apiKey.get(0), application)) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "You're not authorized");
        }


        System.out.println("Key val: " + apiKey.get(0));

        return chain.filter(exchange);
    }
}
