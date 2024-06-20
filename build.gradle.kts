// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id("com.android.application") version "8.1.3" apply false
    id("org.jetbrains.kotlin.android") version "1.8.10" apply false
    id("com.google.gms.google-services") version "4.3.10" apply false
    id("com.google.firebase.crashlytics") version "2.8.1" apply false
}

val versionMajor = 1
val versionMinor = 0
val versionPatch = 2
val versionClassifier: String = "SNAPSHOT"
val apkBaseName: String = "MycroftAI"

allprojects {
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