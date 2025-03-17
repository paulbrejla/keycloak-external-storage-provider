package de.paulbrejla.keycloak.provider;

import de.paulbrejla.keycloak.infrastructure.ExternalUserWs;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.storage.UserStorageProviderFactory;

public class ExternalUserStorageProviderFactory implements UserStorageProviderFactory<ExternalUserStorageProvider> {
    private ExternalUserWs externalUserWs;

    @Override
    public ExternalUserStorageProvider create(KeycloakSession keycloakSession, ComponentModel componentModel) {
        this.externalUserWs = new ExternalUserWs(keycloakSession);
        return new ExternalUserStorageProvider(keycloakSession, componentModel, this.externalUserWs);
    }

    @Override
    public String getId() {
        return "external-user-storage";
    }
}
