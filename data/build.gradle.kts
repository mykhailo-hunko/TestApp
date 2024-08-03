import org.gradle.kotlin.dsl.support.kotlinCompilerOptions

plugins {
    alias(libs.plugins.android.library)
    alias(libs.plugins.google.devtools.ksp)
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.testapp.data"
    compileSdk = 34
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.koin.android)
    implementation(libs.kotlinx.serialization)
    implementation(libs.kotlinx.coroutines)
    implementation(libs.room.runtime)
    implementation(libs.retrofit)
    implementation(libs.retrofit.kotlinx.serialization)

    ksp(libs.room.compiler)
}
