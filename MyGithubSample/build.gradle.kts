// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    id("com.android.application") version Versions.ANDROID apply false
    id("com.android.library") version Versions.ANDROID apply false
    id("org.jetbrains.kotlin.android") version Versions.KOTLIN apply false
    id("com.google.dagger.hilt.android") version Versions.HILT apply false
    id("org.jetbrains.kotlin.jvm") version "1.7.21" apply false
}

buildscript{
    dependencies{
        classPaths(
            "androidx.navigation.safeargs:androidx.navigation.safeargs.gradle.plugin:${Versions.NAVIGATION}"
        )
    }
}