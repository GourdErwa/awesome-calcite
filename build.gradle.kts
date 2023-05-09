plugins {
    id("java")
}

group = "io.gourd.awesome"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.calcite:calcite-core:1.34.0")
    implementation("org.apache.calcite:calcite-mongodb:1.34.0")

    compileOnly("org.projectlombok:lombok:1.18.26")

    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
