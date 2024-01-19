plugins {
    alias(libs.plugins.androidApplication)
}

apply(from = "$rootDir/gradle/common-feature.gradle")

android {
    namespace = "com.aksio.authentication"

    defaultConfig {
        targetSdk = libs.versions.gradle.target.sdk.get().toInt()
        applicationId = "com.aksio.authentication"
        versionCode = 1
        versionName = "1.0"

        vectorDrawables {
            useSupportLibrary = true
        }
    }

    buildTypes {
        release {
            isMinifyEnabled = false
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
}

dependencies {
    implementation(project(":features:authentication"))

    implementation(libs.splash.screen)
    implementation(libs.lifecycle.vm)
    //Compose
    implementation(libs.compose.activity)
}