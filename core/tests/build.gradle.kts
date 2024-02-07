plugins {
    alias(libs.plugins.android.library)
}

apply(from = "$rootDir/gradle/common-android.gradle")

android {
    namespace = "com.aksio.core.tests_shared"
}

dependencies {
    implementation(project(":core:models"))
    implementation(project(":core:common:features"))
    implementation(project(":core:common:utilities"))
    implementation(project(":core:repository:authentication"))
    implementation(libs.bundles.testing.unit)
}
