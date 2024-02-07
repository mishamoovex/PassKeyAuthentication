plugins {
    alias(libs.plugins.android.library)
}

apply(from = "$rootDir/gradle/common-feature.gradle")

android {
    namespace = "com.aksio.features.authentication"
}

dependencies {
    implementation(project(":core:models"))
    implementation(project(":core:common:features"))
    implementation(project(":core:designsystem"))
    implementation(project(":core:common:utilities"))
    implementation(project(":core:repository:authentication"))

    testImplementation(project(":core:tests"))
}