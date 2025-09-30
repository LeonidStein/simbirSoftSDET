plugins {
    id("java")
    id("io.qameta.allure") version "2.12.0"
}

group = "com.simbirsoft"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

val junitPlatformVersion = "5.10.0"
val junitAggregatorVersion = "5.10.0"
val allureVersion = "2.25.0"
val aspectJVersion = "1.9.21"
val seleniumJavaVersion = "4.35.0"
val webdriverManagerVersion = "6.2.0"
val javaFakerVersion = "1.0.2"
val slf4jLoggVersion = "2.0.17"
val ownerVersion = "1.0.12"
val encodingStandard = "UTF-8"
val gradleProjectVersion = "8.13"
val javaProjectVersion = 17

val agent: Configuration by configurations.creating {
    isCanBeConsumed = true
    isCanBeResolved = true
}

dependencies {
    // aspectjweaver
    agent("org.aspectj:aspectjweaver:${aspectJVersion}")

    // JUnit5
    implementation(platform("org.junit:junit-bom:$junitPlatformVersion"))
    implementation("org.junit.jupiter:junit-jupiter:$junitAggregatorVersion")

    // Allure
    implementation(platform("io.qameta.allure:allure-bom:$allureVersion"))
    implementation("io.qameta.allure:allure-junit5")

    // Selenium
    implementation("org.seleniumhq.selenium:selenium-java:$seleniumJavaVersion")

    // WebdriverManager
    implementation("io.github.bonigarcia:webdrivermanager:$webdriverManagerVersion")

    // Fake test data
    implementation("com.github.javafaker:javafaker:$javaFakerVersion")

    // log
    implementation("org.slf4j:slf4j-simple:$slf4jLoggVersion")

    // owner
    implementation("org.aeonbits.owner:owner:$ownerVersion")
}

java {
    toolchain {
        languageVersion.set(JavaLanguageVersion.of(javaProjectVersion))
    }
}


tasks.withType<JavaCompile> {
    options.encoding = encodingStandard
    options.compilerArgs.add("-parameters")
}

tasks.withType(Wrapper::class) {
    gradleVersion = gradleProjectVersion
}


tasks.test {
    useJUnitPlatform()
    ignoreFailures = true
    jvmArgs = listOf(
        "-javaagent:${agent.singleFile}"
    )
}

