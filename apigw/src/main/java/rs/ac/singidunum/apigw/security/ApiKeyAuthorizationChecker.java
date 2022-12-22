package rs.ac.singidunum.apigw.security;

public interface ApiKeyAuthorizationChecker {

    public boolean isAuthorized(String key, String application);

}
