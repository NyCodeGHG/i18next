plugins {
    `java-library`
     maven
    `maven-publish`
    id("com.jfrog.bintray") version "1.8.5"
}

repositories {
    jcenter()
}

group = "com.i18next"
version = "1.0"

dependencies {
    implementation("org.json:json:20200518")
    implementation("org.slf4j:slf4j-api:1.7.30")
    // Use JUnit test framework
    testImplementation("junit:junit:4.13")
}

configure<JavaPluginConvention> {
    sourceCompatibility = JavaVersion.VERSION_11
}
val javaComponent = components["java"]

tasks {
    val sourcesJar = task<Jar>("sourcesJar") {
        dependsOn(classes)
        archiveClassifier.set("sources")
        from(sourceSets["main"].allSource)
    }

    val javadocJar = task<Jar>("javadocJar") {
        from(javadoc)
        archiveClassifier.set("javadoc")
    }

    publishing {
        publications {
            create<MavenPublication>("mavenJava") {
                from(javaComponent)
                artifact(sourcesJar)
                artifact(javadocJar)
            }
        }
    }
}

bintray {
    user = System.getenv("BINTRAY_USER")
    key = System.getenv("BINTRAY_KEY")
    setPublications("mavenJava")
    pkg {
        repo = "maven"
        name = "i18next"
        userOrg = "votebot"
        setLicenses("Apache-2.0")
        vcsUrl = "https://github.com/votebot/i18next.git"
        version {
            name = project.version as String
        }

    }
}

fun com.jfrog.bintray.gradle.BintrayExtension.pkg(block: com.jfrog.bintray.gradle.BintrayExtension.PackageConfig.() -> Unit) = pkg(delegateClosureOf(block))
fun com.jfrog.bintray.gradle.BintrayExtension.PackageConfig.version(block: com.jfrog.bintray.gradle.BintrayExtension.VersionConfig.() -> Unit) = version(delegateClosureOf(block))
fun com.jfrog.bintray.gradle.BintrayExtension.VersionConfig.gpg(block: com.jfrog.bintray.gradle.BintrayExtension.GpgConfig.() -> Unit) = gpg(delegateClosureOf(block))