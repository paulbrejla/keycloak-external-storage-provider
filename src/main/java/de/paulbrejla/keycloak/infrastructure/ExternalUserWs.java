package de.paulbrejla.keycloak.infrastructure;

import com.fasterxml.jackson.databind.ObjectMapper;
import de.paulbrejla.keycloak.domain.ExternalUser;
import org.keycloak.broker.provider.util.SimpleHttp;
import org.keycloak.connections.httpclient.HttpClientProvider;
import org.keycloak.models.KeycloakSession;

public class ExternalUserWs {
    private KeycloakSession session;
    private ObjectMapper mapper = new ObjectMapper();

    public ExternalUserWs(KeycloakSession session) {
        this.session = session;
    }

    public ExternalUser fetchUserByReferenceKey(String referenceKey) {
        return this.fetchUser();
    }

    private ExternalUser mockFetchUser() {
        return new ExternalUser("username", "email", "password", "referenceKey", "paul", "firstName" );
    }
    private ExternalUser fetchUser() {
        try {
            SimpleHttp httpClient = SimpleHttp.doGet("https://webhook.works/api/v1/public/webhooks/d397505c-821a-4963-9b66-4eb03f18eea0", this.session);
            ExternalUser externalUser = this.mapper.treeToValue(httpClient.asJson(), ExternalUser.class);
            return externalUser;
        } catch (Exception e) {
            return null;
        }
    }
}
