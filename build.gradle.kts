import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    kotlin("jvm") version "1.7.10"
    id("com.google.protobuf") version "0.9.1"
    id("maven-publish")
    application
}


group = "ke.bb.protobuf"
version = "0.0.1"

repositories {
    mavenCentral()
}

publishing {
    publications {
        create("maven_public", MavenPublication::class) {
            groupId = "ke.bb.protobuf"
            artifactId = "message"
            version = "0.0.1"
            from(components.getByName("java"))
        }
    }
}


dependencies {
    implementation("com.google.protobuf:protobuf-java:3.21.7")
    implementation("com.google.protobuf:protoc:3.21.7")
    testImplementation(kotlin("test"))
}

tasks.test {
    useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
    kotlinOptions.jvmTarget = "1.8"
}


protobuf {
    protoc {
        artifact = "com.google.protobuf:protoc:3.21.7"
    }
}