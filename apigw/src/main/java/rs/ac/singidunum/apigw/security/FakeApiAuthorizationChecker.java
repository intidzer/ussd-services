package rs.ac.singidunum.apigw.security;

import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service("fake")
public class FakeApiAuthorizationChecker implements ApiKeyAuthorizationChecker{

    private final static Map<String, List<String>> keys = Map.of(
        "supersecure", List.of("customer")
    );

    @Override
    public boolean isAuthorized(String key, String application) {
        return keys.getOrDefault(key, List.of())
            .stream()
            .anyMatch(k -> k.contains(application));
    }
}
