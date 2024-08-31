plugins {
    id("java")
    id("application")
}

group = "com.learning"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    // HTTP Client for making API requests to OpenAI
    implementation("org.apache.httpcomponents.client5:httpclient5:5.2")

    // JSON library to handle JSON parsing (OpenAI response handling)
    implementation("com.fasterxml.jackson.core:jackson-databind:2.15.2")

    // JUnit for testing
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")

    implementation("org.slf4j:slf4j-api:2.0.0-alpha1")
    implementation("org.slf4j:slf4j-simple:2.0.0-alpha1") // or any other SLF4J binding

}

tasks.test {
    useJUnitPlatform()
}

// Specify the main class for the application
application {
    mainClass.set("com.learning.SubtitleCreator")
}
