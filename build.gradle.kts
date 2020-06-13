plugins {
    `java-library`
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.json:json:20200518")
    implementation("org.slf4j:slf4j-api:1.7.30")
    // Use JUnit test framework
    testImplementation("junit:junit:4.13")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_14
}
