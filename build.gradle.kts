plugins {
    id("com.github.johnrengelman.shadow") version "7.1.2"
    id("java")

}

group = "me.zdziszkee"
version = "1.0"

repositories {
    mavenCentral()
}

dependencies {
    testImplementation("org.junit.jupiter:junit-jupiter-api:5.6.0")
    testRuntimeOnly("org.junit.jupiter:junit-jupiter-engine")
    implementation("org.ow2.asm:asm:7.0")

}

tasks.getByName<Test>("test") {
    useJUnitPlatform()
}