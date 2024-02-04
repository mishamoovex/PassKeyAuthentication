@file:Suppress("UnstableApiUsage")

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
include(":core:common")
include(":core:models")
include(":core:hilt")
include(":core:repository:authentication")
include(":core:data:db")
include(":core:data:firebase:auth")
include(":features:authentication")
include(":features:home")
include(":core:tests")
 