plugins {
    alias(libs.plugins.android.library)
}

apply(from = "$rootDir/gradle/common-feature.gradle")

android {
    namespace = "com.aksio.features.home"
}

dependencies {
    implementation(project(":core:designsystem"))
}