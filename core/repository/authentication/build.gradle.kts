plugins {
    alias(libs.plugins.androidLibrary)
}

apply(from = "$rootDir/gradle/common-android.gradle")

android {
    namespace = "com.aksio.core.repository.authentication"
}