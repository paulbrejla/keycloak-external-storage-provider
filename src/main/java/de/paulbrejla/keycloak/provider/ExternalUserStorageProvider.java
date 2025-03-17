package de.paulbrejla.keycloak.provider;

import de.paulbrejla.keycloak.domain.ExternalUser;
import de.paulbrejla.keycloak.infrastructure.ExternalUserAdapter;
import de.paulbrejla.keycloak.infrastructure.ExternalUserWs;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.models.*;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;

public class ExternalUserStorageProvider implements UserStorageProvider,
        UserLookupProvider,
        CredentialInputValidator {
    private final KeycloakSession session;
    private final ComponentModel model;
    private ExternalUserWs externalUserWs;

    public ExternalUserStorageProvider(KeycloakSession session, ComponentModel model, ExternalUserWs externalUserWsV1) {
        this.session = session;
        this.model = model;
        this.externalUserWs = externalUserWsV1;
    }

    @Override
    public void close() {

    }

    @Override
    public boolean supportsCredentialType(String credentialType) {
        return true;
    }

    @Override
    public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {
        return true;
    }

    @Override
    public boolean isValid(RealmModel realm, UserModel user, CredentialInput credentialInput) {
        return true;
    }

    @Override
    public UserModel getUserById(RealmModel realm, String id) {
        ExternalUser externalUser = this.externalUserWs.fetchUserByReferenceKey(id);

        return new ExternalUserAdapter(session, realm, this.model, externalUser);
    }

    @Override
    public UserModel getUserByUsername(RealmModel realm, String username) {
        ExternalUser externalUser = this.externalUserWs.fetchUserByReferenceKey(username);

        return new ExternalUserAdapter(session, realm, this.model, externalUser);
    }

    @Override
    public UserModel getUserByEmail(RealmModel realm, String email) {
        ExternalUser externalUser = this.externalUserWs.fetchUserByReferenceKey(email);

        return new ExternalUserAdapter(session, realm, this.model, externalUser);
    }
}
