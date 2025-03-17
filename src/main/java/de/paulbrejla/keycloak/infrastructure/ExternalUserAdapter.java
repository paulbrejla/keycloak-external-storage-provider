package de.paulbrejla.keycloak.infrastructure;

import de.paulbrejla.keycloak.domain.ExternalUser;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.SubjectCredentialManager;
import org.keycloak.models.UserModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageUtil;
import org.keycloak.storage.adapter.AbstractUserAdapter;
import org.keycloak.storage.federated.UserFederatedStorageProvider;

import java.util.stream.Stream;

public class ExternalUserAdapter extends AbstractUserAdapter {

    private final ExternalUser user;

    public ExternalUserAdapter(KeycloakSession session, RealmModel realm, ComponentModel storageProviderModel, ExternalUser user) {
        super(session, realm, storageProviderModel);
        this.storageId = new StorageId(storageProviderModel.getId(), user.username());
        this.user = user;
    }

    @Override
    public String getEmail() {
        return user.email();
    }

    @Override
    public String getUsername() {
        return user.username();
    }

    @Override
    public SubjectCredentialManager credentialManager() {
        return null;
    }

    @Override
    public Stream<String> getRequiredActionsStream() {
        return getFederatedStorage().getRequiredActionsStream(realm, this.getId());
    }

    @Override
    public void addRequiredAction(String action) {
        getFederatedStorage().addRequiredAction(realm, this.getId(), action);
    }

    @Override
    public void removeRequiredAction(String action) {
        getFederatedStorage().removeRequiredAction(realm, this.getId(), action);
    }
    UserFederatedStorageProvider getFederatedStorage() {
        return UserStorageUtil.userFederatedStorage(session);
    }

    @Override
    public void addRequiredAction(RequiredAction action) {
        getFederatedStorage().addRequiredAction(realm, this.getId(), action.name());
    }

    @Override
    public void removeRequiredAction(RequiredAction action) {
        getFederatedStorage().removeRequiredAction(realm, this.getId(), action.name());
    }

    @Override
    public String getId() {
        return super.getId();
    }
}