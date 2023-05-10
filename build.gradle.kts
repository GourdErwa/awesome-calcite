plugins {
    idea
    java
    id("io.freefair.lombok") version "8.0.1"
}

group = "io.gourd.awesome"
version = "1.0-SNAPSHOT"

repositories {
    mavenCentral()
}

dependencies {
    implementation("org.apache.calcite:calcite-core:1.34.0")
    implementation("org.apache.calcite:calcite-mongodb:1.34.0")

    implementation("org.jooq:jooq:3.18.3")

    implementation("com.alibaba:druid:1.2.17")
    implementation("com.mysql:mysql-connector-j:8.0.33")

    compileOnly("org.projectlombok:lombok:1.18.26")
    implementation("org.slf4j:slf4j-api:2.0.7")
    implementation("ch.qos.logback:logback-core:1.4.7")
    implementation("ch.qos.logback:logback-classic:1.4.7")

    testImplementation("org.projectlombok:lombok:1.18.26")
    testAnnotationProcessor("org.projectlombok:lombok:1.18.26")
    testImplementation(platform("org.junit:junit-bom:5.9.1"))
    testImplementation("org.junit.jupiter:junit-jupiter")
}

tasks.test {
    useJUnitPlatform()
}
