// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    val room_version = "2.6.1"
    val ksp_version = "2.1.0-1.0.29"

    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
    alias(libs.plugins.compose.compiler) apply false
    id("androidx.room") version "$room_version" apply false
    id("com.google.dagger.hilt.android") version "2.51.1" apply false
    id("com.google.devtools.ksp") version "$ksp_version" apply false
}