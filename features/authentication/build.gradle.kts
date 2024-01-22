plugins {
    alias(libs.plugins.android.library)
}

apply(from = "$rootDir/gradle/common-feature.gradle")

android {
    namespace = "com.aksio.features.authentication"
}

dependencies {
    implementation(project(":core:designsystem"))
    implementation(project(":core:repository:authentication"))
}