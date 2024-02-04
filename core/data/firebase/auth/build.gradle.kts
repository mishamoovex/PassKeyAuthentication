plugins {
    alias(libs.plugins.android.library)
}

apply(from = "$rootDir/gradle/common-android.gradle")

android {
    namespace = "com.aksio.core.data.firebase.auth"
}

dependencies {
    implementation(project(":core:models"))
    implementation(libs.firebase.auth)
}