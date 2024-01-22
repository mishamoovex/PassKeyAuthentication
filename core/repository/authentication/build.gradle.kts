plugins {
    alias(libs.plugins.android.library)
}

apply(from = "$rootDir/gradle/common-android.gradle")

android {
    namespace = "com.aksio.core.repository.authentication"
}

dependencies {
    implementation(project(":core:data:store"))
}