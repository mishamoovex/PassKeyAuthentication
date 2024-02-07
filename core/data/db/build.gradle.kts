plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.kotlin.android)
    kotlin("kapt")
}

apply(from = "$rootDir/gradle/common-android.gradle")

android {
    namespace = "com.aksio.core.data.db"
}

dependencies {
    implementation(project(":core:models"))

    implementation(libs.room)
    implementation(libs.room.coroutines)
    annotationProcessor(libs.room.annotation.processor)
    kapt(libs.room.kapt)
}