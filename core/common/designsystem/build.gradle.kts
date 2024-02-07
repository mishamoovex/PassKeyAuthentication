plugins {
    alias(libs.plugins.android.library)
}

apply(from = "$rootDir/gradle/common-android.gradle")

android {
    namespace = "com.aksio.core.common.designsystem"

    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.compose.compiller.get()
    }
}

dependencies {
    val composeBom = platform(libs.compose.bom)
    implementation(composeBom)
    androidTestImplementation(composeBom)
    debugImplementation(libs.compose.tooling)
    implementation(libs.compose.tooling.preview)
    implementation(libs.compose.ui)
    implementation(libs.compose.graphics)
    implementation(libs.compose.material3)
}