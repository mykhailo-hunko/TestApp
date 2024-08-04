plugins {
    alias(libs.plugins.jetbrains.kotlin.jvm)
}

dependencies {
    implementation(libs.kotlinx.coroutines)
    implementation(libs.koin.core)

    testImplementation(libs.junit)
    testImplementation(libs.mockito)
    testImplementation(libs.kotlinx.test)
    testImplementation(libs.assertJ.core)
}