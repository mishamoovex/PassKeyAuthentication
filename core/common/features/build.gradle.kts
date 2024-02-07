plugins {
    alias(libs.plugins.android.library)
}

apply(from = "$rootDir/gradle/common-android.gradle")

android {
    namespace = "com.aksio.core.common.features"
}

dependencies {

    testImplementation(project(":core:tests"))

    implementation(libs.android.lifecycle.vm)
}