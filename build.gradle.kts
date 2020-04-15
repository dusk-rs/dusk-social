
plugins {
    kotlin("jvm") version "1.3.71"
}

buildscript {
    repositories {
        jcenter()
    }
}

val koinVersion = "2.1.5"

allprojects {
    apply(plugin = "kotlin")
    apply(plugin = "idea")
    apply(plugin = "org.jetbrains.kotlin.jvm")

    group = "org.redrune"
    version = "0.0.1"

    java.sourceCompatibility = JavaVersion.VERSION_11

    repositories {
        mavenCentral()
        mavenLocal()
        jcenter()
        maven(url = "https://repo.maven.apache.org/maven2")
        maven(url = "https://jitpack.io")
        maven(url = "https://dl.bintray.com/michaelbull/maven")
        maven(url = "https://redrune.bintray.com/redrune-rsps")
    }

    dependencies {
        //Main
        implementation(kotlin("stdlib-jdk8"))
        implementation(kotlin("reflect"))
        implementation("io.netty:netty-all:4.1.44.Final")
        implementation(group = "com.displee", name = "rs-cache-library", version = "6.3")
        implementation(group = "org.yaml", name = "snakeyaml", version = "1.8")
        implementation(group = "io.github.classgraph", name = "classgraph", version = "4.6.3")
        implementation(group = "org.koin", name = "koin-core", version = koinVersion)
        implementation(group = "org.koin", name = "koin-logger-slf4j", version = koinVersion)
        implementation(group = "com.michael-bull.kotlin-inline-logger", name = "kotlin-inline-logger-jvm", version = "1.0.2")

        //Logging
        implementation("org.slf4j:slf4j-api:1.7.30")
        implementation("ch.qos.logback:logback-classic:1.2.3")
        implementation("rs.dusk.core:dusk-shared:0.0.10")

        //Utilities
        implementation("com.google.guava:guava:19.0")
        implementation("org.apache.commons:commons-lang3:3.0")

        //Testing
        testImplementation(group = "org.koin", name = "koin-test", version = koinVersion)
        testImplementation("org.junit.jupiter:junit-jupiter-api:5.5.2")
    }

    tasks {
        compileKotlin {
            kotlinOptions.jvmTarget = "1.8"
        }
        compileTestKotlin {
            kotlinOptions.jvmTarget = "1.8"
        }
    }

}
