plugins {
    id("java")
}

group = "org.example"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val allureVersion = "2.24.0"
val aspectJVersion = "1.9.20.1"
val agent: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = true
}

dependencies {
    agent("org.aspectj:aspectjweaver:${aspectJVersion}")
    implementation("com.codeborne:selenide:7.11.1")
    implementation("org.slf4j:slf4j-api:2.0.9")
    implementation("ch.qos.logback:logback-classic:1.5.20")
    testImplementation("org.testng:testng:7.11.0")
    testImplementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    testImplementation("io.qameta.allure:allure-testng")
}

java {
    toolchain {
        languageVersion = JavaLanguageVersion.of(17)
    }
}

tasks.test {
    useTestNG {
        suites("src/test/testng.xml")
    }

    testLogging {
        events("passed", "skipped", "failed")
    }
    jvmArgs = listOf(
        "-javaagent:${agent.singleFile}"
    )
    systemProperty("allure.results.directory", "build/allure-results")
}