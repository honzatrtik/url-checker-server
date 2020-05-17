import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
	id("org.springframework.boot") version "2.3.0.RELEASE"
	id("io.spring.dependency-management") version "1.0.9.RELEASE"
	kotlin("jvm") version "1.3.72"
	kotlin("plugin.spring") version "1.3.72"
	kotlin("kapt") version "1.3.72"
	id("maven")
	id("idea")
}


group = "urlchecker"
java.sourceCompatibility = JavaVersion.VERSION_1_8

repositories {
	maven("https://dl.bintray.com/honzatrtik/maven/")
	maven("https://dl.bintray.com/arrow-kt/arrow-kt/")
	mavenCentral()
}

val arrowVersion = "0.10.5"

dependencies {
	implementation("org.springframework.boot:spring-boot-starter-webflux")
	implementation("com.fasterxml.jackson.module:jackson-module-kotlin")
	implementation("io.projectreactor.kotlin:reactor-kotlin-extensions")
	implementation("org.jetbrains.kotlin:kotlin-reflect")
	implementation("org.jetbrains.kotlin:kotlin-stdlib-jdk8")
	implementation("org.jetbrains.kotlinx:kotlinx-coroutines-reactor")

	implementation("url-checker:url-checker_2.13:0.2")

	implementation("io.arrow-kt:arrow-core:$arrowVersion")
	implementation("io.arrow-kt:arrow-optics:$arrowVersion")
	implementation("io.arrow-kt:arrow-syntax:$arrowVersion")
	implementation("io.arrow-kt:arrow-fx:$arrowVersion")
	implementation("io.arrow-kt:arrow-fx-reactor:$arrowVersion")

	kapt("io.arrow-kt:arrow-meta:$arrowVersion")

	testImplementation("org.springframework.boot:spring-boot-starter-test") {
		exclude(group = "org.junit.vintage", module = "junit-vintage-engine")
	}
	testImplementation("io.projectreactor:reactor-test")


}

idea {
	module {
		sourceDirs.addAll(files(
			"build/generated/source/kapt/main",
			"build/generated/source/kapt/debug",
			"build/generated/source/kapt/release",
			"build/generated/source/kaptKotlin/main",
			"build/generated/source/kaptKotlin/debug",
			"build/generated/source/kaptKotlin/release",
			"build/tmp/kapt/main/kotlinGenerated")
		)
		generatedSourceDirs.addAll(files(
			"build/generated/source/kapt/main",
			"build/generated/source/kapt/debug",
			"build/generated/source/kapt/release",
			"build/generated/source/kaptKotlin/main",
			"build/generated/source/kaptKotlin/debug",
			"build/generated/source/kaptKotlin/release",
			"build/tmp/kapt/main/kotlinGenerated")
		)
	}
}

tasks.withType<Test> {
	useJUnitPlatform()
}

tasks.withType<KotlinCompile> {
	kotlinOptions {
		freeCompilerArgs = listOf("-Xjsr305=strict")
		jvmTarget = "1.8"
	}
}
