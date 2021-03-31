plugins {
    `java-library`
    `maven-publish`
}

repositories {
    mavenCentral()
}

group = "de.nycode"
version = "1.0.0-SNAPSHOT"

dependencies {
    implementation("org.json:json:20200518")
    implementation("org.slf4j:slf4j-api:1.7.30")
    // Use JUnit test framework
    testImplementation("junit:junit:4.13")
}

java {
    sourceCompatibility = JavaVersion.VERSION_11
    targetCompatibility = JavaVersion.VERSION_11
    withJavadocJar()
    withSourcesJar()
}

publishing {
    val artifactoryUsername = System.getenv("ARTIFACTORY_USERNAME") ?: return@publishing
    val artifactoryPassword = System.getenv("ARTIFACTORY_PASSWORD") ?: return@publishing
    publications {
        create<MavenPublication>("maven") {
            groupId = "de.nycode"
            artifactId = "i18next"
            version = "1.0.0-SNAPSHOT"
            from(components["java"])
        }
    }
    repositories {
        maven {
            val releasesRepoUrl = "https://nycode.jfrog.io/artifactory/nycode-releases/"
            val snapshotsRepoUrl = "https://nycode.jfrog.io/artifactory/nycode-snapshots/"
            url = uri(if (version.toString().endsWith("SNAPSHOT")) snapshotsRepoUrl else releasesRepoUrl)
            credentials(PasswordCredentials::class) {
                username = artifactoryUsername
                password = artifactoryPassword
            }
        }
    }
}
