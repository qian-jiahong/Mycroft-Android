pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
        maven("https://maven.fabric.io/public")
        maven("https://maven.google.com")
    }
}
dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
        maven("https://maven.fabric.io/public")
        maven("https://maven.google.com")
    }
}

include(":shared")
include(":mobile")
include(":wear")
rootProject.name = "MycroftAI"