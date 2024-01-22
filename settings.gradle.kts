@file:Suppress("UnstableApiUsage")

include(":core:data:store")


pluginManagement {
    repositories {
        google()
        mavenCentral()
        gradlePluginPortal()
    }
}

dependencyResolutionManagement {
    repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
    repositories {
        google()
        mavenCentral()
    }
}

rootProject.name = "Authentication"
include(":app")
include(":core:designsystem")
include(":core:repository:authentication")
include(":features:authentication")
 