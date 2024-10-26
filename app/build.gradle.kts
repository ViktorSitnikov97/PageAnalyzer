import org.gradle.api.tasks.testing.logging.TestExceptionFormat
import org.gradle.api.tasks.testing.logging.TestLogEvent

plugins {
    checkstyle
    jacoco
    application
    id("checkstyle")
    id("io.freefair.lombok") version "8.4"
    id("com.github.ben-manes.versions") version "0.51.0"
    id("com.github.johnrengelman.shadow") version "8.1.1"
}

group = "hexlet.code"
version = "1.0-SNAPSHOT"
application {
    mainClass.set("hexlet.code.App")
}

repositories {
    mavenCentral()
}

dependencies {

    implementation("io.javalin:javalin:6.1.3")
    implementation("org.slf4j:slf4j-simple:2.0.10")//logger
    implementation("io.javalin:javalin-rendering:6.1.3")
    implementation("gg.jte:jte:3.0.1")
    implementation("gg.jte:jte:3.0.1")

    implementation("com.h2database:h2:2.2.220") // для работы с БД
    implementation("com.zaxxer:HikariCP:5.0.1")// Хикари БД
    implementation("org.postgresql:postgresql:42.7.2") // для работы с БД postgresql

    implementation("io.javalin:javalin-bundle:6.1.3")//для тестировония на Javalin
    implementation("com.konghq:unirest-java:3.14.5") // для генерации HTTP(S)-запросов
    testImplementation("com.squareup.okhttp3:mockwebserver:4.12.0") // для проверки запросов, отсылаемых на другой сервер
    testImplementation("org.assertj:assertj-core:3.25.3")
    testImplementation(platform("org.junit:junit-bom:5.10.0"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
    testLogging {
        exceptionFormat = TestExceptionFormat.FULL
        events = mutableSetOf(TestLogEvent.FAILED, TestLogEvent.PASSED, TestLogEvent.SKIPPED)
        // showStackTraces = true
        // showCauses = true
        showStandardStreams = true
    }
}
tasks.jacocoTestReport { reports { xml.required.set(true) } }