package de.paulbrejla.keycloak.domain;

public record ExternalUser(String username, String email, String password, String referenceKey) {}
