plugins {
    alias(libs.plugins.android.library)
}

apply(from = "$rootDir/gradle/common-android.gradle")

android {
    namespace = "com.aksio.core.data.store"
}

dependencies {
    implementation(libs.kotlin.serialization)
    implementation(libs.android.store)
}