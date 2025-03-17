plugins {
    id("java")
}

group = "de.paulbrejla"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
    compileOnly("org.keycloak:keycloak-model-legacy:22.0.0")
    compileOnly("org.keycloak:keycloak-server-spi:22.0.0")
    compileOnly("org.keycloak:keycloak-server-spi-private:22.0.0")
    compileOnly("org.keycloak:keycloak-core:22.0.0")
    compileOnly("org.keycloak:keycloak-services:22.0.0")
}

tasks.test {
    useJUnitPlatform()
}