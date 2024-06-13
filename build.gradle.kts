// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
        maven("https://maven.fabric.io/public")
        maven("https://maven.google.com")
    }
    dependencies {
        classpath("com.android.tools.build:gradle:8.1.3")
        classpath("org.jetbrains.kotlin:kotlin-gradle-plugin:1.9.0")
        classpath(kotlin("serialization", version = "1.9.0"))
        classpath("com.google.gms:google-services:4.3.10")
        classpath("com.google.firebase:firebase-crashlytics-gradle:2.8.0")
    }
}

val versionMajor = 1
val versionMinor = 0
val versionPatch = 2
val versionClassifier: String = "SNAPSHOT"
val apkBaseName: String = "MycroftAI"

allprojects {
    repositories {
        maven("https://maven.google.com")
        google()
        mavenCentral()
    }

    project.extra.apply {
        set("versionCode", versionMajor * 10000 + versionMinor * 100 + versionPatch)
        set("versionName", getVersionName())
        set("apkBaseName", apkBaseName)
    }
}

task<Delete>("clean") {
    delete(rootProject.buildDir)
}

fun getVersionName(): String {
    var versionName = "${versionMajor}.${versionMinor}.${versionPatch}"
    if (versionClassifier.isBlank().not()) {
        versionName += "-$versionClassifier"
    }

    return versionName
}

subprojects {
    project.configurations.all {
        resolutionStrategy.eachDependency {
            if (this.requested.group == "com.android.support"
                && !this.requested.name.contains("multidex")
            ) {
                this.useVersion("$rootProject.supportVersion")
            }
        }
    }
}