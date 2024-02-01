plugins {
    alias(libs.plugins.android.library)
}

apply(from = "$rootDir/gradle/common-android.gradle")

android {
    namespace = "com.aksio.core.tests_shared"
}

dependencies {
    implementation(libs.bundles.testing.unit)
}
