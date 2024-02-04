plugins {
    alias(libs.plugins.android.library)
}

apply(from = "$rootDir/gradle/common-android.gradle")

android {
    namespace = "com.aksio.core.data.db"
}

dependencies {

}