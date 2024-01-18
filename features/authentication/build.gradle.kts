plugins {
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.kotlinAndroid)
}

apply {
    from("$rootDir/feature.gradle")
}

android {
    namespace = "com.aksio.features.authentication"

    defaultConfig {
        consumerProguardFiles("consumer-rules.pro")
    }
}

dependencies {

}