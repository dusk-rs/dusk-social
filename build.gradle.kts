plugins {
	kotlin("jvm") version "1.3.71"
}

buildscript {
	repositories {
		jcenter()
	}
}

val koinVersion = "2.1.5"

subprojects {
	apply(plugin = "kotlin")
	apply(plugin = "idea")
	apply(plugin = "org.jetbrains.kotlin.jvm")
	
	group = "rs.dusk"
	version = "0.0.1"
	
	java.sourceCompatibility = JavaVersion.VERSION_11
	
	repositories {
		mavenCentral()
		mavenLocal()
		jcenter()
		maven(url = "https://repo.maven.apache.org/maven2")
		maven(url = "https://jitpack.io")
		maven(url = "https://dl.bintray.com/michaelbull/maven")
	}
	
	dependencies {
		//Development
		implementation(kotlin("stdlib-jdk8"))
		implementation(kotlin("reflect"))
		implementation(group = "org.koin", name = "koin-core", version = koinVersion)
		implementation(group = "org.koin", name = "koin-logger-slf4j", version = koinVersion)
		implementation(group = "com.github.ajalt", name = "clikt", version = "2.6.0")
		
		//Core
		implementation(group = "rs.dusk.core", name = "network", version = "0.1.0")
		implementation(group = "io.netty", name = "netty-all", version = "4.1.44.Final")
		
		//Logging
		implementation(group = "org.slf4j", name = "slf4j-api", version = "1.7.30")
		implementation(group = "ch.qos.logback", name = "logback-classic", version = "1.2.3")
		implementation(group = "rs.dusk.core", name = "utility", version = "0.1.0")
		implementation(
			group = "com.michael-bull.kotlin-inline-logger",
			name = "kotlin-inline-logger-jvm",
			version = "1.0.2"
		)
		
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
